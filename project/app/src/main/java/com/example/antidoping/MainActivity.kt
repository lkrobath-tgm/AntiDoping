package com.example.antidoping

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.huma.room_for_asset.RoomAsset
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import android.widget.Toast
import android.view.KeyEvent.KEYCODE_ENTER



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

        val searchResult2:TextView = findViewById(R.id.textView)
        val searchResult3:TextView = findViewById(R.id.textView2)


        // look at DAO class -> Return Type is now Observable (Reactive)


        searchText.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                // If the event is a key-down event on the "enter" button
                if (event.getAction() === KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    // Perform action on key press
                    /*profileDAO.getSubstancesAndMedisByName(searchText.text.toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ result ->
                            Log.i("substanceByName", "Ethanol result count: ${result.size}")
                            searchResult.text = result.first().getName()
                            searchResult2.text = result.get(1).getName()
                            searchResult3.text = result.get(2).getName()
                        }, { exception ->
                            Log.e("aspirin", "$exception")
                        })*/




                    profileDAO.getMedisAndSubstances(searchText.text.toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({result ->
                            searchResult.text = result.first().getName()
                            searchResult2.text = result.get(1).getName()
                            searchResult3.text = result.get(2).getName()
                        },{exception ->
                            Log.e("Exception","$exception")
                        })
                    Toast.makeText(applicationContext, "TEST", Toast.LENGTH_SHORT).show();
                    return true
                }
                return false
            }
        })
    }
}