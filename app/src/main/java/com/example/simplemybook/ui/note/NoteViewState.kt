package com.example.simplemybook.ui.note

import com.example.simplemybook.data.Note
import com.example.simplemybook.ui.base.BaseViewState
import java.lang.Error

class NoteViewState (note: Note? = null, error: Throwable? = null) : BaseViewState<Note?> (note, error)