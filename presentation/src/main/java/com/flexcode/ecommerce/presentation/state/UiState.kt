package com.flexcode.ecommerce.presentation.state

import com.flexcode.ecommerce.domain.model.ListingsResponse
import com.flexcode.ecommerce.domain.model.StripeResponse

data class UiState(
    val isLoading: Boolean = false,
    val listings: List<com.flexcode.ecommerce.domain.model.ListingsResponse> = emptyList(),
    val singleListing: com.flexcode.ecommerce.domain.model.ListingsResponse? = null,
    val errorMsg: String? = null,
    val successMsg: String? = null,
    val stripeResponse: com.flexcode.ecommerce.domain.model.StripeResponse? = null,
    val stripeSuccessPayment: String? = null,
)
