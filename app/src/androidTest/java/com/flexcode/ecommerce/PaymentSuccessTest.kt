package com.flexcode.ecommerce

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.flexcode.ecommerce.presentation.views.SuccessScreen
import org.junit.Rule
import org.junit.Test

class PaymentSuccessTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun tet_success_icon_is_shown() {
        rule.setContent {
            SuccessScreen(
                toBack = { /*TODO*/ },
            ) {
            }
        }
        rule.onNodeWithTag("success_icon").assertIsDisplayed()
    }

    @Test
    fun test_success_text_has_the_correct_message() {
        rule.setContent {
            SuccessScreen(toBack = { /*TODO*/ }) {
            }
        }

        // use string in resource instead of hard coding a text
        rule.onNodeWithTag("success_text")
            .assertTextContains("Payment has been made successfully")
    }

    @Test
    fun test_success_back_button_exists_and_is_clickable() {
        rule.setContent {
            SuccessScreen(toBack = { /*TODO*/ }) {
            }
        }
        rule.onNodeWithTag("success_back_btn").assertHasClickAction()
    }
}
