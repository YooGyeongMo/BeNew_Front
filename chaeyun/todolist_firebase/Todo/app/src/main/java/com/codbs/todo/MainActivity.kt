package com.codbs.todo

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query

class MainActivity : AppCompatActivity(), OnDialogCloseListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var mFab: FloatingActionButton
    private var firestore: FirebaseFirestore? = null
    private lateinit var adapter: ToDoAdapter
    private lateinit var mList: MutableList<ToDoModel>
    private var query: Query? = null
    private var listenerRegistration: ListenerRegistration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)

        recyclerView = findViewById(R.id.recyclerview)
        mFab = findViewById(R.id.floatingActionButton)
        firestore = FirebaseFirestore.getInstance()
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(this@MainActivity))

        mFab.setOnClickListener(View.OnClickListener {
            AddNewTask.newInstance().show(
                supportFragmentManager, AddNewTask.TAG
            )
        })
        mList = ArrayList()
        adapter = ToDoAdapter(this@MainActivity, mList)
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