package com.daniilk87.simplebook.data

import com.daniilk87.simplebook.data.provider.FireStoreProvider
import com.daniilk87.simplebook.data.provider.RemoteDataProvider

class Repository (val remoteDataProvider: RemoteDataProvider) {

    fun getNotes () = remoteDataProvider.subscribeToAllNotes()

    suspend fun saveNote (note: Note) = remoteDataProvider.saveNote(note)
    suspend fun getNoteById (id: String) = remoteDataProvider.getNoteById(id)
    suspend fun getCurrentUser () = remoteDataProvider.getCurrentUser()
    suspend fun deleteNotes(id: String) = remoteDataProvider.deleteNote(id)
}