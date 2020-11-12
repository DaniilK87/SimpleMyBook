package com.daniilk87.simplebook.ui.splash

import com.daniilk87.simplebook.ui.base.BaseViewState

class SplashViewState (isAuth: Boolean ? = null , error: Throwable? = null) :
    BaseViewState<Boolean?>(isAuth, error)