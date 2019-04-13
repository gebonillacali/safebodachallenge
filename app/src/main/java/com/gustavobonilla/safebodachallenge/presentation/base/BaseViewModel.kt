package com.gustavobonilla.safebodachallenge.presentation.base

interface BaseViewModel<T, Parameters> {

    /**
     * Saves data in the [ViewModel] for later use or to be stored while the app is alive.
     * To be used for example (Configuration change - Screen rotation.)
     *
     * @param key the name of the value that will be stored.
     * @param value the value itself to be stored.
     */
    fun saveInMemoryValues(key: String, value: Any)

    /**
     * Retrieves the value stored in [ViewModel].
     *
     * @param key the key associated to the value stored.
     *
     * @return the value associated with the key provided or null if not found.
     */
    fun <T> retrieveInMemoryValues(key: String): T?

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