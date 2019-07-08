package com.iventurebd.mymvvm.di.module

import androidx.lifecycle.ViewModelProvider
import com.iventurebd.mymvvm.di.factory.MvvmModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: MvvmModelFactory):
            ViewModelProvider.Factory
}
