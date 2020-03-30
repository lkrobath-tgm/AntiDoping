package com.example.antidoping

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.huma.room_for_asset.RoomAsset
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        database = RoomAsset.databaseBuilder(
            this,
            AppDatabase::class.java,
            "nada-small.db"
        ).build()
        val profileDAO = database.getNadaDAO()

        val searchText: EditText = findViewById(R.id.searchingtext)

        val searchResult: TextView = findViewById(R.id.searchResult)


        // look at DAO class -> Return Type is now Observable (Reactive)
        profileDAO.getSubstancesByName("Ethanol")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.i("substanceByName", "Ethanol result count: ${result.size}")
                searchResult.text = result.first().name
            }, { exception ->
                Log.e("aspirin", "$exception")
            })


    }
}