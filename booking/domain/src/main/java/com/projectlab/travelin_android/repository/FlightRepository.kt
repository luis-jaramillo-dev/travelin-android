package repository
import model.CityLocation
import model.Flight
interface FlightRepository{

    suspend fun searchCityLocations(keyword: String): List<CityLocation>

    suspend fun getFlights(
        origin: String,
        destination: String,
        date: String
    ):List<Flight>
}