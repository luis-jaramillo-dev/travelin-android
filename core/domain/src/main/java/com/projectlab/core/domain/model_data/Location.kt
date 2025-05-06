package com.projectlab.core.domain.model_data

import android.graphics.Region
import com.google.firebase.firestore.GeoPoint

data class Location(
    val name : String = "",
    val country : String = "",
    val countryCode : String = "",
    val coordinates : GeoPoint = GeoPoint(0.0, 0.0),
    val timeZone : String = "",
    val region: String = ""
)
