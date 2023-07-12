package com.taskmanager.myapplication.domain.models

data class Task(
    val name: String,
    val description: String,
    val taskListId: Int,
    val id: Int,
    var favorite: Boolean,
    var completed: Boolean
)