package com.gmlab.team_benew.chat

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.gmlab.team_benew.R
import com.gmlab.team_benew.main.MainAuthService
import com.gmlab.team_benew.main.MainView
import com.gmlab.team_benew.main.UserNameCallback

class ChatListFragment: Fragment() ,ChatRoomNameCallback{
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chatlist, container, false)
    }
    override fun onResume(){
        super.onResume()
        getChatRoomName()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getChatRoomName()

        //여기는 각 채팅목록 클릭시 채팅방으로 이동하는 거 구현하기
    }
    private fun getChatRoomName(){
        val loginId=getLoginIdFromSharedPreferences()


            val service=ChatService(this)
            service.createChatRoom(loginId)




    }
    private fun getLoginIdFromSharedPreferences():Int{
        val sharedPref=activity?.getSharedPreferences("Prefs",Context.MODE_PRIVATE)
        return sharedPref?.getInt("loginId",-1)?: -1

    }


    override fun ChatRoomNameReceived(userName:String){
        val chatuser=view?.findViewById<TextView>(R.id.chatlist_userid)
        chatuser?.text=userName
    }


}