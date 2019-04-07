package com.gustavobonilla.safebodachallenge.data.entity

import com.google.gson.annotations.SerializedName

data class Meta(
        @SerializedName("@Version")
        val version: String,
        @SerializedName("Link")
        val link: List<Link>,
        @SerializedName("TotalCount")
        val totalCount: Int
)

data class Link(
        @SerializedName("@Href")
        val href: String,
        @SerializedName("@Rel")
        val rel: String
)

data class Names(
        @SerializedName("Name")
        val name: Name
)

data class Name(
        @SerializedName("$")
        val value: String,
        @SerializedName("@LanguageCode")
        val languageCode: String
)