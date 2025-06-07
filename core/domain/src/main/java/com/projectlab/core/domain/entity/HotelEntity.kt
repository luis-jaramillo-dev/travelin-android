package com.projectlab.core.domain.entity

import com.projectlab.core.domain.model.EntityId
import java.time.Instant

/**
 * HotelEntity represents a hotel booking in the system.
 * @property id Unique identifier for the hotel booking.
 * @property hotelName Name of the hotel.
 * @property hotelRoomNumber Number of the room booked.
 * @property hotelPhone Phone number of the hotel.
 * @property locationRef Reference to the location associated with the hotel.
 * @property guestName Name of the guest who booking.
 * @property guestPhone Phone number of the guest.
 * @property idNumber Identification number of the guest.
 * @property checkInDate Check-in date for the hotel booking.
 * @property checkOutDate Check-out date for the hotel booking.
 * @property hotelPrice Price of the hotel booking.
 * @property userRef Reference to the user associated with the itinerary, that is
 * associated with hotel booking.
 * @property itineraryRef Reference to the itinerary associated with the hotel booking.
 *
 * @author ricardoceadev
 */


data class HotelEntity(
    val id : String = "",
    val hotelName : String = "",
    val hotelRoomNumber : Int = 0,
    val hotelPhone : Int = 0,
    val locationRef : EntityId? = null,
    val guestName : String = "",
    val guestPhone : Int = 0,
    val idNumber : Int = 0,
    val checkInDate : Instant,
    val checkOutDate : Instant,
    val hotelPrice : Double = 0.0,
//    val userRef : EntityId? = null,
//    val itineraryRef : EntityId? = null
)
