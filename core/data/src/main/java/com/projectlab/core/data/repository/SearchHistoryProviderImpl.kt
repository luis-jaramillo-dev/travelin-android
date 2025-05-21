package com.projectlab.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import com.projectlab.core.domain.proto.SearchHistory
import com.projectlab.core.domain.repository.SearchHistoryProvider
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SearchHistoryProviderImpl @Inject constructor(
    private val searchHistoryStore: DataStore<SearchHistory>,
) : SearchHistoryProvider {
    override suspend fun getActivitiesSearchHistory(): List<String> {
        return getSearchHistory().activitiesList
    }

    override suspend fun getFlightsSearchHistory(): List<String> {
        return getSearchHistory().flightsList
    }

    override suspend fun getHotelsSearchHistory(): List<String> {
        return getSearchHistory().hotelsList
    }

    override suspend fun addActivitySearchEntry(value: String): List<String> {
        val list = getSearchHistory().activitiesList
        list.add(value)
        searchHistoryStore.updateData { searchHistory ->
            searchHistory.toBuilder().addActivities(value).build()
        }
        return list
    }

    override suspend fun addFlightSearchEntry(value: String): List<String> {
        val list = getSearchHistory().flightsList
        list.add(value)
        searchHistoryStore.updateData { searchHistory ->
            searchHistory.toBuilder().addFlights(value).build()
        }
        return list
    }

    override suspend fun addHotelSearchEntry(value: String): List<String> {
        val list = getSearchHistory().hotelsList
        list.add(value)
        searchHistoryStore.updateData { searchHistory ->
            searchHistory.toBuilder().addHotels(value).build()
        }
        return list
    }

    override suspend fun removeActivitySearchEntry(index: Int): List<String> {
        val list = getSearchHistory().activitiesList
        list.removeAt(index)
        searchHistoryStore.updateData { searchHistory ->
            searchHistory.toBuilder().clearActivities().addAllActivities(list).build()
        }
        return list
    }

    override suspend fun removeFlightSearchEntry(index: Int): List<String> {
        val list = getSearchHistory().flightsList
        list.removeAt(index)
        searchHistoryStore.updateData { searchHistory ->
            searchHistory.toBuilder().clearFlights().addAllFlights(list).build()
        }
        return list
    }

    override suspend fun removeHotelSearchEntry(index: Int): List<String> {
        val list = getSearchHistory().hotelsList
        list.removeAt(index)
        searchHistoryStore.updateData { searchHistory ->
            searchHistory.toBuilder().clearHotels().addAllHotels(list).build()
        }
        return list
    }

    override suspend fun clearActivitiesSearchHistory(): List<String> {
        searchHistoryStore.updateData { searchHistory ->
            searchHistory.toBuilder().clearActivities().build()
        }
        return mutableListOf()
    }

    override suspend fun clearFlightsSearchHistory(): List<String> {
        searchHistoryStore.updateData { searchHistory ->
            searchHistory.toBuilder().clearFlights().build()
        }
        return mutableListOf()
    }

    override suspend fun clearHotelsSearchHistory(): List<String> {
        searchHistoryStore.updateData { searchHistory ->
            searchHistory.toBuilder().clearHotels().build()
        }
        return mutableListOf()
    }

    private suspend fun getSearchHistory(): SearchHistory {
        return try {
            searchHistoryStore.data.first()
        } catch (_: IOException) {
            SearchHistory.getDefaultInstance()
        }
    }
}
