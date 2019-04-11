package com.gustavobonilla.safebodachallenge.presentation.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import javax.inject.Inject

/**
 * Defines the [BaseFragment] to be used in the App.
 *
 * This fragment handles the injection of the [BaseViewModel] required for the fragment,
 * also handles the lifecycle of the [BaseViewModel].
 */
abstract class BaseFragment<T, Parameters>: Fragment(), BaseView<T> {

    @Inject
    lateinit var viewModel: BaseViewModel<T, Parameters>

    //region Fragment lifecycle methods
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        inject()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResource(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.subscribe(subscribeListener(), subscribeErrorListener())
    }

    override fun onDetach() {
        viewModel.unSubscribe()
        super.onDetach()
    }
    //endregion
}