@file:Suppress("ktlint")
package com.flexcode.ecommerce.domain.utils

/**
 * app constants
 * should be placed in the local properties
 */
object Constants {

    const val SECRET_KEY =
        "sk_test_51LvaLgAEl9eFnQSiq8gxe7RpEwWaLyJoZP9KpjOAyVWg0ioUjj0fPtRWsDkG7RTTrLFURVON76aPKePVkRzPHAWV0016AOwcpJ"
    const val PUBLISH_KEY =
        "pk_test_51LvaLgAEl9eFnQSi3tIcLTHCx4oLCXqzrK62jHQnxlnz3AddoGRjno8KYmPOlCdSfptlAP2oWEaTW84fhUilD61q00tAc2qVd3"

    const val AUTHORIZATION = "Bearer $SECRET_KEY"

    const val BASE_URL = "https://api.stripe.com"
}
