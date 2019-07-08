package com.iventurebd.mymvvm.data.database.user

import javax.inject.Inject

class UserRepository @Inject constructor(
  private val userDao: UserDao
) : UserRepo {

  /*override fun insertUser(user: User): MutableLiveData<Boolean> {
    userDao.insertSingle(user)

  }*/
}