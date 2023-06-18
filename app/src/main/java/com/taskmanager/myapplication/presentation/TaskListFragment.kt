package com.taskmanager.myapplication.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.taskmanager.myapplication.databinding.FragmentTaskListBinding


class TaskListFragment(private val taskListId: Int) : Fragment() {

    private lateinit var binding: FragmentTaskListBinding
    private lateinit var viewModel: TaskListViewModel

//    val taskListId by lazy {
//        arguments?.getInt(ARG_TASK_LIST_ID)!!
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskListBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[TaskListViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TaskListAdapter()

        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(requireActivity())
        viewModel.list.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        val callback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                adapter.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                TODO("Not yet implemented")
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
            .attachToRecyclerView(binding.rv)

        viewModel.getTasksFromTaskList(taskListId)
    }

//    companion object {
//        private const val ARG_TASK_LIST_ID = "task_list_id"
//
//        fun newInstance(idTaskList: Int) =
//            TaskListFragment().apply {
//                arguments = Bundle().apply {
//                    putInt(ARG_TASK_LIST_ID, idTaskList)
//                }
//            }
//    }
}