package com.example.ibird

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView


class C_ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_c__list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = arrayOf("Canada goose", "Curlew")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        val listView = view.findViewById<ListView>(R.id.C_ListView)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            // Handle item click
            when (position) {
                0 -> navigateToActivity(Canadagoose::class.java)
                1 -> navigateToActivity(Curlew::class.java)
            }
        }
    }

    private fun navigateToActivity(activityClass: Class<*>) {
        val intent = Intent(requireContext(), activityClass)
        startActivity(intent)
    }

}