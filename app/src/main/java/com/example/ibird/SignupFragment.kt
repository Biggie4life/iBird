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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SignupFragment : Fragment() {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: FragmentSignupBinding
    private lateinit var database: DatabaseReference

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

        binding.btnSignUp.setOnClickListener {
            // Get user data from the UI fields (e.g., name, email, password)
            val Name = binding.txtname.text.toString()
            val LastName = binding.txtLastName.text.toString()
            val Email = binding.txtEmail.text.toString()
            val Password = binding.txtPassword.text.toString()

            // Encode the email address to create a valid path in the database
            val sanitizedEmail = encodeEmail(Email)

            database = FirebaseDatabase.getInstance().getReference("Users")
            val user = User(Name, LastName, sanitizedEmail, Password)

            database.child(sanitizedEmail).setValue(user).addOnSuccessListener {
                binding.txtname.text.clear()
                binding.txtLastName.text.clear()
                binding.txtEmail.text.clear()
                binding.txtPassword.text.clear()

                Toast.makeText(requireContext(), "Register Successful", Toast.LENGTH_SHORT).show()
                goToFragment(homeFragment())

            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Registration Failed", Toast.LENGTH_SHORT).show()
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
    private fun encodeEmail(email: String): String {
        return email.replace(".", ",")
    }
}