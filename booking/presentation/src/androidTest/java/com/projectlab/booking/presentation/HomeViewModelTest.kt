package com.projectlab.booking.presentation

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.projectlab.booking.presentation.fakes.fakeActivities
import com.projectlab.booking.presentation.fakes.fakeFavoriteActivities
import com.projectlab.booking.presentation.home.HomeViewModel
import com.projectlab.core.data.mapper.toDto
import com.projectlab.core.domain.model.Location
import com.projectlab.core.domain.proto.SearchHistory.HistoryType
import com.projectlab.core.domain.repository.ActivityRepository
import com.projectlab.core.domain.repository.SearchHistoryProvider
import com.projectlab.core.domain.repository.UserSessionProvider
import com.projectlab.core.domain.use_cases.activities.GetActivitiesUseCase
import com.projectlab.core.domain.use_cases.error.ErrorMapper
import com.projectlab.core.domain.use_cases.location.GetCoordinatesFromCityUseCase
import com.projectlab.core.domain.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.After
import org.mockito.Mockito.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {
    private val getActivitiesUseCase = mock<GetActivitiesUseCase>()
    private val errorMapper = mock<ErrorMapper>()
    private val historyProvider = mock<SearchHistoryProvider>()
    private val getCoordinatesFromCityUseCase = mock<GetCoordinatesFromCityUseCase>()
    private val activityRepository = mock<ActivityRepository>()


    private lateinit var homeViewModel: HomeViewModel

    private val dispatcher: TestDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)

        homeViewModel = HomeViewModel(
            getActivitiesUseCase,
            errorMapper,
            historyProvider,
            getCoordinatesFromCityUseCase,
            activityRepository,
            dispatcher
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun searchHistoryGetsFetched() = runTest {
        val history = listOf("Madrid", "Paris")
        Mockito.`when`(historyProvider.getSearchHistory(HistoryType.ACTIVITY)).thenReturn(history)

        homeViewModel.fetchSearchHistory()

        advanceUntilIdle()

        assertEquals(listOf("Paris", "Madrid"), homeViewModel.uiState.value.history)
    }

    @Test
    fun onSearchSubmittedNavigatesWhenQueryIsProvided() = runTest {
        homeViewModel.onQueryChange("Rome")
        homeViewModel.onSearchSubmitted()

        val event = homeViewModel.navigationEvent.first()
        assertEquals("search_activities_with_query/Rome", event)
    }

    @Test
    fun onSearchPressedUpdatesHistoryAndClearsQuery() = runTest {
        homeViewModel.onQueryChange("London")
        Mockito.`when`(historyProvider.addSearchEntry(HistoryType.ACTIVITY, "London"))
            .thenReturn(listOf("Paris", "London"))

        homeViewModel.onSearchPressed()

        advanceUntilIdle()
        val state = homeViewModel.uiState.value
        assertEquals("", state.query)
        assertEquals(listOf("London", "Paris"), state.history)
    }

    @Test
    fun onDeleteHistoryEntryRemovesEntryAndUpdatesHistory() = runTest {
        Mockito.`when`(historyProvider.removeSearchEntry(HistoryType.ACTIVITY, "Paris"))
            .thenReturn(listOf("London"))

        homeViewModel.onDeleteHistoryEntry("Paris")

        advanceUntilIdle()
        assertEquals(listOf("London"), homeViewModel.uiState.value.history)
    }

    @Test
    fun fetchRecommendedActivitiesAccordingToLocation() = runTest(dispatcher) {
        val location = Location(10.500000, -66.916664)

        val dtoActivities = fakeActivities.map { it.toDto() }

        Mockito.`when`(getActivitiesUseCase(10.500000, -66.916664)).thenReturn(Result.Success(fakeActivities))
        Mockito.`when`(activityRepository.getFavoriteActivities())
            .thenReturn(flowOf(emptyList()))

        homeViewModel.fetchRecommendedActivities(location)
        advanceUntilIdle()

        val state = homeViewModel.uiState.value
        assertEquals(dtoActivities.size, state.recommendedActivities.size)
        assertEquals(false, state.isLoading)
        assertEquals(true, state.hasLoadedRecommendations)
    }

    @Test
    fun fetchFavoriteActivitiesWithValidUserIdUpdatesUiStateWithFavorites() = runTest(dispatcher) {

        val userId = "user123"
        val favoriteActivities = listOf(fakeFavoriteActivities.first())

        Mockito.`when`(activityRepository.getFavoriteActivities()).thenReturn(flowOf(favoriteActivities))

        homeViewModel.fetchFavoriteActivities()
        advanceUntilIdle()

        val state = homeViewModel.uiState.value
        assertEquals(favoriteActivities, state.favoriteActivities)
    }
}