package com.gustavobonilla.safebodachallenge.test

import android.support.test.espresso.action.TypeTextAction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.contrib.PickerActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.widget.DatePicker
import com.gustavobonilla.safebodachallenge.pageview.*
import com.gustavobonilla.safebodachallenge.presentation.adapter.viewholder.CityViewHolder
import com.gustavobonilla.safebodachallenge.presentation.adapter.viewholder.FlightScheduleViewHolder
import org.junit.Before
import java.util.*

abstract class BaseSectionTest {

    private lateinit var citySelectionPageView: CitySelectionPageView
    private lateinit var dateSectionPageView: DateSelectionPageView
    protected lateinit var homeSectionPageView: HomeSectionPageView
    protected lateinit var flightScheduleSectionPageView: FlightScheduleSectionPageView
    protected lateinit var flightDetailSectionPageView: FlightDetailSectionPageView

    @Before
    open fun init() {
        homeSectionPageView = HomeSectionPageView()
        citySelectionPageView = CitySelectionPageView()
        dateSectionPageView = DateSelectionPageView()
        flightScheduleSectionPageView = FlightScheduleSectionPageView()
        flightDetailSectionPageView = FlightDetailSectionPageView()
    }

    protected fun goToFlightDetail() {
        goToFlightSchedule()
        clickAndOpenMap()
        Thread.sleep(WAIT_TIME)
    }

    protected fun goToFlightSchedule() {
        selectCityFromSearch(HomeSectionPageView.HomeSectionElements.ORIGIN_AIRPORT, "Barcelona")
        selectCityFromSearch(HomeSectionPageView.HomeSectionElements.DESTINATION_AIRPORT, "Miami")
        selectDateFlight()
        homeSectionPageView.getViewInteraction(HomeSectionPageView.HomeSectionElements.SEARCH_FLIGHTS_BUTTON)
                ?.perform(ViewActions.click())
        Thread.sleep(WAIT_TIME)
    }

    private fun clickAndOpenMap() {
        val recyclerViewInteraction = flightScheduleSectionPageView.getViewInteraction(FlightScheduleSectionPageView.FlightScheduleSectionElements.RECYCLER_VIEW)
        recyclerViewInteraction?.perform(RecyclerViewActions.actionOnItemAtPosition<FlightScheduleViewHolder>(0, ViewActions.click()))
        Thread.sleep(WAIT_TIME)
    }

    protected fun selectCityFromSearch(pageElement: PageElement, searchText: String) {
        homeSectionPageView.getViewInteraction(pageElement)
                ?.perform(ViewActions.click())
        Thread.sleep(WAIT_SHORT_TIME)
        citySelectionPageView.getViewInteraction(CitySelectionPageView.CitySelectionElements.TEXT_SEARCH)
                ?.perform(ViewActions.clearText(), TypeTextAction(searchText))
        val recyclerViewInteraction = citySelectionPageView.getViewInteraction(CitySelectionPageView.CitySelectionElements.RECYCLER_VIEW)
        recyclerViewInteraction?.perform(RecyclerViewActions.actionOnItemAtPosition<CityViewHolder>(0, ViewActions.click()))
        Thread.sleep(WAIT_SHORT_TIME)
    }

    protected fun selectDateFlight() {
        homeSectionPageView.getViewInteraction(HomeSectionPageView.HomeSectionElements.DATE_FLIGHT)
                ?.perform(ViewActions.click())
        val year = Calendar.getInstance().get(Calendar.YEAR)
        dateSectionPageView.getViewInteraction(DateSelectionPageView.DateSelectionElements.DATE_FLIGHT_PICKER, DatePicker::class.java)
                ?.perform(PickerActions.setDate(year, 5, 11))
        dateSectionPageView.getViewInteraction(DateSelectionPageView.DateSelectionElements.ACCEPT_BUTTON)
                ?.perform(ViewActions.click())
    }

    companion object {
        const val WAIT_SHORT_TIME = 300L
        const val WAIT_TIME = 6000L
    }
}