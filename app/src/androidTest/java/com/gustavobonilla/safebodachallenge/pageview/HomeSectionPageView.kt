package com.gustavobonilla.safebodachallenge.pageview

import com.gustavobonilla.safebodachallenge.R

class HomeSectionPageView: PageView {
    enum class HomeSectionElements(val resId: Int): PageElement {
        AIRPORT_CODE(R.id.airportCode) {
            override fun getIdRes(): Int = resId
        },
        DAY_NUMBER(R.id.dayNumber) {
            override fun getIdRes(): Int = resId
        },
        ORIGIN_AIRPORT(R.id.originAirport) {
            override fun getIdRes(): Int = resId
        },
        DESTINATION_AIRPORT(R.id.destinationAirport) {
            override fun getIdRes(): Int = resId
        },
        DATE_FLIGHT(R.id.dateFlight) {
            override fun getIdRes(): Int = resId
        },
        SEARCH_FLIGHTS_BUTTON(R.id.btnSearch) {
            override fun getIdRes(): Int = resId
        }
    }
}