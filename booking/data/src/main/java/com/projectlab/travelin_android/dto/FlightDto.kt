package dto


data class FlightOffersResponseDto(
    val data: List<FlightDto>
)

data class FlightDto(
    var id: String,
    val price: PriceDto,
    val itineraries: List<ItineraryDto>
)

data class ItineraryDto(
    val segments: List<SegmentDto>
)

data class SegmentDto(
    val carrierCode: String,
    val number: String,
    val departure: AirportDto,
    val arrival: AirportDto
)


data class AirportDto(
    val iataCode: String,
    val at: String // ISO 8601: "2025-06-15T13:00"
)


data class PriceDto(
    val amount: String?,
    val currency: String?
)

