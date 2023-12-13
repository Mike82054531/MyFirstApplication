package com.jnu.student

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

// BookDetailsActivity.java


class BookDetailsActivity : AppCompatActivity() {
    private var editTextBookName: EditText? = null
    private var position = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)
        editTextBookName = findViewById<EditText>(R.id.editTextBookName)
        val buttonSave = findViewById<Button>(R.id.buttonSave)
        val buttonCancel = findViewById<Button>(R.id.buttonCancel)

        // Check if editing an existing book or creating a new one
        val intent = intent
        if (intent.hasExtra("bookName")) {
            val bookName = intent.getStringExtra("bookName")
            position = intent.getIntExtra("position", -1)
            editTextBookName.setText(bookName)
        }
        buttonSave.setOnClickListener { saveBookDetails() }
        buttonCancel.setOnClickListener { finish() }
    }

    private fun saveBookDetails() {
        val editedBookName = editTextBookName!!.text.toString()
        if (editedBookName.trim { it <= ' ' }.isEmpty()) {
            // Ensure the book name is not empty
            editTextBookName!!.error = "Please enter a book name"
        } else {
            // Create an intent to pass back the edited book details
            val resultIntent = Intent()
            resultIntent.putExtra("editedBookName", editedBookName)
            resultIntent.putExtra("position", position)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
