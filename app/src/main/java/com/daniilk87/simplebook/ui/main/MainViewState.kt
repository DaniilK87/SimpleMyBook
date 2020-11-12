package com.daniilk87.simplebook.ui.main


import com.daniilk87.simplebook.data.Note
import com.daniilk87.simplebook.ui.base.BaseViewState

class MainViewState (notes: List<Note>? = null , error: Throwable? = null)
    : BaseViewState<List<Note>?>(notes, error)