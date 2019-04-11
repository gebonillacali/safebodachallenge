package com.gustavobonilla.safebodachallenge.presentation.util

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.domain.model.Coordinate

interface PolylineHandler {
    fun drawMarkers(coordinates: List<Coordinate>)

    /**
     * Subscribe to the publisher that indicates if the user moves the map to find out in a new city has been reached.
     */
    fun subscribeToPublisher(observer: (City) -> Unit)

    /**
     * Adds a city to draw the polyline.
     *
     * @param city the city that contains the position in a coordinate.
     */
    fun addCity(city: City)

    /**
     * Creates the polyline in the Map.
     */
    fun createPolyline()

    /**
     * Unsubscribes from publisher.
     */
    fun unSubscribe()

    companion object {
        fun create(googleMap: GoogleMap,
                   bitmapDescriptor: BitmapDescriptor): PolylineHandler {
            return PolylineHandlerImpl(googleMap, bitmapDescriptor)
        }
    }
}