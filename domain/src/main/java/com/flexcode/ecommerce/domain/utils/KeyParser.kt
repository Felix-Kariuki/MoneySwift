package com.flexcode.ecommerce.domain.utils

import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KeyParser @Inject constructor() : Parser<String> {
    override suspend fun parse(response: String): List<String> {
        val jsonObject = JSONObject(response)
        val key = jsonObject.getString("id")
        val list = mutableListOf<String>()

        list.add(key)

        return list.ifEmpty {
            emptyList()
        }
    }
}
