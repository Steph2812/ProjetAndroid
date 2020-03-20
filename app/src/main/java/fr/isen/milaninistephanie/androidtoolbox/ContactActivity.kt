package fr.isen.milaninistephanie.androidtoolbox

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_contact.*
import java.util.*


class ContactActivity : AppCompatActivity() {

    val contacts = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        imageContact.setOnClickListener {
            val pictureDialog = AlertDialog.Builder(this)
            pictureDialog.setTitle("Select Action")
            val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
            pictureDialog.setItems(pictureDialogItems
            ) { dialog, which ->
                when (which) {
                    1-> takePicture()
                    0 -> openGalleryForImage()
                }
            }
            pictureDialog.show()

            //openGalleryForImage()
            //takePicture()
        }
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_CONTACTS),
                REQUEST_PERMISSION
            )
        }
        else {

            getContactsData()
            contactRecycler.adapter = ContactAdapter(contacts.sorted())
            contactRecycler.layoutManager = LinearLayoutManager(this)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PERMISSION){
            contactRecycler.adapter = ContactAdapter(contacts.sorted())
        }
    }



    private fun getContactsData(): List<String> {
        val contactList = ArrayList<String>()
        val contactsCursor =
            contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        if (contactsCursor != null && contactsCursor.count > 0) {
            while (contactsCursor.moveToNext()) {

                    val name = contactsCursor.getString(contactsCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    contacts.add("Nom : $name")

            }
            contactsCursor.close()

        }
        return contactList
    }

    private fun openGalleryForImage() {

        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_TAKE_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_IMAGE_PICK) {
            imageContact.setImageURI(data?.data) // handle chosen image
        }
    }

    companion object {
        private const val REQUEST_IMAGE_PICK = 1
        private const val REQUEST_TAKE_PHOTO = 0
        private const val REQUEST_PERMISSION = 2
    }

}



