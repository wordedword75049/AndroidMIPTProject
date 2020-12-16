package com.db.dprice.kisapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
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
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() , NoteAdapter.Listener {
    private fun LoadNotes (): List<Person> {
        return personRepository.loadAll()
    }

    private lateinit var textView: TextView
    private lateinit var context: Context

    private lateinit var personRepository: PersonRepository
    private lateinit var databaseHolder: DatabaseHolder
    private val coroutineScope = MainScope()
    private lateinit var allnotes: List<Person>


    override fun onPersonClick(id: Long) {
        startActivity(NoteDetailActivity.getIntent(this, id))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        context = this
        setContentView(R.layout.activity_person_list)
        //databaseHolder = DatabaseHolder(context)
        //textView = findViewById(R.id.textView)
        //personRepository = PersonRepository(databaseHolder)
    }

    @SuppressLint("StaticFieldLeak")
    override fun onResume() {
        super.onResume()

        coroutineScope.launch {
            val result = withContext(Dispatchers.IO) { // Запуск корутины на фоновом поток + ожидание резульата на главном
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
                //LoadNotes()
            }
            //allnotes = result
            val allnotes : List<Person> = LoadNotes()
            //textView.text = allnotes

            NoteRepository.initialize(context, allnotes)

            val recyclerView = findViewById<RecyclerView>(R.id.personRecyclerView)

            recyclerView.layoutManager = LinearLayoutManager(context)

            recyclerView.setHasFixedSize(true)
            recyclerView.recycledViewPool.setMaxRecycledViews(0, 5)

            val adapter = NoteAdapter()
            recyclerView.adapter = adapter
            adapter.setNoteList(NoteRepository.getPersonList())
            adapter.setListener(this@MainActivity)
        }


    }

    override fun onPause() {
        super.onPause()
        coroutineScope.cancel()
    }

}
