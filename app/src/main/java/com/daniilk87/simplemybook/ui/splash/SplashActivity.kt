package com.daniilk87.simplemybook.ui.splash

import android.os.Handler
import androidx.lifecycle.ViewModelProviders
import com.daniilk87.simplemybook.MainActivity
import com.daniilk87.simplemybook.ui.base.BaseActivity


class SplashActivity : BaseActivity<Boolean?, SplashViewState>(){

    override val viewModel by lazy {
        ViewModelProviders.of(this). get (SplashViewModel::class.java)
    }

    override val layoutRes = null

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({viewModel.requestUser()}, 1000)
    }

    override fun renderData(data: Boolean?) {
        data?.takeIf { it }?.let {
            startMainActivity()
        }
    }

    fun startMainActivity(){
        MainActivity.getStartIntent(this)
        finish()
    }

}