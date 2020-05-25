package com.simplekjl.theapp.presentation.preferences

import android.content.Context
import android.content.SharedPreferences


//
// Created by  on 5/25/20.
//

class SharePreferencesHelper(context: Context, prefFileName: String?) : PreferencesHelper {
    // access token key name
    private val mPrefs: SharedPreferences =
        context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    companion object {
        // access token key name
        private const val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"

        // user login status key name
        private const val PREF_KEY_USER_LOGIN_STATUS = "PREF_KEY_USER_LOGIN_STATUS"
    }

    override fun getAccessToken(): String? = mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null)

    override fun setAccessToken(accessToken: String?) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()
    }

    override fun getUserLoginStatus(): Boolean =
        mPrefs.getBoolean(PREF_KEY_USER_LOGIN_STATUS, false)

    override fun setUserLoginStatus(loginStatus: Boolean) {
        mPrefs.edit().putBoolean(PREF_KEY_USER_LOGIN_STATUS, loginStatus).apply()
    }

}