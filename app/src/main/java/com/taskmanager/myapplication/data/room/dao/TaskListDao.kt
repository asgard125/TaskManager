package com.taskmanager.myapplication.data.room.dao

import androidx.room.*
import com.taskmanager.myapplication.data.room.entity.TaskListEntity

@Dao
abstract class TaskListDao {
    @Insert
    abstract suspend fun addTaskList(taskListEntity: TaskListEntity)

    @Delete
    abstract suspend fun deleteTaskList(taskListEntity: TaskListEntity)

    @Update
    abstract suspend fun updateTaskList(taskListEntity: TaskListEntity)

    @Query("SELECT * FROM taskList")
    abstract suspend fun getAllTaskLists() : List<TaskListEntity>
}