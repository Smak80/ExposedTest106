package ru.smak.exposed.database

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Student(id: EntityID<Int>) : IntEntity(id){

    var firstName: String by Students.firstName
    var lastName by Students.lastName
    var admission by Students.admission
    var birth by Students.birth
    var groupId by Students.groupId

    companion object : IntEntityClass<Student>(Students)
}