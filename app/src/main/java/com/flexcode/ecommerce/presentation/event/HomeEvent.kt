package com.flexcode.ecommerce.presentation.event

sealed interface HomeEvent {

    data class GetListingById(val id: Int) : HomeEvent

    data object GetAllListings : HomeEvent

    data object ResetState : HomeEvent

    data object ResetStripeState : HomeEvent

    data class StripeBilling(val amount: String, val currency: String = "usd") : HomeEvent
}
