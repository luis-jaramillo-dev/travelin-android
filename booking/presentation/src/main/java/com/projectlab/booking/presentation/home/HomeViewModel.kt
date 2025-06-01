package com.projectlab.booking.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.core.data.usecase.GetActivitiesUseCase
import com.projectlab.core.domain.proto.SearchHistory.HistoryType
import com.projectlab.core.domain.repository.LocationRepository
import com.projectlab.core.domain.repository.SearchHistoryProvider
import com.projectlab.core.presentation.ui.utils.ErrorMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getActivitiesUseCase: GetActivitiesUseCase,
    private val locationRepository: LocationRepository,
    private val errorMapper: ErrorMapper,
    private val historyProvider: SearchHistoryProvider,
    ) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<String>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun fetchSearchHistory() {
        viewModelScope.launch {
            val list = historyProvider.getSearchHistory(HistoryType.ACTIVITY)
            _uiState.update { it.copy(history = list.reversed()) }
        }
    }


    fun onSearchSubmitted() {
        val query = uiState.value.query
        if (query.isNotBlank()) {
            viewModelScope.launch {
                _navigationEvent.emit("search_activities_with_query/$query")
            }
        }
    }

    fun onQueryChange(newQuery: String) {
        _uiState.update { it.copy(query = newQuery) }
    }

    fun onSearchPressed() {
        if (_uiState.value.query.isBlank()) return
        viewModelScope.launch {
            val updated = historyProvider.addSearchEntry(HistoryType.ACTIVITY, _uiState.value.query)
            _uiState.update { it.copy(query = "", history = updated.reversed()) }
        }
    }

    fun onDeleteHistoryEntry(value: String) {
        viewModelScope.launch {
            val updated = historyProvider.removeSearchEntry(HistoryType.ACTIVITY, value)
            _uiState.update { it.copy(history = updated.reversed()) }
        }
    }

    fun clearQuery() {
        _uiState.update { it.copy(query = "") }
    }

}