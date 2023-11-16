package com.codbs.benew_project.TeamProject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.codbs.benew_project.R
import com.codbs.benew_project.databinding.FragmentTeamMemberBinding


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
        binding2.myRecyclerView2.layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

        return binding2.root

    }

    fun getMember():MutableList<TeamMemberData>{
        var memberlist= mutableListOf<TeamMemberData>()
        for (i in 1..6) {
            val memberName= "member $i"
            val memberdata= TeamMemberData(memberName)
            memberlist.add(memberdata)
        }
        return memberlist
    }

}