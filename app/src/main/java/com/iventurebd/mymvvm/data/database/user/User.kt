package com.iventurebd.mymvvm.data.database.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(

  @Expose @PrimaryKey @SerializedName("id")
  @ColumnInfo(name = "id") var id: Int? = null,

  @Expose @SerializedName("first_name")
  @ColumnInfo(name = "first_name") var firstName: String? = null,

  @Expose @SerializedName("last_name")
  @ColumnInfo(name = "last_name") var lastName: String? = null,

  @Expose @SerializedName("email")
  @ColumnInfo(name = "email") var email: String? = null,

  @Expose @SerializedName("password")
  @ColumnInfo(name = "password") var password: String? = null,

  @Expose @SerializedName("phone_number")
  @ColumnInfo(name = "phone_number") var phoneNumber: String? = null,

  @Expose @SerializedName("is_logged_in")
  @ColumnInfo(name = "is_logged_in") var is_logged_in: Boolean? = null

)
