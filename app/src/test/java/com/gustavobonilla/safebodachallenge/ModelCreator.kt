package com.gustavobonilla.safebodachallenge

import com.google.gson.GsonBuilder
import com.gustavobonilla.safebodachallenge.data.entity.Airports
import com.gustavobonilla.safebodachallenge.data.entity.CityEntity
import com.gustavobonilla.safebodachallenge.data.entity.FlightScheduleEntity
import com.gustavobonilla.safebodachallenge.data.entity.Schedule
import com.gustavobonilla.safebodachallenge.data.mapper.FlightScheduleMapper
import com.gustavobonilla.safebodachallenge.data.typeadapter.AirportTypeAdapter
import com.gustavobonilla.safebodachallenge.data.typeadapter.ScheduleTypeAdapter

object ModelCreator {
    const val DELAY_TIME_MS = 2000L

    private val gson = GsonBuilder()
            .registerTypeAdapter(Schedule::class.java, ScheduleTypeAdapter())
            .registerTypeAdapter(Airports::class.java, AirportTypeAdapter())
            .create()

    private const val flightScheduleApiResponse = """
{
	"ScheduleResource": {
		"Schedule": [{
			"TotalJourney": {
				"Duration": "P1DT1H29M"
			},
			"Flight": [{
				"Departure": {
					"AirportCode": "TPE",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T01:55"
					},
					"Terminal": {
						"Name": 1
					}
				},
				"Arrival": {
					"AirportCode": "NRT",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T06:15"
					},
					"Terminal": {
						"Name": 3
					}
				},
				"MarketingCarrier": {
					"AirlineID": "GK",
					"FlightNumber": 12
				},
				"Equipment": {
					"AircraftCode": 320
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 145,
					"DatePeriod": {
						"Effective": "2019-05-02",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "NRT",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T10:50"
					},
					"Terminal": {
						"Name": 2
					}
				},
				"Arrival": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T08:50"
					},
					"Terminal": {
						"Name": 5
					}
				},
				"MarketingCarrier": {
					"AirlineID": "AA",
					"FlightNumber": 8405
				},
				"OperatingCarrier": {
					"AirlineID": "JL"
				},
				"Equipment": {
					"AircraftCode": 773
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 145,
					"DatePeriod": {
						"Effective": "2019-05-02",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T11:30"
					},
					"Terminal": {
						"Name": 1
					}
				},
				"Arrival": {
					"AirportCode": "RSW",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T15:24"
					}
				},
				"MarketingCarrier": {
					"AirlineID": "UA",
					"FlightNumber": 2167
				},
				"Equipment": {
					"AircraftCode": 739
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 145,
					"DatePeriod": {
						"Effective": "2019-05-02",
						"Expiration": "2019-05-31"
					}
				}
			}]
		}, {
			"TotalJourney": {
				"Duration": "P1DT1H29M"
			},
			"Flight": [{
				"Departure": {
					"AirportCode": "TPE",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T01:55"
					},
					"Terminal": {
						"Name": 1
					}
				},
				"Arrival": {
					"AirportCode": "NRT",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T06:15"
					},
					"Terminal": {
						"Name": 3
					}
				},
				"MarketingCarrier": {
					"AirlineID": "GK",
					"FlightNumber": 12
				},
				"Equipment": {
					"AircraftCode": 320
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 145,
					"DatePeriod": {
						"Effective": "2019-05-02",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "NRT",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T10:50"
					},
					"Terminal": {
						"Name": 2
					}
				},
				"Arrival": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T08:50"
					},
					"Terminal": {
						"Name": 5
					}
				},
				"MarketingCarrier": {
					"AirlineID": "JL",
					"FlightNumber": 10
				},
				"Equipment": {
					"AircraftCode": 773
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 145,
					"DatePeriod": {
						"Effective": "2019-05-02",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T11:30"
					},
					"Terminal": {
						"Name": 1
					}
				},
				"Arrival": {
					"AirportCode": "RSW",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T15:24"
					}
				},
				"MarketingCarrier": {
					"AirlineID": "UA",
					"FlightNumber": 2167
				},
				"Equipment": {
					"AircraftCode": 739
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 145,
					"DatePeriod": {
						"Effective": "2019-05-02",
						"Expiration": "2019-05-31"
					}
				}
			}]
		}, {
			"TotalJourney": {
				"Duration": "P1DT1H29M"
			},
			"Flight": [{
				"Departure": {
					"AirportCode": "TPE",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T01:55"
					},
					"Terminal": {
						"Name": 1
					}
				},
				"Arrival": {
					"AirportCode": "NRT",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T06:15"
					},
					"Terminal": {
						"Name": 3
					}
				},
				"MarketingCarrier": {
					"AirlineID": "GK",
					"FlightNumber": 12
				},
				"Equipment": {
					"AircraftCode": 320
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 145,
					"DatePeriod": {
						"Effective": "2019-05-02",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "HND",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T10:40"
					},
					"Terminal": {
						"Name": "I"
					}
				},
				"Arrival": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T08:30"
					},
					"Terminal": {
						"Name": 5
					}
				},
				"MarketingCarrier": {
					"AirlineID": "NH",
					"FlightNumber": 112
				},
				"Equipment": {
					"AircraftCode": "77W"
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 145,
					"DatePeriod": {
						"Effective": "2019-05-02",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T11:30"
					},
					"Terminal": {
						"Name": 1
					}
				},
				"Arrival": {
					"AirportCode": "RSW",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T15:24"
					}
				},
				"MarketingCarrier": {
					"AirlineID": "UA",
					"FlightNumber": 2167
				},
				"Equipment": {
					"AircraftCode": 739
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 145,
					"DatePeriod": {
						"Effective": "2019-05-02",
						"Expiration": "2019-05-31"
					}
				}
			}]
		}, {
			"TotalJourney": {
				"Duration": "P1DT1H29M"
			},
			"Flight": [{
				"Departure": {
					"AirportCode": "TPE",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T01:55"
					},
					"Terminal": {
						"Name": 1
					}
				},
				"Arrival": {
					"AirportCode": "NRT",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T06:15"
					},
					"Terminal": {
						"Name": 3
					}
				},
				"MarketingCarrier": {
					"AirlineID": "GK",
					"FlightNumber": 12
				},
				"Equipment": {
					"AircraftCode": 320
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 145,
					"DatePeriod": {
						"Effective": "2019-05-02",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "HND",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T10:40"
					},
					"Terminal": {
						"Name": "I"
					}
				},
				"Arrival": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T08:30"
					},
					"Terminal": {
						"Name": 5
					}
				},
				"MarketingCarrier": {
					"AirlineID": "TG",
					"FlightNumber": 6088
				},
				"OperatingCarrier": {
					"AirlineID": "NH"
				},
				"Equipment": {
					"AircraftCode": "77W"
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 145,
					"DatePeriod": {
						"Effective": "2019-05-02",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T11:30"
					},
					"Terminal": {
						"Name": 1
					}
				},
				"Arrival": {
					"AirportCode": "RSW",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T15:24"
					}
				},
				"MarketingCarrier": {
					"AirlineID": "UA",
					"FlightNumber": 2167
				},
				"Equipment": {
					"AircraftCode": 739
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 145,
					"DatePeriod": {
						"Effective": "2019-05-02",
						"Expiration": "2019-05-31"
					}
				}
			}]
		}, {
			"TotalJourney": {
				"Duration": "P1DT1H29M"
			},
			"Flight": [{
				"Departure": {
					"AirportCode": "TPE",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T01:55"
					},
					"Terminal": {
						"Name": 1
					}
				},
				"Arrival": {
					"AirportCode": "NRT",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T06:15"
					},
					"Terminal": {
						"Name": 3
					}
				},
				"MarketingCarrier": {
					"AirlineID": "GK",
					"FlightNumber": 12
				},
				"Equipment": {
					"AircraftCode": 320
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 145,
					"DatePeriod": {
						"Effective": "2019-04-29",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "HND",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T10:40"
					},
					"Terminal": {
						"Name": "I"
					}
				},
				"Arrival": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T08:30"
					},
					"Terminal": {
						"Name": 5
					}
				},
				"MarketingCarrier": {
					"AirlineID": "UA",
					"FlightNumber": 7912
				},
				"OperatingCarrier": {
					"AirlineID": "NH"
				},
				"Equipment": {
					"AircraftCode": "77W"
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 145,
					"DatePeriod": {
						"Effective": "2019-04-29",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T11:30"
					},
					"Terminal": {
						"Name": 1
					}
				},
				"Arrival": {
					"AirportCode": "RSW",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T15:24"
					}
				},
				"MarketingCarrier": {
					"AirlineID": "UA",
					"FlightNumber": 2167
				},
				"Equipment": {
					"AircraftCode": 739
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 145,
					"DatePeriod": {
						"Effective": "2019-04-29",
						"Expiration": "2019-05-31"
					}
				}
			}]
		}, {
			"TotalJourney": {
				"Duration": "P1DT2H44M"
			},
			"Flight": [{
				"Departure": {
					"AirportCode": "TPE",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T01:55"
					},
					"Terminal": {
						"Name": 1
					}
				},
				"Arrival": {
					"AirportCode": "NRT",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T06:15"
					},
					"Terminal": {
						"Name": 3
					}
				},
				"MarketingCarrier": {
					"AirlineID": "GK",
					"FlightNumber": 12
				},
				"Equipment": {
					"AircraftCode": 320
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 123457,
					"DatePeriod": {
						"Effective": "2019-05-13",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "NRT",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T10:50"
					},
					"Terminal": {
						"Name": 2
					}
				},
				"Arrival": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T08:50"
					},
					"Terminal": {
						"Name": 5
					}
				},
				"MarketingCarrier": {
					"AirlineID": "AA",
					"FlightNumber": 8405
				},
				"OperatingCarrier": {
					"AirlineID": "JL"
				},
				"Equipment": {
					"AircraftCode": 773
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 123457,
					"DatePeriod": {
						"Effective": "2019-05-13",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T12:49"
					},
					"Terminal": {
						"Name": 3
					}
				},
				"Arrival": {
					"AirportCode": "RSW",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T16:39"
					}
				},
				"MarketingCarrier": {
					"AirlineID": "AA",
					"FlightNumber": 2561
				},
				"Equipment": {
					"AircraftCode": 738
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 123457,
					"DatePeriod": {
						"Effective": "2019-05-13",
						"Expiration": "2019-05-31"
					}
				}
			}]
		}, {
			"TotalJourney": {
				"Duration": "P1DT2H44M"
			},
			"Flight": [{
				"Departure": {
					"AirportCode": "TPE",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T01:55"
					},
					"Terminal": {
						"Name": 1
					}
				},
				"Arrival": {
					"AirportCode": "NRT",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T06:15"
					},
					"Terminal": {
						"Name": 3
					}
				},
				"MarketingCarrier": {
					"AirlineID": "GK",
					"FlightNumber": 12
				},
				"Equipment": {
					"AircraftCode": 320
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 123457,
					"DatePeriod": {
						"Effective": "2019-05-13",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "HND",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T10:40"
					},
					"Terminal": {
						"Name": "I"
					}
				},
				"Arrival": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T08:30"
					},
					"Terminal": {
						"Name": 5
					}
				},
				"MarketingCarrier": {
					"AirlineID": "UA",
					"FlightNumber": 7912
				},
				"OperatingCarrier": {
					"AirlineID": "NH"
				},
				"Equipment": {
					"AircraftCode": "77W"
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 123457,
					"DatePeriod": {
						"Effective": "2019-05-13",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T12:49"
					},
					"Terminal": {
						"Name": 3
					}
				},
				"Arrival": {
					"AirportCode": "RSW",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T16:39"
					}
				},
				"MarketingCarrier": {
					"AirlineID": "AA",
					"FlightNumber": 2561
				},
				"Equipment": {
					"AircraftCode": 738
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 123457,
					"DatePeriod": {
						"Effective": "2019-05-13",
						"Expiration": "2019-05-31"
					}
				}
			}]
		}, {
			"TotalJourney": {
				"Duration": "P1DT2H44M"
			},
			"Flight": [{
				"Departure": {
					"AirportCode": "TPE",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T01:55"
					},
					"Terminal": {
						"Name": 1
					}
				},
				"Arrival": {
					"AirportCode": "NRT",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T06:15"
					},
					"Terminal": {
						"Name": 3
					}
				},
				"MarketingCarrier": {
					"AirlineID": "GK",
					"FlightNumber": 12
				},
				"Equipment": {
					"AircraftCode": 320
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 123457,
					"DatePeriod": {
						"Effective": "2019-05-13",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "HND",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T10:40"
					},
					"Terminal": {
						"Name": "I"
					}
				},
				"Arrival": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T08:30"
					},
					"Terminal": {
						"Name": 5
					}
				},
				"MarketingCarrier": {
					"AirlineID": "NH",
					"FlightNumber": 112
				},
				"Equipment": {
					"AircraftCode": "77W"
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 123457,
					"DatePeriod": {
						"Effective": "2019-05-13",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T12:49"
					},
					"Terminal": {
						"Name": 3
					}
				},
				"Arrival": {
					"AirportCode": "RSW",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T16:39"
					}
				},
				"MarketingCarrier": {
					"AirlineID": "AA",
					"FlightNumber": 2561
				},
				"Equipment": {
					"AircraftCode": 738
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 123457,
					"DatePeriod": {
						"Effective": "2019-05-13",
						"Expiration": "2019-05-31"
					}
				}
			}]
		}, {
			"TotalJourney": {
				"Duration": "P1DT2H44M"
			},
			"Flight": [{
				"Departure": {
					"AirportCode": "TPE",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T01:55"
					},
					"Terminal": {
						"Name": 1
					}
				},
				"Arrival": {
					"AirportCode": "NRT",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T06:15"
					},
					"Terminal": {
						"Name": 3
					}
				},
				"MarketingCarrier": {
					"AirlineID": "GK",
					"FlightNumber": 12
				},
				"Equipment": {
					"AircraftCode": 320
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 123457,
					"DatePeriod": {
						"Effective": "2019-05-13",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "HND",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T10:40"
					},
					"Terminal": {
						"Name": "I"
					}
				},
				"Arrival": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T08:30"
					},
					"Terminal": {
						"Name": 5
					}
				},
				"MarketingCarrier": {
					"AirlineID": "TG",
					"FlightNumber": 6088
				},
				"OperatingCarrier": {
					"AirlineID": "NH"
				},
				"Equipment": {
					"AircraftCode": "77W"
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 123457,
					"DatePeriod": {
						"Effective": "2019-05-13",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T12:49"
					},
					"Terminal": {
						"Name": 3
					}
				},
				"Arrival": {
					"AirportCode": "RSW",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T16:39"
					}
				},
				"MarketingCarrier": {
					"AirlineID": "AA",
					"FlightNumber": 2561
				},
				"Equipment": {
					"AircraftCode": 738
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 123457,
					"DatePeriod": {
						"Effective": "2019-05-13",
						"Expiration": "2019-05-31"
					}
				}
			}]
		}, {
			"TotalJourney": {
				"Duration": "P1DT2H44M"
			},
			"Flight": [{
				"Departure": {
					"AirportCode": "TPE",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T01:55"
					},
					"Terminal": {
						"Name": 1
					}
				},
				"Arrival": {
					"AirportCode": "NRT",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T06:15"
					},
					"Terminal": {
						"Name": 3
					}
				},
				"MarketingCarrier": {
					"AirlineID": "GK",
					"FlightNumber": 12
				},
				"Equipment": {
					"AircraftCode": 320
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 123457,
					"DatePeriod": {
						"Effective": "2019-05-13",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "NRT",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T10:50"
					},
					"Terminal": {
						"Name": 2
					}
				},
				"Arrival": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T08:50"
					},
					"Terminal": {
						"Name": 5
					}
				},
				"MarketingCarrier": {
					"AirlineID": "JL",
					"FlightNumber": 10
				},
				"Equipment": {
					"AircraftCode": 773
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 123457,
					"DatePeriod": {
						"Effective": "2019-05-13",
						"Expiration": "2019-05-31"
					}
				}
			}, {
				"Departure": {
					"AirportCode": "ORD",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T12:49"
					},
					"Terminal": {
						"Name": 3
					}
				},
				"Arrival": {
					"AirportCode": "RSW",
					"ScheduledTimeLocal": {
						"DateTime": "2019-05-16T16:39"
					}
				},
				"MarketingCarrier": {
					"AirlineID": "AA",
					"FlightNumber": 2561
				},
				"Equipment": {
					"AircraftCode": 738
				},
				"Details": {
					"Stops": {
						"StopQuantity": 0
					},
					"DaysOfOperation": 123457,
					"DatePeriod": {
						"Effective": "2019-05-13",
						"Expiration": "2019-05-31"
					}
				}
			}]
		}],
		"Meta": {
			"@Version": "1.0.0",
			"Link": [{
				"@Href": "https:\/\/api.lufthansa.com\/v1\/operations\/schedules\/TPE\/FMY\/2019-05-16?limit=20&offset=0&directFlights=false",
				"@Rel": "self"
			}, {
				"@Href": "https:\/\/api.lufthansa.com\/v1\/operations\/schedules\/TPE\/FMY\/2019-05-15?limit=20&offset=0&directFlights=false",
				"@Rel": "previousRange"
			}, {
				"@Href": "https:\/\/api.lufthansa.com\/v1\/operations\/schedules\/TPE\/FMY\/2019-05-16?limit=20&offset=20&directFlights=false",
				"@Rel": "next"
			}, {
				"@Href": "https:\/\/api.lufthansa.com\/v1\/operations\/schedules\/TPE\/FMY\/2019-05-16?limit=20&offset=0&directFlights=false",
				"@Rel": "nextRange"
			}, {
				"@Href": "https:\/\/api.lufthansa.com\/v1\/references\/airports\/{airportCode}",
				"@Rel": "related"
			}, {
				"@Href": "https:\/\/api.lufthansa.com\/v1\/references\/aircraft\/{aircraftCode}",
				"@Rel": "related"
			}]
		}
	}
}
    """

    private val flightScheduleEntity = gson.fromJson<FlightScheduleEntity>(flightScheduleApiResponse)
    val schedule = flightScheduleEntity.scheduleResource.schedule.first()
    val flightSchedule = flightScheduleEntity.scheduleResource.schedule.map(FlightScheduleMapper::transform)

    private const val cityJsonResponse = """
{
    "CityResource": {
        "Cities": {
            "City": [{
                "CityCode": "AAA",
                "CountryCode": "PF",
                "Position": {
                    "Coordinate": {
                        "Latitude": -16.645,
                        "Longitude": -144.4916667
                    }
                },
                "Names": {
                    "Name": {
                        "@LanguageCode": "en",
                        "${'$'}": "Anaa"
                    }
                },
                "Airports": {
                    "AirportCode": ["SXF", "TXL"]
                }
            }, {
                "CityCode": "AAB",
                "CountryCode": "AU",
                "Position": {
                    "@nil": "true"
                },
                "Names": {
                    "Name": {
                        "@LanguageCode": "en",
                        "${'$'}": "Arrabury"
                    }
                },
                "Airports": {
                    "AirportCode": ["SXF", "TXL"]
                }
            }]
        },
        "Meta": {
            "@Version": "1.0.0",
            "Link": [{
                "@Href": "https:\/\/api.lufthansa.com\/v1\/references\/cities\/?limit=2&offset=0&lang=en",
                "@Rel": "self"
            }, {
                "@Href": "https:\/\/api.lufthansa.com\/v1\/references\/cities\/?limit=2&offset=2&lang=en",
                "@Rel": "next"
            }, {
                "@Href": "https:\/\/api.lufthansa.com\/v1\/references\/cities\/?limit=2&offset=8382&lang=en",
                "@Rel": "last"
            }, {
                "@Href": "https:\/\/api.lufthansa.com\/v1\/references\/airports\/{airportCode}",
                "@Rel": "related"
            }, {
                "@Href": "https:\/\/api.lufthansa.com\/v1\/references\/countries\/{countryCode}",
                "@Rel": "related"
            }],
            "TotalCount": 8383
        }
    }
}
    """
    val cityEntity = gson.fromJson<CityEntity>(cityJsonResponse)

    const val airportResponse = """
{
  "AirportCode": ["SXF", "TXL"]
}
    """
    const val scheduleJsonResponse = """
{
        "TotalJourney": {
          "Duration": "P1DT1H29M"
        },
        "Flight": [
          {
            "Departure": {
              "AirportCode": "SXF",
              "ScheduledTimeLocal": {
                "DateTime": "2019-05-16T01:55"
              },
              "Terminal": {
                "Name": 1
              }
            },
            "Arrival": {
              "AirportCode": "NRT",
              "ScheduledTimeLocal": {
                "DateTime": "2019-05-16T06:15"
              },
              "Terminal": {
                "Name": 3
              }
            },
            "MarketingCarrier": {
              "AirlineID": "GK",
              "FlightNumber": 12
            },
            "Equipment": {
              "AircraftCode": 320
            },
            "Details": {
              "Stops": {
                "StopQuantity": 0
              },
              "DaysOfOperation": 145,
              "DatePeriod": {
                "Effective": "2019-05-02",
                "Expiration": "2019-05-31"
              }
            }
          },
          {
            "Departure": {
              "AirportCode": "NRT",
              "ScheduledTimeLocal": {
                "DateTime": "2019-05-16T10:50"
              },
              "Terminal": {
                "Name": 2
              }
            },
            "Arrival": {
              "AirportCode": "ORD",
              "ScheduledTimeLocal": {
                "DateTime": "2019-05-16T08:50"
              },
              "Terminal": {
                "Name": 5
              }
            },
            "MarketingCarrier": {
              "AirlineID": "AA",
              "FlightNumber": 8405
            },
            "OperatingCarrier": {
              "AirlineID": "JL"
            },
            "Equipment": {
              "AircraftCode": 773
            },
            "Details": {
              "Stops": {
                "StopQuantity": 0
              },
              "DaysOfOperation": 145,
              "DatePeriod": {
                "Effective": "2019-05-02",
                "Expiration": "2019-05-31"
              }
            }
          },
          {
            "Departure": {
              "AirportCode": "ORD",
              "ScheduledTimeLocal": {
                "DateTime": "2019-05-16T11:30"
              },
              "Terminal": {
                "Name": 1
              }
            },
            "Arrival": {
              "AirportCode": "TXL",
              "ScheduledTimeLocal": {
                "DateTime": "2019-05-16T15:24"
              }
            },
            "MarketingCarrier": {
              "AirlineID": "UA",
              "FlightNumber": 2167
            },
            "Equipment": {
              "AircraftCode": 739
            },
            "Details": {
              "Stops": {
                "StopQuantity": 0
              },
              "DaysOfOperation": 145,
              "DatePeriod": {
                "Effective": "2019-05-02",
                "Expiration": "2019-05-31"
              }
            }
          }
        ]
      }
    """
}