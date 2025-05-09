package mapper

import android.util.Log
import com.google.gson.Gson
import dto.FlightDto
import model.Flight
import model.FlightSegment
import model.Price

fun FlightDto.toDomain(): Flight {
    // Convertir los segmentos de itinerario
    val segments = itineraries.first().segments.map { segment ->
        FlightSegment(
            airline = segment.carrierCode,
            flightNumber = segment.number,
            departureAirport = segment.departure.iataCode,
            arrivalAirport = segment.arrival.iataCode,
            departureTime = segment.departure.at,
            arrivalTime = segment.arrival.at
        )
    }
    // Construcción del objeto Price
    val domainPrice = Price(
        amount = price.amount?:"0.0",
        currency = price.currency?:"USD"
    )
    Log.i("price",price.amount?:"fue nulo")
    // Construir y retornar el objeto Flight
    return Flight(
        id = this.id,
        segments = segments,
        price = domainPrice
    )
}