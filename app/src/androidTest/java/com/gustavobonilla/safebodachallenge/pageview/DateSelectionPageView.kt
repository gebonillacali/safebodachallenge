package com.gustavobonilla.safebodachallenge.pageview

import com.gustavobonilla.safebodachallenge.R

/**
 * Represents the items in CitySelection Page.
 */
class DateSelectionPageView: PageView {

    enum class DateSelectionElements(val resId: Int): PageElement {
        DATE_FLIGHT_PICKER(R.id.dateFlight) {
            override fun getIdRes(): Int = resId
        },
        ACCEPT_BUTTON(android.R.id.button1) {
            override fun getIdRes(): Int = resId
        }
    }
}