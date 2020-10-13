package com.daniilk87.simplebook.ui.note

import com.daniilk87.simplebook.data.Note
import com.daniilk87.simplebook.ui.base.BaseViewState

class NoteViewState (note: Note? = null, error: Throwable? = null) : BaseViewState<Note?> (note, error)