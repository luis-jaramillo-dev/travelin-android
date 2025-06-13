package com.projectlab.core.domain.model

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

data class Location(
    var latitude: Double,
    var longitude: Double,
    val city: String? = null,
    val country: String? = null
){
    fun distanceTo(other: Location): Float {
        val latDistance = Math.toRadians(other.latitude - latitude)
        val longDistance = Math.toRadians(other.longitude - longitude)
        val a = sin(latDistance / 2) * sin(latDistance / 2) +
                cos(Math.toRadians(latitude)) * cos(Math.toRadians(other.latitude)) *
                sin(longDistance / 2) * sin(longDistance / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return EARTH_RADIUS_METERS * c.toFloat()
    }

    companion object {
        private const val EARTH_RADIUS_METERS = 6_371_000
    }
}
