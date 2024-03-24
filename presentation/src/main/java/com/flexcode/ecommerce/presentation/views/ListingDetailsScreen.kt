package com.flexcode.ecommerce.presentation.views

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.flexcode.ecommerce.designSystem.R
import com.flexcode.ecommerce.designSystem.components.EcommerceButton
import com.flexcode.ecommerce.designSystem.components.EcommerceResultText
import com.flexcode.ecommerce.designSystem.components.FullWidthImageItem
import com.flexcode.ecommerce.designSystem.theme.Blue
import com.flexcode.ecommerce.designSystem.theme.DisabledColor
import com.flexcode.ecommerce.designSystem.theme.buttonColor
import com.flexcode.ecommerce.designSystem.theme.primaryColor
import com.flexcode.ecommerce.designSystem.theme.spacing
import com.flexcode.ecommerce.domain.model.StripeResponse
import com.flexcode.ecommerce.domain.utils.Constants
import com.flexcode.ecommerce.presentation.components.WishListIcon
import com.flexcode.ecommerce.presentation.event.HomeEvent
import com.flexcode.ecommerce.presentation.state.UiState
import com.flexcode.ecommerce.presentation.viewModel.EcommerceViewModel
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import kotlin.math.roundToInt

@Composable
fun ListingDetailsScreenRoute(
    viewModel: EcommerceViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    toSuccessScreen: () -> Unit,
    id: Int,
    paymentSheet: PaymentSheet,
) {
    val state by viewModel.state.collectAsState()

    ListingDetailsScreen(
        state = state,
        onEvent = viewModel::onEvent,
        id = id,
        paymentSheet = paymentSheet,
        navigateBack = navigateBack,
        toSuccessScreen = toSuccessScreen,
    )
}

@Composable
fun ListingDetailsScreen(
    modifier: Modifier = Modifier,
    onEvent: (HomeEvent) -> Unit,
    navigateBack: () -> Unit,
    toSuccessScreen: () -> Unit,
    id: Int,
    paymentSheet: PaymentSheet,
    state: UiState,
) {
    LaunchedEffect(key1 = true) {
        onEvent(HomeEvent.GetListingById(id))
    }

    val context = LocalContext.current.applicationContext
    LaunchedEffect(state.stripeResponse) {
        state.stripeResponse?.let {
            PaymentConfiguration.init(
                context,
                com.flexcode.ecommerce.domain.utils.Constants.PUBLISH_KEY,
            )
            paymentFlow(paymentSheet, it)
        }
    }

    LaunchedEffect(key1 = state.stripeSuccessPayment) {
        state.stripeSuccessPayment?.let {
            // to success
            toSuccessScreen()
            onEvent(HomeEvent.ResetStripeState)
            onEvent(HomeEvent.ResetState)
        }
    }

    LaunchedEffect(key1 = state.errorMsg) {
        state.errorMsg?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    val scrollState = rememberScrollState()

    val isWishlist = state.singleListing?.wishListed ?: false

    Box(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
    ) {
        Box {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .zIndex(10f)
                    .padding(
                        top = 40.dp,
                        start = spacing().small,
                        end = spacing().small,
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                IconButton(
                    onClick = {
                        navigateBack()
                        onEvent(HomeEvent.ResetState)
                    },
                    colors = IconButtonDefaults.iconButtonColors(containerColor = primaryColor),
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White,
                    )
                }

                WishListIcon(tint = if (isWishlist) buttonColor else Color.White)
            }

            FullWidthImageItem(
                image = state.singleListing?.image,
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .fillMaxHeight(.7f),
                topRadius = spacing().none,
                bottomRadius = spacing().none,
            )
        }

        Column(
            modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .align(Alignment.BottomCenter)
                .fillMaxHeight(.5f)
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(topStart = spacing().large, topEnd = spacing().large),
                ),
        ) {
            Spacer(modifier = modifier.height(spacing().medium))
            EcommerceResultText(
                text = state.singleListing?.title ?: "",
                textPadding = spacing().medium,
                textColor = Blue,
                fontSize = 18.sp,
                style = MaterialTheme.typography.bodyLarge,
            )

            Row(
                modifier = modifier.padding(start = spacing().medium),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = buttonColor,
                    modifier = modifier.size(24.dp),
                )

                Spacer(modifier = modifier.width(spacing().none))

                EcommerceResultText(
                    text = "${state.singleListing?.rating?.rate ?: ""}",
                    textPadding = spacing().small,
                    textColor = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                )

                Icon(
                    painter = painterResource(id = R.drawable.icon_chat),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = modifier.size(24.dp),

                )

                EcommerceResultText(
                    text = "${state.singleListing?.rating?.count ?: 0} reviews",
                    textPadding = spacing().small,
                    textColor = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            EcommerceResultText(
                text = state.singleListing?.description ?: "",
                textPadding = spacing().medium,
                textColor = Color.White,
                fontSize = 16.sp,
            )

            Divider(thickness = .5.dp)

            Row(
                modifier
                    .fillMaxWidth()
                    .padding(
                        top = spacing().large,
                        bottom = spacing().extraMedium,
                        end = spacing().medium,
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                EcommerceResultText(
                    text = "$${state.singleListing?.price ?: 0.0}",
                    textPadding = spacing().medium,
                    textColor = Blue,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodyLarge,
                )

                EcommerceButton(
                    text = R.string.buy_now,
                    modifier = modifier.width(180.dp),
                    clickable = !state.isLoading,
                    strokeColor = if (state.isLoading) primaryColor else buttonColor,
                    textColor = if (state.isLoading) DisabledColor else Color.White,
                ) {
                    onEvent(
                        HomeEvent.StripeBilling(
                            "${state.singleListing?.price?.roundToInt()}",
                            "usd",
                        ),
                    )
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                color = buttonColor,
                trackColor = Color.White,
                modifier = modifier.align(
                    Alignment.Center,
                ),

            )
        }
    }
}

fun paymentFlow(
    paymentSheet: PaymentSheet,
    response: com.flexcode.ecommerce.domain.model.StripeResponse?,
) {
    response?.let {
        paymentSheet.presentWithPaymentIntent(
            it.clientSecret,
            PaymentSheet.Configuration(
                "Felix Kariuki",
                PaymentSheet.CustomerConfiguration(
                    it.customerId,
                    it.key,
                ),
            ),
        )
    }
}
