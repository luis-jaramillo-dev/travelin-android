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
        return getMutableSearchHistory(type)
    }

    override suspend fun addSearchEntry(type: HistoryType, value: String): List<String> {
        val list = getMutableSearchHistory(type)
        list.add(value)

        searchHistoryStore.updateData { searchHistory ->
            val builder = searchHistory.toBuilder()

            when (type) {
                HistoryType.ACTIVITY -> builder.addActivities(value)
                HistoryType.FLIGHT -> builder.addFlights(value)
                HistoryType.HOTEL -> builder.addHotels(value)

                HistoryType.UNSPECIFIED,
                HistoryType.UNRECOGNIZED,
                    -> throw IllegalArgumentException("$type is not a valid search history type")
            }

            builder.build()
        }

        return list
    }

    override suspend fun removeSearchEntry(type: HistoryType, index: Int): List<String> {
        val list = getMutableSearchHistory(type)
        list.removeAt(index)

        searchHistoryStore.updateData { searchHistory ->
            val builder = searchHistory.toBuilder()

            when (type) {
                HistoryType.ACTIVITY -> builder.clearActivities().addAllActivities(list)
                HistoryType.FLIGHT -> builder.clearFlights().addAllFlights(list)
                HistoryType.HOTEL -> builder.clearHotels().addAllHotels(list)

                HistoryType.UNSPECIFIED,
                HistoryType.UNRECOGNIZED,
                    -> throw IllegalArgumentException("$type is not a valid search history type")
            }

            builder.build()
        }

        return list
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

        return mutableListOf()
    }

    private suspend fun getMutableSearchHistory(type: HistoryType): MutableList<String> {
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
}
