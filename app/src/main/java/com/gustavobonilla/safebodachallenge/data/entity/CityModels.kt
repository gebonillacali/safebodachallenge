package com.gustavobonilla.safebodachallenge.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.gustavobonilla.safebodachallenge.domain.model.City as CityModel
import com.gustavobonilla.safebodachallenge.domain.model.Coordinate as CoordinateModel

//region Dao Entities
@Entity(tableName = "cities")
data class CityDaoEntity(
        @PrimaryKey
        val cityCode: String,
        val airportCode: String,
        val cityName: String,
        val latitude: Double,
        val longitude: Double)
//endregion

//region Api Entities
data class CityEntity(
    @SerializedName("CityResource")
    val cityResource: CityResource
)

data class CityResource(
    @SerializedName("Cities")
    val cities: Cities,
    @SerializedName("Meta")
    val meta: Meta
)

data class Cities(
    @SerializedName("City")
    val city: List<City>
)

data class City(
    @SerializedName("Airports")
    val airports: Airports,
    @SerializedName("CityCode")
    val cityCode: String,
    @SerializedName("CountryCode")
    val countryCode: String,
    @SerializedName("Names")
    val names: Names,
    @SerializedName("Position")
    val position: Position
)

data class Position(
    @SerializedName("Coordinate")
    val coordinate: Coordinate
)

data class Coordinate(
    @SerializedName("Latitude")
    val latitude: Double,
    @SerializedName("Longitude")
    val longitude: Double
)

data class Airports(
    val airportCodes: List<String>
)
//endregion

