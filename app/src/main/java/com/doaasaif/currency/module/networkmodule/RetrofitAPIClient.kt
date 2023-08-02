package com.doaasaif.currency.module.networkmodule

import android.content.Context
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit


import com.doaasaif.currency.module.NetworkConnectionInterceptor
 import com.doaasaif.data.remote.ApiService
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext

import okhttp3.OkHttpClient
import javax.inject.Singleton

/*

object RetrofitAPIClient {
        private var retrofit: Retrofit? = null
        fun getRetrofitClient(@ApplicationContext context: Context): Retrofit? {
            if (retrofit == null) {
                val oktHttpClient = OkHttpClient.Builder()
                    .addInterceptor(NetworkConnectionInterceptor(context))
                    .addInterceptor(NetworkInterceptor)
                // Adding NetworkConnectionInterceptor with okHttpClientBuilder.
                 retrofit = Retrofit.Builder()
                    .baseUrl("http://data.fixer.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(oktHttpClient.build())
                    .build()
            }
            return retrofit
        }
        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }*/
