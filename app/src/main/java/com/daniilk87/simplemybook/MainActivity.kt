package com.daniilk87.simplemybook


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.daniilk87.simplemybook.data.Note
import com.daniilk87.simplemybook.ui.MainAdapter
import com.daniilk87.simplemybook.ui.MainViewModel
import com.daniilk87.simplemybook.ui.MainViewState
import com.daniilk87.simplemybook.ui.base.BaseActivity
import com.daniilk87.simplemybook.ui.note.NoteActivity
import com.daniilk87.simplemybook.ui.splash.SplashActivity
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity:BaseActivity<List<Note>?, MainViewState>(), LogoutDialog.LogoutListener {

    override fun onLogout () {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            }
    }

    companion object {fun getStartIntent(context: Context) = Intent(context,
        MainActivity::class.java)
    }

    override val viewModel:MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)}

    override val layoutRes: Int = R.layout.activity_main
    private lateinit var adapter:MainAdapter

    override fun onCreate (savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        adapter = MainAdapter(object : MainAdapter.OnItemClickListener {
            override fun onItemClick (note: Note ) {
                openNoteScreen(note)
            }
        })
        mainRecycler.adapter = adapter
        fab.setOnClickListener(object:View.OnClickListener {
            override fun onClick (v: View?) {
                openNoteScreen(null)
            }
        })
    }

    override fun renderData (data:List<Note>?) {
        if (data == null)return
        adapter.notes=data
    }

    private fun openNoteScreen (note: Note?) {
        val intent = NoteActivity.start(this, note?.id)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu (menu: Menu?): Boolean =
        MenuInflater(this).inflate(R.menu.menu_main, menu).let { true }
    override fun onOptionsItemSelected (item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.logout -> showLogoutDialog().let {true}
            else -> false
        }

    private fun showLogoutDialog () {
        supportFragmentManager.findFragmentByTag(LogoutDialog.TAG) ?:
        LogoutDialog.createInstance().show(supportFragmentManager,
            LogoutDialog.TAG)
    }

}



