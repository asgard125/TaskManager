package com.taskmanager.myapplication.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.taskmanager.myapplication.domain.models.TaskList

@Dao
abstract class TaskListDao {
    @Insert
    abstract fun addTaskList(taskListEntity: TaskList)

    @Delete
    abstract fun deleteTaskList(taskListEntity: TaskList)

    @Update
    abstract fun updateTaskList(taskListEntity: TaskList)

    @Query("SELECT * FROM taskList")
    abstract fun getAllTaskLists() : LiveData<List<TaskList>>

    @Query("SELECT * FROM taskList WHERE id = :id")
    abstract fun getTaskListById(id: Int) : LiveData<List<TaskList>>
}