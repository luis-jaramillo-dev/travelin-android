package com.projectlab.core.domain.repository

import com.projectlab.core.domain.proto.SearchHistory.HistoryType

/**
 * Interface for providing search history.
 * This interface is used to retrieve and cache search histories.
 *
 * - `getSearchHistory()` is a suspend function that search history,
 * - `addSearchEntry()` adds a new entry to that search history,
 * - `removeSearchEntry()` removes the entry at the specified index from that search history,
 * - `clearSearchHistory()` clears that search history.
 */
interface SearchHistoryProvider {
    suspend fun getSearchHistory(type: HistoryType): List<String>
    suspend fun addSearchEntry(type: HistoryType, value: String): List<String>
    suspend fun removeSearchEntry(type: HistoryType, index: Int): List<String>
    suspend fun clearSearchHistory(type: HistoryType): List<String>
}
