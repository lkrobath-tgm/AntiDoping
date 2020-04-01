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
import org.w3c.dom.Text


class SearchActivity : AppCompatActivity() {
    private var btn: Button? = null
    private val TAG = "tag"
    lateinit var database: AppDatabase
    var isSubstance:Boolean = false
    var isMedicine:Boolean = false

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

        val howManySearchResults:TextView = findViewById(R.id.howManySearch)

        val searchResult:TextView = findViewById(R.id.textView3)

        val onlySubstance:CheckBox = findViewById(R.id.substance)

        val onlyMedicine:CheckBox = findViewById(R.id.medicine)


        onlySubstance.setOnClickListener {
            if(onlySubstance.isChecked){
                isSubstance = true
                Toast.makeText(applicationContext, "Substanz", Toast.LENGTH_SHORT).show();
            }
        }

        onlyMedicine.setOnClickListener {
            if(onlyMedicine.isChecked){
                isMedicine = true
                Toast.makeText(applicationContext, "Medizin", Toast.LENGTH_SHORT).show();
            }
        }

        //Search Query
        searchText.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){
                    if(query.equals("") || query.equals(" ")){
                        howManySearchResults.text = "0 Suchergebnisse"
                    }else {
                        var notNullQuery: String = query
                        if(isMedicine){
                            profileDAO.getMedisByName(notNullQuery)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ result ->
                                    searchResult.text = result.first().Name
                                    howManySearchResults.text =
                                        result.size.toString() + "Suchergebnisse"
                                }, { exception ->
                                    Log.e("Exception", "$exception")
                                })
                        }
                        if(isSubstance){
                            profileDAO.getSubstancesByName(notNullQuery)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ result ->
                                    searchResult.text = result.first().Name
                                    howManySearchResults.text =
                                        result.size.toString() + "Suchergebnisse"
                                }, { exception ->
                                    Log.e("Exception", "$exception")
                                })
                        }
                        if(isSubstance == false && isMedicine == false) {
                            profileDAO.getMedisAndSubstances(notNullQuery)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ result ->
                                    searchResult.text = result.first().getName()
                                    howManySearchResults.text =
                                        result.size.toString() + "Suchergebnisse"
                                }, { exception ->
                                    Log.e("Exception", "$exception")
                                })
                        }
                    }
                }else{
                    Toast.makeText(applicationContext, "There is no word that we can search for! ", Toast.LENGTH_SHORT).show();
                }

                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                if(newText.equals("") || newText.equals(" ")){
                    howManySearchResults.text = "0 Suchergebnisse"
                }else{
                    if(isMedicine){
                        profileDAO.getMedisByName(newText)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ result ->
                                searchResult.text = result.first().Name
                                howManySearchResults.text =
                                    result.size.toString() + "Suchergebnisse"
                            }, { exception ->
                                Log.e("Exception", "$exception")
                            })
                    }
                    if(isSubstance){
                        profileDAO.getSubstancesByName(newText)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ result ->
                                searchResult.text = result.first().Name
                                howManySearchResults.text =
                                    result.size.toString() + "Suchergebnisse"
                            }, { exception ->
                                Log.e("Exception", "$exception")
                            })
                    }
                    if(isSubstance == false && isMedicine == false) {
                        profileDAO.getMedisAndSubstances(newText)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ result ->
                                searchResult.text = result.first().getName()
                                howManySearchResults.text =
                                    result.size.toString() + "Suchergebnisse"
                            }, { exception ->
                                Log.e("Exception", "$exception")
                            })
                    }
                }
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