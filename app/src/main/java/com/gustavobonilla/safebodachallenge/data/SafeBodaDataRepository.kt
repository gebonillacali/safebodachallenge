package com.gustavobonilla.safebodachallenge.data

import com.gustavobonilla.safebodachallenge.data.api.LuftansaServiceApi
import com.gustavobonilla.safebodachallenge.data.entity.Cities
import com.gustavobonilla.safebodachallenge.data.entity.CityEntity
import com.gustavobonilla.safebodachallenge.data.entity.CityResource
import com.gustavobonilla.safebodachallenge.data.entity.Meta
import com.gustavobonilla.safebodachallenge.data.mapper.CityEntityMapper
import com.gustavobonilla.safebodachallenge.data.mapper.FlightScheduleMapper
import com.gustavobonilla.safebodachallenge.data.room.AppDatabase
import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.domain.model.FlightSchedule
import com.gustavobonilla.safebodachallenge.domain.repository.SafeBodaRepository
import com.gustavobonilla.safebodachallenge.isNotNull
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * Implementation for [SafeBodaRepository] in which obtains the info from API and Local DB.
 */
class SafeBodaDataRepository(private val apiService: LuftansaServiceApi,
                             private val dao: AppDatabase): SafeBodaRepository {

    override fun checkAuthtoken(action: (Boolean, Disposable?) -> Unit) {
        apiService.checkAuthToken(action)
    }

    override fun updateCities(offset: Int): Observable<Int> {
        return apiService.api.getCities(offset = offset)
                .toObservable()
                .onErrorResumeNext(Observable.just(UPDATE_CITIES_DUMMY_ERROR_VALUE))
                .doOnNext {
                    val citiesDao = it.cityResource.cities.city
                            .filter { city -> city.airports.isNotNull() }
                            .map(CityEntityMapper::transformCityToCityDao)
                    dao.getCities().insert(citiesDao)
                }
                .map {
                    it.cityResource.meta.totalCount
                }
    }

    override fun getCities(termSearch: String): Observable<List<City>> {
        return dao.getCities().getAllCitiesByTermSearch(termSearch)
                .map {
                    it.map(CityEntityMapper::transformCityDaoToCityModel)
                }.toObservable()
                .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
    }

    override fun getFlightSchedule(originAirport: String, destinationAirport: String, date: String): Observable<List<FlightSchedule>> {
        return apiService.api.getFlightSchedules(
                originAirport,
                destinationAirport,
                date,
                false).map { flightScheduleEntity ->
            flightScheduleEntity.scheduleResource.schedule.map(FlightScheduleMapper::transform)
        }.toObservable()
    }

    override fun getCityByAirportCode(airportCode: String): Observable<City> {
        return dao.getCities().getCityByAirportCode(airportCode)
                .map(CityEntityMapper::transformCityDaoToCityModel)
                .toObservable()
    }

    companion object {
        private val UPDATE_CITIES_DUMMY_ERROR_VALUE = CityEntity(CityResource(Cities(emptyList()), Meta("", emptyList(), 0)))
        private const val DEBOUNCE_TIME = 2000L
    }
}