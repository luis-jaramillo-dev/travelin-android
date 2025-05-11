package com.projectlab.core.domain.entity

import com.projectlab.core.domain.model.EntityId
import java.time.Instant


data class HotelEntity(
    val id : EntityId? = null,
    val hotelName : String = "",
    val hotelRoomNumber : Int = 0,
    val hotelPhone : Int = 0,
    val locationRef : Instant,
    val guestName : String = "",
    val guestPhone : Int = 0,
    val idNumber : Int = 0,
    val checkInDate : Instant,
    val checkOutDate : Instant,
    val hotelPrice : Double = 0.0,
    // val itineraryRef : EntityId? = null
)
