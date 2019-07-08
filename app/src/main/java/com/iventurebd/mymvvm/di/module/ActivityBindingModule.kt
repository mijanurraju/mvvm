package com.iventurebd.mymvvm.di.module

import com.iventurebd.mymvvm.di.ActivityScoped
import com.iventurebd.mymvvm.ui.main.MainActivityModule
import com.iventurebd.mymvvm.ui.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

  @ActivityScoped
  @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
  abstract fun mainActivity(): MainActivity

}
