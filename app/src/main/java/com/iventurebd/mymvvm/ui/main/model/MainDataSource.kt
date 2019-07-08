package com.iventurebd.mymvvm.ui.main.model

interface MainDataSource {
  fun getMainData(): List<String>

  fun getUserName(): String

  fun setUserName(user: String)
}
