package com.projectlab.booking.presentation.detail.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.core.data.mapper.toDto
import com.projectlab.core.domain.use_cases.error.ErrorMapper
import com.projectlab.core.domain.entity.FavoriteActivityEntity
import com.projectlab.core.domain.use_cases.activities.GetActivityUseCase
import com.projectlab.core.domain.use_cases.activities.IsFavoriteActivityUseCase
import com.projectlab.core.domain.use_cases.activities.RemoveFavoriteActivityByIdUseCase
import com.projectlab.core.domain.use_cases.activities.SaveFavoriteActivityUseCase
import com.projectlab.core.domain.use_cases.location.GetCityFromCoordinatesUseCase
import com.projectlab.core.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityDetailViewModel @Inject constructor(
    private val isFavoriteActivityUseCase: IsFavoriteActivityUseCase,
    private val saveFavoriteActivityUseCase: SaveFavoriteActivityUseCase,
    private val removeFavoriteActivityByIdUseCase: RemoveFavoriteActivityByIdUseCase,
    private val getCityFromCoordinatesUseCase: GetCityFromCoordinatesUseCase,
    private val getActivityUseCase: GetActivityUseCase,
    private val errorMapper: ErrorMapper,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ActivityDetailUiState())
    val uiState: StateFlow<ActivityDetailUiState> = _uiState.asStateFlow()

    private val _favoriteActivityIds = MutableStateFlow<List<String>>(emptyList())
    val favoriteActivityIds: StateFlow<List<String>> = _favoriteActivityIds.asStateFlow()

    private val favoriteIdsSet: MutableSet<String> = mutableSetOf()

    fun onViewDetail(activityId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                when (val result = getActivityUseCase(activityId)) {
                    is Result.Success -> {
                        val isFavorite = isFavoriteActivityUseCase(activityId)

                        if (isFavorite.isSuccess) {
                            _uiState.update {
                                it.copy(
                                    activity = result.data.toDto(),
                                    isFavorite = isFavorite.getOrElse { false },
                                )
                            }
                        } else {
                            _uiState.update {
                                it.copy(
                                    error = isFavorite.exceptionOrNull()?.localizedMessage
                                        ?: "Unknown error"
                                )
                            }
                        }
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

    fun toggleFavoriteActivity(activity: FavoriteActivityEntity) {
        _uiState.update { it.copy(isFavoriteLoading = true) }
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
            } finally {
                _uiState.update { it.copy(isFavoriteLoading = false) }
            }
        }
    }
}
