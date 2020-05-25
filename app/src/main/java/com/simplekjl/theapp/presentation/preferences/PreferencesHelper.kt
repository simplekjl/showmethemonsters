package com.simplekjl.theapp.presentation.preferences

interface PreferencesHelper {
    // access token getter method
    fun getAccessToken(): String?

    // access token setter method
    fun setAccessToken(accessToken: String?)

    // user login status getter method
    fun getUserLoginStatus(): Boolean

    // user login status setter method
    fun setUserLoginStatus(loginStatus: Boolean)

}