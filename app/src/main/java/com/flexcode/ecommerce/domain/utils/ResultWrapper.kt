package com.flexcode.ecommerce.domain.utils

/**
 * A generic  class representing the state of an operation with optional data, error, and loading indicators.
 * @property data The data result of the operation.
 * @property errorMessage The error that occurred during the operation.
 */
sealed class ResultWrapper<T>(
    val data: T? = null,
    val errorMessage: String? = "Unknown error occurred",
) {
    /**
     * Creates a success state with the specified data.
     * @param data The data result.
     * @return A Resource instance representing the success state with data.
     */
    class Success<T>(data: T) : ResultWrapper<T>(data)

    /**
     * Creates an error state with the specified error.
     * @param error The error that occurred.
     * @return A Resource instance representing the error state with the error and  null data
     */
    class Error<T>(error: String, data: T? = null) : ResultWrapper<T>(data, error)

    /**
     * Creates a loading state.
     * @return A Resource instance representing the loading state.
     */
    class Loading<T>(data: T? = null) : ResultWrapper<T>(data)
}
