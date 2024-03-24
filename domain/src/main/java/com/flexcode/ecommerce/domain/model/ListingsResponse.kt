package com.flexcode.ecommerce.domain.model

data class ListingsResponse(
    val category: String?,
    val description: String?,
    val id: Int?,
    val image: String?,
    val price: Double?,
    val wishListed: Boolean?,
    val rating: RatingResponse?,
    val title: String?,
)
