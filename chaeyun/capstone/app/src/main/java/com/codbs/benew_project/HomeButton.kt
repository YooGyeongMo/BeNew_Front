package com.codbs.benew_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.codbs.benew_project.TeamProject.TeamMemberFrag
import com.codbs.benew_project.TodoList.TodoListCardFrag

class HomeButton : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_button)

        settingButtons()
    }
    fun settingButtons() {
        val dday_button = findViewById<Button>(R.id.d_day)
        val team_button = findViewById<Button>(R.id.team)


        dday_button.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.home_layout, TodoListCardFrag())
            fragmentTransaction.commit()


        }

        team_button.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.home_layout, TeamMemberFrag())
            fragmentTransaction.commit()


        }
    }
}