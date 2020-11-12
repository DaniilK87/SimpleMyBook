package com.daniilk87.simplebook.data.provider

import androidx.lifecycle.LiveData
import com.daniilk87.simplebook.data.Note
import com.daniilk87.simplebook.data.model.NoteResult
import com.daniilk87.simplebook.data.model.User
import kotlinx.coroutines.channels.ReceiveChannel

interface RemoteDataProvider {

    fun subscribeToAllNotes(): ReceiveChannel<NoteResult>

    suspend fun getCurrentUser(): User?
    suspend fun getNoteById (id: String): Note?
    suspend fun saveNote (note: Note): Note?
    suspend fun deleteNote (id: String)
}