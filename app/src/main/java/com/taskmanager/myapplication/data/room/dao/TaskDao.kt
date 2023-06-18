package com.taskmanager.myapplication.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.taskmanager.myapplication.data.room.entity.TaskEntity

@Dao
abstract class TaskDao {
    @Insert
    abstract suspend fun addTask(task: TaskEntity)

    @Delete
    abstract suspend fun deleteTask(task: TaskEntity)

    @Query("SELECT * FROM task WHERE taskListId = :taskListId")
    abstract suspend fun getTasksFromTaskList(taskListId: Int): List<TaskEntity>

}