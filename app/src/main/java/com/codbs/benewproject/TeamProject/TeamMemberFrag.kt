package com.codbs.benewproject.TeamProject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.codbs.benewproject.TodoList.ProjectData
import com.codbs.benewproject.databinding.FragmentTeamMemberBinding


class TeamMemberFrag : Fragment() {
    val binding2 by lazy{ FragmentTeamMemberBinding.inflate(layoutInflater)}



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val memberlist=getMember()
        val adapter1= TeamMemberAdapter()
        adapter1.memberlist=memberlist
        binding2.myRecyclerView2.adapter=adapter1
        binding2.myRecyclerView2.layoutManager= LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,false)

        return binding2.root

    }

    fun getMember():MutableList<ProjectData>{
        var memberlist= mutableListOf<ProjectData>()
        for (i in 1..6) {
            val memberName= "member $i"
            val memberdata= ProjectData(memberName)
            memberlist.add(memberdata)
        }
        return memberlist
    }
}