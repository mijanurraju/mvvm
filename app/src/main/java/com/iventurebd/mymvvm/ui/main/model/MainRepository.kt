package com.iventurebd.mymvvm.ui.main.model

import androidx.lifecycle.MutableLiveData
import com.iventurebd.mymvvm.data.prefrences.PreferenceHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class MainRepository @Inject constructor(
  private val preferenceHelper: PreferenceHelper
) : MainDataSource {

  private var name: MutableLiveData<String>? = null

  override fun getMainData(): List<String> = listOf()

  override fun getUserName(): String {
    return "Mijanur Rahman Raju"
  }

  override fun setUserName(user: String) {
  }

}
