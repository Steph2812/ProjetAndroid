package fr.isen.milaninistephanie.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cycle_de_vie.*

class CycleDeVie : AppCompatActivity() {
    var cycleText:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cycle_de_vie)
        cycleText = "On create \n"
        cycle.text = cycleText
        Log.d("LifeCycleActivity", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        cycleText += "On start \n"
        cycle.text = cycleText
        Log.d("LifeCycleActivity","onStart")
    }

    override fun onResume() {
        super.onResume()
        cycleText += "On Resume \n"
        cycle.text = cycleText
        Log.d("LifeCycleActivity","onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        cycleText += "On Destroy \n"
        cycle.text = cycleText
        Log.d("LifeCycleActivity", "onDestroy")
        Toast.makeText(this,"l'Activité est détruite", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        cycleText += "On Stop \n"
        cycle.text = cycleText
        Log.d("LifeCycleActivity", "onPause")
    }

}

