package com.test.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*


class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        buttonAccount.setOnClickListener{
            startActivity(Intent(this, SignUpActivity:: class.java))
            finish()
        }
        buttonEditIn.setOnClickListener{
            doLogin()
        }


    }


    private fun doLogin() {
        if (emailEditIn.text.toString().isEmpty()) {
            emailEditIn.error = "Please enter email"
            emailEditIn.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailEditIn.text.toString()).matches()) {
            emailEditIn.error = "Please enter valid email"
            emailEditIn.requestFocus()
            return
        }

        if (passwordEditIn.text.toString().isEmpty()) {
            passwordEditIn.error = "Please enter password"
            passwordEditIn.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(emailEditIn.text.toString(), passwordEditIn.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {

                    updateUI(null)
                }
            }
    }





    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?){

        if (currentUser != null) {
            if(currentUser.isEmailVerified) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }else{
                Toast.makeText(
                    baseContext, "Please verify your email address.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                baseContext, "Login failed.",
                Toast.LENGTH_SHORT
            ).show()
        }

    }


}
