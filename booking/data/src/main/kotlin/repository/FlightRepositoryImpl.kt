package repository

import api.FlightApiService
import com.google.firebase.firestore.FirebaseFirestore
import jakarta.inject.Inject
import mapper.toDomain
import model.Flight

class FlightRepositoryImpl @Inject constructor(
    private val api: FlightApiService,
    private val firestore: FirebaseFirestore
): FlightRepository {
    override suspend fun getFlights(
        origin: String,
        destination: String,
        date: String
    ): List<Flight>{
        val response = api.getFlightOffers(origin,destination,date, adults = 1,max =5)
        val flights = response.data.map{it.toDomain()}

        //Guardar en FireStore
        flights.forEach{
            firestore.collection("flights").document(it.id).set(it)
        }
        return flights
    }
}