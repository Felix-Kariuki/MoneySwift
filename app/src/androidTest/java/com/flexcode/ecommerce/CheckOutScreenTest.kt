package com.flexcode.ecommerce

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.flexcode.ecommerce.presentation.components.sampleItem
import com.flexcode.ecommerce.presentation.state.UiState
import com.flexcode.ecommerce.presentation.views.ListingDetailsScreen
import org.junit.Rule
import org.junit.Test

class CheckOutScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun test_pay_now_is_clickable() {
        rule.setContent {
            ListingDetailsScreen(
                state = UiState(
                    singleListing = sampleItem,
                ),
                onEvent = {},
                navigateBack = {},
                id = 1,
                toSuccessScreen = {},
            )
        }
        rule.onNodeWithTag("pay_button").assertHasClickAction()
    }

    @Test
    fun test_navigate_back_is_clickable() {
        rule.setContent {
            ListingDetailsScreen(
                state = UiState(
                    singleListing = sampleItem,
                ),
                onEvent = {},
                navigateBack = {},
                id = 1,
                toSuccessScreen = {},
            )
        }
        rule.onNodeWithTag("back_button_checkout").assertHasClickAction()
    }
}
