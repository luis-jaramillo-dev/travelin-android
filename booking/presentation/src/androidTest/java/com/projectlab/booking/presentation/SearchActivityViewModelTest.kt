package com.projectlab.booking.presentation

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.projectlab.booking.presentation.search.activities.SearchActivityViewModel
import com.projectlab.core.domain.repository.SearchHistoryProvider
import com.projectlab.core.domain.use_cases.activities.GetActivitiesUseCase
import com.projectlab.core.domain.use_cases.activities.RemoveFavoriteActivityByIdUseCase
import com.projectlab.core.domain.use_cases.activities.SaveFavoriteActivityUseCase
import com.projectlab.core.domain.use_cases.error.ErrorMapper
import com.projectlab.core.domain.use_cases.location.GetCityFromCoordinatesUseCase
import com.projectlab.core.domain.use_cases.location.GetCoordinatesFromCityUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.mockito.Mockito.mock

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchActivityViewModelTest {

    private val getActivitiesUseCase = mock<GetActivitiesUseCase>()
    private val getCoordinatesFromCityUseCase = mock<GetCoordinatesFromCityUseCase>()
    private val getCityFromCoordinatesUseCase = mock<GetCityFromCoordinatesUseCase>()
    private val errorMapper = mock<ErrorMapper>()
    private val historyProvider = mock<SearchHistoryProvider>()
    private val saveFavoriteActivityUseCase = mock<SaveFavoriteActivityUseCase>()
    private val removeFavoriteActivityByIdUseCase = mock<RemoveFavoriteActivityByIdUseCase>()

    private val searchActivityViewModel = SearchActivityViewModel(
        getActivitiesUseCase = getActivitiesUseCase,
        getCoordinatesFromCityUseCase = getCoordinatesFromCityUseCase,
        getCityFromCoordinatesUseCase = getCityFromCoordinatesUseCase,
        errorMapper = errorMapper,
        historyProvider = historyProvider,
        saveFavoriteActivityUseCase = saveFavoriteActivityUseCase,
        removeFavoriteActivityByIdUseCase = removeFavoriteActivityByIdUseCase
    )

    @Test
    fun queryStateUpdatesWithNewQuery() = runTest {
        searchActivityViewModel.onQueryChanged("Barcelona")
        assertEquals("Barcelona", searchActivityViewModel.uiState.value.query)
    }

    @Test
    fun searchWithInitialQueryUpdatesStateCorrectly() = runTest {
        searchActivityViewModel.searchWithInitialQuery("Paris")
        assertEquals("Paris", searchActivityViewModel.uiState.value.query)
    }

}