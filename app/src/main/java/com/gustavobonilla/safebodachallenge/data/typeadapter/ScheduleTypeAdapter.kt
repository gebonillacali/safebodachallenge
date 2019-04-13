package com.gustavobonilla.safebodachallenge.data.typeadapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.gustavobonilla.safebodachallenge.data.entity.Flight
import com.gustavobonilla.safebodachallenge.data.entity.Schedule
import com.gustavobonilla.safebodachallenge.data.entity.TotalJourney
import java.lang.reflect.Type

/**
 * Helps to tell [Retrofit] and [Gson] how to deserialize the json response for [Schedule] object.
 */
class ScheduleTypeAdapter : JsonDeserializer<Schedule> {
    override fun deserialize(scheduleElement: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Schedule {
        val jsonObject = scheduleElement.asJsonObject
        var totalJourney = TotalJourney("0")
        val flightList = mutableListOf<Flight>()
        if (jsonObject.has("TotalJourney")) {
            jsonObject?.get("TotalJourney")?.let { totalJourneyElement ->
                totalJourney = context.deserialize(totalJourneyElement, TotalJourney::class.java) as TotalJourney
            }
        }

        if (jsonObject.has("Flight")) {
            jsonObject?.get("Flight")?.let { flightElement ->
                if (flightElement.isJsonArray) {
                    flightList.addAll(flightElement.asJsonArray.map {
                        context.deserialize(it, Flight::class.java) as Flight
                    })
                } else {
                    flightList.add(context.deserialize(flightElement.asJsonObject, Flight::class.java) as Flight)
                }
            }
        }
        return Schedule(flightList, totalJourney)
    }
}