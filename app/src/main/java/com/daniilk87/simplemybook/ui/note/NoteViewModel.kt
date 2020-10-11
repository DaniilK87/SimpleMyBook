package com.daniilk87.simplemybook.ui.note

import com.daniilk87.simplemybook.data.Note
import com.daniilk87.simplemybook.data.Repository
import com.daniilk87.simplemybook.data.model.NoteResult
import com.daniilk87.simplemybook.ui.base.BaseViewModel

class NoteViewModel : BaseViewModel<Note?, NoteViewState>() {

    init {
        viewStateLiveData.value = NoteViewState()
    }

    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    fun loadNote(noteId: String) {
        Repository.getNoteById(noteId).observeForever { result ->
            result ?: return@observeForever
            when(result){
                is NoteResult.Success<*> ->  viewStateLiveData.value = NoteViewState(note = result.data as? Note)
                is NoteResult.Error -> viewStateLiveData.value = NoteViewState(error = result.error)
            }
        }
    }

    override fun onCleared() {
        pendingNote?.let {
            Repository.saveNote(it)
        }
    }

}