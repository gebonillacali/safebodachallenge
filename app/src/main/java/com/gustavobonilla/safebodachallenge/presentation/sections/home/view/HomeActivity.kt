package com.gustavobonilla.safebodachallenge.presentation.sections.home.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gustavobonilla.safebodachallenge.R
import com.gustavobonilla.safebodachallenge.isNull
import com.gustavobonilla.safebodachallenge.presentation.navigation.Navigation

class HomeActivity : AppCompatActivity() {

    //region [Activity] Lifecycle impl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        if (savedInstanceState.isNull()) {
            initHomeFragment()
        }
    }
    //endregion

    //region private impl
    private fun initHomeFragment() {
        Navigation.addFragment(supportFragmentManager, R.id.homeFragmentContainer, HomeFragment.newInstance())
    }
    //endregion
}
