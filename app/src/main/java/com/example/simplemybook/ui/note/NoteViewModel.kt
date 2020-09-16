package com.example.simplemybook.ui.note

import androidx.lifecycle.ViewModel
import com.example.simplemybook.data.Note
import com.example.simplemybook.data.Repository

class NoteViewModel(private val repository: Repository = Repository) :
    ViewModel() {
    private lateinit var viewModel: NoteViewModel
    private var pendingNote:Note? = null

    fun saveChanges(note: Note) {
        pendingNote = note
    }
    override fun onCleared() {
        if (pendingNote != null ) {
            repository.saveNote(pendingNote!!)
        }
    }
}