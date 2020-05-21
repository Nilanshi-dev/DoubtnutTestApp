package com.example.doubtnuttestapp.retrofit

import android.app.Application
import com.example.doubtnuttestapp.ApplicationConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class NetworkModule(val application: Application): KoinComponent {

    fun createAPiService(): ApiInterface {
        return createAPiService.create(ApiInterface::class.java)
    }

    //for app configuration
    private val createAPiService: Retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(ApplicationConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private val okHttpClient by lazy {
        val (cert, trust) = trustAllCerts()
        OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.MINUTES)
            .sslSocketFactory(cert, trust)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    private fun trustAllCerts(): Pair<SSLSocketFactory, X509TrustManager> {
        val cert = arrayOf<TrustManager>(object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {

            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {

            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, cert, java.security.SecureRandom())
        return Pair(sslContext.socketFactory, cert[0] as X509TrustManager)
    }
}