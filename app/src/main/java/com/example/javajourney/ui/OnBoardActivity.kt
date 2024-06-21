package com.example.javajourney.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.javajourney.R
import android.widget.Button
import com.example.javajourney.ui.starter.starter_Activity

class OnBoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)


        val mulaiButton: Button = findViewById(R.id.mulai)
        mulaiButton.setOnClickListener {

            val intent = Intent(this, starter_Activity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
