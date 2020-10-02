package com.example.simplemybook.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.simplemybook.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

abstract class BaseActivity < T, S : BaseViewState<T >> : AppCompatActivity() {
    abstract val viewModel: BaseViewModel<T, S>
    abstract val layoutRes: Int

    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        viewModel.getViewState().observe(this , androidx.lifecycle.Observer { t ->
            t ?: return@Observer
            t.error?.let {
                renderError (it)
                return@Observer
            }
            renderData(t.data)
        })

    }

    protected fun renderError (error: Throwable) {
        if (error.message != null) showError(error.message!!)
    }

     abstract fun renderData (data : T)


    protected fun showError (error: String ) {
        val snackbar = Snackbar.make(mainRecycler, error, Snackbar.LENGTH_INDEFINITE)
        snackbar.show()
    }
}