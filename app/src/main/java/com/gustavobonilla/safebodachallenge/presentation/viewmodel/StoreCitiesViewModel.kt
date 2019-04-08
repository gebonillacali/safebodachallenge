package com.gustavobonilla.safebodachallenge.presentation.viewmodel

import com.gustavobonilla.safebodachallenge.presentation.base.BaseViewModelImpl
import com.gustavobonilla.safebodachallenge.usecases.UseCase
import javax.inject.Inject
import kotlin.math.roundToInt

class StoreCitiesViewModel @Inject constructor(private val useCase: UseCase<Int, Int>): BaseViewModelImpl<Int, Int>(useCase) {
    private var currentPage = 0

    override fun getData(parameters: Int) {
        fillCitiesInDb(currentPage)
    }

    private fun fillCitiesInDb(currentPage: Int = 0) {
        this.currentPage = currentPage
        useCase.execute(currentPage, {
            if (it <= 0) {
                publisherError.onNext(Throwable("Internet connection error"))
            } else {
                if ((this.currentPage + 100) < it) {
                    val percentage = ((this.currentPage + 100).toDouble() / it.toDouble()) * 100
                    publisher.onNext(percentage.roundToInt())
                    fillCitiesInDb(this.currentPage + 100)
                } else {
                    publisher.onNext(100)
                }
            }
        }, {
            publisherError.onNext(it)
        })
    }
}