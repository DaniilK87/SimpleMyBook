package com.daniilk87.simplebook.ui.note

import com.daniilk87.simplebook.data.Note
import com.daniilk87.simplebook.data.Repository
import com.daniilk87.simplebook.data.model.NoteResult
import com.daniilk87.simplebook.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class NoteViewModel(val repository: Repository) : BaseViewModel<NoteData>() {



    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    fun loadNote(noteId: String) = launch {
        try {
            repository.getNoteById(noteId).let {
                setData(NoteData(note = it))
            }
        } catch (e: Throwable) {
            setError(e)
        }
    }


    fun deleteNote() = launch {
        try {
            pendingNote?.let { repository.deleteNotes(it.id)}
            setData(NoteData(isDeleted = true))
        }catch (e:Throwable) {
            setError(e)
        }
    }

    override fun onCleared() {
        launch {pendingNote?.let {
            repository.saveNote(it)
            }
        }
    }

}