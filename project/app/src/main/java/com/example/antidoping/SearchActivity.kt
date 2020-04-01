package com.example.antidoping

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.content.DialogInterface
import android.util.Log
import android.view.KeyEvent
import com.huma.room_for_asset.RoomAsset
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import android.view.KeyEvent.KEYCODE_ENTER
import android.widget.*


class SearchActivity : AppCompatActivity() {
    private var btn: Button? = null
    private val TAG = "tag"
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.suche_layout_main)




        database = RoomAsset.databaseBuilder(
            this,
            AppDatabase::class.java,
            "nada-small.db"
        ).build()
        val profileDAO = database.getNadaDAO()

        val searchText: SearchView = findViewById(R.id.searchView)

        val searchResult: TextView = findViewById(R.id.textView3)


        searchText.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){
                    var notNullQuery:String = query
                    profileDAO.getMedisAndSubstances(query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({result ->
                            searchResult.text = result.first().getName()
                        },{exception ->
                            Log.e("Exception","$exception")
                        })
                }else{
                    Toast.makeText(applicationContext, "There is no word that we can search for! ", Toast.LENGTH_SHORT).show();
                }

                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                profileDAO.getMedisAndSubstances(newText)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({result ->
                        searchResult.text = result.first().getName()

                    },{exception ->
                        Log.e("Exception","$exception")
                    })
                return true
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}