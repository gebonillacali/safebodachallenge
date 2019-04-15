package com.gustavobonilla.safebodachallenge.test

import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import com.gustavobonilla.safebodachallenge.pageview.FlightDetailSectionPageView
import com.gustavobonilla.safebodachallenge.presentation.sections.home.view.HomeActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FlightDetailSectionTest: BaseSectionTest() {

    @Rule
    @JvmField
    var flightScheduleSectionActivityTestRule = object :ActivityTestRule<HomeActivity>(HomeActivity::class.java) {}

    @Before
    override fun init() {
        super.init()
        goToFlightDetail()
    }

    @Test
    fun checkSectionTitle() {
        flightDetailSectionPageView.getViewInteraction(FlightDetailSectionPageView.FlightDetailSectionViewElements.TITLE)
                ?.check(matches(withText("Flight Detail")))

    }
}