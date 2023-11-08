package com.example.ibird

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

class birdlistFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_birdlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create an array with the letters of the alphabet A to E
        val alphabet = ('A'..'E').toList().toTypedArray()

        // Divide the alphabet into 4 list items
        val items = arrayOf(
            "${alphabet[0]}",
            "${alphabet[1]}",
            "${alphabet[2]}",
            "${alphabet[3]}",
            "${alphabet[4]}"
        )

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        val listView = view.findViewById<ListView>(R.id.A_ListView)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            // Handle item click
            when (position) {
                0 -> navigateToFragment(A_ListFragment())
                1 -> navigateToFragment(B_ListFragment())
                2 -> navigateToFragment(C_ListFragment())
                3 -> navigateToFragment(D_ListFragment())
                4 -> navigateToFragment(E_ListFragment())
            }
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
