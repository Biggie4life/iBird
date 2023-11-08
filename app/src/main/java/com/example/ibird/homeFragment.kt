package com.example.ibird

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.ibird.databinding.FragmentHomeBinding
import com.example.ibird.databinding.FragmentLoginBinding
import java.time.Instant

class homeFragment : Fragment() {


    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using the binding object
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set an onClickListener for the tvsignup TextView
        binding.btnDic.setOnClickListener {
            goToFragment(DictionaryFragment())
        }

        binding.btnhotspots.setOnClickListener {
            val intent = Intent(requireContext(), NavigationActivity::class.java)
            startActivity(intent)
        }

        binding.btnsettings.setOnClickListener {
            goToFragment(settingsFragment())
        }

        binding.btnObservation.setOnClickListener {
            val intent = Intent(requireContext(), Observation::class.java)
            startActivity(intent)
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
