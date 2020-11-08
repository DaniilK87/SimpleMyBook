package com.daniilk87.simplebook.ui.main


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.daniilk87.simplebook.LogoutDialog
import com.daniilk87.simplebook.R
import com.daniilk87.simplebook.data.Note
import com.daniilk87.simplebook.data.provider.FireStoreProvider
import com.daniilk87.simplebook.ui.base.BaseActivity
import com.daniilk87.simplebook.ui.note.NoteActivity
import com.daniilk87.simplebook.ui.splash.SplashActivity
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity:BaseActivity<List<Note>?>(), LogoutDialog.LogoutListener {


    companion object {
        fun getStartIntent(context: Context) = Intent(context,
        MainActivity::class.java).apply { context.startActivity(this) }
    }

    override val viewModel:MainViewModel by viewModel()
    override val layoutRes: Int = R.layout.activity_main
    lateinit var adapter : MainAdapter

    override fun onCreate (savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        mainRecycler.layoutManager = GridLayoutManager (this,2)
        adapter = MainAdapter {NoteActivity.start(this,it.id)}


        mainRecycler.adapter = adapter

        fab.setOnClickListener {
        NoteActivity.start(this)}
    }

    override fun renderData (data:List<Note>?) {
        data?.let { adapter.notes= it }
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

    override fun onLogout () {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            }
    }


}



