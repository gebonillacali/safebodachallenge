package com.gustavobonilla.safebodachallenge.usecases

import com.gustavobonilla.safebodachallenge.ModelCreator
import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.domain.model.FlightSchedule
import com.gustavobonilla.safebodachallenge.domain.repository.SafeBodaRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.reflect.Whitebox
import java.util.concurrent.TimeUnit

@RunWith(PowerMockRunner::class)
@PrepareForTest(UseCase::class, SafeBodaRepository::class)
class UseCaseTest {

    private lateinit var dummyUseCase: UseCase<String, String>
    private val testScheduler = TestScheduler()

    @Before
    fun setUp() {
        dummyUseCase = PowerMockito.spy(DummyUseCase())
    }

    @Test
    fun testExecute() {
        Mockito.`when`(dummyUseCase.repository).thenReturn(SafeBodaRepositoryDummy())
        dummyUseCase.execute("", testObserver)
        testScheduler.advanceTimeBy(ModelCreator.DELAY_TIME_MS, TimeUnit.MILLISECONDS)
        PowerMockito.verifyPrivate(dummyUseCase, Mockito.atLeastOnce()).invoke("checkAuthToken", any())
        PowerMockito.verifyPrivate(dummyUseCase, Mockito.atLeastOnce()).invoke("createUseCase", anyString())
        val field = Whitebox.getField(DummyUseCase::class.java, "compositeDisposable")
        val compositeDisposable =  Whitebox.getFieldValue(field, dummyUseCase) as CompositeDisposable
        Assert.assertTrue(compositeDisposable.size() > 0)
    }
}

class DummyUseCase: UseCase<String, String>(Schedulers.trampoline(), Schedulers.trampoline()) {
    override val repository: SafeBodaRepository = SafeBodaRepositoryDummy()
    override fun createUseCase(parameters: String): Observable<String> = Observable.just("")
}

class SafeBodaRepositoryDummy: SafeBodaRepository {
    override fun checkAuthtoken(action: (Boolean, Disposable?) -> Unit) {
        action(true, null)
    }

    override fun updateCities(offset: Int): Observable<Int> = Observable.just(1)

    override fun getCities(termSearch: String): Observable<List<City>> = Observable.just(listOf())

    override fun getCityByAirportCode(airportCode: String): Observable<City> = Observable.just(City.createDummyCity())

    override fun getFlightSchedule(originAirport: String, destinationAirport: String, date: String): Observable<List<FlightSchedule>>
        = Observable.just(listOf())
}

val testObserver: (String) -> Unit  = {}