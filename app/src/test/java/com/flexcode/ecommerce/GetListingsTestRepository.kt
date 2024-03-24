package com.flexcode.ecommerce

import com.flexcode.ecommerce.domain.model.ListingsResponse
import com.flexcode.ecommerce.domain.repository.GetListingsRepository
import com.flexcode.ecommerce.presentation.components.sampleItem

class GetListingsTestRepository : GetListingsRepository {
    override suspend fun getListings(): Result<List<ListingsResponse>> {
        return Result.success(
            listOf(com.flexcode.ecommerce.presentation.components.sampleItem),
        )
    }
}
