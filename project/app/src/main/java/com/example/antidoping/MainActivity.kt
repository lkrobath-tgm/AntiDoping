package com.example.antidoping

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.widget.TextView
import java.io.IOException


class MainActivity : AppCompatActivity() {

    var DB_PATH: String? = null
    val context: Context = this
    private val mDataBase: SQLiteDatabase? = null
    private val DB_NAME = "Banking.db"
    var txt: TextView? = null

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);


        val db = DBMain(this)


        try {

            db.createDB()
        } catch (ioe: IOException) {

            throw Error("Database not created....")
        }

        try {
            db.openDB()

        } catch (sqle: SQLException) {

            throw sqle
        }


        val db1: SQLiteDatabase
        db1 = openOrCreateDatabase("AntiDoping", SQLiteDatabase.CREATE_IF_NECESSARY, null)
        val c = db1.rawQuery("SELECT * FROM bank", null)

        c.moveToFirst()

        var temp = ""
        while (!c.isAfterLast) {
            val s2 = c.getString(0)
            val s3 = c.getString(1)
            val s4 = c.getString(2)
            temp = "$temp\n Id:$s2\tType:$s3\tBal:$s4"

            c.moveToNext()
        }
    }
}
