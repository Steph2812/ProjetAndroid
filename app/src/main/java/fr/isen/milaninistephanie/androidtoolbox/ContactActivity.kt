package fr.isen.milaninistephanie.androidtoolbox

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import androidx.core.graphics.createBitmap
import kotlinx.android.synthetic.main.activity_contact.*


class ContactActivity : AppCompatActivity() {
    var REQUEST_IMAGE_PICK = 1
    var REQUEST_TAKE_PHOTO = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        //contactTest += formulaireNom.text.toString() + formulairePrenom.text.toString()
        //contactDe.text = contactTest

        imageContact.setOnClickListener {

            openGalleryForImage()
            takePicture()
            /*Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(packageManager)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }*/
        }
    }
    private fun openGalleryForImage() {

        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    private fun takePicture(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,REQUEST_TAKE_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data:Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_IMAGE_PICK){
            imageContact.setImageURI(data?.data) // handle chosen image
        }
    }

}



