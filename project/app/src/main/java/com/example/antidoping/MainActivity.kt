package com.example.antidoping

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.antidoping.entities.Substances

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        class InitDatabase : AsyncTask<Unit, Unit, String>() {
            var database:AppDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "nada-small.db"
            )
            .createFromAsset("databases/nada-small.db").fallbackToDestructiveMigration()
            .build()
            override fun doInBackground(vararg params: Unit): String {

                Log.i("test","test")
                return "Complete"
            }
            override fun onPostExecute(result: String) {

            }
        }

        //Datenbank initialisieren
        val initDatabase = InitDatabase()
        initDatabase.execute()


        val profileDAO = initDatabase.database.getNadaDAO()

        val searchText:EditText = findViewById(R.id.searchingtext)

        val searchResult:TextView = findViewById(R.id.searchResult)


        val takings = profileDAO.getAllTakings()
        val uid = profileDAO.getSpecUid("a")
        takings.forEach(System.out::print)

        val search:Substances = profileDAO.getSubstancesByName(searchText.text.toString())

        searchResult.setText(search.Name)
    }
}