package com.gustavobonilla.safebodachallenge.data.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.gustavobonilla.safebodachallenge.data.entity.Airports
import com.gustavobonilla.safebodachallenge.data.entity.AuthToken
import com.gustavobonilla.safebodachallenge.data.entity.Schedule
import com.gustavobonilla.safebodachallenge.data.typeadapter.AirportTypeAdapter
import com.gustavobonilla.safebodachallenge.data.typeadapter.ScheduleTypeAdapter
import com.gustavobonilla.safebodachallenge.isNotNull
import com.gustavobonilla.safebodachallenge.isNull
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LuftansaServiceApiImpl: LuftansaServiceApi {

    override val api: LuftansaApi = create()

    override fun checkAuthToken(action: (Boolean, Disposable?) -> Unit) {
        var disposable: Disposable? = null
        if (authToken.isNull()) {
            disposable = api.getAuthtoken(keys)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe ({
                        Log.d("AuthToken", "Token " + it.accessToken)
                        authToken = it
                        action(disposable.isNotNull(), disposable)
                    }, {
                        action(disposable.isNotNull(), disposable)
                        Log.e("Error", it.localizedMessage)
                    })
        } else {
            action(false, CompositeDisposable())
        }
    }

    companion object {
        private const val API_URL = "https://api.lufthansa.com/"
        private val keys = mapOf("client_id" to "hvk2ptpn2hy88tfmb4bhxfsd", "client_secret" to "X4qV2twBDS", "grant_type" to "client_credentials")
        private var authToken: AuthToken? = null

        /**
         * Create the instance of [Retrofit] for the [LuftansaApi] endpoint.
         */
        fun create(): LuftansaApi {
            val retrofit = Retrofit.Builder()
                    .client(createHttpClient())
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create(createGson()))
                    .baseUrl(API_URL)
                    .build()
            return retrofit.create(LuftansaApi::class.java)
        }

        /**
         * Creates the [OkHttpClient] with an interception to send or not the auth token depending if exists.
         *
         * @return the [OkHttpClient] with the interception implemented.
         */
        private fun createHttpClient(): OkHttpClient {
            return OkHttpClient.Builder().addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val newRequest = when {
                        authToken.isNotNull() -> {
                            chain.request().newBuilder()
                                    .addHeader("Authorization", "Bearer ${authToken?.accessToken}")
                                    .build()

                        }
                        authToken.isNull() -> chain.request().newBuilder().build()
                        else -> chain.request().newBuilder().build()
                    }
                    return chain.proceed(newRequest)
                }
            }).build()
        }

        /**
         * Creates a [Gson] object that registers type adapters for parsing response of API.
         *
         * @return the [Gson] with the registration of type adapters required.
         */
        private fun createGson(): Gson {
            return GsonBuilder()
                    .registerTypeAdapter(Schedule::class.java, ScheduleTypeAdapter())
                    .registerTypeAdapter(Airports::class.java, AirportTypeAdapter())
                    .create()
        }
    }
}