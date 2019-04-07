package com.gustavobonilla.safebodachallenge.data.api

import com.gustavobonilla.safebodachallenge.data.entity.AuthToken
import com.gustavobonilla.safebodachallenge.data.entity.CityEntity
import com.gustavobonilla.safebodachallenge.data.entity.CountryResponse
import com.gustavobonilla.safebodachallenge.data.entity.FlightScheduleEntity
import io.reactivex.Single
import retrofit2.http.*

/**
 * The Api for getting All flight information.
 */
interface LuftansaApi {

    @FormUrlEncoded
    @POST("/v1/oauth/token")
    fun getAuthtoken(@FieldMap data: Map<String, String>): Single<AuthToken>

    @GET("/v1/references/countries/{countryCode}")
    fun getCountry(@Path("countryCode") countryCode: String): Single<CountryResponse>

    @GET("/v1/references/cities/")
    fun getCities(@Query("lang") lang: String = "en", @Query("limit") limit: Int = 100, @Query("offset") offset: Int): Single<CityEntity>

    @GET("/v1/operations/schedules/{airportOrigin}/{airportDestination}/{date}")
    fun getFlightSchedules(@Path("airportOrigin") airportOrigin: String,
                           @Path("airportDestination") airportDestination: String,
                           @Path("date") date: String,
                           @Query("directFlights") directFlights: Boolean,
                           @Query("limit") limit: Int = 20,
                           @Query("offset") offset: Int = 0): Single<FlightScheduleEntity>
}