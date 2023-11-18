package com.gmlab.team_benew.start

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gmlab.team_benew.R
import com.gmlab.team_benew.auth.AuthResponse
import com.gmlab.team_benew.auth.AuthRetrofitInterface
import com.gmlab.team_benew.auth.AuthService
import com.gmlab.team_benew.auth.LoginActivity
import com.gmlab.team_benew.auth.getRetrofit
import com.gmlab.team_benew.databinding.ActivitySplashBinding
import com.gmlab.team_benew.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity: AppCompatActivity(),SplashView{
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 프로그레스 바 초기 숨김
        binding.pgProgressBar.visibility = View.GONE

        val token = getTokenFromSharedPreferences(this)
        if (token != null) {

            binding.pgProgressBar.visibility = View.VISIBLE // 프로그레스 바 보이기
            Handler().postDelayed({

                val splashAuthService = SplashAuthService()
                splashAuthService.setSplashView(this)
                splashAuthService.verifyUserToken(token)

                startActivity(Intent(this, MainActivity::class.java))

                finish()
            }, 2000)



        } else {
            // token이 null이면 바로 로그인 화면으로 이동
            handler.postDelayed({
                startActivity(Intent(this, IntroActivity::class.java))
                finish()
            }, 3000)
        }
    }


    // SharedPreferences에서 token값을 가져오는 함수
    private fun getTokenFromSharedPreferences(context: Context): String? {
        val sharedPref = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        return sharedPref.getString("userToken", null)

    }

    override fun onTokenCheckSuccess() {
        Log.d("TokenCheck/SUCCESS", "토큰이 유효합니다")
    }

    override fun onTokenCheckFailure() {
        Log.d("TokenCheck/FAILURE", "토큰이 유효하지 않습니다")
    }


}