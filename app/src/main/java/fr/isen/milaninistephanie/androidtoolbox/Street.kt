package fr.isen.milaninistephanie.androidtoolbox

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
class Street (
    val number : Int,
    val name : String

) : Parcelable