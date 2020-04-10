package fr.isen.milaninistephanie.androidtoolbox

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Location(
    val street: Street,
    val city: String,
    val state: String,
    val postcode: Int
): Parcelable