package com.gustavobonilla.safebodachallenge.presentation.sections.flightresult.view

import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.gustavobonilla.safebodachallenge.domain.model.FlightSchedule
import com.gustavobonilla.safebodachallenge.presentation.SafeBodaChallengeApplication
import com.gustavobonilla.safebodachallenge.presentation.base.BaseListItemFragment
import com.gustavobonilla.safebodachallenge.presentation.navigation.Navigation
import com.gustavobonilla.safebodachallenge.presentation.sections.flightdetail.view.FlightDetailActivity
import com.gustavobonilla.safebodachallenge.presentation.sections.flightresult.di.FlightResultModule
import com.gustavobonilla.safebodachallenge.usecases.FlightSchedulesRequest

class FlightResultFragment : BaseListItemFragment<FlightSchedule, FlightSchedulesRequest>() {

    lateinit var originAirport: String
    lateinit var destinationAirport: String
    lateinit var flightDate: String

    //region [Fragment] LifeCycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            originAirport = it.getString(KEY_ORIGIN_AIRPORT)!!
            destinationAirport = it.getString(KEY_DESTINATION_AIRPORT)!!
            flightDate = it.getString(KEY_FLIGHT_DATE)!!
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData(FlightSchedulesRequest(originAirport, destinationAirport, flightDate))
    }
    //endregion

    //region [ClickListener] Impl
    override fun onItemClickListener(item: FlightSchedule) {
        val flightSchedule = Gson().toJson(item)
        activity?.let {
            val bundle = Bundle()
            bundle.putString(FlightDetailActivity.KEY_FLIGHT_SCHEDULE, flightSchedule)
            Navigation.navigateToActivity(it, FlightDetailActivity::class.java, bundle, false)
        }

    }
    //endregion

    //region [BaseView] Impl
    override fun inject() {
        activity?.let {
            (it.application as SafeBodaChallengeApplication)
                    .safeBodaChallengeApplicationComponent
                    .plus(FlightResultModule(it))
                    .inject(this)
        }
    }
    //endregion

    companion object {
        private const val KEY_ORIGIN_AIRPORT = "com.gustavobonilla.safebodachallenge.presentation.sections.flightresult.view.origin.airport"
        private const val KEY_DESTINATION_AIRPORT = "com.gustavobonilla.safebodachallenge.presentation.sections.flightresult.view.destination.airport"
        private const val KEY_FLIGHT_DATE = "com.gustavobonilla.safebodachallenge.presentation.sections.flightresult.view.flight.date"
        fun newInstance(originAirport: String, destinationAirport: String, flightDate:String): FlightResultFragment {
            val bundle = Bundle()
            bundle.putString(KEY_ORIGIN_AIRPORT, originAirport)
            bundle.putString(KEY_DESTINATION_AIRPORT, destinationAirport)
            bundle.putString(KEY_FLIGHT_DATE, flightDate)
            return FlightResultFragment().apply {
                arguments = bundle
                retainInstance = true
            }
        }
    }
}
