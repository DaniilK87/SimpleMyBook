package com.example.simplemybook.data

object Repository {
    private val notes:List<Model.Note>

    init {
        notes = listOf(
            Model.Note(
                "Заметка №1",
                "Место для первой заметки",
                0xfff06292.toInt()
            ),
            Model.Note(
                "Заметка №2",
                "Место для второй заметки",
                0xff9575cd.toInt()
            ),
            Model.Note(
                "Заметка №3",
                "Место для третей заметки",
                0xff64b5f6.toInt()
            ),
            Model.Note(
                "Заметка №4",
                "Место для четверотой заметки",
                0xff4db6ac.toInt()
            ),
            Model.Note(
                "Заметка №5",
                "Место для пятой заметки",
                0xffb2ff59.toInt()
            ),
            Model.Note(
                "Заметка №6",
                "Место для шестой заметки",
                0xffffeb3b.toInt()
            ),
            Model.Note(
                "Заметка №7",
                "Место для седьмой заметки",
                0xffff6e40.toInt()
            ),
            Model.Note(
                "Заметка №8",
                "Место для восьмой заметки",
                0xff800000.toInt()
        )
        )
    }
    fun getNotes (): List<Model.Note> {
        return notes
    }
}