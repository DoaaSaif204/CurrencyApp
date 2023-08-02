package com.doaasaif.currency.module.networkmodule

import androidx.compose.runtime.Composable
import com.doaasaif.currency.CurrencyApplication
import com.doaasaif.currency.module.NetworkConnectionInterceptor
import com.doaasaif.currency.module.networkmodule.NetworkInterceptor.intercept
import com.doaasaif.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RertofitAPIClient {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {


        var okHttpClient =
            OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS).addNetworkInterceptor(
                NetworkConnectionInterceptor(
                    CurrencyApplication.getApplicationContext()
                )
            ).connectTimeout(20, TimeUnit.SECONDS).build()

        return okHttpClient
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("http://data.fixer.io/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()

    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}