package com.daniilk87.simplemybook.ui.note

import com.daniilk87.simplemybook.data.Note
import com.daniilk87.simplemybook.ui.base.BaseViewState

class NoteViewState (note: Note? = null, error: Throwable? = null) : BaseViewState<Note?> (note, error)