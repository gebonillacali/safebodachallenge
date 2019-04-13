package com.gustavobonilla.safebodachallenge.data.mapper

import com.gustavobonilla.safebodachallenge.ModelCreator
import org.junit.Assert
import org.junit.Test

class CityEntityMapperTest {

    @Test
    fun transformCityToCityDao() {
        val city = ModelCreator.cityEntity.cityResource.cities.city[0]
        val cityDaoEntity = CityEntityMapper.transformCityToCityDao(city)
        Assert.assertEquals(city.cityCode, cityDaoEntity.cityCode)
        Assert.assertEquals(city.airports.airportCodes.joinToString { it }, cityDaoEntity.airportCode)
        Assert.assertEquals(city.names.name.value, cityDaoEntity.cityName)
    }

    @Test
    fun transformCityDaoToCityModel() {
        val city = ModelCreator.cityEntity.cityResource.cities.city[0]
        val cityDaoEntity = CityEntityMapper.transformCityToCityDao(city)
        val cityModel = CityEntityMapper.transformCityDaoToCityModel(cityDaoEntity)
        Assert.assertEquals(cityDaoEntity.cityName, cityModel.cityName)
        Assert.assertEquals(cityDaoEntity.airportCode, cityModel.airportCode)
        Assert.assertEquals(cityDaoEntity.latitude, cityModel.position.latitude, 0.0)
        Assert.assertEquals(cityDaoEntity.longitude, cityModel.position.longitude, 0.0)
        Assert.assertEquals(cityDaoEntity.cityCode, cityModel.cityCode)
    }
}