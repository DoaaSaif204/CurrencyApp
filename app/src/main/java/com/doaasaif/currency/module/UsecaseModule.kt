package com.example.mealsapplication.mealz.di

import com.doaasaif.domain.repo.CurrencyConverssionRepo
import com.doaasaif.domain.usecase.CurrencyConverssionUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UsecaseModule {

    @Provides
    fun provideUseCase(currencyRepo: CurrencyConverssionRepo): CurrencyConverssionUsecase {
        return CurrencyConverssionUsecase(currencyRepo)
    }

}