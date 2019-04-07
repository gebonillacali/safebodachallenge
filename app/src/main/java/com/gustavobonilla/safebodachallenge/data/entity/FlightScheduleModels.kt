package com.gustavobonilla.safebodachallenge.data.entity
import com.google.gson.annotations.SerializedName

data class FlightScheduleEntity(
    @SerializedName("ScheduleResource")
    val scheduleResource: ScheduleResource
)

data class ScheduleResource(
    @SerializedName("Meta")
    val meta: Meta,
    @SerializedName("Schedule")
    val schedule: List<Schedule>
)

data class Schedule(
    val flights: List<Flight>,
    val totalJourney: TotalJourney
)

data class TotalJourney(
    @SerializedName("Duration")
    val duration: String
)

data class Flight(
    @SerializedName("Arrival")
    val arrival: Destination,
    @SerializedName("Departure")
    val departure: Destination,
    @SerializedName("Details")
    val details: Details,
    @SerializedName("Equipment")
    val equipment: Equipment,
    @SerializedName("MarketingCarrier")
    val marketingCarrier: MarketingCarrier
)

data class Equipment(
    @SerializedName("AircraftCode")
    val aircraftCode: String,
    @SerializedName("OnBoardEquipment")
    val onBoardEquipment: OnBoardEquipment
)

data class OnBoardEquipment(
    @SerializedName("Compartment")
    val compartment: List<Compartment>,
    @SerializedName("InflightEntertainment")
    val inflightEntertainment: Boolean
)

data class Compartment(
    @SerializedName("ClassCode")
    val classCode: String,
    @SerializedName("ClassDesc")
    val classDesc: String,
    @SerializedName("FlyNet")
    val flyNet: Boolean,
    @SerializedName("LiveTv")
    val liveTv: Boolean,
    @SerializedName("SeatPower")
    val seatPower: Boolean,
    @SerializedName("Usb")
    val usb: Boolean
)

data class Terminal(
    @SerializedName("Name")
    val name: Int
)

data class ScheduledTimeLocal(
    @SerializedName("DateTime")
    val dateTime: String
)

data class Destination(
        @SerializedName("AirportCode")
        val airportCode: String,
        @SerializedName("ScheduledTimeLocal")
        val scheduledTimeLocal: ScheduledTimeLocal,
        @SerializedName("Terminal")
        val terminal: Terminal
)

data class MarketingCarrier(
    @SerializedName("AirlineID")
    val airlineID: String,
    @SerializedName("FlightNumber")
    val flightNumber: Int
)

data class Details(
    @SerializedName("DatePeriod")
    val datePeriod: DatePeriod,
    @SerializedName("DaysOfOperation")
    val daysOfOperation: Int,
    @SerializedName("Stops")
    val stops: Stops
)

data class DatePeriod(
    @SerializedName("Effective")
    val effective: String,
    @SerializedName("Expiration")
    val expiration: String
)

data class Stops(
    @SerializedName("StopQuantity")
    val stopQuantity: Int
)