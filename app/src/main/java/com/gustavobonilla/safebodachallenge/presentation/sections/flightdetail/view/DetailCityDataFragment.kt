package com.gustavobonilla.safebodachallenge.presentation.sections.flightdetail.view

import com.gustavobonilla.safebodachallenge.R
import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.presentation.SafeBodaChallengeApplication
import com.gustavobonilla.safebodachallenge.presentation.base.BaseFragment
import com.gustavobonilla.safebodachallenge.presentation.sections.flightdetail.di.FlightDetailModule
import com.gustavobonilla.safebodachallenge.presentation.viewmodel.CityViewModel
import kotlinx.android.synthetic.main.fragment_detailcity.*

class DetailCityDataFragment: BaseFragment<City, String>() {

    lateinit var city: City

    override fun onResume() {
        super.onResume()
        val currentCity = viewModel.retreiveInMemoryValues<City>("currentCity")
        currentCity?.let {
            fillInfo(city)
        }
    }

    //region [BaseView] Impl
    override fun subscribeErrorListener(): ((Throwable) -> Unit)? {
        return {}
    }

    override fun subscribeListener(): (City) -> Unit {
        return {
            if ((viewModel is CityViewModel && (viewModel as CityViewModel).finishRequesting) || !this::city.isInitialized) {
                city = it
                fillInfo(city)
            }
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_detailcity
    }

    override fun inject() {
        activity?.let {
            (it.application as SafeBodaChallengeApplication)
                    .safeBodaChallengeApplicationComponent
                    .plus(FlightDetailModule(it))
                    .injectInDetail(this)
        }
    }
    //endregion

    //region private impl
    private fun fillInfo(city: City) {
        if (city.airportCode.isNotEmpty() && city.cityName.isNotEmpty()) {
            viewModel.saveInMemoryValues("currentCity", city)
            airportCode.text = city.airportCode
            cityName.text = city.cityName
        }
    }
    //endregion

    companion object {
        fun newInstance() = DetailCityDataFragment().apply {
            retainInstance = true
        }
    }
}