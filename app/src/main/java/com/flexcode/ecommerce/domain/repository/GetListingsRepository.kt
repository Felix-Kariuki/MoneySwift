package com.flexcode.ecommerce.domain.repository

import com.flexcode.ecommerce.domain.model.ListingsResponse

interface GetListingsRepository {

    suspend fun getListings(): Result<List<ListingsResponse>>
}
