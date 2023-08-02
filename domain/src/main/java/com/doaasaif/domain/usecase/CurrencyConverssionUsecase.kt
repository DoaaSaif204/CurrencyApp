package com.doaasaif.domain.usecase

import com.doaasaif.domain.entity.LatestResponse
import com.doaasaif.domain.entity.SymbolsResponse
import com.doaasaif.domain.repo.CurrencyConverssionRepo
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.StringFormat
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class CurrencyConverssionUsecase(private val converssionRepo: CurrencyConverssionRepo) {
    suspend fun getLatest(key:String , base: String?, symbol: String?): LatestResponse {
        return converssionRepo.getLatest(key, base, null)
    }

    suspend fun getSymbols(key: String ): SymbolsResponse {
        return converssionRepo.getSymbols(key )
    }

}

