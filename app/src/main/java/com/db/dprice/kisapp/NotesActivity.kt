package com.db.dprice.kisapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class NotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val cardView : CardView = findViewById(R.id.card_view)

        cardView.setOnClickListener {
            val expandedNoteActivityIntent = Intent(applicationContext, ExpandedNoteActivity::class.java)
            startActivity(expandedNoteActivityIntent)
        }
    }
}