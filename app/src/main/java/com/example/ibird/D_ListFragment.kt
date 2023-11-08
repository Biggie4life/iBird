package com.example.ibird

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

class D_ListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_d__list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = arrayOf("Dunlin", "African Grey Parrot")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        val listView = view.findViewById<ListView>(R.id.A_ListView)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            // Handle item click
            when (position) {
                0 -> navigateToActivity(Dunlin::class.java)
                1 -> navigateToActivity(DartfordWarbler::class.java)
            }
        }
    }

    private fun navigateToActivity(activityClass: Class<*>) {
        val intent = Intent(requireContext(), activityClass)
        startActivity(intent)
    }

}