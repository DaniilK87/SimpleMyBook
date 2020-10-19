package com.daniilk87.simplebook.ui.note

import com.daniilk87.simplebook.data.Note
import com.daniilk87.simplebook.data.Repository
import com.daniilk87.simplebook.data.model.NoteResult
import com.daniilk87.simplebook.ui.base.BaseViewModel

class NoteViewModel(val repository: Repository) : BaseViewModel<NoteViewState.Data, NoteViewState>() {

    init {
        viewStateLiveData.value = NoteViewState()
    }

    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    fun loadNote(noteId: String) {
        repository.getNoteById(noteId).observeForever { result ->
            result ?: return@observeForever
            when(result){
                is NoteResult.Success<*> -> {
                    pendingNote = result.data as? Note
                    viewStateLiveData.value = NoteViewState(NoteViewState.Data(note = pendingNote))
                }
                is NoteResult.Error -> viewStateLiveData.value = NoteViewState(error = result.error)
            }
        }
    }

    fun deleteNote() {
        pendingNote?.let {repository.deleteNotes(it.id).observeForever { result ->
            result ?: return@observeForever
            pendingNote = null
            when(result) {
                is NoteResult.Success<*> -> viewStateLiveData.value = NoteViewState(NoteViewState.Data(isDeleted = true))
                is NoteResult.Error -> viewStateLiveData.value = NoteViewState(error = result.error)
            }
            }
        }
    }

    override fun onCleared() {
        pendingNote?.let {
            repository.saveNote(it)
        }
    }

}