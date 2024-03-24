package com.flexcode.ecommerce.data.repository

import android.content.Context
import com.flexcode.ecommerce.data.mappers.toDomain
import com.flexcode.ecommerce.data.response.ListingsResponseDto
import com.flexcode.ecommerce.data.utils.parseJson
import com.flexcode.ecommerce.domain.model.ListingsResponse
import com.flexcode.ecommerce.domain.repository.GetListingByIdRepository
import com.flexcode.ecommerce.shared.dispatchers.Dispatcher
import com.flexcode.ecommerce.shared.dispatchers.DispatcherProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * @property dispatcher of type [CoroutineDispatcher]
 * @property context
 */
class GetListingByIdRepositoryImpl @Inject constructor(
    @Dispatcher(DispatcherProvider.IO) private val dispatcher: CoroutineDispatcher,
    @ApplicationContext private val context: Context,
) : GetListingByIdRepository {

    override suspend fun getListingById(listingId: Int): Result<ListingsResponse> {
        return try {
            val response = parseJson<List<ListingsResponseDto>>(
                path = "listings.json",
                context = context,
                dispatcher = dispatcher,
            )
            val listing = response.find {
                it.id == listingId
            }!!
            Result.success(listing.toDomain())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}
