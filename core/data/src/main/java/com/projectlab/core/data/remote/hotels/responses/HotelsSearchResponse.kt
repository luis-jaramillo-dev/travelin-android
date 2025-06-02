package com.projectlab.core.data.remote.hotels.responses

import com.google.gson.annotations.SerializedName

data class HotelsSearchResponse(
    val data: List<Data>,
    val meta: Meta
) {

    data class Data(
        val address: Address,
        val chainCode: String,
        val distance: Distance,
        val dupeId: Int,
        @SerializedName("rating")
        val stars: Int,
        val geoCode: GeoCode,
        val hotelId: String,
        val iataCode: String,
        val name: String,
        val hotelAmenities: List<String>?
    ) {

        data class Address(
            val countryCode: String
        )

        data class Distance(
            val unit: String,
            val value: Double
        )

        data class GeoCode(
            val latitude: Double,
            val longitude: Double
        )
    }

    data class Meta(
        val count: Int,
        val links: Links
    ) {
        data class Links(
            val self: String
        )
    }
}