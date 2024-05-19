package ru.smak.exposed.database

import org.jetbrains.exposed.dao.id.IntIdTable

object Groups : IntIdTable("group"){
    val name = varchar("name", 7).index(isUnique = true)
}