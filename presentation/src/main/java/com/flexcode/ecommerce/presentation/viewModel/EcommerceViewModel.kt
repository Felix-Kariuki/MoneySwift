package com.flexcode.ecommerce.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexcode.ecommerce.domain.repository.GetListingByIdRepository
import com.flexcode.ecommerce.domain.repository.GetListingsRepository
import com.flexcode.ecommerce.domain.repository.StripePaymentRepository
import com.flexcode.ecommerce.domain.utils.ResultWrapper
import com.flexcode.ecommerce.presentation.event.HomeEvent
import com.flexcode.ecommerce.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel class for handling getting listings and passing that to the UI
 * @property getListingsRepository Repo for getting all the listings
 * @property getListingByIdRepository Repo for getting a listing by Id
 * @property stripePaymentRepository Repo to initialize stripe billing
 */
@HiltViewModel
class EcommerceViewModel @Inject constructor(
    private val getListingsRepository: GetListingsRepository,
    private val getListingByIdRepository: GetListingByIdRepository,
    private val stripePaymentRepository: StripePaymentRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    /**
     * event
     */

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetAllListings -> {
                getAllListings()
            }

            is HomeEvent.GetListingById -> {
                getAListingById(event.id)
            }

            is HomeEvent.ResetState -> {
                resetState()
            }

            is HomeEvent.StripeBilling -> {
                initStripeBilling(event.amount, event.currency)
            }

            HomeEvent.ResetStripeState -> {
                _state.update { it.copy(stripeSuccessPayment = null) }
            }
        }
    }

    fun resetState() {
        _state.update {
            it.copy(
                isLoading = false,
                errorMsg = null,
                singleListing = null,
                successMsg = null,
                stripeResponse = null,
            )
        }
    }

    /**
     * init' s the payment with stripe
     * @param amount
     * @param currency
     */
    private fun initStripeBilling(amount: String, currency: String) {
        viewModelScope.launch {
            stripePaymentRepository.createStripePayment(amount = amount, currency = currency)
                .collectLatest { response ->
                    _state.update {
                        when (response) {
                            is com.flexcode.ecommerce.domain.utils.ResultWrapper.Success -> {
                                it.copy(
                                    isLoading = false,
                                    stripeResponse = response.data,
                                )
                            }

                            is com.flexcode.ecommerce.domain.utils.ResultWrapper.Loading -> {
                                it.copy(isLoading = true)
                            }

                            is com.flexcode.ecommerce.domain.utils.ResultWrapper.Error -> {
                                it.copy(
                                    isLoading = false,
                                    errorMsg = "Ann error ${response.errorMessage}",
                                )
                            }
                        }
                    }
                }
        }
    }

    /**
     * gets a single listing
     * @param id the listing id
     */
    fun getAListingById(id: Int) {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val response = getListingByIdRepository.getListingById(id)

            if (response.isSuccess) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        singleListing = response.getOrNull(),
                    )
                }
            } else if (response.isFailure) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMsg = response.exceptionOrNull()?.message,
                    )
                }
            }
        }
    }

    /**
     * gets all the listings
     */
    fun getAllListings() {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val response = getListingsRepository.getListings()

            if (response.isSuccess) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        listings = response.getOrNull()?.take(5) ?: emptyList(),
                    )
                }
            } else if (response.isFailure) {
                Timber.e("THE ERROR FETCHING IS ${response.exceptionOrNull()?.message}")
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMsg = response.exceptionOrNull()?.message,
                    )
                }
            }
        }
    }

    fun setStripSuccess() {
        _state.update { it.copy(stripeSuccessPayment = "Payment made successfully") }
    }

    init {
        onEvent(HomeEvent.GetAllListings)
    }
}
