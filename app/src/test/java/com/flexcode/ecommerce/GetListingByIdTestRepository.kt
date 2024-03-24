package com.flexcode.ecommerce

import com.flexcode.ecommerce.domain.model.ListingsResponse
import com.flexcode.ecommerce.domain.repository.GetListingByIdRepository
import com.flexcode.ecommerce.presentation.components.sampleItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class GetListingByIdTestRepository :
    GetListingByIdRepository {

    override suspend fun getListingById(listingId: Int): Result<ListingsResponse> {
        return Result.success(
            com.flexcode.ecommerce.presentation.components.sampleItem,
        )
    }

    private val listingresult = MutableStateFlow(
        Result.success(com.flexcode.ecommerce.presentation.components.sampleItem),
    )

    fun setListingResult(result: Result<ListingsResponse>) {
        listingresult.update { result }
    }
}
