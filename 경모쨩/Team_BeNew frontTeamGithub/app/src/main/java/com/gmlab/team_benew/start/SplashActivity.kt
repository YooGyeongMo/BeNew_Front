package com.gmlab.team_benew.start

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.gmlab.team_benew.R
import com.gmlab.team_benew.databinding.ActivitySplashBinding

class SplashActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var handler = Handler()
        handler.postDelayed({
            val intent = Intent(applicationContext, IntroActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }


}