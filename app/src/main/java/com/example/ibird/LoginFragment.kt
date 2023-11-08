package com.example.ibird

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit // Import the commit method
import com.example.ibird.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var fragmentManager: FragmentManager
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var userReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        userReference = database.getReference("Users")

        binding.btnLogin.setOnClickListener {
            loginUser()
        }

        binding.tvsignup.setOnClickListener {
            goToFragment(SignupFragment())
        }

    }

    private fun loginUser() {
        val emailEditText = view?.findViewById<EditText>(R.id.txtEmail)
        val passwordEditText = view?.findViewById<EditText>(R.id.txtPassword)

        val email = emailEditText?.text.toString()
        val password = passwordEditText?.text.toString()

        val sanitizedEmail = encodeEmail(email)

        userReference.child(sanitizedEmail)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val user = dataSnapshot.getValue(User::class.java)
                    if (user != null && user.password == password) {
                        // Authentication successful, navigate to the next screen (e.g., homeFragment).
                        val fragment = homeFragment() // Assuming you have a HomeFragment class.
                        parentFragmentManager.commit {
                            replace(R.id.fragment_container, fragment)
                            addToBackStack(null) // Add this line if you want to enable back navigation
                        }
                    } else {
                        Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(
                        requireContext(),
                        "Error retrieving user data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    private fun encodeEmail(email: String): String {
        return email.replace(".", ",")
    }

    private fun goToFragment(fragment: Fragment) {
        fragmentManager = requireActivity().supportFragmentManager // Use requireActivity() to get the Activity's FragmentManager
        fragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null) // Add this line if you want to enable back navigation
        }
    }
}