package com.example.bottomnavigation.kuanhanjet

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.bottomnavigation.R
import com.example.bottomnavigation.yeexiaoze.database.user.UserSQLiteHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UserProfileSettingsFragment : AppCompatActivity() {

    //private lateinit var model: UserModel
    private lateinit var sqliteHelper: UserSQLiteHelper

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_profile_settings_user)

        sqliteHelper = UserSQLiteHelper(this)

        val name = intent.getStringExtra("username")

        var username = findViewById<TextView>(R.id.display_username_profile_settings)
        username.text = "Username: $name"

        var info = findViewById<TextView>(R.id.display_email_phoneNum)
        info.text = "Contact Info: " + sqliteHelper.conditionalGetAttribute("contactInfo", "username", name)

        var password = findViewById<TextView>(R.id.display_password)
        password.text = "Password: ${sqliteHelper.conditionalGetAttribute("password", "username", name)}"

        //display
        //sqliteHelper.getRecord()//us

        //return to settings page button
        val backward = findViewById<FloatingActionButton>(R.id.back_ward)
        backward.setOnClickListener {
            onBackPressed()
        }
    }
}