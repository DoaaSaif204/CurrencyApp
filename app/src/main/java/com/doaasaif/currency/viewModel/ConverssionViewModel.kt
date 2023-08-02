package com.doaasaif.currency.viewModel

import android.content.Context
import android.util.ArrayMap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doaasaif.currency.CurrencyApplication
import com.doaasaif.currency.R
import com.doaasaif.currency.module.networkmodule.ApiStatus
import com.doaasaif.domain.entity.LatestResponse
import com.doaasaif.domain.entity.Symbols
import com.doaasaif.domain.entity.SymbolsResponse
import com.doaasaif.domain.usecase.CurrencyConverssionUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConverssionViewModel @Inject constructor(
    @ApplicationContext private val applicationContext: Context,

    private val currencyUseCase: CurrencyConverssionUsecase
) :
    ViewModel() {


    val TAG = "ConeverssionViewModel"
    private val mutableLatest: MutableStateFlow<LatestResponse?> = MutableStateFlow(null)
    private var mutableSymbols: MutableStateFlow<SymbolsResponse?> = MutableStateFlow(null)
    var _mutableSymbols: MutableStateFlow<SymbolsResponse?> = mutableSymbols
    var latest = mutableLatest.value
    var error: MutableLiveData<String?> = MutableLiveData(null)
    var converssionResult: MutableLiveData<Double?> = MutableLiveData(0.0)
    val _symbols = MutableStateFlow<List<String>?>(null)
    val apiStatus: MutableLiveData<ApiStatus> = MutableLiveData(ApiStatus.SUCCESS)


    fun getLatest(value: Double, base: String?, dest: String?) {
        var refreshMinutes: Long?
        var currencyRate: Double
        CoroutineScope(Dispatchers.IO).launch {

            try {

                apiStatus.postValue(ApiStatus.LOADING)
                //if (mutableLatest.value != null) {

                // val lastTimeStamp: Long? = mutableLatest.value?.timestamp
                //refreshMinutes = ((System.currentTimeMillis() - lastTimeStamp!!) / 1000 / 60)
                //    latest endpoint will return real-time exchange rate data updated every 60 minutes, every 10 minutes or every 60 seconds.
                /*        if (refreshMinutes!! > 10) {
                            mutableLatest.value = currencyUseCase.getLatest(base, null)
                        }
                    } else {*/
                var response =
                    currencyUseCase.getLatest(applicationContext.getString(R.string.key), null, null)
                mutableLatest.value = response
                //  }
                if (mutableLatest.value!!.success) {


                    dest?.let {
                        currencyRate = mutableLatest?.value?.rates?.get(dest)!!
                        converssionResult.postValue(value * currencyRate)
                        apiStatus.postValue(ApiStatus.SUCCESS)
                    }

                } else {
                    error.postValue("error")
                    apiStatus.postValue(ApiStatus.ERROR)
                }
            } catch (e: Exception) {
                error.postValue(e.toString())

                Log.d(TAG, e.message.toString())
                apiStatus.postValue(ApiStatus.ERROR)
            }

        }
    }

    fun getSymbols(key: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                apiStatus.postValue(ApiStatus.LOADING)

                var symbolsResponse = currencyUseCase.getSymbols(key)

                mutableSymbols.value = symbolsResponse


                if (symbolsResponse.success) {
                    var x = ArrayList(symbolsResponse.symbols.keys)

                    _symbols.tryEmit(x)
                    _symbols.emit(x)
                    apiStatus.postValue(ApiStatus.SUCCESS)
                    Log.d(TAG, "get symbols success")
                } else {
                    apiStatus.postValue(ApiStatus.ERROR)
                    error.postValue(applicationContext.getString(R.string.api_error).toString())
                }

                //     Log.d(TAG, _symbols.symbols)
            } catch (e: Exception) {
                apiStatus.postValue(ApiStatus.ERROR)
                error.postValue(applicationContext.getString(R.string.api_error).toString())
                Log.d(TAG, e.message.toString())
            }
        }
    }
/*
    fun getHistory() {
        CoroutineScope(Dispatchers.IO).launch{
            try {
                mutableCategories.value = getMealsUseCase()
                Log.d(TAG,  mutableCategories.value.toString())
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }
        }
    }*/

}


