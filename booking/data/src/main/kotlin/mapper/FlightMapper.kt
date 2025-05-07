package mapper

import android.util.Log
import com.google.gson.Gson
import dto.FlightDto
import model.Flight

fun FlightDto.toDomain():Flight{
    val firstItinerary = itineraries.first()
    val firstSegment = itineraries.first().segments.first()
    val lastSegment = firstItinerary.segments.last()
    println("FirstSegment: $firstSegment")
    Log.d("FlightMapper", Gson().toJson(firstSegment))
    return Flight(
        id = id,
        origin = firstSegment.departure.iataCode,
        destination = lastSegment.arrival.iataCode,
        departureTime = firstSegment.departure.at,
        price = price.total
    )
}