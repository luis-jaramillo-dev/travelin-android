package com.projectlab.booking.presentation.home

import androidx.lifecycle.ViewModel
import com.projectlab.core.data.usecase.GetActivitiesUseCase
import com.projectlab.core.domain.repository.LocationRepository
import com.projectlab.core.presentation.ui.utils.ErrorMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getActivitiesUseCase: GetActivitiesUseCase,
    private val locationRepository: LocationRepository,
    private val errorMapper: ErrorMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onQueryChange(newQuery: String) {
        _uiState.update { it.copy(query = newQuery) }
    }

    fun clearQuery() {
        _uiState.update { it.copy(query = "") }
    }

}