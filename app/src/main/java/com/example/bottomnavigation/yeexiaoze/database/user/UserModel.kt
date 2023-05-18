package com.example.bottomnavigation.yeexiaoze.database.user

import androidx.room.Entity
import androidx.room.PrimaryKey

//The user attributes
@Entity
data class UserModel (
    @PrimaryKey var username: String = "",
    var contactInfo: String = "",
    var password: String = "",
    var role: String = "",
    var companyName: String = ""
)
