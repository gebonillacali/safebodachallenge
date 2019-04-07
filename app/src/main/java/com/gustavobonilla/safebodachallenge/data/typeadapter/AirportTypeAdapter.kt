package com.gustavobonilla.safebodachallenge.data.typeadapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.gustavobonilla.safebodachallenge.data.entity.Airports
import com.gustavobonilla.safebodachallenge.isNotNull
import java.lang.reflect.Type

class AirportTypeAdapter: JsonDeserializer<Airports> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Airports {
        val jsonObject = json.asJsonObject
        val airportCode = jsonObject.get("AirportCode")
        return if (airportCode.isNotNull()) {
            if (airportCode.isJsonArray) {
                Airports(airportCode.asJsonArray.map { context.deserialize(it, String::class.java) as String })
            } else {
                Airports(listOf(airportCode.asString))
            }
        } else {
            Airports(listOf())
        }
    }
}