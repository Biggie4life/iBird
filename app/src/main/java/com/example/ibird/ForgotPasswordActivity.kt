package com.example.ibird

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var emailEditText: EditText
    private lateinit var resetButton: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        auth = FirebaseAuth.getInstance()

        emailEditText = findViewById(R.id.emailEditText)
        resetButton = findViewById(R.id.resetButton)

        resetButton.setOnClickListener {
            resetPassword()
        }
    }

    private fun resetPassword() {
        val email = emailEditText.text.toString().trim()

        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show()
            return
        }

        // Send password reset email
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task: Task<Void> ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@ForgotPasswordActivity,
                        "Password reset email sent. Check your email inbox.",
                        Toast.LENGTH_SHORT
                    ).show()

                    goToFragment(LoginFragment())
                } else {
                    Toast.makeText(
                        this@ForgotPasswordActivity,
                        "Failed to send password reset email. Please check your email address.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }
        private fun goToFragment(fragment: Fragment) {
            fragmentManager = this.supportFragmentManager // Use requireActivity() to get the Activity's FragmentManager
            fragmentManager.commit {
                replace(R.id.fragment_container, fragment)
                addToBackStack(null) // Add this line if you want to enable back navigation
            }
        }
    }
