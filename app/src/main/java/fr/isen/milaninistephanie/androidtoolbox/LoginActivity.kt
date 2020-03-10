package fr.isen.milaninistephanie.androidtoolbox

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    var monLogin = "Stephanie"
    var monMotDePasse = "123"
    var KEYLOGIN = "Login"
    var KEYMDP = "motDePasse"
    var pref = "Pref_Shared"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences(pref, Context.MODE_PRIVATE)

        val saveId: String? = sharedPreferences.getString(KEYLOGIN,"")
        val saveMdp: String? = sharedPreferences.getString(KEYMDP,"")

        if (saveId == monLogin && saveMdp == monMotDePasse){
            goToHome()
        }


        loginButton.setOnClickListener {
            //lis les datas
            val identifiantUser  = logIdentifiant.text.toString()
            val motDePasse = Password.text.toString()

            //regarde si c'est le bon utilisateur
            if (identifiantUser == monLogin && motDePasse == monMotDePasse){
                saveLogin(identifiantUser, motDePasse)
                goToHome()
            }
             //sinon sauvegarde
             else {
                Toast.makeText(this,"ERROR", Toast.LENGTH_SHORT).show()
            }

        }
        cleanLogin()
    }

    private fun goToHome(){
        val intent = Intent(this, HomeActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    private fun saveLogin (identifiant: String, motDepasse: String){
        val editor = sharedPreferences.edit()
        // ajoute les data
        editor.putString(KEYLOGIN, identifiant)
        editor.putString(KEYMDP, motDepasse)
        //sauvegarde les changements
        editor.apply()
    }
    private fun cleanLogin() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }


}
