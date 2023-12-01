package com.gmlab.team_benew.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmlab.team_benew.databinding.ChatlistRecyclerItemBinding

class ChatlistAdapter: RecyclerView.Adapter<Holder>() {
    var modelList= mutableListOf<ChatData_recycler>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding=ChatlistRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return Holder(binding)

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(this.modelList[position])

    }

    override fun getItemCount(): Int {
        return modelList.size
    }


}
class Holder(val binding:ChatlistRecyclerItemBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(myModel:ChatData_recycler){
        binding.userName.text="${myModel.chatRoomName}"
    }
}

