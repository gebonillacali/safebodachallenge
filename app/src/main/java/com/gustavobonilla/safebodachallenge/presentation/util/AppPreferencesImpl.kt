package com.gustavobonilla.safebodachallenge.presentation.util

import android.content.SharedPreferences

/**
 * Stores information in a persistent place by using the [SharedPreferences] class.
 */
class AppPreferencesImpl(private val sharedPreferences: SharedPreferences) {

    /**
     * Sets a value that should be stored for later use.
     *
     * @param key the key that will associated the value.
     */
    fun setValue(key: Keys, value: Any) {
        val edit = sharedPreferences.edit()
        when (value) {
            is Boolean -> edit.putBoolean(key.value, value)
            is Int -> edit.putInt(key.value, value)
            is Float -> edit.putFloat(key.value, value)
            is String -> edit.putString(key.value, value)
        }
        edit.apply()
    }

    /**
     * Obtains the [SharedPreferences] object that contains all persisted values.
     *
     * @return the [SharedPreferences] object.
     */
    fun getPreferences(): SharedPreferences = sharedPreferences

    /**
     * Defines all the keys that will be used for the persistent values.
     */
    enum class Keys(val value: String) {
        CITY_FIRST_LOAD("com.gustavobonilla.safebodachallenge.presentation.util.city.first.load")
    }

    companion object {
        const val FILENAME_APP_PREFERENCES = "safeboda_preferences"
    }
}