package fr.isen.milaninistephanie.androidtoolbox

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_formulaire.*
import org.json.JSONObject
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class Formulaire : AppCompatActivity() {
    var KEYNOM = "Nom"
    var KEYPRENOM = "Prenom"
    var KEYDATE = "Date"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulaire)

        val cal: Calendar = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { datePicker: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val sdf = SimpleDateFormat("dd/MM/yyyy")
                Calendrier.text = sdf.format(cal.time)


            }
        formulaireDate.setOnClickListener {
            showDatePicker(dateSetListener)
            //calculAge(cal)
        }

        formulaireButton.setOnClickListener {
            val nom = formulaireNom.text.toString()
            val prenom = formulairePrenom.text.toString()
            val datedenaissance = Calendrier.text.toString()
            writeJson(nom, prenom, datedenaissance)

        }
        formulaireCheck.setOnClickListener {
            affichagePopUp()
        }


    }

    private fun writeJson(formulaireNom: String, formulairePrenom: String, Calendrier: String) {
        val objet = JSONObject()
        objet.put(KEYNOM, formulaireNom)
        objet.put(KEYPRENOM, formulairePrenom)
        objet.put(KEYDATE, Calendrier)


        // Toast.makeText(this, "Le fichier Json contient : " + json, Toast.LENGTH_LONG).show()
        val json = objet.toString()
        val path: String = filesDir.absolutePath + "test.json"
        File(path).writeText(json)

    }

    private fun affichagePopUp() {

        val path: String = filesDir.absolutePath + "test.json"
        val json: String = File(path).readText(Charsets.UTF_8)
        val objetMessage = JSONObject(json)
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Personne enregistrée")
        alert.setMessage(
            " Nom : " + objetMessage.get(KEYNOM) +
                    "\n Prenom : " + objetMessage.get(KEYPRENOM) +
                    "\n Date de naissance : " + objetMessage.get(KEYDATE)
        ).create().show()
    }

    private fun showDatePicker(dateSetListener: DatePickerDialog.OnDateSetListener) {
        val cal = Calendar.getInstance()
        DatePickerDialog(
            this, dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    /*private fun calculAge(cal: Calendar){
        val aujourdhui = Calendar.getInstance()
        var Age: Int = aujourdhui.get(Calendar.YEAR) - cal.get(Calendar.YEAR)

    }*/

}
