package com.gmlab.team_benew.main

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gmlab.team_benew.R

class MainActivity : AppCompatActivity() { //compat 호환성을 해준다는 이야기

    //lifecycle 콜백함수
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //사용자에게 보여줄 레이아웃 선정 파일 ID인수로
        Log.d(TAG, "onCreate")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart")
    }
    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause(){
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}