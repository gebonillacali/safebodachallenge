package com.gustavobonilla.safebodachallenge.presentation.util

import android.content.SharedPreferences

class AppPreferencesImpl(private val sharedPreferences: SharedPreferences) {

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

    fun getPreferences(): SharedPreferences = sharedPreferences

    enum class Keys(val value: String) {
        CITY_FIRST_LOAD("com.gustavobonilla.safebodachallenge.presentation.util.city.first.load")
    }

    companion object {
        const val FILENAME_APP_PREFERENCES = "safeboda_preferences"
    }
}