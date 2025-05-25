package com.projectlab.booking.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.core.data.usecase.GetActivitiesUseCase
import com.projectlab.core.domain.proto.SearchHistory
import com.projectlab.core.domain.proto.SearchHistory.HistoryType
import com.projectlab.core.domain.repository.SearchHistoryProvider
import com.projectlab.core.presentation.ui.utils.ErrorMapper
import com.projectlab.core.presentation.ui.utils.LocationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getActivitiesUseCase: GetActivitiesUseCase,
    private val locationUtils: LocationUtils,
    private val errorMapper: ErrorMapper,
    private val historyProvider: SearchHistoryProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        // Load search history when the ViewModel is created
        viewModelScope.launch {
            val list = historyProvider.getSearchHistory(HistoryType.ACTIVITY)
            _uiState.update { it.copy(history = list.takeLast(3).reversed()) }
        }
    }

    fun onQueryChange(newQuery: String) {
        _uiState.update { it.copy(query = newQuery) }
    }

    fun onSearchPressed() {
        if (_uiState.value.query.isBlank()) return
        viewModelScope.launch {
            historyProvider.addSearchEntry(HistoryType.ACTIVITY, _uiState.value.query)
            val updated = historyProvider.getSearchHistory(HistoryType.ACTIVITY)
            _uiState.update { it.copy(history = updated.takeLast(3).reversed()) }
        }
    }

    fun clearQuery() {
        _uiState.update { it.copy(query = "") }
    }

}