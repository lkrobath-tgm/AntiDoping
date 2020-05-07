package com.example.antidoping

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.antidoping.entities.JoinMedisSubstanceData
import com.example.antidoping.epoxy.SingleItemController
import com.huma.room_for_asset.RoomAsset
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SearchActivity : AppCompatActivity() {
    private var btn: Button? = null
    private val TAG = "tag"
    lateinit var database: AppDatabase
    var isSubstance:Boolean = false
    var isMedicine:Boolean = false

    lateinit var itemsList:List<JoinMedisSubstanceData>


    private val controller:SingleItemController by lazy {SingleItemController()}
    private val recyclerView : RecyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.suche_layout_main)

        val recyclerView:RecyclerView = findViewById(R.id.recyclerView)



        val back:ImageView = findViewById(R.id.imageView20)
        back.setOnClickListener{
            val intent = Intent(this@SearchActivity, MainActivity::class.java)
            startActivity(intent)
        }

        var menu: ImageView = findViewById(R.id.imageView2);

        menu.setOnClickListener{
            val intent = Intent(this@SearchActivity, MenuActivity::class.java)
            startActivity(intent)
        }


        database = RoomAsset.databaseBuilder(
            this,
            AppDatabase::class.java,
            "nada-small.db"
        ).build()
        val profileDAO = database.getNadaDAO()

        val searchText: SearchView = findViewById(R.id.searchView)

        val howManySearchResults:TextView = findViewById(R.id.howManySearch)

        val onlySubstance:CheckBox = findViewById(R.id.substance)

        val onlyMedicine:CheckBox = findViewById(R.id.medicine)


        onlySubstance.setOnClickListener {
            if(onlySubstance.isChecked){
                isSubstance = true
            }
        }

        onlyMedicine.setOnClickListener {
            if(onlyMedicine.isChecked){
                isMedicine = true
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
                                    controller.initJoinList(result)
                                    controller.requestModelBuild()
                                    itemsList = controller.getListMedis()
                                    howManySearchResults.text =
                                        controller.initHowManyResults()
                                }, { exception ->
                                    Log.e("Exception", "$exception")
                                })
                        }
                        if(isSubstance){
                            profileDAO.getSubstancesByName(notNullQuery)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ result ->
                                    controller.initJoinList(result)
                                    controller.requestModelBuild()
                                    itemsList = controller.getListMedis()
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
                                    controller.initJoinList(result)
                                    controller.requestModelBuild()
                                    itemsList = controller.getListMedis()
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
                                controller.initJoinList(result)
                                controller.requestModelBuild()
                                itemsList = controller.getListMedis()
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
                                controller.initJoinList(result)
                                controller.requestModelBuild()
                                itemsList = controller.getListMedis()
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
                                controller.initJoinList(result)
                                controller.requestModelBuild()
                                itemsList = controller.getListMedis()
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
        initRecycler()
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


    fun onItemClick(position: Int) {
        itemsList = controller.getListMedis()
        val intent = Intent(this, DetailseiteActivity::class.java)
        intent.putExtra("selected_note", "test")
        startActivity(intent)
    }

    private fun initRecycler(){
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = controller.adapter
            addItemDecoration(DividerItemDecoration(this@SearchActivity, linearLayoutManager.orientation))
        }
        //This statement builds model and add it to the recycler view
        controller.requestModelBuild()
    }
    fun startIntent(intent:Intent){
        startActivity(intent)
    }
}