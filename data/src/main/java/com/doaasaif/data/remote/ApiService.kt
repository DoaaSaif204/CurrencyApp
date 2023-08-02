package com.doaasaif.data.remote

import com.doaasaif.domain.entity.HistoryResponse
import com.doaasaif.domain.entity.LatestResponse
import com.doaasaif.domain.entity.SymbolsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/latest")
    suspend fun getLatest(
        @Query("access_key") access_key: String,
        @Query("base") base: String?,
        @Query("symbols") symbols: String?
    ): LatestResponse


    //http://data.fixer.io/api/symbols?access_key=39a32d78105521996fdc106e62d7a30d
    @GET("api/symbols")
    suspend fun getSymbols(@Query("access_key") access_key: String): SymbolsResponse


    @GET("api/")
    suspend fun getHistory(): HistoryResponse
}
