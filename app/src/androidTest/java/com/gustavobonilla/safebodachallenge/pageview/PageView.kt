package com.gustavobonilla.safebodachallenge.pageview

import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView
import com.gustavobonilla.safebodachallenge.atPosition
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf

/**
 * Defines the behavior of a [PageView]
 */
interface PageView {

    /**
     * Gets the [ViewInteraction] which is the reference of a view in the app to perform validations
     * and actions.
     */
    fun getViewInteraction(pageElement: PageElement): ViewInteraction? {
        return when {
            pageElement.getIdRes() > 0 -> Espresso.onView(allOf(ViewMatchers.withId(pageElement.getIdRes()), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
            pageElement.getIdRes() == PageElement.NO_ID -> Espresso.onView(ViewMatchers.withContentDescription(pageElement.getContentDescription()))
            else -> null
        }
    }

    /**
     * Gets the [ViewInteraction] which is the reference of a view in the app to perform validations
     * and actions.
     */
    fun getViewInteraction(pageElement: PageElement, text: String): ViewInteraction? {
        return when {
            pageElement.getIdRes() > 0 -> Espresso.onView(allOf(ViewMatchers.withId(pageElement.getIdRes()), withText(text)))
            pageElement.getIdRes() == PageElement.NO_ID -> Espresso.onView(ViewMatchers.withContentDescription(pageElement.getContentDescription()))
            else -> null
        }
    }

    /**
     * Gets the [ViewInteraction] which is the reference of a view in the app to perform validations
     * and actions.
     */
    fun <T> getViewInteraction(pageElement: PageElement, clazz: Class<T>): ViewInteraction? {
        return when {
            pageElement.getIdRes() > 0 -> Espresso.onView(withClassName(Matchers.equalTo(clazz.name)))
            pageElement.getIdRes() == PageElement.NO_ID -> Espresso.onView(ViewMatchers.withContentDescription(pageElement.getContentDescription()))
            else -> null
        }
    }

    fun getItemInRecycler(recyclerView: PageElement, itemPosition: Int, checkTextValue: String) {
        val recyclerViewInteraction = getViewInteraction(recyclerView)
        recyclerViewInteraction?.run {
            check(matches(atPosition(itemPosition, hasDescendant(withText(checkTextValue)))))
        }
    }
}