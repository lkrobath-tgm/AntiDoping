package com.example.antidoping

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mehrbereich_main)

        var menu: ImageView = findViewById(R.id.backToMainView);
        var verbot: Button = findViewById(R.id.verbotslisteButton);
        var osl: Button = findViewById(R.id.oslButton);
        var benu: Button = findViewById(R.id.benutzerhinweisButton);
        var imp: Button = findViewById(R.id.impressumButton);

        menu.setOnClickListener{
            val intent = Intent(this@MenuActivity, MainActivity::class.java)
            startActivity(intent)


        }

        verbot.setOnClickListener{
            //val intent = Intent(this@MenuActivity, VerbotActivity::class.java)
            //startActivity(intent)
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nada.at/de/medizin/verbotsliste"))
            startActivity(i)
        }
        osl.setOnClickListener{
            val intent = Intent(this@MenuActivity, OSLActivity::class.java)
            startActivity(intent)
        }
        benu.setOnClickListener{
            //val intent = Intent(this@MenuActivity, BenutzerActivity::class.java)
            //startActivity(intent)
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nada.at/de/medizin/medikamentenabfrage/benutzerhinweise"))
            startActivity(i)
        }
        imp.setOnClickListener{
            //val intent = Intent(this@MenuActivity, ImpressumActivity::class.java)
            //startActivity(intent)
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nada.at/de/impressum"))
            startActivity(i)
        }



    }
}