package com.gustavobonilla.safebodachallenge.domain.repository

import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.domain.model.FlightSchedule
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface SafeBodaRepository {

    /**
     * Check if the auth token exists if not tries to retreive one.
     *
     * @param action function that will be executed once the operation of retrieving an auth token is performed.
     */
    fun checkAuthtoken(action: (Boolean, Disposable?) -> Unit)

    /**
     * Stores and Updates the cities from API into the local DB.
     *
     * @param offset the offset to request and subsequently store in local DB.
     *
     * @return An [Observable] of [Int] that denotes the total of records to retrieve.
     */
    fun updateCities(offset: Int): Observable<Int>

    /**
     * Gets cities regarding the values written in termsearch string that match with the name of a city.
     *
     * @param termSearch the term for searching the cities.
     *
     * @return An [Observable] of [List] of [City] if the termSearch match the records in DB, otherwise an [Observable] with an empty [List].
     */
    fun getCities(termSearch: String): Observable<List<City>>

    /**
     * Gets a city by the Airport code given.
     *
     * @param airportCode the Code of the airport to retrieve the city.
     *
     * @return An [Observable] of [City] with the city that match for the given airportCode.
     */
    fun getCityByAirportCode(airportCode: String): Observable<City>

    /**
     * Gets the Flight schedules regarding the origin, destination airport and date.
     *
     * @param originAirport the origin airport.
     * @param destinationAirport the destination airport.
     * @param date the date YYYY-MM-DD or YYYY-MM-DDTHH:mm.
     *
     * @return An [Observable] of [List] of [FlightSchedule] objects that denotes the result of search.
     */
    fun getFlightSchedule(originAirport: String, destinationAirport: String, date: String): Observable<List<FlightSchedule>>
}