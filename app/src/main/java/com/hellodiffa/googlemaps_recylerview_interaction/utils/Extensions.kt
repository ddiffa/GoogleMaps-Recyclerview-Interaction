package com.hellodiffa.googlemaps_recylerview_interaction.utils

import android.location.Geocoder
import com.hellodiffa.googlemaps_recylerview_interaction.model.Hotel
import java.text.NumberFormat
import java.util.*

/*
* CREATED By Diffa
* linkedin : www.linkedin.com/in/diffadwi
* github : www.github.com/ddiffa
* email : ddiffa2@gmail.com
*/
fun getFormat(): NumberFormat {
    val localeID = Locale("in", "ID")
    return NumberFormat.getCurrencyInstance(localeID)
}

fun Hotel.getLocation(geocoder: Geocoder): String {
    val list = geocoder.getFromLocation(
        Lat,
        Lang,
        1
    )
    if (list.isNotEmpty()) {
        return "${list[0].featureName}, ${list[0].adminArea}"
    }
    return ""
}