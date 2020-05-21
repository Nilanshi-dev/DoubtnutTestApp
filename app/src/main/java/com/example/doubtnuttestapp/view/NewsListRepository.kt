package com.example.doubtnuttestapp.view

import com.example.doubtnuttestapp.retrofit.NetworkModule
import io.reactivex.Observable

class NewsListRepository(val networkRequest: NetworkModule) {
    //news list repo
    fun newListData(): Observable<NewListInput> {
        return networkRequest.createAPiService().getNewsListApi()
    }
}