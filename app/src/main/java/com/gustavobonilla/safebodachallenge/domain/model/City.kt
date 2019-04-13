package com.gustavobonilla.safebodachallenge.domain.model

/**
 * Represents a [City] in the app.
 */
data class City(val cityCode: String,
                val countryCode: String,
                val cityName: String,
                val position: Coordinate,
                val airportCode: String) {
    companion object {
        fun createDummyCity() = City("", "", "", Coordinate(0.0,0.0), "")
    }
}


