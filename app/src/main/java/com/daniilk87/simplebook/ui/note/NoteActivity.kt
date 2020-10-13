package com.daniilk87.simplebook.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.daniilk87.simplebook.R
import com.daniilk87.simplebook.data.Note
import com.daniilk87.simplebook.data.extensions.format
import com.daniilk87.simplebook.data.extensions.getColorInt
import com.daniilk87.simplebook.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_note.*
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : BaseActivity<Note?, NoteViewState>() {
    companion object {
        private const val NOTE_KEY = "note"
        private const val DATE_FORMAT = "dd.MM.yy HH:mm"

        fun start(context: Context, noteId: String? = null) = Intent(context, NoteActivity::class.java).apply {
            putExtra(NOTE_KEY, noteId)
            context.startActivity(this)
        }
    }

    override val layoutRes: Int = R.layout.activity_note
    private var note: Note? = null
    override val viewModel by lazy {ViewModelProviders.of(this). get(NoteViewModel:: class.java)}

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

    override fun renderData(data: Note?) {
        this.note = data
        supportActionBar?.title = note?.let {
            SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(it.lastChanged)
        }?: getString(R.string.new_note_title)
        initView()
    }

    private fun initView() {
        note?.run {
            supportActionBar?.title = lastChanged.format()
            titleEt.setText(title)
            bodyEt.setText(textNote)
            toolbar.setBackgroundColor(color.getColorInt( this@NoteActivity ))
        }
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

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}