package com.flexcode.ecommerce.data.di

import com.flexcode.ecommerce.data.repository.GetListingByIdRepositoryImpl
import com.flexcode.ecommerce.data.repository.GetListingsRepositoryImpl
import com.flexcode.ecommerce.data.repository.StripePaymentRepositoryImpl
import com.flexcode.ecommerce.domain.repository.GetListingByIdRepository
import com.flexcode.ecommerce.domain.repository.GetListingsRepository
import com.flexcode.ecommerce.domain.repository.StripePaymentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @[
        Binds
        Singleton
    ]
    internal abstract fun getListingsPropertyBinding(
        getListingsRepositoryImpl: GetListingsRepositoryImpl,
    ): GetListingsRepository

    @[
        Binds
        Singleton
    ]
    internal abstract fun getListingByIdPropertyBinding(
        getListingByIdRepositoryImpl: GetListingByIdRepositoryImpl,
    ): GetListingByIdRepository

    @[
        Binds
        Singleton
    ]
    internal abstract fun providesStripRepoBindings(
        stripePaymentRepository: StripePaymentRepositoryImpl,
    ): StripePaymentRepository
}
