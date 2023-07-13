package com.taskmanager.myapplication.data.room.dao

import androidx.room.*
import com.taskmanager.myapplication.domain.models.TaskList

@Dao
abstract class TaskListDao {
    @Insert
    abstract suspend fun addTaskList(taskListEntity: TaskList)

    @Delete
    abstract suspend fun deleteTaskList(taskListEntity: TaskList)

    @Update
    abstract suspend fun updateTaskList(taskListEntity: TaskList)

    @Query("SELECT * FROM taskList")
    abstract suspend fun getAllTaskLists() : List<TaskList>
}