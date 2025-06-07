package com.projectlab.core.database.dto

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.projectlab.core.domain.entity.HotelEntity
import com.projectlab.core.domain.model.EntityId
import java.time.Instant
import java.util.Date

/**
 * Data Transfer Object (DTO) for Firestore Hotel.
 *
 * @property hotelName The name of the hotel.
 * @property hotelRoomNumber The room number of the hotel.
 * @property hotelPhone The phone number of the hotel.
 * @property locationRef A reference to the location document in Firestore.
 * @property guestName The name of the guest.
 * @property guestPhone The phone number of the guest.
 * @property idNumber The ID number of the guest.
 * @property checkInDate The check-in date for the hotel stay.
 * @property checkOutDate The check-out date for the hotel stay.
 * @property hotelPrice The price of the hotel stay.
 *
 * @author ricardoceadev
 */

data class FirestoreHotelDTO (
    val hotelName: String = "",
    val hotelRoomNumber: Int = 0,
    val hotelPhone: Int = 0,
    val locationRef: DocumentReference? = null,
    val guestName: String = "",
    val guestPhone: Int = 0,
    val idNumber: Int = 0,
    val checkInDate: Timestamp = Timestamp.now(),
    val checkOutDate: Timestamp = Timestamp.now(),
    val hotelPrice: Double = 0.0
) {

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun fromDomain(
            domain: HotelEntity,
            userDocRef: DocumentReference,
            itineraryDocRef: DocumentReference,
            locationDocRef: DocumentReference
        ): FirestoreHotelDTO =
            FirestoreHotelDTO(
            hotelName = domain.hotelName,
            hotelRoomNumber = domain.hotelRoomNumber,
            hotelPhone = domain.hotelPhone,
            locationRef = locationDocRef,
            guestName = domain.guestName,
            guestPhone = domain.guestPhone,
            idNumber = domain.idNumber,
            checkInDate = Timestamp(Date.from(domain.checkInDate)),
            checkOutDate = Timestamp(Date.from(domain.checkOutDate)),
            hotelPrice = domain.hotelPrice
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun toDomain(
        docId: String,
        userRef: EntityId,
        itineraryRef: EntityId
    ): HotelEntity = HotelEntity(
        id = docId,
        hotelName = hotelName,
        hotelRoomNumber = hotelRoomNumber,
        hotelPhone = hotelPhone,
        locationRef = locationRef?.let { EntityId(it.id) },
        guestName = guestName,
        guestPhone = guestPhone,
        idNumber = idNumber,
        checkInDate = Instant.ofEpochMilli(checkInDate.toDate().time),
        checkOutDate = Instant.ofEpochMilli(checkOutDate.toDate().time),
        hotelPrice = hotelPrice,
//        userRef = userRef,
//        itineraryRef = itineraryRef
    )


}