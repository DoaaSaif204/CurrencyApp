package com.doaasaif.currency.module.networkmodule

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

object NetworkInterceptor : Interceptor {



    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val aRequest: Request = chain.request()
        Log.d("Requsest", aRequest.body().toString())
        val aResponse = chain.proceed(aRequest)
        Log.d("Response", aResponse.body().toString())

        when (aResponse.code()) {
            400 -> {
                // Show Bad Request Error Message
            }
            401 -> {
                // Show UnauthorizedError Message
            }

            403 -> {
                // Show Forbidden Message
            }

            404 -> {
                // Show NotFound Message
            }

            // ... and so on
        }
        return aResponse
    }
}
