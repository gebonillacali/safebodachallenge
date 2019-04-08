package com.gustavobonilla.safebodachallenge.presentation.base

interface BaseView<T> {
    /**
     * Subscribes a callback when the [BaseViewModelImpl] triggers error performing data requesting.
     */
    fun subscribeErrorListener(): ((Throwable) -> Unit)?

    /**
     * Subscribes to the [BaseViewModelImpl]
     */
    fun subscribeListener(): (data: T) -> Unit

    /**
     * Gets the layout resource for fragment.
     */
    fun getLayoutResource(): Int

    /**
     * Injects the dependencies in the fragment.
     */
    fun inject()
}