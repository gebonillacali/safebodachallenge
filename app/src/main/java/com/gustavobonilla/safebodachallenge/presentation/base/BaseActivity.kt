package com.gustavobonilla.safebodachallenge.presentation.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

/**
 * Defines the [BaseActivity] to be used in the App.
 *
 * This activity handles the injection of the [BaseViewModel] required for the fragment,
 * also handles the lifecycle of the [BaseViewModel].
 */
abstract class BaseActivity<T, Parameters>: AppCompatActivity(), BaseView<T> {

    @Inject
    lateinit var viewModel: BaseViewModel<T, Parameters>

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(getLayoutResource())
    }

    override fun onStart() {
        super.onStart()
        inject()
        viewModel.subscribe(subscribeListener(), subscribeErrorListener())
    }

    override fun onDestroy() {
        viewModel.unSubscribe()
        super.onDestroy()
    }
    //endregion
}