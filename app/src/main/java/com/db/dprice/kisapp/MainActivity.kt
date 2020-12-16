package com.db.dprice.kisapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.db.dprice.kisapp.database.DatabaseHolder
import com.db.dprice.kisapp.database.Person
import com.db.dprice.kisapp.database.PersonRepository
import com.db.dprice.kisapp.fragment.DemoActivity
import com.db.dprice.kisapp.fragment.DetailFragment
import com.db.dprice.kisapp.fragment.ListFragment
import com.db.dprice.kisapp.resview.NoteAdapter
import com.db.dprice.kisapp.resview.NoteDetailActivity
import com.db.dprice.kisapp.resview.NoteRepository
import kotlinx.coroutines.*
import java.util.*
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() , NoteAdapter.Listener {
    private fun LoadNotes (): List<Person> {
        return personRepository.loadAll()
    }

    fun getIntent(context: Context): Intent? {
        return Intent(context, DemoActivity::class.java)
    }

    private var detailFrameLayoutId by Delegates.notNull<Int>()
    private lateinit var textView: TextView
    private lateinit var context: Context
    private var instance : Bundle? = null
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
        //setContentView(R.layout.activity_person_list)
        //databaseHolder = DatabaseHolder(context)
        //textView = findViewById(R.id.textView)
        //personRepository = PersonRepository(databaseHolder)
        setContentView(R.layout.activity_demo)

        detailFrameLayoutId = if (resources.getBoolean(R.bool.is_pad)) {
            R.id.demoDetailContainer
        } else {
            R.id.demoMainContainer
        }

        instance = savedInstanceState


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
                LoadNotes()
            }
            allnotes = result
            //val allnotes : List<Person> = LoadNotes()
            //textView.text = allnotes

            NoteRepository.initialize(context, allnotes)

            if (instance == null) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.demoMainContainer, ListFragment.newInstance(), ListFragment.TAG)
                    .addToBackStack(null)
                    .commit()
            }
            //startActivity(DemoActivity.getIntent(this@MainActivity))

        }


    }

    fun showDetailFragment(id: Long) {
        if (supportFragmentManager.findFragmentByTag(DetailFragment.TAG) != null) {
            // Если на экране уже есть фрагмент с деталями, то надо его убрать перед показом нового
            supportFragmentManager.popBackStack()
        }
        supportFragmentManager
            .beginTransaction()
            .replace(detailFrameLayoutId, DetailFragment.newInstance(id), DetailFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun onPause() {
        super.onPause()
        coroutineScope.cancel()
    }

}
