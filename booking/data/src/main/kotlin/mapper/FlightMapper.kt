package mapper

import dto.FlightDto
import model.Flight

fun FlightDto.toDomain():Flight{
    val firstSegment = itineraries.first().segments.first()
    return Flight(
        id = id,
        origin = firstSegment.departure.iataCode,
        destination = firstSegment.departure.iataCode,
        departureTime = firstSegment.departure.at,
        price = price.total
    )
}