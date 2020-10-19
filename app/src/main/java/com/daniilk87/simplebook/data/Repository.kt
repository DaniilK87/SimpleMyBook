package com.daniilk87.simplebook.data

import com.daniilk87.simplebook.data.provider.FireStoreProvider
import com.daniilk87.simplebook.data.provider.RemoteDataProvider

class Repository (val remoteDataProvider: RemoteDataProvider) {

    fun getNotes () = remoteDataProvider.subscribeToAllNotes()
    fun saveNote (note: Note) = remoteDataProvider.saveNote(note)
    fun getNoteById (id: String) = remoteDataProvider.getNoteById(id)
    fun getCurrentUser () = remoteDataProvider.getCurrentUser()
    fun deleteNotes(id: String) = remoteDataProvider.deleteNote(id)
}