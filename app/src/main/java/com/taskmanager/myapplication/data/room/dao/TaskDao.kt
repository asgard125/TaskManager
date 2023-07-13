package com.taskmanager.myapplication.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.taskmanager.myapplication.domain.models.Task

@Dao
abstract class TaskDao {
    @Insert
    abstract fun addTask(task: Task)
    @Delete
    abstract fun deleteTask(task: Task)
    @Query("SELECT * FROM Task")
    abstract fun getAllTasks(): LiveData<List<Task>>
    @Query("SELECT * FROM Task WHERE taskListId = :taskListId")
    abstract fun getTasksFromTaskList(taskListId: Int): LiveData<List<Task>>
    @Query("SELECT * FROM Task WHERE favorite = true")
    abstract fun getFavoriteTasks(): LiveData<List<Task>>
}