package com.daniilk87.simplebook.ui.note

import com.daniilk87.simplebook.data.Note
import com.daniilk87.simplebook.ui.base.BaseViewState

class NoteViewState(data: Data = Data(), error: Throwable? = null) : BaseViewState<NoteViewState.Data>(data, error) {
    data class Data(val isDeleted: Boolean = false, val note: Note? = null)
}