package com.gmlab.team_benew.chat

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmlab.team_benew.R
import com.gmlab.team_benew.main.MainAuthService
import com.gmlab.team_benew.main.MainView
import com.gmlab.team_benew.main.UserNameCallback

class ChatListFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chatlist, container, false)
    }
    override fun onResume(){
        super.onResume()
        getUser()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUser()

        //여기는 각 채팅목록 클릭시 채팅방으로 이동하는 거 구현하기
    }
    private fun getUser(){
        val token=getTokenFromSharedPreferences()
        val account=getAccountFromSharedPreferences()

        if(token!=null && account!=null){

        }

    }


    private fun getTokenFromSharedPreferences():String?{
        val sharedPref=activity?.getSharedPreferences("Prefs", Context.MODE_PRIVATE)
        return sharedPref?.getString("userToken",null)
    }

    private fun getAccountFromSharedPreferences(): String? {
        val sharedPref = activity?.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        return sharedPref?.getString("userAccount", null)
    }

}