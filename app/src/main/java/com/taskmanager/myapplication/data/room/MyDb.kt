package com.taskmanager.myapplication.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.taskmanager.myapplication.data.room.dao.TaskDao
import com.taskmanager.myapplication.data.room.dao.TaskListDao
import com.taskmanager.myapplication.domain.models.Task
import com.taskmanager.myapplication.domain.models.TaskList

@Database(entities = [TaskList::class, Task::class], version = 1, exportSchema = false)
abstract class MyDb : RoomDatabase(){
    abstract fun taskListDao() : TaskListDao
    abstract fun taskDao() : TaskDao
}