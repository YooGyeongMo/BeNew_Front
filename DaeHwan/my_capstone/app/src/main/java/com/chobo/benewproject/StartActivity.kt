package com.chobo.benewproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.Locale

class StartActivity : AppCompatActivity() {

    private lateinit var btn_start : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        btn_start = findViewById(R.id.btn_start_start)

        btn_start.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.flay_start_next, RegisterFragment())
            fragmentTransaction.commit()
        }
    }
}