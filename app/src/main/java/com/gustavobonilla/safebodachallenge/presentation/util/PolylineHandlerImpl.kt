package com.gustavobonilla.safebodachallenge.presentation.util

import android.graphics.Color
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.domain.model.Coordinate
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class PolylineHandlerImpl(private val googleMap: GoogleMap,
                          private val bitmapDescriptor: BitmapDescriptor): PolylineHandler {

    private val cities = mutableListOf<City>()

    private val publisher: PublishSubject<City> = PublishSubject.create()
    private val markers = mutableListOf<Marker>()
    private val compositeDisposable = CompositeDisposable()

    //region [PolygonHandler] Impl
    override fun drawMarkers(coordinates: List<Coordinate>) {
        googleMap.clear()
        coordinates.forEach {
            addMarker(it)
        }
    }

    override fun subscribeToPublisher(observer: (City) -> Unit) {
        compositeDisposable.add(publisher.subscribe(observer))
    }

    override fun unSubscribe() {
        compositeDisposable.clear()
    }

    override fun addCity(city: City) {
        cities.add(city)
    }

    override fun createPolyline() {
        googleMap.addPolyline(PolylineOptions()
                .addAll(cities.map { LatLng(it.position.latitude, it.position.longitude) })
                .width(5f)
                .color(Color.argb(255, 255, 136, 0)))
        cities.forEach {
            addMarker(it.position)
        }
        setMarkerListener()
        googleMap.uiSettings?.isZoomControlsEnabled = true
        centerMapAdjustZoom(cities.first().position)
    }
    //endregion

    //region private impl
    /**
     * Adds a marker in the map
     *
     * @param coordinate the coordinate in which the marker will be added.
     */
    private fun addMarker(coordinate: Coordinate) {
        markers.add(googleMap.addMarker(MarkerOptions()
                .position(LatLng(coordinate.latitude, coordinate.longitude))
                .icon(bitmapDescriptor)) as Marker)
    }

    /**
     * Create the [MarkerListener] for the map.
     */
    private fun setMarkerListener() {
        googleMap.setOnMarkerClickListener { marker ->
            val city = cities.find { it.position.latitude ==  marker.position.latitude && it.position.longitude ==  marker.position.longitude}
            city?.let {
                publisher.onNext(city)
            }
            centerMapAdjustZoom(Coordinate(marker.position.latitude, marker.position.longitude))
            true
        }
    }

    /**
     * Center the map and zoom to the working area.
     */
    private fun centerMapAdjustZoom(centerCoordinate: Coordinate) {
        val zoom = CameraUpdateFactory.zoomTo(8f)
        val center = CameraUpdateFactory.newLatLng(LatLng(centerCoordinate.latitude, centerCoordinate.longitude))
        googleMap.moveCamera(center)
        googleMap.animateCamera(zoom)
    }
    //endregion
}