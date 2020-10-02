package com.example.simplemybook.ui.note

import android.service.voice.VoiceInteractionSession
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.simplemybook.data.Note
import com.example.simplemybook.data.Repository
import com.example.simplemybook.data.model.NoteResult
import com.example.simplemybook.ui.base.BaseViewModel

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