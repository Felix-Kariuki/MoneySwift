package com.flexcode.ecommerce

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.flexcode.ecommerce.presentation.components.sampleItem
import com.flexcode.ecommerce.presentation.state.UiState
import com.flexcode.ecommerce.presentation.views.ListingScreen
import org.junit.Rule
import org.junit.Test

class ListingsScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun test_listing_item_is_clickable() {
        rule.setContent {
            ListingScreen(
                state = UiState(
                    listings = listOf(
                        sampleItem,
                    ),
                ),
                toDetails = {},
            )
        }
        rule.onNodeWithTag("listing_item").assertHasClickAction()
    }

    @Test
    fun test_loading_state_shows_loading_indicator() {
        rule.setContent {
            ListingScreen(
                state = UiState(
                    listings = listOf(),
                    isLoading = true,
                ),

                toDetails = {},
            )
        }
        rule.onNodeWithTag("ecommerce_progress_indicator").assertIsDisplayed()
    }

    @Test
    fun test_error_state_shows_error_indicator() {
        rule.setContent {
            ListingScreen(
                state = UiState(
                    listings = listOf(),
                    isLoading = false,
                ),

                toDetails = {},
            )
        }
        rule.onNodeWithTag("ecommerce_error_indicator").assertIsDisplayed()
    }

    @Test
    fun test_success_state_shows_error_indicator() {
        rule.setContent {
            ListingScreen(
                state = UiState(
                    listings = listOf(sampleItem),
                    isLoading = false,
                ),

                toDetails = {},
            )
        }
        rule.onNodeWithTag("listings_v_grid").assertIsDisplayed()
    }
}
