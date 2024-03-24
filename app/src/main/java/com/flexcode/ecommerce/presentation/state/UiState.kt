package com.flexcode.ecommerce.presentation.state

import com.flexcode.ecommerce.domain.model.ListingsResponse
import com.flexcode.ecommerce.domain.model.StripeResponse

data class UiState(
    val isLoading: Boolean = false,
    val listings: List<ListingsResponse> = emptyList(),
    val singleListing: ListingsResponse? = null,
    val errorMsg: String? = null,
    val successMsg: String? = null,
    val stripeResponse: StripeResponse? = null,
    val stripeSuccessPayment: String? = null,
)
