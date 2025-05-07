package repository
import model.Flight
interface FlightRepository{
    suspend fun getFlights(
        origin: String,
        destination: String,
        date: String
    ):List<Flight>
}