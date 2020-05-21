package com.example.doubtnuttestapp.view

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.doubtnuttestapp.Resource
import com.example.doubtnuttestapp.baseCode.BaseViewModel
import com.example.doubtnuttestapp.retrofit.NetworkModule
import org.koin.core.KoinComponent
import org.koin.core.inject

class NewsListViewModel(val context: Application) : BaseViewModel(), KoinComponent {
    private val networkRequest: NetworkModule by inject()

    fun getNewList(isConnected: Boolean): MutableLiveData<Resource<Any>> {
        return newListAsync(
            isConnected,
            NewsListRepository(
                networkRequest
            ).newListData()
        )
    }
}