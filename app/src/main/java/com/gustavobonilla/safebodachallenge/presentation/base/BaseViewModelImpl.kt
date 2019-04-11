package com.gustavobonilla.safebodachallenge.presentation.base

import android.arch.lifecycle.ViewModel
import com.gustavobonilla.safebodachallenge.isNotNull
import com.gustavobonilla.safebodachallenge.usecases.UseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

/**
 * Defines a [BaseViewModelImpl] for the app.
 *
 * Handles the logic of subscribe, unsubscribe and collect the disposables to be disposed when is required.
 */
abstract class BaseViewModelImpl<T, Parameters>(private val useCase: UseCase<T, Parameters>): ViewModel(), BaseViewModel<T, Parameters> {

    private var disposables = CompositeDisposable()

    protected val publisher: PublishSubject<T> = PublishSubject.create()
    protected val publisherError: PublishSubject<Throwable> = PublishSubject.create()
    private val memoryValues = mutableMapOf<String, Any>()

    //region [BaseViewModel] Impl
    override fun subscribe(observer: (listCategory: T) -> Unit, errorObserver: ((Throwable) -> Unit)?) {
        disposables.add(publisher.subscribe(observer))
        if (errorObserver.isNotNull()) {
            disposables.add(publisherError.subscribe(errorObserver))
        }
    }

    override fun unSubscribe() {
        useCase.clear()
        disposables.clear()
    }

    override fun saveInMemoryValues(key: String, value: Any) {
        memoryValues[key] = value
    }

    override fun retreiveInMemoryValues(key: String): Any? {
        return if (memoryValues.contains(key)) memoryValues[key] else null
    }
    //endregion
}