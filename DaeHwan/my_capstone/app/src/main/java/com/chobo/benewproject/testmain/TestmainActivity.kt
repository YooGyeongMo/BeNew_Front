package com.chobo.benewproject.testmain

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.chobo.benewproject.R
import com.chobo.benewproject.profilecard.ProfileCardActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestmainActivity : AppCompatActivity() {

    private lateinit var btn_goProfile: Button
    private lateinit var btn_testheader: Button
    private lateinit var tv_test : TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testmain)

        val token = intent.getStringExtra("token")
        val memberId = intent.getStringExtra("id")

        btn_goProfile = findViewById(R.id.btn_goProfile)
        btn_testheader = findViewById(R.id.btn_testheader)
        tv_test = findViewById(R.id.tv_test)

        btn_goProfile.setOnClickListener {
            val intent = Intent(this, ProfileCardActivity::class.java)
            intent.putExtra("token", token)
            intent.putExtra("id", memberId)
            startActivity(intent)
        }
    }
}