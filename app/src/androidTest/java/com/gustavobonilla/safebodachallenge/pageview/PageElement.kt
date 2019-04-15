package com.gustavobonilla.safebodachallenge.pageview

/**
 * Defines a element inside a page.
 */
interface PageElement {
    companion object {
        const val NO_ID = -1
    }

    /**
     * Gets the Resource Id of an Element.
     */
    fun getIdRes(): Int

    /**
     * Returns the content description of an element.
     */
    fun getContentDescription(): String = ""
}