package com.example.simplemybook.ui

import androidx.lifecycle.Observer
import com.example.simplemybook.data.Note
import com.example.simplemybook.data.Repository
import com.example.simplemybook.data.model.NoteResult
import com.example.simplemybook.ui.base.BaseViewModel

class MainViewModel (val repository: Repository = Repository) :
    BaseViewModel<List<Note>?, MainViewState>() {
    private val notesObserver = object:Observer<NoteResult> {
        override fun onChanged (t:NoteResult?) {
            if (t == null) return
            when (t) {
                is NoteResult.Success<*> -> {
                    viewStateLiveData.value = MainViewState(notes = t.data as? List<Note>)
                }
                is NoteResult.Error -> {
                    viewStateLiveData.value = MainViewState(error = t.error)
                }
            }
        }
    }

    private val repositoryNotes = repository.getNotes()
    init {
        viewStateLiveData.value = MainViewState()
        repositoryNotes.observeForever(notesObserver)
    }

    override fun onCleared () {
        repositoryNotes.removeObserver(notesObserver)
    }
}