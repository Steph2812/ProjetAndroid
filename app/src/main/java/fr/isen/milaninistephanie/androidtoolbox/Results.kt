package fr.isen.milaninistephanie.androidtoolbox

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Results (
    val name : Name,
    val location : Location,
    val picture : Picture,
    val email : String,
    val dob : Dob
) : Parcelable