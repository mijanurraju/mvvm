package com.iventurebd.mymvvm.data.prefrences

import android.content.Context
import android.content.SharedPreferences
import com.iventurebd.mymvvm.di.PreferenceInfo
import javax.inject.Inject

class AppPreferenceHelper @Inject constructor(
  context: Context,
  @PreferenceInfo private val prefFileName: String
) : PreferenceHelper {

  companion object {
    private const val PREF_KEY_LOGGED_IN = "pref_key_logged_in"
    private const val PREF_KEY_ACCESS_TOKEN = "pref_key_access_token"
  }

  private val ctx = context

  private val mPrefs: SharedPreferences = context.getSharedPreferences(
      prefFileName, Context.MODE_PRIVATE
  )

  override fun isLoggedIn(): Boolean = mPrefs.getBoolean(PREF_KEY_LOGGED_IN, false)

  override fun setLoggedIn(isLoggedIn: Boolean) = mPrefs.edit().putBoolean(
      PREF_KEY_LOGGED_IN, isLoggedIn
  ).apply()

  override fun getAccessToken(): String = mPrefs.getString(PREF_KEY_ACCESS_TOKEN, "") ?: ""

  override fun setAccessToken(accessToken: String) = mPrefs.edit().putString(
      PREF_KEY_ACCESS_TOKEN, accessToken
  ).apply()

}
