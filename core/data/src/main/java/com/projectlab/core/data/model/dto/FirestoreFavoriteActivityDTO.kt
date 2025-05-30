package com.projectlab.core.data.model.dto

import com.google.firebase.firestore.DocumentReference
import com.projectlab.core.domain.entity.FavoriteActivityEntity

data class FirestoreFavoriteActivityDTO(
    val apiId: String,
    val name: String,
    val description: String,
    val minimumDuration: String,
    val price: Float,
    val currency: String,
    val rating: Float,
    val locationRef: DocumentReference,
) {
    companion object {
        fun fromDomain(
            activity: FavoriteActivityEntity,
            locationRef: DocumentReference,
        ): FirestoreFavoriteActivityDTO = FirestoreFavoriteActivityDTO(
            apiId = activity.apiId,
            name = activity.name,
            description = activity.description,
            minimumDuration = activity.minimumDuration,
            price = activity.price,
            currency = activity.currency,
            rating = activity.rating,
            locationRef = locationRef,
        )
    }
}
