package com.simplekjl.theapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.simplekjl.theapp.R
import com.simplekjl.theapp.presentation.login.LoginFragment
import com.simplekjl.theapp.presentation.pokemonlist.PokemonListFragment
import com.simplekjl.theapp.presentation.preferences.SharePreferencesHelper
import kotlinx.android.synthetic.main.login_activity.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {


    private val mPref: SharePreferencesHelper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        if (mPref.getUserLoginStatus()) {
            // jump to the list fragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PokemonListFragment.newInstance())
                .commitNow()
        } else {
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, LoginFragment.newInstance())
                    .commitNow()
            }
        }
    }
}
