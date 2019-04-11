package com.gustavobonilla.safebodachallenge.presentation.sections.flightdetail.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gustavobonilla.safebodachallenge.R
import com.gustavobonilla.safebodachallenge.isNotNull
import com.gustavobonilla.safebodachallenge.isNull
import com.gustavobonilla.safebodachallenge.presentation.navigation.Navigation

class FlightDetailActivity : AppCompatActivity() {

    //region lifecycle [Activity]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_detail)
        if (savedInstanceState.isNull()) {
            if (intent.isNotNull() && intent.extras.isNotNull()) {
                val flightSchedule = intent.extras.getString(KEY_FLIGHT_SCHEDULE) ?: ""
                initDetailCityFragment()
                initMapFragment(flightSchedule)
            }
        }
    }
    //endregion

    //region private impl
    /**
     * Initializes (add) the Map Fragment.
     *
     * @param code the city code that the Map should render.
     */
    private fun initMapFragment(code: String) {
        Navigation.addFragment(supportFragmentManager, R.id.mapContainer, FlightDetailFragment.newInstance(code))
    }

    /**
     * Initializes (add) the [DetailCityDataFragment]
     */
    private fun initDetailCityFragment() {
        Navigation.addFragment(supportFragmentManager, R.id.cityDetailContainer, DetailCityDataFragment.newInstance())
    }
    //endregion

    companion object {
        const val KEY_FLIGHT_SCHEDULE = "com.gustavobonilla.safebodachallenge.presentation.sections.flightdetail.view.flight_schedule"
    }
}
