package com.projectlab.core.presentation.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.rememberIconImage
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.projectlab.core.presentation.designsystem.R

@Composable
fun MapActivity(latitude : Double,
         longitude: Double){
    MapboxMap(
        modifier = Modifier,
        mapViewportState = rememberMapViewportState {
            setCameraOptions {
                zoom(10.0)
                center(Point.fromLngLat(longitude,latitude))
                pitch(0.0)
                bearing(0.0)
            }
        },

        ){
        val marker = rememberIconImage(key = "marker", painter = painterResource(R.drawable.ic_location))
        PointAnnotation(point = Point.fromLngLat(longitude, latitude)) {
            iconImage = marker
        }
    }
}
