package com.flexcode.ecommerce.domain.utils

import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomerParser
@Inject constructor() : Parser<String> {
    override suspend fun parse(response: String): List<String> {
        val jsonObject = JSONObject(response)
        val data = jsonObject.getJSONArray("data")
        val jObject = data.getJSONObject(0)
        val customerId = jObject.getString("id")
        val list = mutableListOf<String>()

        list.add(customerId)

        return list.ifEmpty {
            emptyList()
        }
    }
}
