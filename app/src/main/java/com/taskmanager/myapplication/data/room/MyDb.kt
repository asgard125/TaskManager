package com.taskmanager.myapplication.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.taskmanager.myapplication.data.room.dao.TaskDao
import com.taskmanager.myapplication.data.room.dao.TaskListDao
import com.taskmanager.myapplication.data.room.entity.TaskEntity
import com.taskmanager.myapplication.data.room.entity.TaskListEntity

@Database(entities = [TaskListEntity::class, TaskEntity::class], version = 1)
abstract class MyDb : RoomDatabase(){
    abstract fun taskListDao() : TaskListDao
    abstract fun taskDao() : TaskDao
}