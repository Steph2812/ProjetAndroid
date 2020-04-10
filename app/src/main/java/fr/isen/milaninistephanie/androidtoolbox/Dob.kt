package fr.isen.milaninistephanie.androidtoolbox

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class Dob (
        val date: String,
        val age: String
    ) : Parcelable
