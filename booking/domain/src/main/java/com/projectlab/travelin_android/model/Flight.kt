package com.projectlab.travelin_android.model

/*import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.Duration*/
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.Duration
data class Price(
    val amount: String,
    val currency: String
)

data class FlightSegment(
    val airline: String,
    val flightNumber: String,
    val departureAirport: String,
    val arrivalAirport: String,
    val departureTime: String, // formato "2025-06-15T08:00"
    val arrivalTime: String    // formato "2025-06-15T12:00"
)
data class Flight(
    val id:String,
    val segments: List<FlightSegment>,
    val price: Price // ejemplo: "550.00"
) {
    val origin: String get() = segments.firstOrNull()?.departureAirport ?: ""
    val destination: String get() = segments.lastOrNull()?.arrivalAirport ?: ""
    val departureTime: String get() = segments.firstOrNull()?.departureTime ?: ""
    val arrivalTime: String get() = segments.lastOrNull()?.arrivalTime ?: ""

    // Número de escalas (segmentos - 1)
    val stopovers: Int get() = (segments.size - 1).coerceAtLeast(0)

    // Duración total como texto (ej: "12h 15m")
    val durationText: String
        get() {
            val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
            return try {
                val dep = LocalDateTime.parse(departureTime, formatter)
                val arr = LocalDateTime.parse(arrivalTime, formatter)
                val duration = Duration.between(dep, arr)
                val hours = duration.toHours()
                val minutes = duration.toMinutes() % 60
                "${hours}h ${minutes}m"
            } catch (e: Exception) {
                ""
            }
        }
}
enum class TravelClass {
    ECONOMY,
    PREMIUM_ECONOMY,
    BUSINESS,
    FIRST
}

data class FlightQueryParams(
    val originLocationCode: String,              // obligatorio
    val destinationLocationCode: String,         // obligatorio
    val departureDate: String,                   // obligatorio, "YYYY-MM-DD"
    val returnDate: String? = null,              // opcional, "YYYY-MM-DD"
    val adults: Int = 1,                         // obligatorio con default
    val children: Int = 0,                       // opcional
    val infants: Int = 0,                        // opcional
    val travelClass: TravelClass? = null,        // opcional
    val nonStop: Boolean = false,                // opcional
    val maxPrice: Int? = null,                   // opcional
    val max: Int = 250
)