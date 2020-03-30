package com.example.antidoping

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mehrbereich_main)

        var menu: ImageView = findViewById(R.id.backToMainView);

        menu.setOnClickListener{
            val intent = Intent(this@MenuActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }
}