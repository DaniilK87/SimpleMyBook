package com.daniilk87.simplebook.di

import com.daniilk87.simplebook.data.Repository
import com.daniilk87.simplebook.data.provider.FireStoreProvider
import com.daniilk87.simplebook.data.provider.RemoteDataProvider
import com.daniilk87.simplebook.ui.main.MainViewModel
import com.daniilk87.simplebook.ui.note.NoteViewModel
import com.daniilk87.simplebook.ui.splash.SplashViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


    val appModule = module {
        single { FirebaseAuth.getInstance() }
        single { FirebaseFirestore.getInstance() }
        single<RemoteDataProvider> { FireStoreProvider(get(), get()) }
        single { Repository(get()) }
    }

    val splashModule = module {
        viewModel { SplashViewModel(get()) }
    }

    val mainModule = module {
        viewModel { MainViewModel(get()) }
    }

    val noteModule = module {
        viewModel { NoteViewModel(get()) }
    }
