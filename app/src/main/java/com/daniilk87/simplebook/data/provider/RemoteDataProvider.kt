package com.daniilk87.simplebook.data.provider

import androidx.lifecycle.LiveData
import com.daniilk87.simplebook.data.Note
import com.daniilk87.simplebook.data.model.NoteResult
import com.daniilk87.simplebook.data.model.User

interface RemoteDataProvider {
    fun getCurrentUser(): LiveData<User?>
    fun subscribeToAllNotes():LiveData<NoteResult>
    fun getNoteById (id: String):LiveData<NoteResult>
    fun saveNote (note: Note):LiveData<NoteResult>
    fun deleteNote (id: String):LiveData<NoteResult>
}