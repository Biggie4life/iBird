package com.example.ibird

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class Observation : AppCompatActivity() {
    private val observations = mutableListOf<observationdata>()

    private lateinit var birdNameEditText: EditText
    private lateinit var locationEditText: EditText
    private lateinit var notesEditText: EditText
    private lateinit var addObservationButton: Button
    private lateinit var viewObservationsTextView: TextView

    private val uid = FirebaseAuth.getInstance().currentUser?.uid


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_observation)

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

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
        val observation = observationdata(birdName, timestamp, location, notes)

        val uid = FirebaseAuth.getInstance().currentUser?.uid
        uid?.let {
            val database = FirebaseDatabase.getInstance()
            val observationsReference = database.getReference("observations/$it")

            val newobservationReference = observationsReference.push()
            newobservationReference.setValue(observation)
            observations.add(observation)
        }
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