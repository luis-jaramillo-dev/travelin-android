package com.projectlab.booking.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.core.domain.entity.FavoriteActivityEntity
import com.projectlab.core.domain.repository.ActivityRepository
import com.projectlab.core.domain.repository.UserSessionProvider
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
    private val activitiesRepository: ActivityRepository,
    private val userSessionProvider: UserSessionProvider,
) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoritesUIState())
    val uiState: StateFlow<FavoritesUIState> = _uiState.asStateFlow()

    private var favoriteActivitiesSearchJob: Job? = null

    init {
        queryFavoriteActivities()
    }

    fun onQueryChange(newQuery: String) {
        _uiState.update { it.copy(query = newQuery) }
    }

    fun queryFavoriteActivities() {
        favoriteActivitiesSearchJob?.cancel()

        favoriteActivitiesSearchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, destinations = emptyList()) }

            try {
                val userId = userSessionProvider.getUserSessionId()

                if (userId == null) {
                    _uiState.update { it.copy(error = "Could not get current user") }
                } else {
                    val destinations = mutableListOf<FavoriteActivityEntity>()

                    activitiesRepository.queryFavoriteActivities(userId, _uiState.value.query)
                        .collect { activity ->
                            ensureActive()
                            destinations.add(activity)
                            _uiState.update { it.copy(destinations = destinations.toList()) }
                        }
                }
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
                val userId = userSessionProvider.getUserSessionId()

                if (userId == null) {
                    _uiState.update { it.copy(error = "Could not get current user") }
                    return@launch
                }

                activitiesRepository.removeFavoriteActivityById(userId, activityId)

                queryFavoriteActivities()
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.localizedMessage ?: "Unknown error") }
            }
        }
    }
}
