package com.example.doubtnuttestapp

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import com.example.doubtnuttestapp.koin.networkModule
import com.example.doubtnuttestapp.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ApplicationController: Application(), Application.ActivityLifecycleCallbacks  {


    override fun onCreate() {
        super.onCreate()

        // start Koin!
        startKoin {
            androidLogger()
            androidContext(this@ApplicationController)
            modules(
                listOf(
                    viewModelModule,
                    networkModule
                )
            )
        }

        registerActivityLifecycleCallbacks(this)

    }

    companion object {
        var isInside: Boolean = false

        @Volatile
        private var INSTANCE: ApplicationController? = null
        val instance: ApplicationController?
            get() {
                if (INSTANCE == null) {
                    synchronized(ApplicationController::class.java) {
                        if (INSTANCE == null)
                            INSTANCE = ApplicationController()
                    }
                }
                return INSTANCE
            }
    }



    override fun onActivityPaused(activity: Activity?) {
        Log.e("onPaused", activity.toString())
    }

    override fun onActivityResumed(activity: Activity?) {
        Log.e("onPaused", activity!!.localClassName)
    }

    override fun onActivityStarted(activity: Activity?) {
        Log.e("onPaused", activity!!.localClassName)
    }

    override fun onActivityDestroyed(activity: Activity?) {
        Log.e("onPaused", activity!!.localClassName)
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        Log.e("onPaused", activity!!.localClassName)
    }

    override fun onActivityStopped(activity: Activity?) {
        Log.e("onPaused", activity!!.localClassName)
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        Log.e("onPaused", activity!!.localClassName)
    }
}