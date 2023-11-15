package com.codbs.benewproject.TodoList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codbs.benewproject.TeamProject.TeamMemberData
import com.codbs.benewproject.databinding.RecyclerItemTodolistCardBinding

class TodoListCardAdapter:RecyclerView.Adapter<Holder>() {
    var modelList= mutableListOf<TeamMemberData>()



    //1.데이터모델과 뷰 연결(class myviewholder)텍스트뷰랑 데이터를 연결함. 틀이랑 연결한거 아님?
    //2.틀을 객체로 만듦->뷰홀더 생성함(oncreateviewholder)
    //뷰홀더에 쓰일 레이아웃을 객체로 만듦
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding=RecyclerItemTodolistCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }



    //뷰홀더와 데이터 연결(음 뷰홀더랑 데이터 연결하는건가)
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(this.modelList[position])


    }

    //목록의 수
    override fun getItemCount(): Int {
        return modelList.size
    }
}

class Holder(val binding:RecyclerItemTodolistCardBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(myModel: TeamMemberData) {
        binding.userNameTxt.text="${myModel.member_name}"
    }

}