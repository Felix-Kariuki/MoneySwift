package com.flexcode.ecommerce.domain.utils

interface Parser<T> {
    suspend fun parse(response: String): List<T>
}
