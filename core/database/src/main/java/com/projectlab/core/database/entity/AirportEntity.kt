package com.projectlab.core.database.entity

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.GeoPoint

data class AirportEntity(
    val airportCode : String = "",
    val name : String = "",
    val coordinates : GeoPoint = GeoPoint(0.0, 0.0),
    val city : String = "",
    val country : String = "",
    val timeZone : String = "",
    val locationRef : DocumentReference? = null
)
