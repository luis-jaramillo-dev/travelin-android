package com.projectlab.booking.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.core.domain.entity.FavoriteActivityEntity
import com.projectlab.core.domain.entity.FavoriteHotelEntity
import com.projectlab.core.domain.use_cases.activities.QueryFavoriteActivitiesUseCase
import com.projectlab.core.domain.use_cases.activities.RemoveFavoriteActivityByIdUseCase
import com.projectlab.core.domain.use_cases.hotels.QueryFavoriteHotelsUseCase
import com.projectlab.core.domain.use_cases.hotels.RemoveFavoriteHotelUseCase
import com.projectlab.core.domain.use_cases.location.GetCityFromCoordinatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val queryFavoriteActivitiesUseCase: QueryFavoriteActivitiesUseCase,
    private val removeFavoriteActivityByIdUseCase: RemoveFavoriteActivityByIdUseCase,
    private val queryFavoriteHotelsUseCase: QueryFavoriteHotelsUseCase,
    private val removeFavoriteHotelUseCase: RemoveFavoriteHotelUseCase,
    private val getCityFromCoordinatesUseCase: GetCityFromCoordinatesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoritesUIState())
    val uiState: StateFlow<FavoritesUIState> = _uiState.asStateFlow()

    private val _favoriteActivityIds = MutableStateFlow<List<String>>(emptyList())
    val favoriteActivityIds: StateFlow<List<String>> = _favoriteActivityIds.asStateFlow()

    private val _favoriteHotelsIds = MutableStateFlow<List<String>>(emptyList())
    val favoriteHotelsIds: StateFlow<List<String>> = _favoriteHotelsIds.asStateFlow()

    private var favoriteActivitiesSearchJob: Job? = null

    private var favoriteHotelsSearchJob: Job? = null

    private val favoriteIdsSet: MutableSet<String> = mutableSetOf()

    private val cityCache = mutableMapOf<String, String>()

    init {
        queryFavoriteActivities()
    }

    fun onQueryChange(newQuery: String) {
        _uiState.update { it.copy(query = newQuery) }
    }

    private suspend fun getCityName(lat: Double, lng: Double): String {
        val key = "${lat},${lng}"
        return cityCache[key] ?: run {
            val cityName = getCityFromCoordinatesUseCase(lat, lng)
            cityCache[key] = cityName
            cityName
        }
    }

    private suspend fun convertActivityWithCity(activity: FavoriteActivityEntity): FavoriteActivityEntity {
        val cityName = getCityName(activity.latitude, activity.longitude)
        return activity.copy(location = cityName)
    }

    private suspend fun convertHotelWithCity(hotel: FavoriteHotelEntity): FavoriteHotelEntity {
        val cityName = getCityName(hotel.latitude, hotel.longitude)
        return hotel.copy(address = cityName)
    }

    fun queryFavoriteActivities() {
        favoriteActivitiesSearchJob?.cancel()

        favoriteActivitiesSearchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, activities = emptyList()) }

            try {
                val result = queryFavoriteActivitiesUseCase(_uiState.value.query)

                if (result.isFailure) {
                    _uiState.update {
                        it.copy(
                            error = (result.exceptionOrNull() ?: "Unknown error").toString()
                        )
                    }
                }

                val destinations = mutableListOf<FavoriteActivityEntity>()

                result.getOrNull()?.collect { activity ->
                    ensureActive()
                    val activityWithCity = convertActivityWithCity(activity)
                    destinations.add(activityWithCity)
                    _uiState.update { it.copy(activities = destinations.toList()) }
                }

                val ids = destinations.map { it.id }
                _favoriteActivityIds.value = ids
                favoriteIdsSet.clear()
                favoriteIdsSet.addAll(ids)

            } catch (e: Exception) {
                // cancelling a job throws an exception
                if (e !is CancellationException) {
                    _uiState.update { it.copy(error = e.localizedMessage ?: "Unknown error") }
                }
            } finally {
                if (isActive) {
                    // only it wasn't cancelled
                    _uiState.update { it.copy(isLoading = false) }
                }
            }
        }
    }

    fun removeFavoriteActivity(activityId: String) {
        viewModelScope.launch {
            try {
                removeFavoriteActivityByIdUseCase(activityId)
                queryFavoriteActivities()
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.localizedMessage ?: "Unknown error") }
            }
        }
    }

    fun queryFavoriteHotels() {
        favoriteHotelsSearchJob?.cancel()

        favoriteHotelsSearchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, hotels = emptyList()) }

            try {
                val hotels = mutableListOf<FavoriteHotelEntity>()
                val result = queryFavoriteHotelsUseCase(_uiState.value.query)

                result.collect { hotel ->
                    ensureActive()
                    val hotelWithCity = convertHotelWithCity(hotel)
                    hotels.add(hotelWithCity)
                    _uiState.update { it.copy(hotels = hotels.toList()) }
                }

                val ids = hotels.map { it.id }
                _favoriteHotelsIds.value = ids

            } catch (e: Exception) {
                if (e !is CancellationException) {
                    _uiState.update { it.copy(error = e.localizedMessage ?: "Unknown error") }
                }
            } finally {
                if (isActive) {
                    _uiState.update { it.copy(isLoading = false) }
                }
            }
        }
    }

    fun removeFavoriteHotel(hotelId: String) {
        viewModelScope.launch {
            try {
                val result = removeFavoriteHotelUseCase(hotelId)
                queryFavoriteHotels()
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.localizedMessage ?: "Unknown error") }
            }
        }
    }
}