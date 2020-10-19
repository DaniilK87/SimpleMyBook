package com.daniilk87.simplebook.ui.splash

import com.daniilk87.simplebook.data.Repository
import com.daniilk87.simplebook.data.error.NoAuthException
import com.daniilk87.simplebook.ui.base.BaseViewModel

class SplashViewModel (val repository: Repository): BaseViewModel<Boolean?, SplashViewState>() {
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