package com.iventurebd.mymvvm.data.prefrences

interface PreferenceHelper {

  fun isLoggedIn(): Boolean

  fun setLoggedIn(isLoggedIn: Boolean)

  fun getAccessToken(): String

  fun setAccessToken(accessToken: String)

}