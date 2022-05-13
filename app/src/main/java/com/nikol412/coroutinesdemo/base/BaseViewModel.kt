package com.nikol412.coroutinesdemo.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.nikol412.coroutinesdemo.repository.NetworkException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob() + exceptionHandler

    protected val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d("CoroutineExceptionHandler", "Handled coroutine exception")
        when (throwable) {
            is NetworkException -> onNetworkException(throwable.message ?: "")
        }
        throwable.printStackTrace()
    }

    abstract fun onNetworkException(msg: String)
    override fun onCleared() {
        coroutineContext.cancel()
        super.onCleared()
    }
}
