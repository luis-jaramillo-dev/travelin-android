package com.projectlab.core.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.projectlab.core.data.mock.randomHotelAmenities
import com.projectlab.core.data.mock.randomHotelDisplayImageUrl
import com.projectlab.core.data.mock.randomHotelOffers
import com.projectlab.core.data.mock.randomHotelPhoneNumber
import com.projectlab.core.data.mock.randomHotelPhotoUrls
import com.projectlab.core.data.mock.randomHotelRating
import com.projectlab.core.data.remote.hotels.HotelsApiService
import com.projectlab.core.database.services.FirestoreHotel
import com.projectlab.core.domain.entity.HotelEntity
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.domain.model.HotelLocation
import com.projectlab.core.domain.repository.HotelsRepository
import com.projectlab.core.domain.repository.UserSessionProvider
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * HotelsRepositoryImpl is the implementation of the HotelsRepository interface.
 * It performs operations on hotels using services.
 *
 * @param firestoreHotel The FirestoreHotel instance used for Firestore operations.
 * @param apiService The HotelsApiService used for network operations.
 * @param usersRef The Firestore collection reference for users.
 * @param userSessionProvider The UserSessionProvider used to manage user sessions.
 */

class HotelsRepositoryImpl @Inject constructor(
    private val firestoreHotel: FirestoreHotel,
    private val apiService: HotelsApiService,
    private val usersRef: CollectionReference,
    private val userSessionProvider: UserSessionProvider,
) : HotelsRepository {


    override suspend fun createHotel(
        itinId: String,
        hotel: HotelEntity
    ): kotlin.Result<EntityId> {
        return firestoreHotel.createHotel(itinId, hotel)
    }

    override suspend fun getHotelById(
        itinId: String,
        hotelId: String,
    ): kotlin.Result<HotelEntity?> {
        return firestoreHotel.getHotelsById(itinId, hotelId)
    }

    override suspend fun getAllHotels(
        itinId: String,
    ): kotlin.Result<List<HotelEntity>> {
        return firestoreHotel.getAllHotelsForItinerary(itinId)
    }

    override suspend fun updateHotel(
        itinId: String,
        hotel: HotelEntity
    ): kotlin.Result<Unit> {
        return firestoreHotel.updateHotel(itinId, hotel)
    }

    override suspend fun deleteHotel(
        itinId: String,
        hotelId: String,
    ): kotlin.Result<Unit> {
        return firestoreHotel.deleteHotel(itinId, hotelId)
    }

    override suspend fun getHotelsByCity(
        cityCode: String,
        amenities: String,
        ratings: String
    ): Result<List<Hotel>, DataError.Network> {

        return try {
            val ratingQuery = "2, 3, 4, 5"
            val response =
                apiService.getHotelsByCity(cityCode, ratings = ratingQuery)

            val hotelsResponse = response.data.map { it ->
                val hotelOffers =
                    randomHotelOffers(stars = it.stars, countryCode = it.address.countryCode)
                val minHotelOffer = hotelOffers.minOf { it.price.amount }
                Hotel(
                    id = it.hotelId,
                    name = it.name,
                    location = HotelLocation(
                        longitude = it.geoCode.longitude,
                        latitude = it.geoCode.latitude,
                        country = it.address.countryCode,
                        city = it.iataCode,
                        // TODO GET LOCATION BY LON+LAT
                        address = "Plaza Parade, London NW6 5RP,",
                    ),
                    rating = randomHotelRating(it.stars),
                    displayPrice = minHotelOffer.toString(),
                    isFavourite = false,
                    displayImageUrl = randomHotelDisplayImageUrl(),
                    hotelOffers = hotelOffers,
                    amenities = randomHotelAmenities(),
                    photoUrls = randomHotelPhotoUrls(),
                    phoneNumber = randomHotelPhoneNumber()
                )
            }
            Result.Success(hotelsResponse)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(DataError.Network.UNKNOWN)
        }
    }

    override suspend fun getHotelsByCoordinates(
        latitude: Double,
        longitude: Double,
        amenities: String,
        ratings: String
    ): Result<List<Hotel>, DataError.Network> {
        return try {
            val ratingQuery = "2, 3, 4, 5"
            val response =
                apiService.getHotelsByCoordinates(
                    latitude = latitude,
                    longitude = longitude,
                    ratings = ratingQuery
                )

            val hotelsResponse = response.data.map { it ->
                val hotelOffers =
                    randomHotelOffers(stars = it.stars, countryCode = it.address.countryCode)
                val minHotelOffer = hotelOffers.minOf { it.price.amount }
                Hotel(
                    id = it.hotelId,
                    name = it.name,
                    location = HotelLocation(
                        longitude = it.geoCode.longitude,
                        latitude = it.geoCode.latitude,
                        country = it.address.countryCode,
                        city = it.iataCode,
                        // TODO GET LOCATION BY LON+LAT
                        address = "Plaza Parade, London NW6 5RP,",
                    ),
                    rating = randomHotelRating(it.stars),
                    displayPrice = minHotelOffer.toString(),
                    isFavourite = false,
                    displayImageUrl = randomHotelDisplayImageUrl(),
                    hotelOffers = hotelOffers,
                    amenities = randomHotelAmenities(),
                    photoUrls = randomHotelPhotoUrls(),
                    phoneNumber = randomHotelPhoneNumber()
                )
            }
            Result.Success(hotelsResponse)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(DataError.Network.UNKNOWN)
        }
    }

    override suspend fun favoriteHotel(
        hotelId: String
    ): Result<Boolean, DataError.Network> {
        // Get the user ID from the session provider
        val userId = userSessionProvider.getUserSessionId()
            ?: throw NullPointerException("userId is null")
        return try {
            usersRef.document(userId).update("favoritesHotels", FieldValue.arrayUnion(hotelId))
                .await()
            Result.Success(true)

        } catch (e: Exception) {
            Result.Error(DataError.Network.UNKNOWN)
        }
    }

    override suspend fun unfavoriteHotel(
        hotelId: String
    ): Result<Boolean, DataError.Network> {
        // Get the user ID from the session provider
        val userId = userSessionProvider.getUserSessionId()
            ?: throw NullPointerException("userId is null")
        return try {
            usersRef.document(userId).update("favoritesHotels", FieldValue.arrayRemove(hotelId))
                .await()
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(DataError.Network.UNKNOWN)
        }
    }
}