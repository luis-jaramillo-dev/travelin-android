package com.projectlab.core.domain.repository

import com.projectlab.core.domain.proto.SearchHistory.HistoryType

/**
 * Interface for providing search history.
 * This interface is used to retrieve and cache search histories.
 *
 * - `getSearchHistory(type)` is a returns that search history,
 * - `addSearchEntry(type, value)` adds a new entry to that search history,
 * - `removeSearchEntry(type, value)` removes the entry from that search history,
 * - `clearSearchHistory(type)` clears that search history.
 */
interface SearchHistoryProvider {
    suspend fun getSearchHistory(type: HistoryType): List<String>
    suspend fun addSearchEntry(type: HistoryType, value: String): List<String>
    suspend fun removeSearchEntry(type: HistoryType, value: String): List<String>
    suspend fun clearSearchHistory(type: HistoryType): List<String>
}
