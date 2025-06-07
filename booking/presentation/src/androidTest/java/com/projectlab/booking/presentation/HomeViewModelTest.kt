package com.projectlab.booking.presentation

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.projectlab.booking.presentation.home.HomeViewModel
import com.projectlab.core.data.usecase.GetActivitiesUseCase
import com.projectlab.core.domain.proto.SearchHistory.HistoryType
import com.projectlab.core.domain.repository.ActivityRepository
import com.projectlab.core.domain.repository.SearchHistoryProvider
import com.projectlab.core.domain.repository.UserSessionProvider
import com.projectlab.core.domain.use_cases.location.GetCoordinatesFromCityUseCase
import com.projectlab.core.presentation.ui.utils.ErrorMapper
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.mockito.Mockito.mock

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {
    private val getActivitiesUseCase = mock<GetActivitiesUseCase>()
    private val errorMapper = mock<ErrorMapper>()
    private val historyProvider = mock<SearchHistoryProvider>()
    private val getCoordinatesFromCityUseCase = mock<GetCoordinatesFromCityUseCase>()
    private val activityRepository = mock<ActivityRepository>()
    private val userSessionProvider = mock<UserSessionProvider>()

    private val homeViewModel = HomeViewModel(
        getActivitiesUseCase = getActivitiesUseCase,
        errorMapper = errorMapper,
        historyProvider = historyProvider,
        getCoordinatesFromCityUseCase = getCoordinatesFromCityUseCase,
        activityRepository = activityRepository,
        userSessionProvider = userSessionProvider
    )

    @Test
    fun searchHistoryGetsFetched() = runTest {
        val history = listOf("Madrid", "Paris")
        Mockito.`when`(historyProvider.getSearchHistory(HistoryType.ACTIVITY)).thenReturn(history)

        homeViewModel.fetchSearchHistory()

        assertEquals(listOf("Paris", "Madrid"), homeViewModel.uiState.value.history)
    }
}