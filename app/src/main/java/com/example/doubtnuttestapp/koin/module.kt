package com.example.doubtnuttestapp.koin

import com.example.doubtnuttestapp.retrofit.NetworkModule
import com.example.doubtnuttestapp.view.NewsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { NetworkModule(get()) }
}

val viewModelModule = module {
    viewModel { NewsListViewModel(get()) }
}