package com.codbs.benew_project.TodoList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.codbs.benew_project.R
import com.codbs.benew_project.databinding.FragmentTodoListCardBinding


class TodoListCardFrag : Fragment() {
    val binding by lazy{ FragmentTodoListCardBinding.inflate(layoutInflater)}



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        val mymodellist=getMymodel()
        val adapter1= TodoListCardAdapter()
        adapter1.modelList=mymodellist
        binding.myRecyclerView.adapter=adapter1
        binding.myRecyclerView.layoutManager= LinearLayoutManager(context)

        return binding.root

    }
    fun getMymodel():MutableList<ProjectData>{
        var mymodellist= mutableListOf<ProjectData>()
        for (i in 1..10) {
            val todoName= "myproject $i"
            val mymodeldata= ProjectData(todoName)
            mymodellist.add(mymodeldata)
        }
        return mymodellist
    }

}