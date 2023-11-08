package com.example.ibird

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

class DictionaryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dictionary, container, false)
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            // Create an array with the letters of the alphabet A to Z
            val alphabet = ('A'..'Z').toList().toTypedArray()

            // Divide the alphabet into 5 list items
            val items = arrayOf(
                "${alphabet[0]}-${alphabet[4]}",
                "${alphabet[5]}-${alphabet[9]}",
                "${alphabet[10]}-${alphabet[14]}",
                "${alphabet[15]}-${alphabet[19]}",
                "${alphabet[20]}-${alphabet.last()}"
            )

            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
            val listView = view.findViewById<ListView>(R.id.listView)
            listView.adapter = adapter

            listView.setOnItemClickListener { parent, view, position, id ->
                // Handle item click
                when (position) {
                    0 -> navigateToFragment(birdlistFragment())
                    // Add more cases if needed
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