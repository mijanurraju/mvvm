package com.iventurebd.mymvvm.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.iventurebd.mymvvm.MainApplication
import com.iventurebd.mymvvm.data.database.AppDatabase
import com.iventurebd.mymvvm.data.database.user.UserRepo
import com.iventurebd.mymvvm.data.database.user.UserRepository
import com.iventurebd.mymvvm.data.network.intercepter.RequestInterceptor
import com.iventurebd.mymvvm.data.prefrences.AppPreferenceHelper
import com.iventurebd.mymvvm.data.prefrences.PreferenceHelper
import com.iventurebd.mymvvm.di.PreferenceInfo
import com.iventurebd.mymvvm.utils.AppConstants
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(application: MainApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(
            context.applicationContext, AppDatabase::class.java, AppConstants.DB_NAME
        ).allowMainThreadQueries().build()

    @Provides
    @PreferenceInfo
    internal fun providePrefFileName(): String = AppConstants.PREF_NAME

    @Provides
    @Singleton
    internal fun providePrefHelper(
        appPreferenceHelper: AppPreferenceHelper
    ): PreferenceHelper = appPreferenceHelper


    @Provides
    @Singleton
    internal fun provideUserRepoHelper(appDatabase: AppDatabase): UserRepo =
        UserRepository(appDatabase.userDao())

    /*
       * The method returns the Gson object
       * */
    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }


    /*
     * The method returns the Cache object
     * */
    @Provides
    @Singleton
    internal fun provideCache(application: Application): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val httpCacheDirectory = File(application.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }


    /*
     * The method returns the Okhttp object
     * */
    @Provides
    @Singleton
    internal fun provideOkhttpClient(cache: Cache): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.cache(cache)
        httpClient.addInterceptor(logging)
        httpClient.addNetworkInterceptor(RequestInterceptor())
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        return httpClient.build()
    }


    /*
     * The method returns the Retrofit object
     * */
    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .build()
    }


}