package ru.smak.exposed.database

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.date
import java.time.LocalDate

object Students : IntIdTable("student"){
    val firstName: Column<String> = varchar("first_name", 50)
    val lastName = varchar("last_name", 50)
    val admission = integer("adm_year")
    val birth: Column<LocalDate> = date("birth").default(LocalDate.now().plusYears(-18))
    val groupId = integer("group_id").references(
        Groups.id,
        onDelete = ReferenceOption.RESTRICT,
        onUpdate = ReferenceOption.CASCADE,
    )
}