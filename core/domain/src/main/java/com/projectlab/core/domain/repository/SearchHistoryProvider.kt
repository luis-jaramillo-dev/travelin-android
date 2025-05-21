package com.projectlab.core.domain.repository

/**
 * Interface for providing search history.
 * This interface is used to retrieve and cache search histories.
 *
 * - `getTYPESearchHistory()` is a suspend function that specific search history,
 * - `addTYPESearchEntry()` adds a new entry to that specific search history,
 * - `clearTYPESearchHistory()` clears that specific search history.
 */
interface SearchHistoryProvider {
    suspend fun getHotelsSearchHistory(): List<String>
    suspend fun getActivitiesSearchHistory(): List<String>
    suspend fun getFlightsSearchHistory(): List<String>

    suspend fun addActivitySearchEntry(value: String): List<String>
    suspend fun addFlightSearchEntry(value: String): List<String>
    suspend fun addHotelSearchEntry(value: String): List<String>

    suspend fun removeActivitySearchEntry(index: Int): List<String>
    suspend fun removeFlightSearchEntry(index: Int): List<String>
    suspend fun removeHotelSearchEntry(index: Int): List<String>

    suspend fun clearActivitiesSearchHistory(): List<String>
    suspend fun clearFlightsSearchHistory(): List<String>
    suspend fun clearHotelsSearchHistory(): List<String>
}
