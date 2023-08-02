package com.doaasaif.domain.repo

import com.doaasaif.domain.entity.HistoryResponse
import com.doaasaif.domain.entity.LatestResponse
import com.doaasaif.domain.entity.SymbolsResponse

interface CurrencyConverssionRepo {

    suspend fun getLatest( key:String,  base:String?, symbol:String?):LatestResponse
    suspend fun getSymbols(key:String):SymbolsResponse
    suspend fun getHistory():HistoryResponse

}