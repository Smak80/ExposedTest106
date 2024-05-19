package ru.smak.exposed.database

import java.time.LocalDate

data class StudentView(
    var id: Int,
    var firstName: String,
    var lastName: String,
    var birth: LocalDate,
    var admission: Int,
    var groupName: String,
)