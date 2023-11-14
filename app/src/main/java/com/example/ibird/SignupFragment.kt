package com.example.ibird

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.ibird.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SignupFragment : Fragment() {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: FragmentSignupBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using the binding object
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtLogin.setOnClickListener {
            goToFragment(LoginFragment())
        }
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference.child("users")

        binding.btnSignUp.setOnClickListener {
            // Get user data from the UI fields (e.g., name, email, password)
            val Name = binding.txtname.text.toString()
            val LastName = binding.txtLastName.text.toString()
            val Email = binding.txtEmail.text.toString()
            val Password = binding.txtPassword.text.toString()

            if (Name.isNotEmpty() && LastName.isNotEmpty() && Email.isNotEmpty() &&
                Password.isNotEmpty()) {

                auth.createUserWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            // Registration successful
                            val user = auth.currentUser

                            // Create a new user entry in the database
                            val userData = User(Name, LastName, Email,Password)

                            user?.let {
                                val userId = it.uid  // Use the actual user ID from Firebase Authentication
                                databaseReference.child(userId).setValue(userData)
                                goToFragment(homeFragment())
                            }
                        } else {
                            // Handle registration failures
                            val errorMessage = task.exception?.localizedMessage ?: "Registration failed."
                            Toast.makeText(
                                requireContext(),
                                "Error: $errorMessage",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

            }else {
                // Handle empty fields or other registration errors
                Toast.makeText(requireContext(), "Failed to register.", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun goToFragment(fragment: Fragment) {
        fragmentManager = requireActivity().supportFragmentManager // Use requireActivity() to get the Activity's FragmentManager
        fragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null) // Add this line if you want to enable back navigation
        }
    }
}