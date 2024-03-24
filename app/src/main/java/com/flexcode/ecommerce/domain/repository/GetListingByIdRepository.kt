package com.flexcode.ecommerce.domain.repository

import com.flexcode.ecommerce.domain.model.ListingsResponse

interface GetListingByIdRepository {

    suspend fun getListingById(listingId: Int): Result<ListingsResponse>
}
