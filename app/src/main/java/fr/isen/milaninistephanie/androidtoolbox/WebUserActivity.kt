package fr.isen.milaninistephanie.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_web_user.*

class WebUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_user)
        val people: Results = intent.getParcelableExtra("User")
        valeurNom.text = people.name.title +" "+ people.name.first +" "+ people.name.last
        street.text = people.location.street.name
        streetNumber.text = people.location.street.number.toString()
        city.text = people.location.city
        state.text = people.location.state
        codePostale.text =  people.location.postcode.toString()
        AdEmail.text = people.email
        age.text =people.dob.age
        dateBirth.text = people.dob.date
        Picasso.get()
            .load(people.picture.large)
            .fit().centerInside()
            .into(imageView2)
    }

}
