package com.projectlab.booking.presentation.activities.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.core.data.mapper.toDto
import com.projectlab.core.data.model.ActivityDto
import com.projectlab.core.data.remote.ActivityApiService
import com.projectlab.core.data.usecase.GetActivityUseCase
import com.projectlab.core.domain.util.Result
import com.projectlab.core.presentation.ui.utils.ErrorMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityDetailViewModel @Inject constructor(
    private val activityApiService: ActivityApiService,
    private val getActivityUseCase: GetActivityUseCase,
    private val errorMapper: ErrorMapper

) : ViewModel() {

    private val _uiState = MutableStateFlow(ActivityDetailUiState())
    val uiState: StateFlow<ActivityDetailUiState> = _uiState.asStateFlow()

    fun onViewDetail(activityId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                when (val result = getActivityUseCase(activityId)) {
                    is Result.Success -> {
                        _uiState.update { it.copy(activity = result.data.toDto()) }
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
}
