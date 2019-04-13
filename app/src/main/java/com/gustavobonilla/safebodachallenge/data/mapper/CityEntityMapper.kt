package com.gustavobonilla.safebodachallenge.data.mapper

import com.gustavobonilla.safebodachallenge.data.entity.City
import com.gustavobonilla.safebodachallenge.data.entity.CityDaoEntity
import com.gustavobonilla.safebodachallenge.domain.model.Coordinate
import com.gustavobonilla.safebodachallenge.getAttribute
import com.gustavobonilla.safebodachallenge.domain.model.City as CityModel

/**
 * Maps the data from the Api with the models in domain layer and vice versa.
 */
object CityEntityMapper {

    /**
     * Transforms the API [City] model to the DB [CityDaoEntity].
     *
     * @param city the API [City] object.
     *
     * @return the [CityDaoEntity] object transformed from the [City].
     */
    fun transformCityToCityDao(city: City): CityDaoEntity {
        return CityDaoEntity(
                city.cityCode,
                city.airports.getAttribute({city.airports.airportCodes.joinToString { airport ->  airport }},""),
                city.names.name.value,
                city.position.coordinate.getAttribute({city.position.coordinate.latitude},0.0),
                city.position.coordinate.getAttribute({city.position.coordinate.longitude},0.0))
    }

    /**
     * Transforms the DB [CityDaoEntity] to the domain layer [City] model.
     *
     * @param cityDao the [CityDaoEntity] object.
     *
     * @return the [CityModel] transformed from the [CityDaoEntity].
     */
    fun transformCityDaoToCityModel(cityDao: CityDaoEntity): CityModel {
        return CityModel(
                cityDao.cityCode,
                "",
                cityDao.cityName,
                Coordinate(cityDao.latitude, cityDao.longitude),
                cityDao.airportCode)
    }
}