package com.projectlab.booking.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.core.data.mapper.toDto
import com.projectlab.core.domain.entity.FavoriteActivityEntity
import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.domain.model.Location
import com.projectlab.core.domain.proto.SearchHistory.HistoryType
import com.projectlab.core.domain.repository.ActivityRepository
import com.projectlab.core.domain.repository.SearchHistoryProvider
import com.projectlab.core.domain.use_cases.activities.GetActivitiesUseCase
import com.projectlab.core.domain.use_cases.activities.RemoveFavoriteActivityByIdUseCase
import com.projectlab.core.domain.use_cases.activities.SaveFavoriteActivityUseCase
import com.projectlab.core.domain.use_cases.error.ErrorMapper
import com.projectlab.core.domain.use_cases.hotels.GetFavoriteHotelsUseCase
import com.projectlab.core.domain.use_cases.hotels.GetHotelsByCoordinatesUseCase
import com.projectlab.core.domain.use_cases.hotels.RemoveFavoriteHotelUseCase
import com.projectlab.core.domain.use_cases.hotels.SaveFavoriteHotelUseCase
import com.projectlab.core.domain.use_cases.location.GetCoordinatesFromCityUseCase
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getActivitiesUseCase: GetActivitiesUseCase,
    private val errorMapper: ErrorMapper,
    private val historyProvider: SearchHistoryProvider,
    private val getCoordinatesFromCityUseCase: GetCoordinatesFromCityUseCase,
    private val getHotelsByCoordinatesUseCase: GetHotelsByCoordinatesUseCase,
    private val saveFavoriteActivityUseCase: SaveFavoriteActivityUseCase,
    private val saveFavoriteHotelUseCase: SaveFavoriteHotelUseCase,
    private val removeFavoriteActivityByIdUseCase: RemoveFavoriteActivityByIdUseCase,
    private val getFavoriteHotelsUseCase: GetFavoriteHotelsUseCase,
    private val removeFavoriteHotelUseCase: RemoveFavoriteHotelUseCase,
    private val activityRepository: ActivityRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isFavoriteLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<String>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    private val _favoriteActivityIds = MutableStateFlow<List<String>>(emptyList())
    val favoriteActivityIds: StateFlow<List<String>> = _favoriteActivityIds.asStateFlow()

    private val _favoriteHotelsIds = MutableStateFlow<List<String>>(emptyList())
    val favoriteHotelsIds: StateFlow<List<String>> = _favoriteHotelsIds.asStateFlow()

    private val favoriteActivitiesIdsSet: MutableSet<String> = mutableSetOf()

    private val favoriteHotelsIdsSet: MutableSet<String> = mutableSetOf()

    fun fetchSearchHistory() {
        viewModelScope.launch(dispatcher) {
            val list = historyProvider.getSearchHistory(HistoryType.ACTIVITY)
            _uiState.update { it.copy(history = list.reversed()) }
        }
    }


    fun onSearchSubmitted() {
        val query = uiState.value.query
        if (query.isNotBlank()) {
            viewModelScope.launch {
                _navigationEvent.emit("search_activities_with_query/$query")
            }
        }
    }

    fun onQueryChange(newQuery: String) {
        _uiState.update { it.copy(query = newQuery) }
    }

    fun onSearchPressed() {
        if (_uiState.value.query.isBlank()) return
        viewModelScope.launch {
            val updated = historyProvider.addSearchEntry(HistoryType.ACTIVITY, _uiState.value.query)
            _uiState.update { it.copy(query = "", history = updated.reversed()) }
        }
    }

    fun onDeleteHistoryEntry(value: String) {
        viewModelScope.launch {
            val updated = historyProvider.removeSearchEntry(HistoryType.ACTIVITY, value)
            _uiState.update { it.copy(history = updated.reversed()) }
        }
    }

    fun clearQuery() {
        _uiState.update { it.copy(query = "") }
    }

    fun fetchRecommendedActivities(location: Location?) {
        viewModelScope.launch {
            if (uiState.value.hasLoadedRecommendations) return@launch

            _uiState.update { it.copy(isLoading = true) }

            val parisCoordinates = getCoordinatesFromCityUseCase("Paris") ?: (48.8566 to 2.3522)
            val initialCoordinates =
                location?.let { it.latitude to it.longitude } ?: parisCoordinates

            var result = getActivitiesUseCase(initialCoordinates.first, initialCoordinates.second)

            if (location != null && result is Result.Success && result.data.isEmpty()) {
                result = getActivitiesUseCase(parisCoordinates.first, parisCoordinates.second)
            }

            when (result) {
                is Result.Success -> {
                    val filtered = result.data
                        .filter { it.pictures.isNotEmpty() }
                        .take(10)
                        .map { it.toDto() }

                    _uiState.update {
                        it.copy(
                            recommendedActivities = filtered,
                            hasLoadedRecommendations = true
                        )
                    }

                    fetchFavoriteActivities()
                }

                is Result.Error -> {
                    _uiState.update { it.copy(error = errorMapper.map(result.error)) }
                }
            }

            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun fetchRecommendedHotels(location: Location?) {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true) }

            val parisCoordinates = getCoordinatesFromCityUseCase("Paris") ?: (48.8566 to 2.3522)

            val initialCoordinates =
                location?.let { it.latitude to it.longitude } ?: parisCoordinates

            var result =
                getHotelsByCoordinatesUseCase(
                    initialCoordinates.first, initialCoordinates.second, emptyList(), emptyList()
                )

            if (location != null && result is Result.Success && result.data.isEmpty()) {
                result =
                    getHotelsByCoordinatesUseCase(
                        parisCoordinates.first,
                        parisCoordinates.second,
                        emptyList(),
                        emptyList()
                    )
            }

            when (result) {
                is Result.Success -> {
                    val filtered = result.data
                        .take(10)

                    _uiState.update {
                        it.copy(
                            recommendedHotels = filtered,
                            hasLoadedRecommendations = true
                        )
                    }

                    fetchFavoriteHotels()
                }

                is Result.Error -> {
                    _uiState.update { it.copy(error = errorMapper.map(result.error as DataError.Network)) }
                }
            }

            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun fetchFavoriteActivities() {
        viewModelScope.launch {
            activityRepository.getFavoriteActivities().collect { favorites ->
                _uiState.update { it.copy(favoriteActivities = favorites) }

                val favoriteIds = favorites.map { it.id }
                _favoriteActivityIds.value = favoriteIds

                favoriteActivitiesIdsSet.clear()
                favoriteActivitiesIdsSet.addAll(favoriteIds)
            }
        }
    }

    fun fetchFavoriteHotels() {
        viewModelScope.launch {
            try {
                getFavoriteHotelsUseCase().collect { favorites ->
                    _uiState.update { it.copy(favoriteHotels = favorites) }

                    val favoriteIds = favorites.map { it.id }
                    _favoriteHotelsIds.value = favoriteIds

                    favoriteHotelsIdsSet.clear()
                    favoriteHotelsIdsSet.addAll(favoriteIds)
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.localizedMessage ?: "Unknown error") }
            }
        }
    }

    fun toggleFavoriteActivity(activity: FavoriteActivityEntity) {
        _uiState.update { it.copy(isFavoriteLoading = true) }
        viewModelScope.launch {
            try {
                if (favoriteActivitiesIdsSet.contains(activity.id)) {
                    removeFavoriteActivityByIdUseCase(activity.id)
                    favoriteActivitiesIdsSet.remove(activity.id)
                } else {
                    val result = saveFavoriteActivityUseCase(activity)
                    if (result.isFailure) {
                        throw result.exceptionOrNull() ?: Exception("Unknown error saving favorite")
                    }
                    favoriteActivitiesIdsSet.add(activity.id)
                }
                _favoriteActivityIds.value = favoriteActivitiesIdsSet.toList()
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.localizedMessage ?: "Unknown error") }
            } finally {
                _uiState.update { it.copy(isFavoriteLoading = false) }
            }
        }
    }

    fun toggleFavoriteHotel(hotel: Hotel) {
        _uiState.update { it.copy(isFavoriteLoading = true) }
        viewModelScope.launch {
            try {
                if (favoriteHotelsIdsSet.contains(hotel.id)) {
                    val result = removeFavoriteHotelUseCase(hotel.id)
                    favoriteHotelsIdsSet.remove(hotel.id)
                } else {
                    val result = saveFavoriteHotelUseCase(hotel)
                    favoriteHotelsIdsSet.add(hotel.id)
                }
                _favoriteHotelsIds.value = favoriteHotelsIdsSet.toList()
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.localizedMessage ?: "Unknown error") }
            } finally {
                _uiState.update { it.copy(isFavoriteLoading = false) }
            }
        }
    }
}