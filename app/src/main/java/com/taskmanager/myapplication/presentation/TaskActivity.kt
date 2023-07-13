package com.taskmanager.myapplication.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.taskmanager.myapplication.databinding.ActivityMainBinding
import com.taskmanager.myapplication.databinding.ActivityTaskBinding
import com.taskmanager.myapplication.databinding.DialogAddTaskBinding
import com.taskmanager.myapplication.di.Dependencies
import com.taskmanager.myapplication.domain.models.Task
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TaskActivity : AppCompatActivity() {

    lateinit var binding: ActivityTaskBinding

    val taskID by lazy { intent.getIntExtra(ARG_TASK_ID, 0)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var vm = ViewModelProvider(this)[MainViewModel::class.java]
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var task = vm.getTaskByIdFromList(taskID)
        if (task != null) {
            Log.d("task name----------", task.name)
        }else{
            Log.d("хуево----------", "хуево")
        }
        if (task != null) {
            binding.taskTitle.setText(task.name)
        }
        if (task != null) {
            binding.taskDescription.setText(task.description)
        }

    }
    companion object {
        private const val ARG_TASK_ID = "taskListID"
        fun getIntent(context: Context, id: Int) : Intent{
            val intent = Intent(context, TaskActivity::class.java)
            intent.putExtra(ARG_TASK_ID, id)
            return intent
        }
    }
}