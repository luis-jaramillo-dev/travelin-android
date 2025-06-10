package com.projectlab.core.data.remote.hotels.responses

data class HotelOffersResponse(
    val data: List<Data>
) {
    data class Data(
        val available: Boolean,
        val hotel: Hotel,
        val offers: List<Offer>,
        val self: String,
        val type: String
    ) {
        data class Hotel(
            val chainCode: String,
            val cityCode: String,
            val dupeId: String,
            val hotelId: String,
            val latitude: Double,
            val longitude: Double,
            val name: String,
            val type: String
        )

        data class Offer(
            val checkInDate: String,
            val checkOutDate: String,
            val guests: Guests,
            val id: String,
            val policies: Policies,
            val price: Price,
            val rateCode: String,
            val rateFamilyEstimated: RateFamilyEstimated,
            val room: Room,
            val self: String
        ) {
            data class RateFamilyEstimated(
                val code: String,
                val type: String
            )

            data class Room(
                val description: Description,
                val type: String,
                val typeEstimated: TypeEstimated
            ) {
                data class Description(
                    val lang: String,
                    val text: String
                )

                data class TypeEstimated(
                    val bedType: String,
                    val beds: Int,
                    val category: String
                )
            }

            data class Guests(
                val adults: Int,
                val childAges: Int?
            )

            data class Price(
                val base: String,
                val currency: String,
                val total: String,
                val variations: Variations
            ) {
                data class Variations(
                    val average: Average,
                    val changes: List<Change>
                ) {
                    data class Average(
                        val base: String
                    )

                    data class Change(
                        val base: String,
                        val endDate: String,
                        val startDate: String
                    )
                }
            }

            data class Policies(
                val cancellations: List<Cancellation>,
                val paymentType: String,
                val refundable: Refundable
            ) {
                data class Cancellation(
                    val amount: String,
                    val deadline: String,
                    val numberOfNights: Int,
                    val policyType: String
                )

                data class Refundable(
                    val cancellationRefund: String
                )
            }
        }
    }
}