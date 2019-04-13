package com.gustavobonilla.safebodachallenge.presentation.viewmodel

import com.google.gson.Gson
import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.domain.model.FlightSchedule
import com.gustavobonilla.safebodachallenge.presentation.base.BaseViewModelImpl
import com.gustavobonilla.safebodachallenge.usecases.UseCase
import javax.inject.Inject

class CityViewModel @Inject constructor(private val useCase: UseCase<City, String>): BaseViewModelImpl<City, String>(useCase) {

    lateinit var flightSchedule: FlightSchedule
    lateinit var lastSourceFlightSchedule: String
    var finishRequesting = false

    fun notifyTappedCity(city: City) {
        publisher.onNext(city)
    }

    override fun getData(parameters: String) {
        if (shouldPerformParsing(parameters)) {
            lastSourceFlightSchedule = parameters
            flightSchedule = Gson().fromJson(parameters, FlightSchedule::class.java)
        }
        val airports = mutableListOf<String>()
        airports.addAll(flightSchedule.flights.map { it.departureAirport })
        airports.add(flightSchedule.flights.last().arrivalAirport)

        obtainCities(airports)
    }

    private fun shouldPerformParsing(parameters: String) =
            !this::flightSchedule.isInitialized || (this::lastSourceFlightSchedule.isInitialized && lastSourceFlightSchedule != parameters)

    private fun obtainCities(list: List<String>) {
        if (list.isNotEmpty()) {
            val flight = list.first()
            useCase.execute(flight, {
                publisher.onNext(it)
                obtainCities(list.subList(1, list.size))
            }, {
                publisherError.onNext(it)
                obtainCities(list.subList(1, list.size))
            })
        } else {
            finishRequesting = true
            publisher.onNext(City.createDummyCity())
        }
    }
}