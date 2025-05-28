package com.projectlab.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import com.projectlab.core.domain.proto.SearchHistory
import com.projectlab.core.domain.proto.SearchHistory.HistoryType
import com.projectlab.core.domain.repository.SearchHistoryProvider
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SearchHistoryProviderImpl @Inject constructor(
    private val searchHistoryStore: DataStore<SearchHistory>,
) : SearchHistoryProvider {
    override suspend fun getSearchHistory(type: HistoryType): List<String> {
        val searchHistory = try {
            searchHistoryStore.data.first()
        } catch (_: IOException) {
            SearchHistory.getDefaultInstance()
        }

        return when (type) {
            HistoryType.ACTIVITY -> searchHistory.activitiesList
            HistoryType.FLIGHT -> searchHistory.flightsList
            HistoryType.HOTEL -> searchHistory.hotelsList

            HistoryType.UNSPECIFIED,
            HistoryType.UNRECOGNIZED,
                -> throw IllegalArgumentException("$type is not a valid search history type")
        }
    }

    override suspend fun addSearchEntry(type: HistoryType, value: String): List<String> {
        val history = LinkedHashSet(getSearchHistory(type))

        if (history.contains(value)) {
            history.remove(value)
        }

        history.add(value)

        searchHistoryStore.updateData { searchHistory ->
            val builder = searchHistory.toBuilder()

            when (type) {
                HistoryType.ACTIVITY -> builder.clearActivities().addAllActivities(history)
                HistoryType.FLIGHT -> builder.clearFlights().addAllFlights(history)
                HistoryType.HOTEL -> builder.clearHotels().addAllHotels(history)

                HistoryType.UNSPECIFIED,
                HistoryType.UNRECOGNIZED,
                    -> throw IllegalArgumentException("$type is not a valid search history type")
            }

            builder.build()
        }

        return history.toList()
    }

    override suspend fun removeSearchEntry(type: HistoryType, value: String): List<String> {
        val history = LinkedHashSet(getSearchHistory(type))
        history.remove(value)

        searchHistoryStore.updateData { searchHistory ->
            val builder = searchHistory.toBuilder()

            when (type) {
                HistoryType.ACTIVITY -> builder.clearActivities().addAllActivities(history)
                HistoryType.FLIGHT -> builder.clearFlights().addAllFlights(history)
                HistoryType.HOTEL -> builder.clearHotels().addAllHotels(history)

                HistoryType.UNSPECIFIED,
                HistoryType.UNRECOGNIZED,
                    -> throw IllegalArgumentException("$type is not a valid search history type")
            }

            builder.build()
        }

        return history.toList()
    }

    override suspend fun clearSearchHistory(type: HistoryType): List<String> {
        searchHistoryStore.updateData { searchHistory ->
            val builder = searchHistory.toBuilder()

            when (type) {
                HistoryType.ACTIVITY -> builder.clearActivities()
                HistoryType.FLIGHT -> builder.clearFlights()
                HistoryType.HOTEL -> builder.clearHotels()

                HistoryType.UNSPECIFIED,
                HistoryType.UNRECOGNIZED,
                    -> throw IllegalArgumentException("$type is not a valid search history type")
            }

            builder.build()
        }

        return listOf()
    }
}
