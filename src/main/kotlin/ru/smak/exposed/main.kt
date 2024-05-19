package ru.smak.exposed

import ru.smak.exposed.database.DbContext
import java.time.LocalDate

fun main() {
    DbContext.addGroup(
        "05-106"
    )
    DbContext.addGroup(
        "05-104"
    )
    DbContext.addStudent(
        "Петр",
        "Петров",
        LocalDate.now().plusYears(-20),
        2023,
        "05-106"
    )
    DbContext.addStudent(
        "Иван",
        "Иванов",
        LocalDate.now().plusYears(-20),
        2023,
        "05-104"
    )
    DbContext.addStudent(
        "Александр",
        "Сидоров",
        LocalDate.now().plusYears(-20),
        2023,
        "05-107"
    )
    DbContext.renameStudent("Иванов", "Васечкин")
    DbContext.getAllStudents().forEach {
        println(it)
    }
}