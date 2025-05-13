package com.projectlab.core.data.model.dto

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.projectlab.core.domain.entity.HotelEntity
import com.projectlab.core.domain.model.EntityId
import java.time.Instant
import java.util.Date

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
        id = EntityId(docId),
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
        userRef = userRef,
        itineraryRef = itineraryRef
    )


}