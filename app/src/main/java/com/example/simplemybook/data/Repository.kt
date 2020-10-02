package com.example.simplemybook.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simplemybook.data.provider.FireStoreProvider
import com.example.simplemybook.data.provider.RemoteDataProvider
import java.util.*

object Repository {
    private val remoteProvider: RemoteDataProvider = FireStoreProvider()

    fun getNotes () = remoteProvider.subscribeToAllNotes()
    fun saveNote (note: Note) = remoteProvider.saveNote(note)
    fun getNoteById (id: String) = remoteProvider.getNoteById(id)
}