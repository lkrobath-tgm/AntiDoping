package com.example.antidoping

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.antidoping.entities.JoinMedisSubstanceData
import com.huma.room_for_asset.RoomAsset
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.w3c.dom.Text

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



            val id:String = bundle?.getString("id").toString()
            if(id != null || !id.equals("")) {
                profileDAO.getMedisOrSubstanceById(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        //Es kann nur ein Item geben (mit gleicher Uid)
                        item = result
                        name.text = item.getName()+""
                    }, { exception ->
                        Log.e("Exception", "$exception")
                    })
            }


    }
}