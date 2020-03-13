package fr.isen.milaninistephanie.androidtoolbox

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {
    var textBonjour :String = "Bonjour "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val login = intent.getStringExtra("login")
        textBonjour += login
        homeBonjour.text = textBonjour
        cycledeVie.setOnClickListener {
            //Toast.makeText(this, "clique sur le bouton", Toast.LENGTH_LONG).show()
            val intent = Intent(this, CycleDeVie::class.java)
            startActivity(intent)
        }

        homeDeconexion.setOnClickListener {
            goToHome()

        }
        sauvegarde.setOnClickListener {
            val intent = Intent(this, Formulaire::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        permission.setOnClickListener {
            val intent = Intent(this, ContactActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        webservices.setOnClickListener {
            val intent = Intent(this, WebServiceActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }



    private fun goToHome(){
        val intent = Intent(this, LoginActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

}
