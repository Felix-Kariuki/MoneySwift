package com.flexcode.ecommerce.data.response

data class ListingsResponseDto(
    val category: String?,
    val description: String?,
    val id: Int?,
    val image: String?,
    val price: Double?,
    val wishListed: Boolean?,
    val rating: RatingResponseDto?,
    val title: String?,
)
