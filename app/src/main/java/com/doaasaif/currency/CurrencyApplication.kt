package com.doaasaif.currency

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CurrencyApplication: Application() {


    init {
        instance = this
    }

    companion object {
        private var instance: CurrencyApplication? = null

        fun getApplicationContext() : Context {
            return instance!!.applicationContext
        }
    }
    override fun onCreate() {
        super.onCreate()
        val context: Context = CurrencyApplication.getApplicationContext()
    }
}