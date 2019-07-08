package com.iventurebd.mymvvm.di.component

import com.iventurebd.mymvvm.MainApplication
import com.iventurebd.mymvvm.di.module.ActivityBindingModule
import com.iventurebd.mymvvm.di.module.ApiModule
import com.iventurebd.mymvvm.di.module.AppModule
import com.iventurebd.mymvvm.di.module.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ApiModule::class,
        ActivityBindingModule::class,
        ViewModelModule::class]
)
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MainApplication>()
}