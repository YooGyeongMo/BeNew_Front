package com.chobo.benewproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.chobo.benewproject.profilecard.ProfileCardFragment

class TestmainActivity : AppCompatActivity() {

    private lateinit var btn_goProfile : Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testmain)

        val token = intent.getStringExtra("token")

        btn_goProfile = findViewById(R.id.btn_goProfile)

        btn_goProfile.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.flay_testmain_profilecard, ProfileCardFragment())
            fragmentTransaction.commit()
        }
    }
}