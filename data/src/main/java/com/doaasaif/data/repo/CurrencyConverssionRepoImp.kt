package com.doaasaif.data.repo

import com.doaasaif.data.remote.ApiService
import com.doaasaif.domain.entity.HistoryResponse
import com.doaasaif.domain.entity.LatestResponse
import com.doaasaif.domain.entity.SymbolsResponse
import com.doaasaif.domain.repo.CurrencyConverssionRepo
import retrofit2.await

class CurrencyConverssionRepoImp(private val apiService:ApiService): CurrencyConverssionRepo {
    override suspend fun getLatest(key :String , base:String? ,symbol:String? ): LatestResponse {
       return apiService.getLatest(key,base, symbol
       )
     }



    override suspend fun getSymbols(key: String): SymbolsResponse {
        return apiService.getSymbols(key)

    }



    override suspend fun getHistory(): HistoryResponse {
        TODO("Not yet implemented")
    }
}