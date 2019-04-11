package com.gustavobonilla.safebodachallenge.presentation.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.SupportActivity

object Navigation {
    /**
     * Navigates to an [SupportActivity]
     *
     * @param context the given context from starting the next activity.
     * @param clazz the next activity class.
     * @param bundle the [Bundle] with data requested by Map.
     * @param finishCurrentActivity indicates if the current activity must finished or not.
     */
    fun navigateToActivity(context: Context, clazz: Class<out SupportActivity>, bundle: Bundle? = null, finishCurrentActivity: Boolean = true) {
        val intent = Intent(context, clazz)
        bundle?.let {
            intent.putExtras(it)
        }
        context.startActivity(intent)
        if (context is SupportActivity && finishCurrentActivity) {
            context.finish()
        }
    }

    /**
     * Adds a [Fragment] in the backstack in the [FragmentManager]
     *
     * @param fragment the fragment to be added.
     */
    fun addFragment(supportFragmentManager: FragmentManager, @IdRes idResContainer: Int, fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(idResContainer, fragment)
        transaction.commit()
    }

    /**
     * Replace a [Fragment] in the backstack in the [FragmentManager]
     *
     * @param fragment the fragment to be added.
     */
    fun replaceFragment(supportFragmentManager: FragmentManager, @IdRes idResContainer: Int, fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(idResContainer, fragment).addToBackStack(Fragment::class.java.simpleName)
        transaction.commit()
    }
}