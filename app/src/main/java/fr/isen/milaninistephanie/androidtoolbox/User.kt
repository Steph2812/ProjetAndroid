package fr.isen.milaninistephanie.androidtoolbox

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User() : Parcelable{
    val results: ArrayList<Results> = ArrayList()

}
