package com.codbs.benew_project.TeamProject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codbs.benew_project.databinding.RecyclerItemTeamMemberBinding

class TeamMemberAdapter : RecyclerView.Adapter<Holder2>() {
    var memberlist= mutableListOf<TeamMemberData>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder2 {
        val binding2= RecyclerItemTeamMemberBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder2(binding2)
    }

    override fun getItemCount(): Int {
        return memberlist.size
    }

    override fun onBindViewHolder(holder: Holder2, position: Int) {
        holder.bind(this.memberlist[position])

    }


}
class Holder2 (val binding2:RecyclerItemTeamMemberBinding): RecyclerView.ViewHolder(binding2.root){
    fun bind(memberlist: TeamMemberData) {
        binding2.memberName2.text="${memberlist.member_name}"

    }

}