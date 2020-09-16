package com.example.simplemybook.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

object Repository {
    private var notesLiveData = MutableLiveData<List<Note>>()

    var notes:MutableList<Note> = mutableListOf(
        Note(
            id = UUID.randomUUID().toString(),
            title = "Заметка №1",
            note = "Место для первой заметки",
                    color = Color.WHITE
        ),
        Note(
            id = UUID.randomUUID().toString(),
            title = "Заметка №2",
            note = "Место для второй заметки",
                    color = Color.BLUE
        ),
        Note(
            id = UUID.randomUUID().toString(),
            title = "Заметка №3",
            note = "Место для третий заметки",
                    color = Color.GREEN
        ),
        Note(
            id = UUID.randomUUID().toString(),
            title = "Заметка №4",
            note = "Место для четвертой заметки",
                    color = Color.PINK
        ),
        Note(
            id = UUID.randomUUID().toString(),
            title = "Заметка №5",
            note = "Место для пятой заметки",
                    color = Color.RED
        ),
        Note(
            id = UUID.randomUUID().toString(),
            title = "Заметка №6",
            note = "Место для шестой заметки" ,
                    color = Color.YELLOW
        ),
        Note(
            id = UUID.randomUUID().toString(),
            title = "Заметка №7",
            note = "Место для седьмой заметки" ,
                    color = Color.VIOLET
        )
    )

    init {
        notesLiveData.value = notes
    }

    fun getNotes (): LiveData<List<Note>> {
        return notesLiveData
    }

    fun saveNote(note: Note ) {
        addOrReplace(note)
        notesLiveData.value = notes
    }

    private fun addOrReplace(note: Note ) {
        for (i in 0 until notes.size) {
            if (notes[i] == note) {
                notes. set (i, note)
                return
            }
        }
        notes.add(note)
    }

}