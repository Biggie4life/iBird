package com.example.ibird

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

data class Results(
    val birdName: String,
    val timestamp: Long,
    val location: String,
    val notes: String
)

class Observation : AppCompatActivity() {
    private val observations = mutableListOf<Results>()

    private lateinit var birdNameEditText: EditText
    private lateinit var locationEditText: EditText
    private lateinit var notesEditText: EditText
    private lateinit var addObservationButton: Button
    private lateinit var viewObservationsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_observation)



        birdNameEditText = findViewById(R.id.txtBirdName)
        locationEditText = findViewById(R.id.txtLocation)
        notesEditText = findViewById(R.id.txtNotes)
        addObservationButton = findViewById(R.id.btnObservation)
        viewObservationsTextView = findViewById(R.id.viewObservationsTextView)

        addObservationButton.setOnClickListener {
            val birdName = birdNameEditText.text.toString()
            val location = locationEditText.text.toString()
            val notes = notesEditText.text.toString()

            addObservation(birdName, location, notes)
            updateObservationsTextView()
        }
    }

    private fun addObservation(birdName: String, location: String, notes: String) {
        val timestamp = System.currentTimeMillis()
        val observation = Results(birdName, timestamp, location, notes)
        observations.add(observation)
    }

    private fun updateObservationsTextView() {
        if (observations.isEmpty()) {
            viewObservationsTextView.text = "No observations yet."
        } else {
            val observationsText = buildString {
                append("All Observations:\n")
                observations.forEach { append("$it\n") }
            }
            viewObservationsTextView.text = observationsText
        }
    }
}