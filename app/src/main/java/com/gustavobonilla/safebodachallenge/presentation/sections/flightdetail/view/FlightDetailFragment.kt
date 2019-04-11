package com.gustavobonilla.safebodachallenge.presentation.sections.flightdetail.view

import android.content.Context
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.gustavobonilla.safebodachallenge.R
import com.gustavobonilla.safebodachallenge.bitmapDescriptorFromVector
import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.isNotNull
import com.gustavobonilla.safebodachallenge.presentation.SafeBodaChallengeApplication
import com.gustavobonilla.safebodachallenge.presentation.base.BaseView
import com.gustavobonilla.safebodachallenge.presentation.base.BaseViewModel
import com.gustavobonilla.safebodachallenge.presentation.sections.flightdetail.di.FlightDetailModule
import com.gustavobonilla.safebodachallenge.presentation.util.PolylineHandler
import com.gustavobonilla.safebodachallenge.presentation.viewmodel.CityViewModel
import javax.inject.Inject

class FlightDetailFragment : SupportMapFragment(), OnMapReadyCallback, BaseView<City> {

    @Inject
    lateinit var cityViewModel: BaseViewModel<City, String>

    private lateinit var flightSchedule: String
    private lateinit var polygonHandler: PolylineHandler

    //region Fragment Lifecycle
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        inject()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments?.containsKey(KEY_FLIGHT_SCHEDULE) == true) {
            flightSchedule = arguments?.getString(KEY_FLIGHT_SCHEDULE) ?: ""
        }
        getMapAsync(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityViewModel.subscribe(subscribeListener(), subscribeErrorListener())
    }

    override fun onResume() {
        super.onResume()
        val currentCity = cityViewModel.retreiveInMemoryValues("currentCity")
        if (currentCity.isNotNull() && currentCity is City) {
            if (cityViewModel is CityViewModel) {
                (cityViewModel as CityViewModel).notifyTappedCity(currentCity)
            }
        }
        if (this::polygonHandler.isInitialized) {
            subscribeToPolygonHandler()
        }
    }

    override fun onDetach() {
        cityViewModel.unSubscribe()
        polygonHandler.unSubscribe()
        super.onDetach()
    }
    //endregion

    //region [OnMapReadyCallback] Impl
    override fun onMapReady(googleMap: GoogleMap) {
        setupMapAndPolygon(googleMap)
        cityViewModel.getData(flightSchedule)
    }
    //endregion

    //region [BaseView] Impl
    override fun subscribeErrorListener(): ((Throwable) -> Unit)? {
        return {}
    }

    override fun subscribeListener(): (City) -> Unit {
        return {
            if ((cityViewModel as CityViewModel).finishRequesting) {
                polygonHandler.createPolyline()
            } else {
                polygonHandler.addCity(it)
            }
        }
    }

    override fun getLayoutResource(): Int {
        return -1 //Not required for [SupportMapFragment]
    }

    override fun inject() {
        activity?.let {
            (it.application as SafeBodaChallengeApplication)
                    .safeBodaChallengeApplicationComponent
                    .plus(FlightDetailModule(it))
                    .inject(this)
        }
    }
    //endregion

    //region private impl
    private fun setupMapAndPolygon(googleMap: GoogleMap) {
        val iconCity = activity?.bitmapDescriptorFromVector(R.drawable.plane)
        iconCity?.let {
            if (!this::polygonHandler.isInitialized) {
                polygonHandler = PolylineHandler.create(googleMap, it)
            }
            subscribeToPolygonHandler()
        }
    }

    private fun subscribeToPolygonHandler() {
        polygonHandler.subscribeToPublisher {
            if (cityViewModel is CityViewModel) {
                (cityViewModel as CityViewModel).notifyTappedCity(it)
            }
        }
    }
    //endregion

    companion object {
        const val KEY_FLIGHT_SCHEDULE = "com.gustavobonilla.safebodachallenge.presentation.sections.flightdetail.view.flight_schedule.fragment"

        fun newInstance(flightSchedule: String): FlightDetailFragment {
            val bundle = Bundle()
            bundle.putString(KEY_FLIGHT_SCHEDULE, flightSchedule)
            return FlightDetailFragment().apply {
                arguments = bundle
                retainInstance = true
            }
        }
    }
}