package com.gustavobonilla.safebodachallenge.presentation.base

interface BaseViewModel<T, Parameters> {
    /**
     * Subscribes the observers when the data is requested.
     * This also performs the first request and add this to the disposables.
     *
     * @param observer the observer that will be watching when new data is coming.
     * @param errorObserver the observer that will be watching when an error is emitted.
     */
    fun subscribe(observer: (listCategory: T) -> Unit, errorObserver: ((Throwable) -> Unit)? = null)

    /**
     * Unsubscribes the observers when the data is requested and dispose all disposables in [BaseViewModelImpl].
     */
    fun unSubscribe()

    /**
     * Get and execute the request to obtain the required data.
     *
     * @param parameters the parameters required for request
     */
    fun getData(parameters: Parameters)
}