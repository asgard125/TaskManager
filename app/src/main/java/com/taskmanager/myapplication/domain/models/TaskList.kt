package com.taskmanager.myapplication.domain.models

data class TaskList(
    var name: String,
    val id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}