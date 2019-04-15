package com.gustavobonilla.safebodachallenge.pageview

import com.gustavobonilla.safebodachallenge.R

class FlightScheduleSectionPageView: PageView {
    enum class FlightScheduleSectionElements(val resId: Int): PageElement {
        INSTRUCTIONS_TEXT(R.id.instructionsText) {
            override fun getIdRes(): Int = resId
        },
        RECYCLER_VIEW(R.id.recyclerView) {
            override fun getIdRes(): Int = resId
        },
        ORIGIN_AIRPORT(R.id.originAirport) {
            override fun getIdRes(): Int = resId
        },
        DESTINATION_AIRPORT(R.id.destinationAirport) {
            override fun getIdRes(): Int = resId
        }
    }
}