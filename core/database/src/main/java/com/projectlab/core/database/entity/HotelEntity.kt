package com.projectlab.core.database.entity

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference

data class HotelEntity(
    val hotelName : String = "",
    val hotelRoomNumber : Int = 0,
    val hotelPhone : Int = 0,
    val locationRef : DocumentReference? = null,
    val guestName : String = "",
    val guestPhone : Int = 0,
    val idNumber : Int = 0,
    val checkInDate : Timestamp,
    val checkOutDate : Timestamp,
    val hotelPrice : Double = 0.0,
    val itineraryRef : DocumentReference? = null
)
