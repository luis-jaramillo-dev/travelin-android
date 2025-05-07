package dto

data class FlightOffersResponseDto(
    val data: List<FlightDto>
)

data class FlightDto(
    val id: String,
    val price: PriceDto,
    val itineraries: List<ItineraryDto>
)
data class PriceDto(val total: String)
data class ItineraryDto(val segments: List<SegmentDto>)
data class SegmentDto(val departure: AirportDto, val arrival: AirportDto)

data class AirportDto(val iataCode: String, val at: String)
