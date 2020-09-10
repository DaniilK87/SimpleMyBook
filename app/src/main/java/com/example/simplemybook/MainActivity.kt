package com.example.simplemybook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.simplemybook.ui.MainAdapter
import com.example.simplemybook.ui.MainViewModel
import com.example.simplemybook.ui.MainViewState
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: MainAdapter


    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of( this). get (MainViewModel::class.java)
        adapter = MainAdapter()
        mainRecycler.adapter = adapter
        viewModel.viewState().observe( this, Observer<MainViewState> { t ->
            t?.let {adapter.notes = it.notes }
        })
    }
}




