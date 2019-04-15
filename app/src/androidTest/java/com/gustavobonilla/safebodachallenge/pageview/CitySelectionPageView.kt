package com.gustavobonilla.safebodachallenge.pageview
import com.gustavobonilla.safebodachallenge.R

/**
 * Represents the items in CitySelection Page.
 */
class CitySelectionPageView: PageView {

    enum class CitySelectionElements(val resId: Int): PageElement {
        TITLE(R.id.instructionsText) {
            override fun getIdRes(): Int = resId
        },
        TEXT_SEARCH(R.id.searchText) {
            override fun getIdRes(): Int = resId
        },
        RECYCLER_VIEW(R.id.recyclerView) {
            override fun getIdRes(): Int = resId
        }
    }
}