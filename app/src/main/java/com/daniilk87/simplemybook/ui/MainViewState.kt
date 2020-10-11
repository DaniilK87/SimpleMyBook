package com.daniilk87.simplemybook.ui


import com.daniilk87.simplemybook.data.Note
import com.daniilk87.simplemybook.ui.base.BaseViewState

class MainViewState (notes: List<Note>? = null , error: Throwable? = null)
    : BaseViewState<List<Note>?>(notes, error)