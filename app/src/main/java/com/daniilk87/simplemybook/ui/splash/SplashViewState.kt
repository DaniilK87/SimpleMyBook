package com.daniilk87.simplemybook.ui.splash

import com.daniilk87.simplemybook.ui.base.BaseViewState

class SplashViewState (isAuth: Boolean ? = null , error: Throwable? = null) :
    BaseViewState<Boolean?>(isAuth, error)