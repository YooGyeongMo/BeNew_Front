package com.chobo.benewproject.testmain

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.chobo.benewproject.Login.LoginData
import com.chobo.benewproject.Login.LoginRequest
import com.chobo.benewproject.R
import com.chobo.benewproject.profilecard.ProfileCardFragment
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

        btn_testheader.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://ec2-3-39-251-72.ap-northeast-2.compute.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(testGetAccount::class.java)

            val call: Call<ProfileData> = memberId?.let {
                apiService.getProfile("Bearer $token", it)
            } ?: return@setOnClickListener

            call.enqueue(object : Callback<ProfileData> {
                override fun onResponse(call: Call<ProfileData>, response: Response<ProfileData>) {
                    if (response.isSuccessful) {
                        val profileData: ProfileData? = response.body()
                        profileData?.let {
                            val nickname = it.nickname

                            tv_test.text = nickname
                        }
                    } else {
                    }
                }

                override fun onFailure(call: Call<ProfileData>, t: Throwable) {

                }
            })
        }

        btn_goProfile.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.flay_testmain_profilecard, ProfileCardFragment())
            fragmentTransaction.commit()
        }
    }
}