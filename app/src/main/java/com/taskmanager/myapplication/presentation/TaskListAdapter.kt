package com.taskmanager.myapplication.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.taskmanager.myapplication.R
import com.taskmanager.myapplication.databinding.FragmentTaskBinding
import com.taskmanager.myapplication.domain.models.Task

class TaskListAdapter(private val hostListener: TaskListAdapterListener) : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>()  {
    private var tasksList: MutableList<Task> = mutableListOf()
    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = FragmentTaskBinding.bind(view)
        fun bind(task: Task) {
                binding.apply {
                    taskName.text = task.name
                    completeCheckbox.isChecked = task.completed
                    completeCheckbox.setOnClickListener {
                        task.completed = !task.completed
                        hostListener.onChanged(task)
                    }

                    favoriteButton.setOnClickListener {
                        task.favorite = !task.favorite
                        hostListener.onChanged(task)
                    }
                    if (task.favorite) {
                        favoriteButton.setImageResource(R.drawable.baseline_white_star_24)
                    } else {
                        favoriteButton.setImageResource(R.drawable.baseline_white_star_border_24)
                    }

                    deleteButton.setOnClickListener {
                        hostListener.onDelete(task)
                    }

                    root.setOnClickListener {
                        task.id?.let { it1 -> hostListener.onClick(it1) }
                    }
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasksList[position])
    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newTasksList: List<Task>){
        tasksList = newTasksList.toMutableList()
        notifyDataSetChanged()
    }
}