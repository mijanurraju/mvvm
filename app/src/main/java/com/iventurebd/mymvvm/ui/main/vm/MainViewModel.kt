package com.iventurebd.mymvvm.ui.main.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iventurebd.mymvvm.ui.main.model.MainRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(
  private val mainRepository: MainRepository
) : ViewModel() {

  var userName = MutableLiveData<String>()

  init {
    userName.value = mainRepository.getUserName()
  }

}
