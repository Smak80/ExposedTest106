package ru.smak.exposed.database

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Group(id: EntityID<Int>) : IntEntity(id) {
    var name by Groups.name

    companion object : IntEntityClass<Group>(Groups)
}