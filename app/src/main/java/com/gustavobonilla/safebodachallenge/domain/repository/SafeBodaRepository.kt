package com.gustavobonilla.safebodachallenge.domain.repository

import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.domain.model.FlightSchedule
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface SafeBodaRepository {

    fun checkAuthtoken(action: (Boolean, Disposable?) -> Unit)

    fun updateCities(offset: Int): Observable<Int>

    fun getCities(termSearch: String): Observable<List<City>>

    fun getCityByAirportCode(airportCode: String): Observable<City>

    fun getFlightSchedule(originAirport: String, destinationAirport: String, date: String): Observable<List<FlightSchedule>>
}