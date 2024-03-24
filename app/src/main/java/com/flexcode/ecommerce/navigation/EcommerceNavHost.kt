package com.flexcode.ecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.flexcode.ecommerce.navigation.routes.Routes
import com.flexcode.ecommerce.presentation.views.ListingDetailsScreenRoute
import com.flexcode.ecommerce.presentation.views.ListingsScreenRoute
import com.flexcode.ecommerce.presentation.views.SuccessScreenRoute
import com.stripe.android.paymentsheet.PaymentSheet

@Composable
fun EcommerceNavHost(
    navController: NavHostController,
    startDest: String = Routes.LISTINGS_SCREEN,
    paymentSheet: PaymentSheet,
) {
    NavHost(
        navController = navController,
        startDestination = startDest,

    ) {
        composable(Routes.LISTINGS_SCREEN) {
            ListingsScreenRoute {
                navController.navigate(
                    Routes.LISTINGS_DETAILS_SCREEN.replace(
                        oldValue = "{id}",
                        newValue = "$it",
                    ),
                )
            }
        }

        composable(Routes.LISTINGS_DETAILS_SCREEN) {
            val id = it.arguments?.getString("id")?.toInt() ?: 0
            ListingDetailsScreenRoute(id = id, paymentSheet = paymentSheet, navigateBack = {
                navController.navigateUp()
            }, toSuccessScreen = {
                    navController.navigate(Routes.SUCCESS_SCREEN)
                },)
        }

        composable(Routes.SUCCESS_SCREEN) {
            SuccessScreenRoute(toBack = {
                navController.navigate(Routes.LISTINGS_SCREEN) {
                    popUpTo(Routes.LISTINGS_DETAILS_SCREEN) { inclusive = true }
                    popUpTo(Routes.SUCCESS_SCREEN) { inclusive = true }
                }
            },)
        }
    }
}
