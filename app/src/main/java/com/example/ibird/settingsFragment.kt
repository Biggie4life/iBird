package com.example.ibird

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat.recreate

class settingsFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val modeSwitch = view.findViewById<Switch>(R.id.modeSwitch)
        val unitRadioGroup = view.findViewById<RadioGroup>(R.id.unitRadioGroup)
        val distanceSeekBar = view.findViewById<SeekBar>(R.id.distanceSeekBar)
        val distanceTextView = view.findViewById<TextView>(R.id.distanceTextView)

        // Check the current mode and set the switch accordingly
        modeSwitch.isChecked = (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)

        // Set a listener to toggle the mode when the switch is clicked
        modeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Switch to dark mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                // Switch to light mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            requireActivity().recreate() // Recreate the activity to apply the new mode
        }

    // Handle unit selection (metric or imperial)
    unitRadioGroup.setOnCheckedChangeListener { _, checkedId ->
        when (checkedId) {
            R.id.metricRadioButton -> {
                // Handle metric selection
                saveUnitPreference("metric")
            }
            R.id.imperialRadioButton -> {
                // Handle imperial selection
                saveUnitPreference("imperial")
            }
        }
    }


    // Handle maximum distance selection
    distanceSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            // Update the displayed distance value
            distanceTextView.text = "Maximum Distance: $progress km"
            // Save the distance preference
            saveDistancePreference(progress)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
            // Not used
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            // Not used
        }
    })

    return view
    }

private fun saveUnitPreference(unit: String) {
    // Save the selected unit (metric or imperial) to SharedPreferences or other storage
    val preferences = requireActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE)
    val editor = preferences.edit()
    editor.putString("unit", unit)
    editor.apply()
}

private fun saveDistancePreference(distance: Int) {
    // Save the selected maximum distance to SharedPreferences or other storage
    val preferences = requireActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE)
    val editor = preferences.edit()
    editor.putInt("distance", distance)
    editor.apply()
}
}