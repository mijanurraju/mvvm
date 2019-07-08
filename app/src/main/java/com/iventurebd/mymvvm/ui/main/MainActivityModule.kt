package com.iventurebd.mymvvm.ui.main

import androidx.lifecycle.ViewModel
import com.iventurebd.mymvvm.di.ViewModelKey
import com.iventurebd.mymvvm.ui.main.vm.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

  @Binds
  @IntoMap
  @ViewModelKey(MainViewModel::class)
  abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}
