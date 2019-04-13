package com.gustavobonilla.safebodachallenge.data.entity
import com.google.gson.annotations.SerializedName

/**
 * Defines the model for Country Data Entities.
 */

data class CountryResponse(
    @SerializedName("CountryResource")
    val countryResource: CountryResource
)

data class CountryResource(
    @SerializedName("Countries")
    val countries: Countries,
    @SerializedName("Meta")
    val meta: Meta
)

data class Countries(
    @SerializedName("Country")
    val country: Country
)

data class Country(
    @SerializedName("CountryCode")
    val countryCode: String,
    @SerializedName("Names")
    val names: Names,
    @SerializedName("ZoneCode")
    val zoneCode: String
) {
    val countryName: String = names.name.value
}