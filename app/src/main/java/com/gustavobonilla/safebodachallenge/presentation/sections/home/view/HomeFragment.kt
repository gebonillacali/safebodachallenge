package com.gustavobonilla.safebodachallenge.presentation.sections.home.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.gustavobonilla.safebodachallenge.R
import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.isNotNull
import com.gustavobonilla.safebodachallenge.presentation.SafeBodaChallengeApplication
import com.gustavobonilla.safebodachallenge.presentation.base.BaseFragment
import com.gustavobonilla.safebodachallenge.presentation.navigation.Navigation
import com.gustavobonilla.safebodachallenge.presentation.sections.flightresult.view.FlightResultFragment
import com.gustavobonilla.safebodachallenge.presentation.sections.home.di.HomeModule
import com.gustavobonilla.safebodachallenge.presentation.util.DatePickerControlImpl
import com.gustavobonilla.safebodachallenge.presentation.util.DialogSearchCity
import com.gustavobonilla.safebodachallenge.presentation.viewmodel.SearchCitiesViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : BaseFragment<@JvmSuppressWildcards List<City>, String>() {
    private var currentOriginCity = ""
    private var currentDestinationCity = ""
    private var currentDate = ""

    //region [Fragment] Lifecycle
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialValues()
        setListeners()
    }
    //endregion

    //region [BaseView] Impl
    override fun subscribeErrorListener(): ((Throwable) -> Unit)? {
        return {
            Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    override fun subscribeListener(): (data: List<City>) -> Unit {
        return { _ ->

        }
    }

    override fun getLayoutResource(): Int = R.layout.fragment_home

    override fun inject() {
        activity?.let {
            (it.application as SafeBodaChallengeApplication)
                    .safeBodaChallengeApplicationComponent
                    .plus(HomeModule(it))
                    .inject(this)
        }
    }
    //endregion

    //region private impl
    private fun setInitialValues() {
        setAirportInitialValues(originAirport, "originAirportCode", "originCityName")
        setAirportInitialValues(destinationAirport, "destinationAirportCode", "destinationCityName")
        setDateInControl()
    }

    private fun setAirportInitialValues(airportView: View, keyForAirport: String, keyForCityName: String) {
        val airportCode = viewModel.retreiveInMemoryValues<String>(keyForAirport) ?: ""
        val cityName = viewModel.retreiveInMemoryValues<String>(keyForCityName) ?: ""
        if (airportCode.isNotEmpty() && cityName.isNotEmpty()) {
            setAirportValues(airportView, airportCode, cityName)
        }
    }

    private fun setListeners() {
        originAirport.setOnClickListener {
            DialogSearchCity(context!!, viewModel as SearchCitiesViewModel, currentOriginCity) {
                currentOriginCity = it.cityName
                viewModel.saveInMemoryValues("originAirportCode", it.airportCode)
                viewModel.saveInMemoryValues("originCityName", it.cityName)
                setAirportValues(originAirport, it.airportCode, it.cityName)
            }.show()
        }

        destinationAirport.setOnClickListener {
            DialogSearchCity(context!!, viewModel as SearchCitiesViewModel, currentDestinationCity) {
                currentDestinationCity = it.cityName
                viewModel.saveInMemoryValues("destinationAirportCode", it.airportCode)
                viewModel.saveInMemoryValues("destinationCityName", it.cityName)
                setAirportValues(destinationAirport, it.airportCode, it.cityName)
            }.show()
        }

        dateFlight.setOnClickListener {
            DatePickerControlImpl(WeakReference(it.context),
                    DatePickerControlImpl.getCurrentCalendar(currentDate)) { date ->
                currentDate = date
                viewModel.saveInMemoryValues("flightDate", date)
                setDateInControl()
            }.show()
        }

        btnSearch.setOnClickListener {
            activity?.let {
                val originAirportCode = originAirport.findViewById<TextView>(R.id.airportCode).text.toString()
                val destinationAirportCode = destinationAirport.findViewById<TextView>(R.id.airportCode).text.toString()

                if (originAirportCode.isEmpty() || originAirportCode == "AIR" ||
                        destinationAirportCode.isEmpty() || destinationAirportCode == "AIR") {
                    Toast.makeText(context, "Please select 2 airports", Toast.LENGTH_LONG).show()
                    return@let
                }

                Navigation.replaceFragment(
                        it.supportFragmentManager,
                        R.id.homeFragmentContainer,
                        FlightResultFragment.newInstance(
                                originAirportCode,
                                destinationAirportCode,
                                currentDate
                        ))
            }
        }
    }

    private fun setAirportValues(airportView: View, airportCode: String, cityName: String) {
        airportView.findViewById<TextView>(R.id.airportCode).text = airportCode
        airportView.findViewById<TextView>(R.id.cityName).text = cityName
    }

    private fun setDateInControl() {
        val date = viewModel.retreiveInMemoryValues<String>("flightDate") ?: ""
        val dateValue = if (date.isNotEmpty())
            date
        else
            SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(Calendar.getInstance().time)
        val (dayNumber, dateInfo) = getFormatedDate(dateValue)
        dateFlight.findViewById<TextView>(R.id.dayNumber).text = dayNumber
        dateFlight.findViewById<TextView>(R.id.dateInfo).text = dateInfo
    }

    private fun getFormatedDate(date: String): Pair<String, String> {
        val dateParse = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date)
        val dayNumber = SimpleDateFormat("dd MMM", Locale.ENGLISH).format(dateParse).toUpperCase()
        val dateInfo = SimpleDateFormat("EE. dd MMM yyyy", Locale.ENGLISH).format(dateParse).toLowerCase()
        return Pair(dayNumber, dateInfo)
    }

    //endregion

    companion object {
        fun newInstance() = HomeFragment().apply { retainInstance = true }
    }
}
