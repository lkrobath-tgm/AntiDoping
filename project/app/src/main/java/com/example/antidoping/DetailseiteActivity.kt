package com.example.antidoping

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.antidoping.entities.JoinMedisSubstanceData
import com.huma.room_for_asset.RoomAsset
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailseiteActivity : AppCompatActivity(){
    private lateinit var database: AppDatabase
    private lateinit var item: JoinMedisSubstanceData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailseite_layout_main)

        database = RoomAsset.databaseBuilder(
            this,
            AppDatabase::class.java,
            "nada-small.db"
        ).build()

        val back:ImageView = findViewById(R.id.imageView2)
        back.setOnClickListener{
            val intent = Intent(this@DetailseiteActivity, SearchActivity::class.java)
            startActivity(intent)
        }

        val profileDAO = database.getNadaDAO()
        val bundle:Bundle? = intent.extras

        val name:TextView = findViewById(R.id.textView)
        val inComp:ImageView = findViewById(R.id.imageView)
        val outComp:ImageView = findViewById(R.id.imageView4)
        val anwendungsgebiete:TextView = findViewById(R.id.textView7)
        val wirksamkeit:TextView = findViewById(R.id.textView9)
        val nebenwirkungen:TextView = findViewById(R.id.textView11)
        val inCompText:TextView = findViewById(R.id.inCompText)
        val outCompText:TextView = findViewById(R.id.outCompText)

            val id:Int? = bundle?.getInt("id")
            val stringId:String = id.toString()
            profileDAO.getMedisById(stringId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    item = result
                    name.text = item.getName()+""
                    nebenwirkungen.text = item.getNW()
                    anwendungsgebiete.text = item.getAG()
                    wirksamkeit.text = item.getEW()
                    when(item.getInComp()){
                        0 -> inComp.setImageResource(R.drawable.ic_in_comp_allowed)
                        0 -> inCompText.text = "erlaubt"
                        0 -> inCompText.setTextColor(Color.GREEN)
                        1 -> inComp.setImageResource(R.drawable.ic_in_comp_restricted)
                        1 -> inCompText.text = "beschränkt"
                        1 -> inCompText.setTextColor(Color.YELLOW)
                        2 -> inComp.setImageResource(R.drawable.ic_in_comp_forbidden)
                        2 -> inCompText.text = "verboten"
                        2 -> inCompText.setTextColor(Color.RED)
                    }
                    when(item.getOutComp()){
                        0 -> outComp.setImageResource(R.drawable.ic_in_comp_allowed)
                        0 -> outCompText.text = "erlaubt"
                        0 -> outCompText.setTextColor(Color.GREEN)
                        1 -> outComp.setImageResource(R.drawable.ic_in_comp_restricted)
                        1 -> outCompText.text = "beschränkt"
                        1 -> outCompText.setTextColor(Color.YELLOW)
                        2 -> outComp.setImageResource(R.drawable.ic_in_comp_forbidden)
                        2 -> outCompText.text = "verboten"
                        2 -> outCompText.setTextColor(Color.RED)
                    }

                }, { exception ->
                    Log.e("Exception", "$exception")
                })

                profileDAO.getSubstancesById(stringId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        item = result
                        name.text = item.getName()+""
                }, { exception ->
                    Log.e("Exception", "$exception")
                })
    }
}