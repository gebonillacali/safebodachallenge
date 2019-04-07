package com.gustavobonilla.safebodachallenge.data.mapper

import com.gustavobonilla.safebodachallenge.data.entity.City
import com.gustavobonilla.safebodachallenge.data.entity.CityDaoEntity
import com.gustavobonilla.safebodachallenge.domain.model.Coordinate
import com.gustavobonilla.safebodachallenge.getAttribute

object CityEntityMapper {
    fun transformCityToCityDao(city: City): CityDaoEntity {
        return CityDaoEntity(
                city.cityCode,
                city.airports.getAttribute({city.airports.airportCodes.joinToString { airport ->  airport }},""),
                city.names.name.value,
                city.position.coordinate.getAttribute({city.position.coordinate.latitude},0.0),
                city.position.coordinate.getAttribute({city.position.coordinate.longitude},0.0))
    }

    fun transformCityDaoToCityModel(cityDao: CityDaoEntity): com.gustavobonilla.safebodachallenge.domain.model.City {
        return com.gustavobonilla.safebodachallenge.domain.model.City(
                cityDao.cityCode,
                "",
                cityDao.cityName,
                Coordinate(cityDao.latitude, cityDao.longitude),
                cityDao.airportCode)
    }
}