package com.taskmanager.myapplication.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TaskList")
data class TaskList(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var name: String
)