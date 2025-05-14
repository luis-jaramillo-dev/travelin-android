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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityDetailViewModel @Inject constructor(
    private val activityApiService: ActivityApiService,
    private val getActivityUseCase: GetActivityUseCase,
    private val errorMapper: ErrorMapper

) : ViewModel() {

    private val _activity = MutableStateFlow<ActivityDto?>(null)
    val activity: StateFlow<ActivityDto?> = _activity.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()


    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun onViewDetail(activityId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                when (val result = getActivityUseCase(activityId)) {
                    is Result.Success -> {
                        _activity.value = result.data.toDto()
                    }

                    is Result.Error -> {
                        _error.value = errorMapper.map(result.error)
                    }
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
