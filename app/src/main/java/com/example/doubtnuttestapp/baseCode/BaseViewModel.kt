package com.example.doubtnuttestapp.baseCode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.doubtnuttestapp.Resource
import com.example.doubtnuttestapp.view.NewListInput
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.HttpException

abstract class BaseViewModel : ViewModel() {
    private val mCompositeDisposable = CompositeDisposable()

    protected fun newListAsync(
        isConnected: Boolean,
        configurationAsync: Observable<NewListInput>
    ): MutableLiveData<Resource<Any>> {
        val result: MutableLiveData<Resource<Any>> = MutableLiveData()
        result.value = Resource.loading(null)
        if (isConnected) {
            mCompositeDisposable.add(
                configurationAsync.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        result.value = Resource.success(it)
                    }, {
                        result.value = Resource.error(onError(it), null)
                    })
            )
        } else {
            result.value = Resource.error("Please check your internet connection", 0)
        }
        return result
    }

    //error handling
    private fun onError(e: Throwable): String {
        e.printStackTrace()
        if (e is HttpException) {
            val errorJson = e.response()!!.errorBody()!!.string()
            try {
                val loginJsonObject = JSONObject(errorJson)
                return loginJsonObject.getString("message")
            } catch (e: Exception) {
                e.printStackTrace()
            }
            val message = e.message()
            return message
        } else {
            return e.message.toString()
        }
    }

}