package com.gustavobonilla.safebodachallenge.domain.model

data class City(val cityCode: String,
                val countryCode: String,
                val cityName: String,
                val position: Coordinate,
                val airportCode: String)