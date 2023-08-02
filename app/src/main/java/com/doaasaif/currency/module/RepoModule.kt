package com.example.mealsapplication.mealz.di

import com.doaasaif.data.remote.ApiService
import com.doaasaif.data.repo.CurrencyConverssionRepoImp
import com.doaasaif.domain.repo.CurrencyConverssionRepo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

object RepoModule {

    @Provides
    fun provideRepo( apiService: ApiService): CurrencyConverssionRepo {
        return CurrencyConverssionRepoImp( apiService)
    }
}