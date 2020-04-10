package fr.isen.milaninistephanie.androidtoolbox

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Name(
    val title: String,
    val first: String,
    val last: String
) : Parcelable