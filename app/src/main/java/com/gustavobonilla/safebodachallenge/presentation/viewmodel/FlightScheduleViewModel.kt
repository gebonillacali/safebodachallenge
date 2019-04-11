package com.gustavobonilla.safebodachallenge.presentation.viewmodel

import com.gustavobonilla.safebodachallenge.domain.model.FlightSchedule
import com.gustavobonilla.safebodachallenge.presentation.base.BaseViewModelImpl
import com.gustavobonilla.safebodachallenge.usecases.FlightSchedulesRequest
import com.gustavobonilla.safebodachallenge.usecases.UseCase
import javax.inject.Inject

class FlightScheduleViewModel @Inject constructor(
        private val useCase: UseCase<List<FlightSchedule>, FlightSchedulesRequest>):
        BaseViewModelImpl<List<FlightSchedule>, FlightSchedulesRequest>(useCase) {
    lateinit var flightSchedules: List<FlightSchedule>
    lateinit var currentRequest: FlightSchedulesRequest
    override fun getData(parameters: FlightSchedulesRequest) {
        if (shouldPerformRequest(parameters)) {
            currentRequest = parameters
            useCase.execute(parameters, {
                flightSchedules = it
                publisher.onNext(flightSchedules)
            }, {
                publisherError.onNext(it)
            })
        } else {
            publisher.onNext(flightSchedules)
        }
    }

    private fun shouldPerformRequest(parameters: FlightSchedulesRequest) =
            !this::flightSchedules.isInitialized || (this::currentRequest.isInitialized && currentRequest != parameters)
}