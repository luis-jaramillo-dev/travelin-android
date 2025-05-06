package com.projectlab.core.domain.model_data

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference

data class Activity(
    val name : String = "",
    val locationRef : DocumentReference? = null,
    val activityDate : Timestamp = Timestamp.now(),
    val details : String = "",
    val activityPrice : Double = 0.0,
    val itineraryRef : DocumentReference? = null
)
