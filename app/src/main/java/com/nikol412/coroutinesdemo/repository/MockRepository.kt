package com.nikol412.coroutinesdemo.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MockRepository(private val defaultDispatcher: CoroutineDispatcher) {
    suspend fun loadItem(i: Int): String = withContext(defaultDispatcher) {
        return@withContext when (i) {
            1 -> {
                delay(300)
                throw NetworkException("network exception thrown")
            }
            2 -> {
                delay(500)
                "https://picsum.photos/200/200"
            }
            3 -> {
                delay(100)
                "https://picsum.photos/150/150"
            }
            else -> throw NetworkException("network overflow thrown")
        }
    }
}

class NetworkException(msg: String) : Throwable(msg)