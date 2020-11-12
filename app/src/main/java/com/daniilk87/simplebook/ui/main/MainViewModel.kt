package com.daniilk87.simplebook.ui.main

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import com.daniilk87.simplebook.data.Note
import com.daniilk87.simplebook.data.Repository
import com.daniilk87.simplebook.data.model.NoteResult
import com.daniilk87.simplebook.ui.base.BaseViewModel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class MainViewModel (val repository: Repository) : BaseViewModel<List<Note>?>() {

    private val notesChannel = repository.getNotes()

    init {
        launch {
            notesChannel.consumeEach {
                when (it) {
                    is NoteResult.Success<*> -> setData(it.data as? List<Note>)
                    is NoteResult.Error -> setError(it.error)
                    }
                }
        }
    }

    @VisibleForTesting
    public override fun onCleared () {
        notesChannel.cancel()
    }
}