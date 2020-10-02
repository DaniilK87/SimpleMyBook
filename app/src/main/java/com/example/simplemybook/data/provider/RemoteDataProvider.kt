package com.example.simplemybook.data.provider

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import com.example.simplemybook.data.Note
import com.example.simplemybook.data.model.NoteResult

interface RemoteDataProvider {
    fun subscribeToAllNotes():LiveData<NoteResult>
    fun getNoteById (id: String):LiveData<NoteResult>
    fun saveNote (note: Note):LiveData<NoteResult>
}