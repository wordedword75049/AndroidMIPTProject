package com.db.dprice.kisapp

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.db.dprice.kisapp.database.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private fun LoadNotes (): String {
        return personRepository!!.loadAll().toString()
    }

    private lateinit var textView: TextView
    private lateinit var context: Context
    private lateinit var personRepository: PersonRepository
    private lateinit var databaseHolder: DatabaseHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        context = this

        databaseHolder = DatabaseHolder(context)
        textView = findViewById(R.id.textView)
        personRepository = PersonRepository(databaseHolder)

        for (i in 0..9){
            val person = Person()
            person.setName(UUID.randomUUID().toString())
            person.setPath(UUID.randomUUID().toString())
            person.setDate(Calendar.getInstance().toString())

            personRepository.create(person)
        }
        //val allnotes : String = LoadNotes()
        //textView.text = allnotes
    }

}
