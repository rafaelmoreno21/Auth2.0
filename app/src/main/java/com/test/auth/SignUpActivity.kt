package com.test.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()

        buttonEditUp.setOnClickListener{

            signUpUser()

        }





    }

    private fun signUpUser(){

        if (emailEditUp.text.toString().isEmpty()) {
            emailEditUp.error = "Please enter email"
            emailEditUp.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailEditUp.text.toString()).matches()) {
            emailEditUp.error = "Please enter valid email"
            emailEditUp.requestFocus()
            return
        }

        if (passwordEditUp.text.toString().isEmpty()) {
            passwordEditUp.error = "Please enter password"
            passwordEditUp.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(emailEditUp.text.toString(), passwordEditUp.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this,LoginActivity::class.java))
                    finish()

                } else {

                    Toast.makeText(baseContext, "Sign Up failed. Try again",
                        Toast.LENGTH_SHORT).show()

                }

                // ...
            }


    }





}
