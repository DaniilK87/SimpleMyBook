package com.daniilk87.simplebook.ui.note

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.daniilk87.simplebook.R
import com.daniilk87.simplebook.data.Note
import com.daniilk87.simplebook.extensions.getColorInt
import com.daniilk87.simplebook.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_note.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : BaseActivity<NoteViewState.Data, NoteViewState>() {
    companion object {
        private const val NOTE_KEY = "note"
        private const val DATE_FORMAT = "dd.MM.yy HH:mm"

        fun start(context: Context, noteId: String? = null) =
            Intent(context, NoteActivity::class.java).apply {
            putExtra(NOTE_KEY, noteId)
            context.startActivity(this)
        }
    }

    override val layoutRes: Int = R.layout.activity_note
    private var  note: Note? = null

    override val viewModel: NoteViewModel by viewModel()

    var color: Note.Color = Note.Color.WHITE

    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            saveNote()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val noteId = intent.getStringExtra(NOTE_KEY)

        noteId?.let {
            viewModel.loadNote(it)
        } ?: let {
            supportActionBar?.title =  getString(R.string.new_note_title)
            initView()
        }
    }

    override fun renderData(data: NoteViewState.Data) {
        if (data.isDeleted) {
            finish()
            return
        }
    }

    private fun initView() {
        titleEt.removeTextChangedListener(textWatcher)
        bodyEt.removeTextChangedListener(textWatcher)

        note?.let {
            titleEt.setTextKeepState(it.title)
            bodyEt.setTextKeepState(it.textNote)
            toolbar.setBackgroundColor(it.color.getColorInt(this))
        }

        titleEt.addTextChangedListener(textWatcher)
        bodyEt.addTextChangedListener(textWatcher)
    }

    private fun saveNote() {
        titleEt.text?.let {
            if (it.length < 3) return
        } ?: return

        note = note?.copy(
            title = titleEt.text.toString(),
            textNote = bodyEt.text.toString(),
            lastChanged = Date()
        ) ?: Note(UUID.randomUUID().toString(), titleEt.text.toString(), bodyEt.text.toString())

        note?.let { viewModel.save(it) }

    }

    override fun onCreateOptionsMenu(menu: Menu?) = menuInflater.inflate(R.menu.menu_note, menu).let {true}


    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.palette -> choosePalette().let { true }
        R.id.delete -> deleteNote().let { true }
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    fun choosePalette() {
        if (colorPicker.isOpen) {
            colorPicker.close()
        } else {colorPicker.open()}
    }

    fun deleteNote() {
        AlertDialog.Builder(this)
            .setMessage("Удалить?")
            .setNegativeButton("Нет") {dialog, which -> dialog.dismiss()}
            .setPositiveButton("Да")  {dialog, which -> viewModel.deleteNote()}
            .show()
    }

}