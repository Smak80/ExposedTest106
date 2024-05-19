package ru.smak.exposed.database

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.DriverManager
import java.time.LocalDate

object DbContext {
    private val db: Database

    init{
        db = Database.connect({
            DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db106",
                "root",
                "")
        })
        createMissingTables()
    }

    private fun createMissingTables(){
        transaction(db){
            SchemaUtils.createMissingTablesAndColumns(
                Students, Groups
            )
        }
    }

    fun addStudent(
        firstName: String,
        lastName: String,
        birth: LocalDate,
        admYear: Int,
        groupName: String,
    ){
        transaction(db) {
            findGroupIdByName(groupName)?.let{
                Student.new {
                    this.firstName = firstName
                    this.lastName = lastName
                    this.birth = birth
                    admission = admYear
                    groupId = it
                }
                commit()
            } ?: rollback()
        }
    }

    fun getAllStudents(): List<StudentView>{
        return transaction(db){
            Join(Groups, Students, onColumn = Groups.id, otherColumn = Students.groupId)
                .selectAll().where{ Students.firstName eq "Петр" }.map {
                    StudentView(
                        it[Students.id].value,
                        it[Students.firstName],
                        it[Students.lastName],
                        it[Students.birth],
                        it[Students.admission],
                        it[Groups.name]
                    )
            }
        }
    }

    fun addGroup(groupName: String) {
        transaction(db) {
            try {
                Group.new {
                    name = groupName
                }
                commit()
            }
            catch (_: Exception){
                rollback()
            }
        }
    }

    fun findGroupIdByName(name: String): Int?{
        return transaction(db){
            try {
                Groups
                    .selectAll()
                    .where { Groups.name eq name }
                    .first()[Groups.id]
                        .value
            } catch (_: Throwable){
                null
            }
        }
    }

    fun renameStudent(oldLastName: String, newLastName: String){
        transaction(db){
            Students.update(
                where = { Students.lastName eq oldLastName}
            ){
                it[lastName] = newLastName
            }
        }
    }

}