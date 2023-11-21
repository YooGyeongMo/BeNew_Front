package com.codbs.benew_project.todolist_firebase

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codbs.benew_project.R
import com.codbs.benew_project.databinding.ActivityTodolistViewBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query

//main
class TodolistView : AppCompatActivity(), OnDialogCloseListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var mFab: FloatingActionButton
    private var firestore: FirebaseFirestore? = null
    private lateinit var adapter: ToDoAdapter
    private lateinit var mList: MutableList<ToDoModel>
    private var query: Query? = null
    private var listenerRegistration: ListenerRegistration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todolist_view)

        FirebaseApp.initializeApp(this)

        recyclerView = findViewById(R.id.recyclerview)
        mFab = findViewById(R.id.floatingActionButton)
        firestore = FirebaseFirestore.getInstance()
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(this@TodolistView))

        mFab.setOnClickListener(View.OnClickListener {
            AddNewTask.newInstance().show(
                supportFragmentManager, AddNewTask.TAG
            )
        })
        mList = ArrayList()
        adapter = ToDoAdapter(this@TodolistView, mList)
        val itemTouchHelper = ItemTouchHelper(TouchHelper(adapter))
        itemTouchHelper.attachToRecyclerView(recyclerView)
        showData()
        recyclerView.setAdapter(adapter)
    }

    private fun showData() {
        query = firestore!!.collection("task").orderBy("time", Query.Direction.DESCENDING)
        listenerRegistration = query!!.addSnapshotListener { value, error ->
            for (documentChange in value!!.documentChanges) {
                if (documentChange.type == DocumentChange.Type.ADDED) {
                    val id = documentChange.document.id
                    val toDoModel = documentChange.document.toObject(
                        ToDoModel::class.java
                    ).withId<ToDoModel>(id)
                    mList!!.add(toDoModel)
                    adapter!!.notifyDataSetChanged()
                }
            }
            listenerRegistration!!.remove()
        }
    }


    override fun onDialogClose(dialogInterface: DialogInterface?) {
        mList!!.clear()
        showData()
        adapter!!.notifyDataSetChanged()
    }


}