package com.example.bottomnavigation.yeexiaoze.general

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.bottomnavigation.yeexiaoze.database.user.UserSQLiteHelper
import com.example.bottomnavigation.databinding.LoginBinding
import com.example.bottomnavigation.phuameikee.MainActivity
import com.example.bottomnavigation.yeexiaoze.database.selfDevelopment.ChapterModel
import com.example.bottomnavigation.yeexiaoze.database.selfDevelopment.ChapterSQLiteHelper
import com.example.bottomnavigation.yeexiaoze.database.selfDevelopment.SubchapterModel
import com.example.bottomnavigation.yeexiaoze.database.selfDevelopment.SubchapterSQLiteHelper

class Login : AppCompatActivity() {

    //Set binding variables
    private lateinit var binding: LoginBinding

    //Use validation methods
    private lateinit var validation: Validation

    //Use SQL helper
    private lateinit var sqliteHelper: UserSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        ChapterSQLiteHelper(this).updateAttribute("title", "Demo Chapter: Interview Preparation", "C1")

        /*
        ChapterSQLiteHelper(this).insertChapter(
            ChapterModel(
                chapterID = "C0",
                title = "Introduction"
            )
        )

        ChapterSQLiteHelper(this).insertChapter(
            ChapterModel(
                chapterID = "C1",
                title = "Interview Preparation"
            )
        )

        SubchapterSQLiteHelper(this).insertSubchapter(
            SubchapterModel(
                subchapterID = "S00",
                title = "Introduction to MYJOB Self-Development Course",
                content = "Welcome to this self-development course!\n" +
                        "\n" +
                        "In this course, you are going to learn skills related to self-development on career prospects, both soft skills and hard skills, which will be useful for finding a job. This course provides a wide range of topics relating to working field, which will be suitable for fresh graduates and people currently working.\n" +
                        "\n" +
                        "Happy self-developing!",
                link = "",
                chapterID = "C0"
            )
        )

        SubchapterSQLiteHelper(this).insertSubchapter(
            SubchapterModel(
                subchapterID = "S10",
                title = "Introduction",
                content = "Welcome to the first chapter of the course!\n" +
                        "\n" +
                        "In this chapter, you are going to learn the fundamentals of preparing for an interview. Preparing for an interview is an important part of an interview. Despite it might not be as important as the performance brought by you during the interview, it gives the interviewer the impression that you are ready for the interview. After you have completed this chapter,\n" +
                        "hope that you have a better image of how to prepare for an interview!\"\n" +
                        "\n" +
                        "Good luck with the first chapter! ",
                link = "",
                chapterID = "C1"
            )
        )

        SubchapterSQLiteHelper(this).insertSubchapter(
            SubchapterModel(
                subchapterID = "S11",
                title = "Writing a perfect resume",
                content = "Before applying for any job, a resume is a must. A perfect resume can give your potential employer a sense of past experience and skills from you, heavily increasing the chance of you being\n" +
                        "hired. Here are some of the points that you should take note of when writing a resume:\n" +
                        "\n" +
                        "Opening - You should start your resume with your contact information and professional profile. Without your contact information, it is impossible for the hiring manager to reach you. Contact information, such as full name, email address, phone number, etc, should be included. Besides that, you should include your professional profile, such as your current qualification, and social media profile links, so that the hiring manager can quickly understand who you are.\n" +
                        "\n" +
                        "Content - You should include all your education, experience, and skills to prove that you are suitable for the job. You have to include your qualifications, specific major, previous position(s), achievement of the position, etc., to show the hiring manager what you have achieved before you enter the company. For the skills highlight, you should include both hard skills and soft skills which are related to the job you are looking for. Remember, you should be honest about the skills that you have acquired.\n" +
                        "\n" +
                        "Conclusion - You may need to conclude the resume with a few sentences, such as volunteer positions, professional acknowledgments, etc. You do not need to include this section if you can't think of any content.\n" +
                        "\n" +
                        "Read More: ",
                link = "https://www.indeed.com/career-advice/resumes-cover-letters/perfect-resume",
                chapterID = "C1"
            )
        )

        SubchapterSQLiteHelper(this).insertSubchapter(
            SubchapterModel(
                subchapterID = "S12",
                title = "Dress code for interview",
                content = "Clothes make the man, a clean professional appearance is important in making a good first impression. Make sure that your dress is representable, and it can give the hiring manager\n" +
                        "a sense of professionalism from you. Here are some of the dressing guides for the interview.\n" +
                        "\n" +
                        "Men - Dress to match the position you are applying for. In most cases, it means a suit, which includes a matching jacket, pants, tie, dress shoes, etc. You should make sure that the suit is neat, clean, and comfortable. Make sure to iron your suit beforehand. You should also shower, comb your hair and wear deodorant before going to an interview.\n" +
                        "\n" +
                        "Women - Dress to match the position you are applying for. In most cases, it means a knee-length skirt or pants, blouse, jacket, etc. You should make sure that the suit is comfortable, conservative, and dark in color. Skirt with short length is strictly prohibited for an interview. You should also shower, pull a ponytail and wear deodorant before going to an interview. In the case of make-up and nail polish, you should use a neutral color that fits your skin tone.\n" +
                        "\n" +
                        "Read More: ",
                link = "https://www.pittstate.edu/careers/students/interview-attire.html",
                chapterID = "C1"
            )
        )

         */

        super.onCreate(savedInstanceState)
        //Initialization
        binding = LoginBinding.inflate(layoutInflater)
        validation = Validation()
        setContentView(binding.root)

        sqliteHelper = UserSQLiteHelper(this)

        //Navigate to SignUp page
        binding.loginNavigate.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }

        binding.loginButton.setOnClickListener {
            //Do validation
            val idBool = validation.errorText(binding.loginIDError, validation(binding.loginID))
            val passwordBool =
                validation.errorText(binding.loginPasswordError, validation(binding.loginPassword))

            if (idBool && passwordBool) {
                login()
            }
        }

        var clickTimes = 0

        //Hide and unhide password
        binding.loginUnhide.setOnClickListener {

            clickTimes++
            showPassword(clickTimes)
        }
    }

    private fun validation(inputField: EditText): String {
        //Check the input field passed
        when (inputField) {

            binding.loginID -> {
                //Set error text
                //Check the type of error occurred
                //Logic: Pass the input field and field type to check if there is any error message (error occured). If any error, will return an error message.
                return if (validation.nullValueCheck(
                        inputField.text.toString(),
                        "email or phone number"
                    ) != ""
                ) {
                    validation.nullValueCheck(inputField.text.toString(), "email or phone number")
                } else if (validation.formatCheck(
                        inputField.text.toString(),
                        "email or phone number"
                    ) != ""
                ) {
                    validation.formatCheck(inputField.text.toString(), "email or phone number")
                } else if (loginExistenceCheck(
                        inputField.text.toString(),
                        sqliteHelper.getAttribute("contactInfo")
                    ) != ""
                ) {
                    loginExistenceCheck(
                        inputField.text.toString(),
                        sqliteHelper.getAttribute("contactInfo")
                    )
                } else {
                    ""
                }
            }

            binding.loginPassword -> {

                return if (validation.nullValueCheck(
                        inputField.text.toString(),
                        "password"
                    ) != ""
                ) {
                    validation.nullValueCheck(inputField.text.toString(), "password")
                } else if (passwordCheck(
                        binding.loginID.text.toString(),
                        inputField.text.toString()
                    ) != ""
                ) {
                    passwordCheck(binding.loginID.text.toString(), inputField.text.toString())
                } else {
                    ""
                }
            }

            else -> {}
        }

        return ""
    }

    private fun showPassword(clickTimes: Int) {
        //If user click the eye button (Times clicked)
        if (clickTimes % 2 == 1) {
            //Unhide button
            binding.loginPassword.transformationMethod = PasswordTransformationMethod.getInstance()
        } else {
            //Hide button
            binding.loginPassword.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
        }
    }

    private fun loginExistenceCheck(inputText: String, attributeList: ArrayList<String>): String {
        for (i in attributeList) {
            if (inputText == i) {
                return ""
            }
        }

        //Only reachable if no record matches
        return "Please ensure that your email or phone number exists."
    }

    private fun passwordCheck(contactInfo: String, password: String): String {
        try {
            if (sqliteHelper.conditionalGetAttribute(
                    "contactInfo",
                    "password", password
                ) != contactInfo
            ) {
                return "Your password is invalid. Please enter your password again."
            }
        } catch (ex: Exception) {
            return "Your password is invalid. Please enter your password again."
        }
        return ""
    }

    private fun login() {
        intent = Intent(
            this,
            MainActivity::class.java
        )

        intent.putExtra(
            "username",
            sqliteHelper.conditionalGetAttribute(
                "username",
                "contactInfo",
                binding.loginID.text.toString()
            )
        )
        startActivity(intent)
    }
}