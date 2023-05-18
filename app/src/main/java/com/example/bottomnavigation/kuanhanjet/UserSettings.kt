package com.example.bottomnavigation.kuanhanjet

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bottomnavigation.R
import com.example.bottomnavigation.phuameikee.MainActivity
import com.example.bottomnavigation.yeexiaoze.general.Login
import org.w3c.dom.Text

class UserSettings : Fragment(R.layout.settings_user) {
    private lateinit var profilePicture: ImageView
    private lateinit var choosePicture: ImageButton
    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //display username (login email text pass to user profile, open sqllite helper, use conditionalgetattribute function pass in username, emailtext, conditionalattribute return a string,
        //(continue) display username = conditionalgetattribute

        //profile picture
        profilePicture = view.findViewById(R.id.profile_pic)
        choosePicture = view.findViewById(R.id.choose_profile_pic)

        //Retrieve username
        val username = arguments?.getString("username")
        Log.i("Main Activity", "$username")

        view.findViewById<TextView>(R.id.name).text = username

        //activate image button and choose picture
        choosePicture.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

        //activate all badges button and go to the profile settings page
        val profile_settings = view.findViewById<Button>(R.id.profile_settings)

        profile_settings.setOnClickListener {
            val intent3 = Intent(context, UserProfileSettingsFragment::class.java)
            intent3.putExtra("username", username)

            startActivity(intent3)
        }

        //activate all badges button and go to the help page
        val help = view.findViewById<Button>(R.id.h_e_l_p)

        help.setOnClickListener {
            val intent4 = Intent(context, UserHelpFragment::class.java)

            startActivity(intent4)
        }

        //activate all badges button and go to the feedback page
        val feedback = view.findViewById<Button>(R.id.f_e_e_d_b_a_c_k)

        feedback.setOnClickListener {
            val intent5 = Intent(context, UserFeedbackFragment::class.java)

            startActivity(intent5)
        }

        val logOut = view.findViewById<Button>(R.id.log_out)

        logOut.setOnClickListener {
            val intent6 = Intent(context, Login::class.java)

            startActivity(intent6)
        }
    }

    //function take picture from gallery and display the profile picture
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            profilePicture.setImageURI(imageUri)
        }
    }

}