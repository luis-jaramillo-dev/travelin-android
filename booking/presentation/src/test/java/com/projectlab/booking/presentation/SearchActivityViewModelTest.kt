package com.projectlab.booking.presentation


import com.projectlab.booking.presentation.fakes.FakeErrorMapper
import com.projectlab.booking.presentation.fakes.FakeGetActivitiesUseCase
import com.projectlab.booking.presentation.search.activities.SearchActivityViewModel
import com.projectlab.core.data.remote.ActivitiesApiService
import com.projectlab.core.domain.repository.SearchHistoryProvider
import com.projectlab.core.domain.use_cases.location.GetCityFromCoordinatesUseCase
import com.projectlab.core.domain.use_cases.location.GetCoordinatesFromCityUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.mockito.kotlin.mock
import org.junit.Test

class SearchActivityViewModelTest {

    private val activitiesApiService = mock<ActivitiesApiService>()
    private val getActivitiesUseCase = FakeGetActivitiesUseCase()
    private val getCoordinatesFromCityUseCase = mock<GetCoordinatesFromCityUseCase>()
    private val getCityFromCoordinatesUseCase = mock<GetCityFromCoordinatesUseCase>()
    private val errorMapper = FakeErrorMapper()
    private val historyProvider = mock<SearchHistoryProvider>()

    private val searchActivityViewModel = SearchActivityViewModel(
        activitiesApiService = activitiesApiService,
        getActivitiesUseCase = getActivitiesUseCase,
        getCoordinatesFromCityUseCase = getCoordinatesFromCityUseCase,
        getCityFromCoordinatesUseCase = getCityFromCoordinatesUseCase,
        errorMapper = errorMapper,
        historyProvider = historyProvider
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