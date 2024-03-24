package com.flexcode.ecommerce.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.flexcode.ecommerce.designSystem.R
import com.flexcode.ecommerce.designSystem.components.EcommerceResultText
import com.flexcode.ecommerce.designSystem.previews.EcommercePreview
import com.flexcode.ecommerce.designSystem.theme.EcommerceTheme
import com.flexcode.ecommerce.designSystem.theme.primaryColor
import com.flexcode.ecommerce.designSystem.theme.secondaryColor
import com.flexcode.ecommerce.designSystem.theme.spacing
import com.flexcode.ecommerce.presentation.event.HomeEvent
import com.flexcode.ecommerce.presentation.viewModel.EcommerceViewModel

@Composable
fun SuccessScreenRoute(
    viewModel: EcommerceViewModel = hiltViewModel(),
    toBack: () -> Unit,
) {
    SuccessScreen(toBack = toBack, onEvent = viewModel::onEvent)
}

@Composable
fun SuccessScreen(
    modifier: Modifier = Modifier,
    toBack: () -> Unit,
    onEvent: (HomeEvent) -> Unit,
) {
    LaunchedEffect(key1 = true) {
        onEvent(HomeEvent.ResetState)
    }

    Column(
        modifier
            .fillMaxSize()
            .background(primaryColor),
    ) {
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
                onClick = toBack,
                colors = IconButtonDefaults.iconButtonColors(containerColor = secondaryColor),
                modifier = modifier.testTag("success_back_btn")
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        }

        Column(
            modifier.fillMaxWidth().fillMaxHeight(.8f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_success),
                contentDescription = null,
                modifier = modifier.size(200.dp).testTag("success_icon"),
            )

            Spacer(modifier = modifier.height(spacing().large))

            EcommerceResultText(
                text = "Payment has been made successfully",
                textColor = Color.White,
                fontSize = 18.sp,
                modifier = modifier.testTag("success_text")
            )
        }
    }
}

@EcommercePreview
@Composable
fun SuccessScreenPreview() {
    EcommerceTheme {
        SuccessScreen(toBack = {}, onEvent = {})
    }
}
