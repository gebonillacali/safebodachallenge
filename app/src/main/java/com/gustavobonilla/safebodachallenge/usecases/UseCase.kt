package com.gustavobonilla.safebodachallenge.usecases

import com.gustavobonilla.safebodachallenge.domain.repository.SafeBodaRepository
import com.gustavobonilla.safebodachallenge.isNotNull
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Defines a Use Case.
 *
 * T is the type for result in the use case.
 * Parameters is the type of the parameters to perform the request of the type T.
 */
abstract class UseCase<T, Parameters>(
        private val observeOn: Scheduler = AndroidSchedulers.mainThread(),
        private val subscribeOn: Scheduler = Schedulers.io()
) {

    private val compositeDisposable = CompositeDisposable()

    abstract val repository: SafeBodaRepository

    /**
     * Check if the auth token exists if not tries to retreive one.
     *
     * @param action function that will be executed once the operation of retrieving an auth token is performed.
     */
    private fun checkAuthToken(action: (Boolean, Disposable?) -> Unit) {
        repository.checkAuthtoken(action)
    }

    /**
     * Defines the call to retrieve the type T from repository.
     *
     * @param parameters the parameters of type Parameters to retrieve the type T.
     * @return An [Observable] of type T with the response from repository.
     */
    protected abstract fun createUseCase(parameters: Parameters): Observable<T>

    /**
     * Execute the use case with the given parameter to retrieve the response of type T.
     *
     * @param parameters the parameters of type Parameters to retrieve the type T.
     * @param observer the observer that will be getting the response as type T.
     */
    fun execute(parameters: Parameters, observer: ((T) -> Unit), errorObserver: ((Throwable) -> Unit)? = null) {
        checkAuthToken { gotNewAuthToken, disposable ->
            if (gotNewAuthToken && disposable.isNotNull()) {
                compositeDisposable.add(disposable!!)
            }
            compositeDisposable.add(createUseCase(parameters)
                    .subscribeOn(subscribeOn)
                    .observeOn(observeOn)
                    .doOnError { throwable ->
                        errorObserver?.let {
                            it(throwable)
                        }
                    }
                    .subscribe {
                        observer(it)
                    })
        }
    }

    fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    fun clear() {
        compositeDisposable.clear()
    }
}