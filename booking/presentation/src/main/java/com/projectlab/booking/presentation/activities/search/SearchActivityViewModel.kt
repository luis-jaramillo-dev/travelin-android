package com.projectlab.booking.presentation.activities.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.core.data.mapper.toDtoList
import com.projectlab.core.data.model.ActivityDto
import com.projectlab.core.domain.model.Location
import com.projectlab.core.domain.util.Result
import com.projectlab.core.data.usecase.GetActivitiesUseCase
import com.projectlab.core.presentation.ui.utils.LocationUtils
import com.projectlab.core.data.remote.ActivitiesApiService
import com.projectlab.core.presentation.ui.utils.ErrorMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchActivityViewModel @Inject constructor(
    private val activitiesApiService: ActivitiesApiService,
    private val getActivitiesUseCase: GetActivitiesUseCase,
    private val locationUtils: LocationUtils,
    private val errorMapper: ErrorMapper
) : ViewModel() {

    private val _activities = MutableStateFlow<List<ActivityDto>>(emptyList())
    val activities: StateFlow<List<ActivityDto>> = _activities.asStateFlow()

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
        Log.d("SearchActivityViewModel", "Search submitted: ${query.value}")
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val locationData = locationUtils.getCoordinatesFromLocation(query.value)
                if (locationData != null) {
                    when (val result = getActivitiesUseCase(locationData.latitude, locationData.longitude)) {
                        is Result.Success -> {
                            Log.d("SearchActivityViewModel", "Fetched ${result.data.size} activities")
                            _activities.value = result.data.toDtoList()
                        }
                        is Result.Error -> {
                            Log.e("SearchActivityViewModel", "Error: ${result.error}")
                            _error.value = errorMapper.map(result.error)
                        }

                    }
                } else {
                    _error.value = "Could not find coordinates for the specified city"
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchByCurrentLocation(location: Location) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val addressString = locationUtils.reverseGeocodeLocation(location)
                address.value = addressString
                query.value = addressString

                when (val result = getActivitiesUseCase(location.latitude, location.longitude)) {
                    is Result.Success -> _activities.value = result.data.toDtoList()
                    is Result.Error -> _error.value = errorMapper.map(result.error)
                }
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
}