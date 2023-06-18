package com.taskmanager.myapplication.domain.models

data class Task(
    val name: String,
    val description: String,
    val taskListId: Int,
    val id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}