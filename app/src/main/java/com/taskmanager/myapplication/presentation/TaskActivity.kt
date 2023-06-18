package com.taskmanager.myapplication.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.taskmanager.myapplication.databinding.ActivityTaskBinding
import com.taskmanager.myapplication.di.Dependencies
import com.taskmanager.myapplication.domain.models.Task
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TaskActivity : AppCompatActivity() {

    lateinit var binding: ActivityTaskBinding

    val taskListID by lazy { intent.getIntExtra(ARG_TASK_LIST_ID, 0)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.add.setOnClickListener {
            val title = binding.title.text.toString()
            val desc = binding.desc.text.toString()
            GlobalScope.launch {
                Dependencies.taskRepository.addTask(Task(title, desc, taskListID))
            }

        }
    }

    companion object {
        private const val ARG_TASK_LIST_ID = "taskListID"
        fun getIntent(context: Context, taskListId: Int) : Intent{
            val intent = Intent(context, TaskActivity::class.java)
            intent.putExtra(ARG_TASK_LIST_ID, taskListId)
            return intent
        }
    }
}