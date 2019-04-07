package com.gustavobonilla.safebodachallenge.data.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.gustavobonilla.safebodachallenge.data.entity.CityDaoEntity
import com.gustavobonilla.safebodachallenge.data.room.BaseDao.Companion.PAGING
import io.reactivex.Single

@Dao
abstract class CitiesDao: BaseDao<CityDaoEntity> {

    override fun getAll(page: Int): Single<List<CityDaoEntity>> {
        return getAllCities(page)
    }

    @Query(GET_ALL_QUERY)
    abstract fun getAllCities(offset: Int): Single<List<CityDaoEntity>>

    @Query(GET_SEARCH_QUERY)
    abstract fun getAllCitiesByTermSearch(cityName: String): Single<List<CityDaoEntity>>

    @Query(GET_AIRPORT_CODE_QUERY)
    abstract fun getCityByAirportCode(airportCode: String): Single<CityDaoEntity>

    companion object {
        const val GET_ALL_QUERY = "select * from cities limit ((:offset - 1) * $PAGING), $PAGING"
        const val GET_SEARCH_QUERY = "select * from cities where lower(cityName) like lower(:cityName)"
        const val GET_AIRPORT_CODE_QUERY = "select * from cities where airportCode = :airportCode"
    }
}