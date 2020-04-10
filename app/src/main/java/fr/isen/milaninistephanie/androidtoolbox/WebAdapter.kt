package fr.isen.milaninistephanie.androidtoolbox

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_web_cell.view.*
import kotlinx.android.synthetic.main.activity_web_user.*
import java.util.*

class WebAdapter(private val randomUsers: User, val context: Context, val peopleOnClickListener: (Results) -> Unit) : RecyclerView.Adapter<WebAdapter.WebViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebAdapter.WebViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.activity_web_cell, parent, false)
        return WebViewHolder(view, randomUsers, context, peopleOnClickListener)
    }


    override fun getItemCount(): Int {
        return randomUsers.results.size
    }

    class WebViewHolder(
        webView: View,
        private val randomUsers: User,
        val context: Context,
        val peopleOnClickListener: (Results) -> Unit
    ) : RecyclerView.ViewHolder(webView) {
        val locationUser: TextView = webView.adress
        val mail: TextView = webView.email
        val picture: ImageView = webView.profil
        val nameUser: TextView = webView.NomPrenom
        val layout = webView.linearLayout2

        fun infoUser(position: Int) {
            val name =
                randomUsers.results[position].name.first + " " + randomUsers.results[position].name.last.toUpperCase(
                    Locale.FRENCH
                )
            val location =
                randomUsers.results[position].location.street.name + " " + randomUsers.results[position].location.street.number + " " + randomUsers.results[position].location.state + " " + randomUsers.results[position].location.city + " " + randomUsers.results[position].location.postcode
            val email = randomUsers.results[position].email



            Picasso.get()
                .load(randomUsers.results[position].picture.medium)
                .fit().centerInside()
                .into(picture)

            nameUser.text = name
            mail.text = email
            locationUser.text = location


        }
    }


    override fun onBindViewHolder(holder: WebViewHolder, position: Int) {
        holder.infoUser(position)
        holder.layout.setOnClickListener {
            peopleOnClickListener.invoke(randomUsers.results[position])
        }
    }
}



