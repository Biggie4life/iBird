package com.example.ibird

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit // Import the commit method
import com.example.ibird.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val TAG = "LoginFragment"
    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using the binding object
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvsignup.setOnClickListener {
            try {
                goToFragment(SignupFragment())
            } catch (e: Exception) {
                Log.e(TAG, "Error navigating to SignupFragment: ${e.message}")
            }
        }

        binding.btnLogin.setOnClickListener {
            goToFragment(homeFragment())
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
