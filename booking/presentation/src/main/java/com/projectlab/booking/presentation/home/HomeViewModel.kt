package com.projectlab.booking.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.core.data.mapper.toDto
import com.projectlab.core.domain.model.Location
import com.projectlab.core.domain.proto.SearchHistory.HistoryType
import com.projectlab.core.domain.repository.ActivityRepository
import com.projectlab.core.domain.repository.SearchHistoryProvider
import com.projectlab.core.domain.repository.UserSessionProvider
import com.projectlab.core.domain.use_cases.activities.GetActivitiesUseCase
import com.projectlab.core.domain.use_cases.error.ErrorMapper
import com.projectlab.core.domain.use_cases.location.GetCoordinatesFromCityUseCase
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
    private val activityRepository: ActivityRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<String>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun fetchSearchHistory() {
        viewModelScope.launch (dispatcher){
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

            val initialCoordinates = location?.let { it.latitude to it.longitude } ?: parisCoordinates

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

    fun fetchFavoriteActivities() {
        viewModelScope.launch {
                activityRepository.getFavoriteActivities().collect { favorites ->
                    _uiState.update { it.copy(favoriteActivities = favorites) }
                }
        }
    }
}