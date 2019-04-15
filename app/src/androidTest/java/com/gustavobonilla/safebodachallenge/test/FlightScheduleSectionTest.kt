package com.gustavobonilla.safebodachallenge.test

import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import com.gustavobonilla.safebodachallenge.pageview.FlightDetailSectionPageView
import com.gustavobonilla.safebodachallenge.pageview.FlightScheduleSectionPageView
import com.gustavobonilla.safebodachallenge.presentation.adapter.viewholder.FlightScheduleViewHolder
import com.gustavobonilla.safebodachallenge.presentation.sections.home.view.HomeActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FlightScheduleSectionTest: BaseSectionTest() {

    @Rule
    @JvmField
    var flightScheduleSectionActivityTestRule = object :ActivityTestRule<HomeActivity>(HomeActivity::class.java) {}

    @Before
    override fun init() {
        super.init()
        goToFlightSchedule()
    }

    @Test
    fun checkOriginAirportTest() {
        flightScheduleSectionPageView.getItemInRecycler(
                FlightScheduleSectionPageView.FlightScheduleSectionElements.RECYCLER_VIEW,
                0,
                "BCN")
        flightScheduleSectionPageView.getItemInRecycler(
                FlightScheduleSectionPageView.FlightScheduleSectionElements.RECYCLER_VIEW,
                1,
                "BCN")
    }

    @Test
    fun checkDestinationAirportTest() {
        flightScheduleSectionPageView.getItemInRecycler(
                FlightScheduleSectionPageView.FlightScheduleSectionElements.RECYCLER_VIEW,
                0,
                "MIA")
        flightScheduleSectionPageView.getItemInRecycler(
                FlightScheduleSectionPageView.FlightScheduleSectionElements.RECYCLER_VIEW,
                1,
                "MIA")
    }

    @Test
    fun clickAndOpenMapTest() {
        val recyclerViewInteraction = flightScheduleSectionPageView.getViewInteraction(FlightScheduleSectionPageView.FlightScheduleSectionElements.RECYCLER_VIEW)
        recyclerViewInteraction?.perform(RecyclerViewActions.actionOnItemAtPosition<FlightScheduleViewHolder>(0, ViewActions.click()))
        flightDetailSectionPageView.getViewInteraction(FlightDetailSectionPageView.FlightDetailSectionViewElements.GOOGLE_MAP)?.check(matches(ViewMatchers.isDisplayed()))
        Thread.sleep(WAIT_TIME)
    }
}