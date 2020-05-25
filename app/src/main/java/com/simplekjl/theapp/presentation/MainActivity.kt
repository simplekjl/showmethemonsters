package com.simplekjl.theapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simplekjl.theapp.R
import com.simplekjl.theapp.presentation.login.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        setActionBar(null)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }
    }
}
