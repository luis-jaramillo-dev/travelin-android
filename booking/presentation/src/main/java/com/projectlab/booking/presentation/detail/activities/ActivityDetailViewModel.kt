package com.projectlab.booking.presentation.detail.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.core.data.mapper.toDto
import com.projectlab.core.data.usecase.GetActivityUseCase
import com.projectlab.core.domain.use_cases.error.ErrorMapper
import com.projectlab.core.domain.entity.FavoriteActivityEntity
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

    fun updateFavorite() {
        val activity = _uiState.value.activity

        if (activity == null) {
            _uiState.update { it.copy(error = "Could not get current activity") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isFavoriteLoading = true) }

            try {
                if (_uiState.value.isFavorite) {
                    removeFavoriteActivityByIdUseCase(activity.id)
                    _uiState.update { it.copy(isFavorite = false) }
                } else {
                    val location = getCityFromCoordinatesUseCase(
                        activity.geoCode.latitude,
                        activity.geoCode.longitude,
                    )

                    val favoriteActivity = FavoriteActivityEntity(
                        id = activity.id,
                        name = activity.name,
                        description = activity.description,
                        minimumDuration = activity.minimumDuration,
                        price = activity.price.amount,
                        currency = activity.price.currencyCode,
                        rating = activity.rating,
                        location = location,
                        latitude = activity.geoCode.latitude,
                        longitude = activity.geoCode.longitude,
                        pictures = activity.pictures,
                    )

                    saveFavoriteActivityUseCase(favoriteActivity)
                    _uiState.update { it.copy(isFavorite = true) }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.localizedMessage ?: "Unknown error") }
            } finally {
                _uiState.update { it.copy(isFavoriteLoading = false) }
            }
        }
    }
}
