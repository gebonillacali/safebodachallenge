package com.gustavobonilla.safebodachallenge.presentation.sections.splash.view

import android.os.Bundle
import android.widget.Toast
import com.gustavobonilla.safebodachallenge.R
import com.gustavobonilla.safebodachallenge.presentation.SafeBodaChallengeApplication
import com.gustavobonilla.safebodachallenge.presentation.base.BaseActivity
import com.gustavobonilla.safebodachallenge.presentation.sections.home.view.HomeActivity
import com.gustavobonilla.safebodachallenge.presentation.navigation.Navigation
import com.gustavobonilla.safebodachallenge.presentation.sections.splash.di.SplashModule
import com.gustavobonilla.safebodachallenge.presentation.util.AppPreferencesImpl
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity<Int, Int>() {

    private lateinit var appPreferencesImpl: AppPreferencesImpl

    //region Activity Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appPreferencesImpl = AppPreferencesImpl(this.getSharedPreferences(AppPreferencesImpl.FILENAME_APP_PREFERENCES, 0))

    }

    override fun onStart() {
        super.onStart()
        if (!appPreferencesImpl.getPreferences().getBoolean(AppPreferencesImpl.Keys.CITY_FIRST_LOAD.value, false)) {
            viewModel.getData(0)
        } else {
            navigateToHome()
        }
    }
    //endregion

    //region [BaseView] Impl
    override fun subscribeErrorListener(): ((Throwable) -> Unit)? {
        return {
            loadingText.text = getString(R.string.splash_city_retrieve_error)
            percentageText.text = it.localizedMessage
            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    override fun subscribeListener(): (data: Int) -> Unit {
        return {
            percentageText.text = "$it %"
            if (it >= 100) {
                appPreferencesImpl.setValue(AppPreferencesImpl.Keys.CITY_FIRST_LOAD, true)
                navigateToHome()
            }
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_splash
    }

    override fun inject() {
        (application as SafeBodaChallengeApplication)
                .safeBodaChallengeApplicationComponent
                .plus(SplashModule(this))
                .inject(this)
    }
    //endregion

    //region private impl
    private fun navigateToHome() {
        Navigation.navigateToActivity(this, HomeActivity::class.java, finishCurrentActivity = true)
    }
    //endregion
}
