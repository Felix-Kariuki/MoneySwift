package com.flexcode.ecommerce.activity

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.flexcode.ecommerce.designSystem.theme.EcommerceTheme
import com.flexcode.ecommerce.navigation.EcommerceNavHost
import com.flexcode.ecommerce.navigation.routes.Routes
import com.flexcode.ecommerce.presentation.viewModel.EcommerceViewModel
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewmodel: EcommerceViewModel by viewModels()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
//            navigationBarStyle = SystemBarStyle.light(primaryColor.toArgb(), primaryColor.toArgb())
        )

        val paymentSheet = PaymentSheet(this) { paymentSheetResult ->
            onPaymentSheetResult(paymentSheetResult)
        }

        setContent {
            navController = rememberNavController()

            EcommerceTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        // .navigationBarsPadding()
                        .imePadding(),
                    bottomBar = {
                    },
                ) { innerPadding ->

                    Timber.i("$innerPadding")

                    EcommerceNavHost(navController = navController, paymentSheet = paymentSheet)
                }
            }
        }
    }

    private fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
        when (paymentSheetResult) {
            is PaymentSheetResult.Canceled -> {
                Toast.makeText(
                    this,
                    "Stripe Payment cancelled",
                    Toast.LENGTH_SHORT,
                ).show()
                viewmodel.resetState()
            }

            is PaymentSheetResult.Failed -> {
                Toast.makeText(
                    this,
                    "Error: ${paymentSheetResult.error.message}",
                    Toast.LENGTH_SHORT,
                )
                    .show()
                viewmodel.resetState()
            }

            is PaymentSheetResult.Completed -> {
                Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show()
                navController.navigate(Routes.SUCCESS_SCREEN)
                viewmodel.setStripSuccess()
                viewmodel.resetState()
            }
        }
    }
}
