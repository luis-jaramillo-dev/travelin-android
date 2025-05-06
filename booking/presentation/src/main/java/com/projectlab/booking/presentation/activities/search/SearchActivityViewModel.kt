package com.projectlab.booking.presentation.activities.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.core.data.model.ActivityDto
import com.projectlab.core.presentation.ui.model.LocationData
import com.projectlab.core.data.usecase.GetActivitiesUseCase
import com.projectlab.core.presentation.ui.utils.LocationUtils
import com.projectlab.core.data.mapper.toDomain
import com.projectlab.core.data.mapper.toDomainList
import com.projectlab.core.data.repository.ActivitiesApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchActivityViewModel @Inject constructor(
    private val activitiesApiService: ActivitiesApiService,
    private val getActivitiesUseCase: GetActivitiesUseCase,
    private val locationUtils: LocationUtils
) : ViewModel() {

    private val _activities = MutableStateFlow<List<ActivityDto>>(emptyList())
    val activities: StateFlow<List<ActivityDto >> = _activities.asStateFlow()

    private val _showAllResults = MutableStateFlow(false)
    val showAllResults: StateFlow<Boolean> = _showAllResults.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    val query = MutableStateFlow("")
    val address = MutableStateFlow<String?>(null)

    fun onQueryChanged(newQuery: String) {
        query.value = newQuery
    }

    fun onSearchSubmitted() {
        viewModelScope.launch {
            try {
                val locationData = locationUtils.getCoordinatesFromLocation(query.value)
                Log.d("SearchActivityViewModel", "Coordinates for city: ${query.value} = $locationData")
                if (locationData != null) {
                    val activitiesResponse = getActivitiesUseCase(locationData.latitude, locationData.longitude)
                    Log.d("SearchActivityViewModel", "Activities count: ${activitiesResponse.data.size}")
                    _activities.value = activitiesResponse.data
                } else {
                    _error.value = "Could not find coordinates for the specified city"
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Unknown error"
            }
        }
    }

    fun searchByLocation(location: LocationData) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                _showAllResults.value = false

                val activitiesResult = getActivitiesUseCase(
                    latitude = location.latitude,
                    longitude = location.longitude
                )

                _activities.value = activitiesResult.data
                address.value = "${location.city}, ${location.country}"
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun showAllResults() {
        _showAllResults.value = true
    }

    fun clearError() {
        _error.value = null
    }
}