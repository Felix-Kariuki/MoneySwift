package com.flexcode.ecommerce

import com.flexcode.ecommerce.domain.model.ListingsResponse
import com.flexcode.ecommerce.domain.repository.GetListingByIdRepository
import com.flexcode.ecommerce.presentation.sampleItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class GetListingByIdTestRepository : GetListingByIdRepository {

    override suspend fun getListingById(listingId: Int): Result<ListingsResponse> {
        return Result.success(
            sampleItem,
        )
    }

    private val listingresult = MutableStateFlow(
        Result.success(sampleItem),
    )

    fun setListingResult(result: Result<ListingsResponse>) {
        listingresult.update { result }
    }
}
