package com.gustavobonilla.safebodachallenge.test

import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import com.gustavobonilla.safebodachallenge.pageview.FlightScheduleSectionPageView
import com.gustavobonilla.safebodachallenge.pageview.HomeSectionPageView
import com.gustavobonilla.safebodachallenge.presentation.sections.home.view.HomeActivity
import org.junit.Rule
import org.junit.Test

class HomeSectionTest: BaseSectionTest() {

    @Rule
    @JvmField
    var homeSectionActivityTestRule = object :ActivityTestRule<HomeActivity>(HomeActivity::class.java) {}

    @Test
    fun checkOriginAirportTest() {
        selectCityFromSearch(HomeSectionPageView.HomeSectionElements.ORIGIN_AIRPORT, "Barcelona")
        Thread.sleep(WAIT_SHORT_TIME)
        homeSectionPageView.getViewInteraction(HomeSectionPageView.HomeSectionElements.AIRPORT_CODE, "BCN")
                ?.check(matches(isDisplayed()))
    }

    @Test
    fun checkDestinationAirportTest() {
        selectCityFromSearch(HomeSectionPageView.HomeSectionElements.DESTINATION_AIRPORT, "Miami")
        Thread.sleep(WAIT_SHORT_TIME)
        homeSectionPageView.getViewInteraction(HomeSectionPageView.HomeSectionElements.AIRPORT_CODE, "MIA")
                ?.check(matches(isDisplayed()))
    }

    @Test
    fun checkDateFlightTest() {
        selectDateFlight()
        Thread.sleep(WAIT_SHORT_TIME)
        homeSectionPageView.getViewInteraction(HomeSectionPageView.HomeSectionElements.DAY_NUMBER)
                ?.check(matches(withText("11 MAY")))
    }

    @Test
    fun checkHomeSectionTest() {
        selectCityFromSearch(HomeSectionPageView.HomeSectionElements.ORIGIN_AIRPORT, "Barcelona")
        selectCityFromSearch(HomeSectionPageView.HomeSectionElements.DESTINATION_AIRPORT, "Miami")
        selectDateFlight()
        homeSectionPageView.getViewInteraction(HomeSectionPageView.HomeSectionElements.SEARCH_FLIGHTS_BUTTON)
                ?.perform(click())
        Thread.sleep(WAIT_TIME)
        flightScheduleSectionPageView.getViewInteraction(FlightScheduleSectionPageView.FlightScheduleSectionElements.INSTRUCTIONS_TEXT)
                ?.check(matches(isDisplayed()))
    }
}