package com.projectlab.core.domain.entity

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference

data class ActivityEntity(
    val name : String = "",
    val locationRef : DocumentReference? = null,
    val activityDate : Timestamp = Timestamp.now(),
    val details : String = "",
    val activityPrice : Double = 0.0,
    val itineraryRef : DocumentReference? = null
)
