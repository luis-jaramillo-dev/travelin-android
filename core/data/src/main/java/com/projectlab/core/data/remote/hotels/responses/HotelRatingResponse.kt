package com.projectlab.core.data.remote.hotels.responses

data class HotelRatingResponse(
    val data: List<Data>,
    val meta: Meta,
    val warnings: List<Warning>
) {
    data class Data(
        val hotelId: String,
        val numberOfRatings: Int,
        val numberOfReviews: Int,
        val overallRating: Int,
        val sentiments: Sentiments,
        val type: String
    ) {
        data class Sentiments(
            val catering: Int,
            val facilities: Int,
            val internet: Int,
            val location: Int,
            val pointsOfInterest: Int,
            val roomComforts: Int,
            val service: Int,
            val sleepQuality: Int,
            val staff: Int,
            val valueForMoney: Int
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

    data class Warning(
        val code: Int,
        val detail: String,
        val source: Source,
        val title: String
    ) {
        data class Source(
            val parameter: String,
            val pointer: String
        )
    }

}