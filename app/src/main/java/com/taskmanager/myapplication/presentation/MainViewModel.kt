package com.taskmanager.myapplication.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taskmanager.myapplication.di.Dependencies
import com.taskmanager.myapplication.domain.models.Task
import com.taskmanager.myapplication.domain.models.TaskList
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    var listOfTasks: LiveData<List<Task>> = Dependencies.taskRepository.getAllTasks()
    var listOfTaskLists: LiveData<List<TaskList>> = Dependencies.taskListRepository.getAllTaskLists()

    private val taskRepository = Dependencies.taskRepository
    private val taskListRepository = Dependencies.taskListRepository
    fun getAllTasks(){
        viewModelScope.launch {
            listOfTasks = taskRepository.getAllTasks()
        }
    }

    fun getTaskByIdFromList(id: Int): Task?{
        for (i in 0 ..(listOfTasks.value?.size ?: 0)){
            if (listOfTasks.value?.get(i)?.id == id) {
                return listOfTasks.value?.get(i)
            }
            Log.d("чето произошло", "чето произошло")
        }
        return null
    }

    fun getTasksFromTaskList(id: Int) {
        viewModelScope.launch {
            listOfTasks = taskRepository.getTasksFromTaskList(id)
        }
    }

    fun getFavoriteTasks() {
        viewModelScope.launch {
            listOfTasks = taskRepository.getFavoriteTasks()
        }
    }

    fun addTask(task: Task){
        viewModelScope.launch {
            taskRepository.addTask(task)
        }
    }
    fun changeTask(task: Task){
        viewModelScope.launch {
            taskRepository.changeTask(task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }
    fun addTaskList(taskList: TaskList){
        viewModelScope.launch {
            taskListRepository.addTaskList(taskList)
        }
    }
}