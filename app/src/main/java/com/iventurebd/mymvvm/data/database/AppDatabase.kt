package com.iventurebd.mymvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iventurebd.mymvvm.BuildConfig
import com.iventurebd.mymvvm.data.database.movie.MovieDao
import com.iventurebd.mymvvm.data.database.user.User
import com.iventurebd.mymvvm.data.database.user.UserDao

@Database(
    entities = [
        (User::class)
    ],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun movieDao(): MovieDao

}