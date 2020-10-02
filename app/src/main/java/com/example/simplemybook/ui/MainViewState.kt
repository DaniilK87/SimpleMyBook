package com.example.simplemybook.ui


import com.example.simplemybook.data.Note
import com.example.simplemybook.ui.base.BaseViewState

class MainViewState (notes: List<Note>? = null , error: Throwable? = null)
    : BaseViewState<List<Note>?>(notes, error)