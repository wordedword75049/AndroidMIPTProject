package com.db.dprice.kisapp

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.db.dprice.kisapp.database.DatabaseHolder
import com.db.dprice.kisapp.database.Person
import com.db.dprice.kisapp.database.PersonRepository
import com.db.dprice.kisapp.resview.NoteAdapter
import com.db.dprice.kisapp.resview.NoteDetailActivity
import com.db.dprice.kisapp.resview.NoteRepository
import java.util.*


class MainActivity : AppCompatActivity() , NoteAdapter.Listener {
    private fun LoadNotes (): List<Person> {
        return personRepository.loadAll()
    }

    private lateinit var textView: TextView
    private lateinit var context: Context
    private lateinit var personRepository: PersonRepository
    private lateinit var databaseHolder: DatabaseHolder


    override fun onPersonClick(id: Long) {
        startActivity(NoteDetailActivity.getIntent(this, id))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        context = this
        setContentView(R.layout.activity_person_list)
        databaseHolder = DatabaseHolder(context)
        //textView = findViewById(R.id.textView)
        personRepository = PersonRepository(databaseHolder)



        for (i in 0..30){
            val person = Person()
            person.setName(UUID.randomUUID().toString())
            person.setPath(UUID.randomUUID().toString())
            person.setDate(Calendar.getInstance().time.toString())

            personRepository.create(person)
        }
        val allnotes : List<Person> = LoadNotes()
        //textView.text = allnotes

        NoteRepository.initialize(context, allnotes)

        val recyclerView = findViewById<RecyclerView>(R.id.personRecyclerView)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        } else {
            recyclerView.layoutManager = LinearLayoutManager(this)
        }
        recyclerView.setHasFixedSize(true)
        recyclerView.recycledViewPool.setMaxRecycledViews(0, 5)

        val adapter = NoteAdapter()
        recyclerView.adapter = adapter
        adapter.setNoteList(NoteRepository.getPersonList())
        adapter.setListener(this)

    }

}
