package com.daniilk87.simplemybook.ui.splash

import com.daniilk87.simplemybook.data.Repository
import com.daniilk87.simplemybook.data.error.NoAuthException
import com.daniilk87.simplemybook.ui.base.BaseViewModel

class SplashViewModel (private val repository: Repository = Repository) :
    BaseViewModel<Boolean?, SplashViewState>() {
    fun requestUser () {
        repository.getCurrentUser().observeForever {
            viewStateLiveData.value = if (it != null) {
                SplashViewState(isAuth = true)
            } else {
                SplashViewState(error = NoAuthException())
            }
        }
    }
}