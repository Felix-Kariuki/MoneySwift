package com.flexcode.ecommerce.data.di

import com.flexcode.ecommerce.data.remote.EcommerceKtorService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideServiceInterface(): EcommerceKtorService {
        return EcommerceKtorService.create()
    }
}
