package com.projectlab.core.domain.model_data
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.Timestamp


data class Itinerary(
    val title: String = "",
    val startDate : Timestamp = Timestamp.now(),
    val endDate : Timestamp = Timestamp.now(),
    val totalItineraryPrice: Double = 0.0,
    val userRef : DocumentReference? = null
)
