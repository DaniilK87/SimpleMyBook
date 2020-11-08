package com.daniilk87.simplebook.ui.splash

import com.daniilk87.simplebook.data.Repository
import com.daniilk87.simplebook.data.error.NoAuthException
import com.daniilk87.simplebook.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SplashViewModel (val repository: Repository): BaseViewModel<Boolean?>() {

    fun requestUser() = launch { repository.getCurrentUser()?.let {
        setData(true)
        } ?: setError(NoAuthException())
    }
}