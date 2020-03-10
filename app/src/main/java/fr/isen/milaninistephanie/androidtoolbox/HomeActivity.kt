package fr.isen.milaninistephanie.androidtoolbox

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

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
    }



    private fun goToHome(){
        val intent = Intent(this, LoginActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

}
