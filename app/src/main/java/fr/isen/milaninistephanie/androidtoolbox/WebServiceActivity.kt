package fr.isen.milaninistephanie.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.textclassifier.TextLinks
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_contact.*
import kotlinx.android.synthetic.main.activity_formulaire.*
import kotlinx.android.synthetic.main.activity_web_service.*


class WebServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_service)

        val queue = Volley.newRequestQueue(this)
        val url: String ="https://randomuser.me/api/?results=10&nat=fr"

        val stringReq = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->

            val userResults = parseJson(response)
            WebRecycler.adapter = WebAdapter(userResults,this)
            WebRecycler.layoutManager = LinearLayoutManager(this)
            WebRecycler.visibility = View.VISIBLE




        },
            Response.ErrorListener {Toast.makeText(this, "That didn't work", Toast.LENGTH_SHORT).show()} )
            queue.add(stringReq)


    }

    private fun parseJson(response : String): User {
        val gson = GsonBuilder().create()
        return  gson.fromJson<User>(response, User::class.java)
    }


}
