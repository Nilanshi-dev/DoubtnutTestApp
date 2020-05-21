package com.example.doubtnuttestapp.retrofit

import com.example.doubtnuttestapp.view.NewListInput
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {

   // b43d957787fe436183af5aaf33bc2317
    //news List
    @GET("top-headlines?country=us&apiKey=b43d957787fe436183af5aaf33bc2317")
    fun getNewsListApi(): Observable<NewListInput>
}