package com.example.simplemybook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.simplemybook.data.Note
import com.example.simplemybook.ui.MainAdapter
import com.example.simplemybook.ui.MainViewModel
import com.example.simplemybook.ui.MainViewState
import com.example.simplemybook.ui.note.NoteActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: MainAdapter



    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener {openNoteScreen( null )}

        viewModel = ViewModelProviders.of( this). get (MainViewModel::class.java)
        adapter = MainAdapter(object : MainAdapter.OnItemClickListener {
            override fun onItemClick (note: Note) {
                openNoteScreen(note)
            }
        })
        mainRecycler.adapter = adapter
        viewModel.viewState().observe( this, Observer<MainViewState> { t ->
            t?.let {adapter.notes = it.notes }
        })
    }
    private fun openNoteScreen (note: Note?) {
        val intent = NoteActivity.getStartIntent( this , note)
        startActivity(intent)
    }
}




