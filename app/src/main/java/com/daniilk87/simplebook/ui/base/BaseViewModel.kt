package com.daniilk87.simplebook.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.okhttp.Dispatcher
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import java.lang.Error
import kotlin.coroutines.CoroutineContext

open class BaseViewModel <S>:ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Default + Job()
    }

    private val viewStateChanel = BroadcastChannel<S>(Channel.CONFLATED)
    private val errorChannel = Channel<Throwable>()

    fun getViewState (): ReceiveChannel<S> = viewStateChanel.openSubscription()
    fun getViewError (): ReceiveChannel<Throwable> = errorChannel

    protected fun setError(e:Throwable) = launch {
        errorChannel.send(e)
    }

    protected fun setData(data: S) = launch {
        viewStateChanel.send(data)
    }

    override fun onCleared() {
        viewStateChanel.close()
        errorChannel.close()
        coroutineContext.cancel()
        super.onCleared()
    }
}