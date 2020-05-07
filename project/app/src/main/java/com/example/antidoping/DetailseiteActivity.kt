package com.example.antidoping

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.antidoping.entities.JoinMedisSubstanceData
import com.huma.room_for_asset.RoomAsset
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailseiteActivity : AppCompatActivity(){
    private lateinit var database: AppDatabase
    private lateinit var item: JoinMedisSubstanceData
    private var mediUid:Int = 0

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
        val inComp:ImageView = findViewById(R.id.imageView4)
        val outComp:ImageView = findViewById(R.id.imageView)
        val anwendungsgebiete:TextView = findViewById(R.id.textView7)
        val wirksamkeit:TextView = findViewById(R.id.textView9)
        val nebenwirkungen:TextView = findViewById(R.id.textView11)
        val inCompText:TextView = findViewById(R.id.textView4)
        val outCompText:TextView = findViewById(R.id.textView5)



        val id:Int? = bundle?.getInt("id")
        val typ:String? = bundle?.getString("typ")

        //Uid ist in der DB immer ein String
        var stringId:String = id.toString()

        if(typ.equals("barcode")){
            var pzn:String? = bundle?.getString("PZN")
            var stringpzn:String = pzn.toString()


            profileDAO.getMedisUidInPackungen(stringpzn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    Toast.makeText(applicationContext, result.pzn, Toast.LENGTH_SHORT).show()
                    mediUid = result.pzn

                }, { exception ->
                    Log.e("Exception", "$exception")
                })
            //Uid in der DB immer String
            stringId = stringpzn

        }

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
                    0 -> {
                        inComp.setImageResource(R.drawable.ic_in_comp_allowed)
                        inCompText.text = "erlaubt"
                        inCompText.setTextColor(Color.parseColor("#4CAF50"))
                    }
                    1 -> {
                        inComp.setImageResource(R.drawable.ic_in_comp_restricted)
                        inCompText.text = "beschränkt"
                        inCompText.setTextColor(Color.YELLOW)
                    }

                    2 -> {
                        inComp.setImageResource(R.drawable.ic_in_comp_forbidden)
                        inCompText.text = "verboten"
                        inCompText.setTextColor(Color.parseColor("#FF0000"))
                    }
                }
                when(item.getOutComp()){
                    0 -> {
                        outComp.setImageResource(R.drawable.ic_in_comp_allowed)
                        outCompText.text = "erlaubt"
                        outCompText.setTextColor(Color.parseColor("#4CAF50"))
                    }
                    1 -> {
                        outComp.setImageResource(R.drawable.ic_in_comp_restricted)
                        outCompText.text = "beschränkt"
                        outCompText.setTextColor(Color.YELLOW)
                    }
                    2 -> {
                        outComp.setImageResource(R.drawable.ic_in_comp_forbidden)
                        outCompText.text = "verboten"
                        outCompText.setTextColor(Color.parseColor("#FF0000"))
                    }
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