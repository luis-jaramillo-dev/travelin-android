package com.projectlab.booking.presentation.search.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.core.data.mapper.toDtoList
import com.projectlab.core.domain.model.Location
import com.projectlab.core.domain.util.Result
import com.projectlab.core.data.usecase.GetActivitiesUseCase
import com.projectlab.core.presentation.ui.utils.LocationUtils
import com.projectlab.core.data.remote.ActivitiesApiService
import com.projectlab.core.domain.proto.SearchHistory
import com.projectlab.core.domain.proto.SearchHistory.HistoryType
import com.projectlab.core.domain.repository.SearchHistoryProvider
import com.projectlab.core.presentation.ui.utils.ErrorMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the SearchActivity screen.
 * It handles the logic for searching activities based on user input and location.
 *
 * @param activitiesApiService API service for fetching activities.
 * @param getActivitiesUseCase Use case for getting activities.
 * @param locationUtils Utility class for handling location-related tasks.
 * @param errorMapper Mapper for converting errors to user-friendly messages.
 */

@HiltViewModel
class SearchActivityViewModel @Inject constructor(
    private val activitiesApiService: ActivitiesApiService,
    private val getActivitiesUseCase: GetActivitiesUseCase,
    private val locationUtils: LocationUtils,
    private val errorMapper: ErrorMapper,
    private val historyProvider: SearchHistoryProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchActivityUiState())
    val uiState: StateFlow<SearchActivityUiState> = _uiState.asStateFlow()

    init {
        // Load search history when the ViewModel is created
        viewModelScope.launch {
            val list = historyProvider.getSearchHistory(HistoryType.ACTIVITY)
            _uiState.update { it.copy(history = list.takeLast(3).reversed()) }
        }
    }

    fun onQueryChanged(newQuery: String) {
        _uiState.update { it.copy(query = newQuery) }
    }

    /**
     * onSearchSubmitted() is called when the user submits a search query.
     * It fetches activities based on the provided location.
     * It updates the UI state with the results or an error message.
     */

    fun searchWithInitialQuery(query: String) {
        if (query == uiState.value.query && uiState.value.activities.isNotEmpty()) return
        _uiState.update { it.copy(query = query) }
        onSearchSubmitted()
    }

    fun onSearchSubmitted() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                // Save the search query to history
                historyProvider.addSearchEntry(HistoryType.ACTIVITY, uiState.value.query)
                // Load 3 last search history items
                val updated = historyProvider.getSearchHistory(HistoryType.ACTIVITY)
                _uiState.update { it.copy(history = updated.takeLast(3).reversed()) }

                val locationData = locationUtils.getCoordinatesFromLocation(uiState.value.query)
                if (locationData != null) {
                    when (val result = getActivitiesUseCase(locationData.latitude, locationData.longitude)) {
                        is Result.Success -> {
                            _uiState.update { it.copy(activities = result.data.toDtoList()) }
                        }
                        is Result.Error -> {
                            _uiState.update { it.copy(error = errorMapper.map(result.error)) }
                        }

                    }
                } else {
                    _uiState.update { it.copy(error = "Could not find coordinates for the specified city") }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.localizedMessage ?: "Unknown error") }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun searchByCurrentLocation(location: Location) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val addressString = locationUtils.reverseGeocodeLocation(location)
                _uiState.update { it.copy(address = addressString, query = addressString) }


                when (val result = getActivitiesUseCase(location.latitude, location.longitude)) {
                    is Result.Success -> {
                        _uiState.update { it.copy(activities = result.data.toDtoList()) }
                    }
                    is Result.Error -> {
                        _uiState.update { it.copy(error = errorMapper.map(result.error)) }
                    }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.localizedMessage ?: "Unknown error") }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun showAllResults() {
        _uiState.update { it.copy(showAllResults = true) }
    }
}
