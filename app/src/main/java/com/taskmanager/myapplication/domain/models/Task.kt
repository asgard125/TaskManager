package com.taskmanager.myapplication.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    var description: String,
    val taskListId: Int,
    var favorite: Boolean,
    var completed: Boolean
)