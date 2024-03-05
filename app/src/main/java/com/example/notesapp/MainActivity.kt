package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView

private  const val VAL = "Button Debug"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val createNote = findViewById<Button>(R.id.btnCreateNote)
        val viewNotes = findViewById<Button>(R.id.btnViewNotes)
        val background = findViewById<ImageView>(R.id.background1)
        val backButton = findViewById<Button>(R.id.btnBack)

        init(createNote, background, viewNotes, backButton)

        createNote.setOnClickListener {
            onCreateNote(createNote, background, viewNotes, backButton)
        }
        viewNotes.setOnClickListener {
            onViewNotes(viewNotes, background, createNote, backButton)
        }

        backButton.setOnClickListener {
            onBackButton(viewNotes, background, createNote, backButton)
        }
    }


    private fun init(createButton: Button, background: ImageView, viewButton: Button, backButton: Button)
    {
        createButton.visibility = View.VISIBLE
        background.visibility = View.VISIBLE
        viewButton.visibility = View.VISIBLE
        backButton.visibility = View.GONE
    }
    private fun onCreateNote(createButton: Button, background: ImageView, viewButton: Button, backButton: Button)
    {
        createButton.visibility = View.GONE
        background.visibility = View.GONE
        viewButton.visibility = View.GONE
        backButton.visibility = View.VISIBLE
        Log.d(VAL, "Create notes Pressed")
    }

    private fun onViewNotes(viewButton: Button,background: ImageView, createButton: Button, backButton: Button)
    {
        viewButton.visibility = View.GONE
        createButton.visibility = View.GONE
        background.visibility = View.GONE
        backButton.visibility = View.VISIBLE

        Log.d(VAL, "View notes Pressed")
    }

    private fun onBackButton(viewButton: Button,background: ImageView, createButton: Button, backButton: Button)
    {
        viewButton.visibility = View.VISIBLE
        createButton.visibility = View.VISIBLE
        background.visibility = View.VISIBLE
        backButton.visibility = View.GONE
    }

}

