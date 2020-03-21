package com.example.antidoping

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);


        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "nada-small.db"
        )
            .createFromAsset("databases/nada-small.db").fallbackToDestructiveMigration()
            .build()

        val textf:EditText = findViewById(R.id.searchingtext)

        val profileDAO = db.getNadaDAO()


        val takings = profileDAO.getAllTakings()
        val uid = profileDAO.getSpecUid("a")
        val i = 0
        takings.forEach(System.out::print)
    }
}