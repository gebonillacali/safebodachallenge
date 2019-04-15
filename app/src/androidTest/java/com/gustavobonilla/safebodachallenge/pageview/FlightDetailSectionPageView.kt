package com.gustavobonilla.safebodachallenge.pageview

import com.gustavobonilla.safebodachallenge.R
import com.gustavobonilla.safebodachallenge.pageview.PageElement.Companion.NO_ID

/**
 * Represents the items in Main Section Page.
 */
class FlightDetailSectionPageView: PageView {

    enum class FlightDetailSectionViewElements(val resId: Int): PageElement {
        TITLE(R.id.cityTitle) {
            override fun getIdRes(): Int = resId
        },
        GOOGLE_MAP(NO_ID) {
            override fun getIdRes(): Int = resId
            override fun getContentDescription(): String = GOOGLE_MAP_DESCRIPTION
        },
        CITY_NAME(R.id.cityName) {
            override fun getIdRes(): Int = resId
        },
        AIRPORT_CODE(R.id.airportCode) {
            override fun getIdRes(): Int = resId
        }
    }

    companion object {
        const val GOOGLE_MAP_DESCRIPTION = "Google Maps"
    }
}