package com.daniilk87.simplemybook.data.provider

import androidx.lifecycle.LiveData
import com.daniilk87.simplemybook.data.Note
import com.daniilk87.simplemybook.data.model.NoteResult
import com.daniilk87.simplemybook.data.model.User

interface RemoteDataProvider {
    fun getCurrentUser(): LiveData<User?>
    fun subscribeToAllNotes():LiveData<NoteResult>
    fun getNoteById (id: String):LiveData<NoteResult>
    fun saveNote (note: Note):LiveData<NoteResult>
}