package com.projectlab.core.database.entity

import com.google.firebase.firestore.GeoPoint

data class LocationEntity(
    val name : String = "",
    val country : String = "",
    val countryCode : String = "",
    val coordinates : GeoPoint = GeoPoint(0.0, 0.0),
    val timeZone : String = "",
    val region: String = ""
)
