package com.gmlab.team_benew.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gmlab.team_benew.R

class HomeFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonNavProfile = view.findViewById<CardView>(R.id.cv_user_info_card)
        val buttonNavProject = view.findViewById<CardView>(R.id.cv_project_info_card)
        val buttonNavMyteamlist = view.findViewById<CardView>(R.id.cv_my_team_list)

        // 모든 버튼에 같은 클릭 리스너 설정
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
}