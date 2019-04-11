package com.gustavobonilla.safebodachallenge.presentation.viewmodel

import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.isNotNull
import com.gustavobonilla.safebodachallenge.presentation.base.BaseViewModelImpl
import com.gustavobonilla.safebodachallenge.usecases.GetCityByAirportCode
import com.gustavobonilla.safebodachallenge.usecases.UseCase
import javax.inject.Inject

class SearchCitiesViewModel @Inject constructor(private val useCase: UseCase<@JvmSuppressWildcards List<City>, String>):
        BaseViewModelImpl<List<City>, String>(useCase) {

    override fun getData(parameters: String) {
        useCase.execute("%$parameters%", { cities ->
            val listMultipleAirports = cities.filter { it.airportCode.contains(",") }.map { city ->
                city.airportCode.split(",").map {
                    City(city.cityCode, city.countryCode, city.cityName, city.position, it.trim())
                }
            }.flatten()
            val listOneAirport = cities.filter { !it.airportCode.contains(",")}
            val mutableList = mutableListOf<City>()
            mutableList.addAll(listMultipleAirports)
            mutableList.addAll(listOneAirport)
            publisher.onNext(mutableList)
        }, {
            publisherError.onNext(it)
        })
    }
}