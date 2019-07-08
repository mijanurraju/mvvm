package com.iventurebd.mymvvm.data.database.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.iventurebd.mymvvm.data.database.user.User

@Dao
interface UserDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertAll(user: List<User>)

  @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertSingle(user: User)

  @Query("SELECT * FROM user") fun loadAll(): List<User>

  @Query("SELECT * FROM user WHERE id=:userId LIMIT 1") fun getUser(userId: Int): User

  @Query("SELECT * FROM user WHERE is_logged_in=:isLoggedIn LIMIT 1")
  fun getLoggedInUser(isLoggedIn: Boolean): User?

  @Update(onConflict = OnConflictStrategy.FAIL) fun update(user: User)

  @Query("SELECT * FROM user WHERE email=:email AND password=:password LIMIT 1") fun getUser(
    email: String,
    password: String
  ): User

  @Query("UPDATE user SET is_logged_in = :isLoggedIn WHERE id=:userId ")
  fun setLoggedIn(
    userId: Int,
    isLoggedIn: Boolean
  )

  @Query("SELECT count(*) FROM  user") fun count(): Int

  @Delete fun deleteUser(vararg user: User)
}