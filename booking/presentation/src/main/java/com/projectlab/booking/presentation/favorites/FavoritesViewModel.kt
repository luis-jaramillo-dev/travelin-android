package com.projectlab.booking.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.core.domain.entity.FavoriteActivityEntity
import com.projectlab.core.domain.use_cases.activities.QueryFavoriteActivitiesUseCase
import com.projectlab.core.domain.use_cases.activities.RemoveFavoriteActivityByIdUseCase
import com.projectlab.core.domain.use_cases.activities.SaveFavoriteActivityUseCase
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
    private val saveFavoriteActivityUseCase: SaveFavoriteActivityUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoritesUIState())
    val uiState: StateFlow<FavoritesUIState> = _uiState.asStateFlow()

    private val _favoriteActivityIds = MutableStateFlow<List<String>>(emptyList())
    val favoriteActivityIds: StateFlow<List<String>> = _favoriteActivityIds.asStateFlow()

    private var favoriteActivitiesSearchJob: Job? = null

    private val favoriteIdsSet: MutableSet<String> = mutableSetOf()

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
                    destinations.add(activity)
                    _uiState.update { it.copy(destinations = destinations.toList()) }
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

    fun toggleFavorite(activity: FavoriteActivityEntity) {
        viewModelScope.launch {
            try {
                if (favoriteIdsSet.contains(activity.id)) {
                    removeFavoriteActivityByIdUseCase(activity.id)
                    favoriteIdsSet.remove(activity.id)
                } else {
                    val result = saveFavoriteActivityUseCase(activity)
                    if (result.isFailure) {
                        throw result.exceptionOrNull() ?: Exception("Unknown error saving favorite")
                    }
                    favoriteIdsSet.add(activity.id)
                }
                _favoriteActivityIds.value = favoriteIdsSet.toList()
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.localizedMessage ?: "Unknown error") }
            }
        }
    }
}
