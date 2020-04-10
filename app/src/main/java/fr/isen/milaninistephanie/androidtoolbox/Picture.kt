package fr.isen.milaninistephanie.androidtoolbox

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Picture (
    val large: String,
    val medium: String,
    val thumbnail: String
) : Parcelable