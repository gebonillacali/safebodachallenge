package com.gustavobonilla.safebodachallenge.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Defines the Auth token for performing request to API.
 */
data class AuthToken(
        @SerializedName("access_token")
        val accessToken: String,
        @SerializedName("token_type")
        val tokenType: String,
        @SerializedName("expires_in")
        val expiresIn: Long)