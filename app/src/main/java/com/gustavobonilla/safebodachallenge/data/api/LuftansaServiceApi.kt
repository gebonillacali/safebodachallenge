package com.gustavobonilla.safebodachallenge.data.api

import io.reactivex.disposables.Disposable

interface LuftansaServiceApi {
    val api: LuftansaApi

    /**
     * Check if the auth token exists if not tries to retreive one.
     *
     * @param action function that will be executed once the operation of retrieving an auth token is performed.
     */
    fun checkAuthToken(action: (Boolean, Disposable?) -> Unit)
}