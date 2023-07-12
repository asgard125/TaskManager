package com.taskmanager.myapplication.data

import com.taskmanager.myapplication.data.room.entity.TaskEntity
import com.taskmanager.myapplication.data.room.entity.TaskListEntity
import com.taskmanager.myapplication.domain.models.Task
import com.taskmanager.myapplication.domain.models.TaskList

class Mapper {
    fun taskListToTaskListEntity(taskList: TaskList) : TaskListEntity {
        return TaskListEntity(taskList.id, taskList.name)
    }


    fun taskToTaskEntity(task: Task): TaskEntity {
        return TaskEntity(task.id, task.name, task.description, task.taskListId, task.favorite, task.completed)
    }

    fun taskEntityToTask(task: TaskEntity): Task {
        return Task(task.name, task.description, task.taskListId, task.id, task.favorite, task.completed)
    }
}