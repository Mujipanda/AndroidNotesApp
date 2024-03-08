package com.example.notesapp

import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.FileOutputStream
import java.text.FieldPosition


public  const val VAL = "output Button Debug"
private  const val SAVE = "output Saved"
class MainActivity : AppCompatActivity() {

    private var titlesList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imageList = mutableListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // recyler view val
        val recyclerView = findViewById<RecyclerView>(R.id.rv_recyclerView)
        // set the reclyer structure to be linear
        recyclerView.layoutManager = LinearLayoutManager(this)
        // passing parameters into the recycler viewer
        //recyclerView.adapter = RecyclerAdapter(titlesList, descList, imageList)
        val RecyclerAdapter = RecyclerAdapter(titlesList)
        // note to self don't touch
        recyclerView.adapter = RecyclerAdapter



        // Id val's
        val createNote = findViewById<Button>(R.id.btnCreateNote);
        val viewNotes = findViewById<Button>(R.id.btnViewNotes);
        val background = findViewById<ImageView>(R.id.background1);
        val backButtonCreateNote = findViewById<Button>(R.id.btnCreateNoteBack);
        val mainMenuConstraint = findViewById<ConstraintLayout>(R.id.mainMenuConstraint)
        val createNoteConstraint = findViewById<ConstraintLayout>(R.id.createNoteConstraint)
        val viewNotesConstraint = findViewById<ConstraintLayout>(R.id.viewNotesConstrinat)
        val backButtonViewNotes = findViewById<Button>(R.id.btnViewNoteBack)
        val title = findViewById<EditText>(R.id.txtTitle)
        val textBox = findViewById<EditText>(R.id.txtNoteBox)
        var txtDebug = findViewById<TextView>(R.id.txtDebug)
        val saveButton = findViewById<Button>(R.id.btnSave)
        val exitKeyBoard = findViewById<Button>(R.id.btnExitKeyboard)
        val deleteButton: Button = findViewById(R.id.btnDeleteNote)



        // initilaise method
        init(createNote, background, viewNotes, mainMenuConstraint, viewNotesConstraint, createNoteConstraint)



        //Create Note button
        createNote.setOnClickListener {

            onCreateNote(mainMenuConstraint, createNoteConstraint)
        }

        //View Notes Button
        viewNotes.setOnClickListener {

           onViewNotes(mainMenuConstraint, viewNotesConstraint)
        }

        // Back Button
        backButtonCreateNote.setOnClickListener {

            onBackButton(mainMenuConstraint, createNoteConstraint)

        }


        // back button
        backButtonViewNotes.setOnClickListener {

            onBackButtonViewNotes(mainMenuConstraint, viewNotesConstraint)
        }
        // save button
        saveButton.setOnClickListener {

            txtDebug = onSaveBtnPress(title.text, textBox.text, txtDebug)
        }

        deleteButton.setOnClickListener {
            deleteNote(RecyclerAdapter);
        }
        // exit keyboard button
        exitKeyBoard.setOnClickListener {

            val view: View? = this.currentFocus

            if(view != null)
            {
                //gets the input manager
                val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

                //tells the input manager to hide the keyboard
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)


                // on screen message
                Toast.makeText(this, "Keyboard hidden", Toast.LENGTH_SHORT).show()
            }
        }
    }


    // method for adding vars to lists
    private  fun removeFromList(adapter: RecyclerAdapter,title: String){
        titlesList.removeAt(currentFile)
        adapter.notifyDataSetChanged()



    }


    private  fun addToList(title: String,/* description: String, image: Int*/)
    {
        titlesList.add(title)
        //RecyclerAdapter(titlesList).notifyDataSetChanged()
        //descList.add(description)
        //imageList.add(image)
        //val RecyclerAdapter = RecyclerAdapter(titlesList)

    }
    private fun postToList(){

        //addToList("Title ", "Desciption", R.mipmap.ic_launcher_round)
     /*  for (i in 1..filesDir.listFiles().size){
            addToList("Title $i", "Desciption$i", R.mipmap.ic_launcher_round)
        }*/
        // loop for how many files are in the internal memory
        for (file in filesDir.listFiles()) {
            //adds en element to the recycler view passing the needed parameters
            //addToList(file.name.toString(), "", R.mipmap.ic_launcher_round)
            //addToList(file.name.toString(), R.mipmap.ic_launcher_round)
            addToList(file.name.toString())
        }
    }

    private fun init(createButton: Button, background: ImageView, viewButton: Button, mainMenu: ConstraintLayout,viewNotes: ConstraintLayout, createNotes: ConstraintLayout)
    {
        // makes sure that the right things are open when you laugh the app
        createButton.visibility = View.VISIBLE
        background.visibility = View.VISIBLE
        viewButton.visibility = View.VISIBLE
        mainMenu.visibility = View.VISIBLE
        viewNotes.visibility = View.GONE
        createNotes.visibility = View.GONE
        postToList()
        //backButton.visibility = View.GONE
    }

    // switches to the create notes page
    private  fun onCreateNote(MainMenu: ConstraintLayout, CreateNote: ConstraintLayout)
    {
        MainMenu.visibility = View.GONE
        CreateNote.visibility = View.VISIBLE
        Log.d(VAL,"CreateNote")
    }


    // switch to the view notes page
    private  fun onViewNotes(mainMenu: ConstraintLayout, viewNotes: ConstraintLayout)
    {
        mainMenu.visibility = View.GONE
        viewNotes.visibility = View.VISIBLE

    }

    // goes back to the main menu
    private fun onBackButton(MainMenu: ConstraintLayout, CreateNote: ConstraintLayout)
    {
        MainMenu.visibility = View.VISIBLE
        CreateNote.visibility = View.GONE
    }

    // goes back to the main menu
    public  fun onBackButtonViewNotes(mainMenu: ConstraintLayout, viewNotes: ConstraintLayout)
    {
        mainMenu.visibility = View.VISIBLE
        viewNotes.visibility = View.GONE
        Toast.makeText(this, currentFile.toString(), Toast.LENGTH_SHORT).show()

    }

    // saves a note when the save button is pressed
    fun onSaveBtnPress(textInput: CharSequence, nameInput: CharSequence, debugInput: TextView) : TextView
    {
        val debugText = findViewById<TextView>(R.id.txtDebug);
        debugInput.text = "";// initiliseing varible


        // if user has put no input for the name of the file
        if(nameInput.isBlank())
        {
            Log.d(SAVE, "name Input is NULL")
            // tells the user what they need to do to correctly save a file
            debugInput.text = "Please input a name before saving"; // shows the user what the issue is
            return debugInput;
        }
        else if(textInput.isBlank())// checks if the the text value is blank
        {
            Log.d(SAVE, "Input is NUll")
            debugInput.text = "Please fill put something in the note before saving"// shows the user what the issue is
            return debugInput;
        }


        else
        {
            // creates the file using the data inputted from the user
            // saved in the local internal storage
            val file = File(filesDir, "$nameInput.text");
            val fileOutput = FileOutputStream(file);
            val content = textInput.toString()
            fileOutput.write(content.toByteArray());
            fileOutput.close();
            Log.d(SAVE, "Input is equal to = $textInput")
            debugInput.text = "File saved as $nameInput succesfully"
            val dir = filesDir
            val files = dir.listFiles()
            Log.d(SAVE, files.size.toString())
        }

        Log.d(VAL, "Save Button Pressed");
        // a little message to show the user the save was succesful
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
        // adds the name saved file to the recycler view

        //addToList(textInput.toString(), "", R.mipmap.ic_launcher_round)
        addToList(textInput.toString())
        return debugInput;
    }

    public  fun openNote(){
        Toast.makeText(this ,"openNote",Toast.LENGTH_SHORT).show()
    }
    private fun deleteNote(adapter: RecyclerAdapter){
        val file = filesDir.listFiles()
        removeFromList(adapter ,file[currentFile].name)

        //file[currentFile].delete()
    }
    public  fun deleteFunc()
    {
        //val file = filesDir.listFiles()
        //Toast.makeText(this, "File Delete", Toast.LENGTH_SHORT).show()
    }

    /*private fun onBackButton(viewButton: Button,background: ImageView, createButton: Button, backButton: Button)
    {
        viewButton.visibility = View.VISIBLE
        createButton.visibility = View.VISIBLE
        background.visibility = View.VISIBLE
        backButton.visibility = View.GONE
    }


    private fun onCreateNote(createButton: Button, background: ImageView, viewButton: Button, backButton: Button, MainMenu: ConstraintLayout)
    {
        createButton.visibility = View.GONE
        background.visibility = View.GONE
        viewButton.visibility = View.GONE
        backButton.visibility = View.VISIBLE
        MainMenu.visibility = View.GONE
        Log.d(VAL, "Create notes Pressed")
    }

    private fun onViewNotes(viewButton: Button,background: ImageView, createButton: Button, backButton: Button)
    {
        viewButton.visibility = View.GONE
        createButton.visibility = View.GONE
        background.visibility = View.GONE
        backButton.visibility = View.VISIBLE

        Log.d(VAL, "View notes Pressed")
    }*/
}

