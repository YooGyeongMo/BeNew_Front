package com.codbs.todo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codbs.todo.ToDoAdapter.MyViewHolder
import com.google.firebase.firestore.FirebaseFirestore

class ToDoAdapter(private val activity: MainActivity, private var todoList: MutableList<ToDoModel>) :
    RecyclerView.Adapter<MyViewHolder>() {
    private var firestore: FirebaseFirestore? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.each_task, parent, false)
        firestore = FirebaseFirestore.getInstance()
        return MyViewHolder(view)
    }

    fun deleteTask(position: Int) {
        val toDoModel = todoList[position]
        firestore!!.collection("task").document(toDoModel.TaskId!!).delete()
        todoList.removeAt(position)
        notifyItemRemoved(position)
    }

    val context: Context
        get() = activity

    fun editTask(position: Int) {
        val toDoModel = todoList[position]
        val bundle = Bundle()
        bundle.putString("task", toDoModel.task)
        bundle.putString("due", toDoModel.due)
        bundle.putString("id", toDoModel.TaskId)
        val addNewTask = AddNewTask()
        addNewTask.arguments = bundle
        addNewTask.show(activity.supportFragmentManager, addNewTask.tag)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val toDoModel = todoList[position]
        holder.mCheckBox.text = toDoModel.task
        holder.mDueDateTv.text = "Due On " + toDoModel.due
        holder.mCheckBox.isChecked = toBoolean(toDoModel.status)
        holder.mCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                firestore!!.collection("task").document(toDoModel.TaskId!!).update("status", 1)
            } else {
                firestore!!.collection("task").document(toDoModel.TaskId!!).update("status", 0)
            }
        }
    }

    private fun toBoolean(status: Int): Boolean {
        return status != 0
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mDueDateTv: TextView
        var mCheckBox: CheckBox

        init {
            mDueDateTv = itemView.findViewById(R.id.due_date_tv)
            mCheckBox = itemView.findViewById(R.id.mcheckbox)
        }
    }
}