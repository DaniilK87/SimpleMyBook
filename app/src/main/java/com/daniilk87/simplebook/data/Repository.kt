package com.daniilk87.simplebook.data

import com.daniilk87.simplebook.data.provider.FireStoreProvider
import com.daniilk87.simplebook.data.provider.RemoteDataProvider

object Repository {
    private val remoteProvider: RemoteDataProvider = FireStoreProvider()

    fun getNotes () = remoteProvider.subscribeToAllNotes()
    fun saveNote (note: Note) = remoteProvider.saveNote(note)
    fun getNoteById (id: String) = remoteProvider.getNoteById(id)
    fun getCurrentUser () = remoteProvider.getCurrentUser()
}