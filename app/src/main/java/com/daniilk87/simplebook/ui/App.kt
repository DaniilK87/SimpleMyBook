package com.daniilk87.simplebook.ui

import android.app.Application
import org.koin.android.ext.android.startKoin
import com.daniilk87.simplebook.di.appModule
import com.daniilk87.simplebook.di.splashModule
import com.daniilk87.simplebook.di.mainModule
import com.daniilk87.simplebook.di.noteModule

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin(this, listOf(appModule, splashModule, mainModule, noteModule))
    }
}