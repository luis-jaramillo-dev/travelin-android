package com.projectlab.core.data.remote.hotels.responses

data class HotelSearchByIdResponse(
    val data: List<Data>,
    val meta: Meta
) {
    data class Data(
        val address: Address,
        val chainCode: String,
        val dupeId: Int,
        val geoCode: GeoCode,
        val hotelId: String,
        val iataCode: String,
        val lastUpdate: String,
        val name: String
    ) {

        data class Address(
            val countryCode: String
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