package com.flexcode.ecommerce.data.mappers

import com.flexcode.ecommerce.data.response.ListingsResponseDto
import com.flexcode.ecommerce.data.response.RatingResponseDto
import com.flexcode.ecommerce.data.response.StripeResponseDto
import com.flexcode.ecommerce.domain.model.ListingsResponse
import com.flexcode.ecommerce.domain.model.RatingResponse
import com.flexcode.ecommerce.domain.model.StripeResponse

/**
 * Mapper file to map Dto to domain models
 */

internal fun ListingsResponseDto.toDomain(): com.flexcode.ecommerce.domain.model.ListingsResponse {
    return com.flexcode.ecommerce.domain.model.ListingsResponse(
        category = category,
        description = description,
        id = id,
        image = image,
        price = price,
        rating = rating?.toDomain(),
        title = title,
        wishListed = wishListed,
    )
}

internal fun RatingResponseDto.toDomain(): com.flexcode.ecommerce.domain.model.RatingResponse {
    return com.flexcode.ecommerce.domain.model.RatingResponse(
        count = count,
        rate = rate,
    )
}

internal fun StripeResponseDto.toDomain(): com.flexcode.ecommerce.domain.model.StripeResponse {
    return com.flexcode.ecommerce.domain.model.StripeResponse(
        key = key,
        clientSecret = clientSecret,
        customerId = customerId,
    )
}
