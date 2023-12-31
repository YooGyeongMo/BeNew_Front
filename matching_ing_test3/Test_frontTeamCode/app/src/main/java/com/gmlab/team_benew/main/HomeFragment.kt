package com.gmlab.team_benew.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gmlab.team_benew.R

class HomeFragment: Fragment(), MainView,UserNameCallback {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)


    }
    override fun onResume() {
        super.onResume()
        getUserInfo() // 사용자 정보를 새로고침하는 메서드 호출
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 여기서 사용자 정보를 가져옴
        getUserInfo()

        //뷰 아이디 가져옴
        val buttonNavProfile = view.findViewById<CardView>(R.id.cv_user_info_card)//카드뷰
        val buttonNavProject = view.findViewById<CardView>(R.id.cv_project_info_card)
        val buttonNavMyteamlist = view.findViewById<CardView>(R.id.cv_my_team_list)

        // 모든 버튼에 !같은 클릭 리스너! 설정
        buttonNavProfile.setOnClickListener { onCardClicked(it) }
        buttonNavProject.setOnClickListener { onCardClicked(it) }
        buttonNavMyteamlist.setOnClickListener { onCardClicked(it) }
    }

    private fun onCardClicked(view: View) {
        when (view.id) {
            R.id.cv_user_info_card -> findNavController().navigate(R.id.action_home_to_profileDetail) // 프로필 디테일로 이동
            R.id.cv_project_info_card -> findNavController().navigate(R.id.action_home_to_projectList) // 프로젝트 리스트로
            R.id.cv_my_team_list -> findNavController().navigate(R.id.action_home_to_teamList) // 팀 리스트로
        }
    }

    //사용자 정보 가져옴
    private fun getUserInfo() {
        val token = getTokenFromSharedPreferences()//토큰
        val account = getAccountFromSharedPreferences()//id

        if (token != null && account != null) {
            val homeService = MainAuthService(this) // HomeService는 네트워크 요청을 처리하는 클래스,MainAuthService 클래스의 인스턴스를 생성
            homeService.setMainView(this)
            homeService.setUserNameCallback(this)
            homeService.getUserName(token,account)//토큰이랑 id를 넘겨줘서 메인에 userid띄움
        }
    }

    override fun onUserNameReceived(userName: String) {
        val tvUserData = view?.findViewById<TextView>(R.id.tv_username_data)
        tvUserData?.text = userName
    }

    private fun getTokenFromSharedPreferences(): String? {
        val sharedPref = activity?.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        //이 안드로이드 앱 내부에 "MyAppPrefs"이름으로 파일 저장, private은 저장옵션
        return sharedPref?.getString("userToken", null)//꺼내오는거라 빈값(null)
    }

    // SharedPreferences에서 account 가져오는 함수
    private fun getAccountFromSharedPreferences(): String? {
        val sharedPref = activity?.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        return sharedPref?.getString("userAccount", null)//sharedpreferences에서 꺼내옴
    }

    override fun onMainGetSuccess() {
        Log.d("USER/GET/SUCCESS","유저정보 획득성공 콜백성공")
    }

    override fun onMainGetFailure() {
        Log.d("USER/GET/FAILURE","유저정보 획득성공 콜백실패")
    }


}