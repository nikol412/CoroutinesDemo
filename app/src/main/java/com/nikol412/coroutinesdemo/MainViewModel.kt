package com.nikol412.coroutinesdemo

import androidx.lifecycle.MutableLiveData
import com.nikol412.coroutinesdemo.base.BaseViewModel
import com.nikol412.coroutinesdemo.repository.MockRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class MainViewModel : BaseViewModel() {
    val firstImageUrl: MutableLiveData<String> = MutableLiveData()
    val secondImageUrl: MutableLiveData<String> = MutableLiveData()
    val thirdImageUrl: MutableLiveData<String> = MutableLiveData()

    val errorText: MutableLiveData<String> = MutableLiveData()
    private val repository: MockRepository by lazy { MockRepository(Dispatchers.IO) }

    fun onLoadAllClick() {
        loadItems()
    }

    private fun loadItems() {
        launch {
            supervisorScope {
                launch {
                    val firstUrl = repository.loadItem(1)
                    firstImageUrl.value = firstUrl
                }
                launch {
                    val secondUrl = repository.loadItem(2)
                    secondImageUrl.value = secondUrl
                }
                launch {
                    val thirdUrl = repository.loadItem(3)
                    thirdImageUrl.value = thirdUrl
                }
            }
        }
    }

    override fun onNetworkException(msg: String) {
        errorText.value = msg
    }
}

