package com.flexcode.ecommerce.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.flexcode.ecommerce.designSystem.components.EcommerceResultText
import com.flexcode.ecommerce.designSystem.components.EcommerceText
import com.flexcode.ecommerce.designSystem.components.NoResultFound
import com.flexcode.ecommerce.designSystem.previews.EcommercePreview
import com.flexcode.ecommerce.designSystem.theme.Blue
import com.flexcode.ecommerce.designSystem.theme.EcommerceTheme
import com.flexcode.ecommerce.designSystem.theme.buttonColor
import com.flexcode.ecommerce.designSystem.theme.spacing
import com.flexcode.ecommerce.presentation.components.ListingItem
import com.flexcode.ecommerce.presentation.state.UiState
import com.flexcode.ecommerce.presentation.viewModel.EcommerceViewModel
import com.flexcode.ecommerce.designSystem.R.drawable as AppDrawable
import com.flexcode.ecommerce.designSystem.R.string as AppString

@Composable
fun ListingsScreenRoute(
    viewModel: EcommerceViewModel = hiltViewModel(),
    toDetails: (Int) -> Unit,
) {
    val state by viewModel.state.collectAsState()
    ListingScreen(state = state, toDetails = toDetails)
}

@Composable
fun ListingScreen(
    modifier: Modifier = Modifier,
    state: UiState,
    toDetails: (Int) -> Unit,
) {

    Box(
        contentAlignment = Alignment.Center
    ){
        if (state.isLoading && state.listings.isEmpty()) {
            // show loader
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    color = buttonColor,
                    modifier = modifier.testTag("ecommerce_progress_indicator"),
                )
            }

        } else if (!state.isLoading && state.listings.isNotEmpty()) {
            // show ui
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(start = spacing().medium, top = 40.dp, end = spacing().medium),
            ) {
                TopItem()

                // list item

                Spacer(modifier = modifier.height(spacing().large))
                ItemCountItem(state = state)

                Spacer(modifier = modifier.height(spacing().medium))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(spacing().medium),
                    verticalArrangement = Arrangement.spacedBy(spacing().medium),
                    modifier = modifier.testTag("listings_v_grid"),
                ) {
                    items(state.listings.size) { i ->
                        ListingItem(item = state.listings[i], onClick = {
                            toDetails(it)
                        },)
                    }

                    item {
                        Spacer(modifier = modifier.height(spacing().large))
                    }
                }
            }
        } else if (!state.isLoading && state.errorMsg?.isNotEmpty() == true) {
            // show error
            ErrorItem(state = state)
        } else if (!state.isLoading && state.listings.isEmpty()) {
            // empty view
            NoResultFound()
        }
    }

}

@Composable
fun ErrorItem(state: UiState) {
    EcommerceResultText(
        text = "Error Fetching listings .... ${state.errorMsg}",
        textPadding = spacing().medium,
    )
}

@Composable
fun ItemCountItem(modifier: Modifier = Modifier, state: UiState) {
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        EcommerceResultText(
            text = "${state.listings.size} items ",
            textColor = Color.White,
            style = MaterialTheme.typography.labelSmall,
        )

        Row {
            EcommerceResultText(
                text = "Popular first",
                textColor = Blue,
                style = MaterialTheme.typography.labelSmall,
            )

            Spacer(modifier = modifier.width(spacing().small))

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = Blue,
            )
        }
    }
}

@Composable
fun TopItem(modifier: Modifier = Modifier) {
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        EcommerceText(text = AppString.app_name, fontSize = 20.sp, textColor = Color.White)

        Icon(
            painter = painterResource(id = AppDrawable.ic_search),
            contentDescription = null,
            tint = Color.White,
        )
    }
}

@EcommercePreview
@Composable
fun ListingScreenPreview() {
    EcommerceTheme {
        ListingScreen(state = UiState(), toDetails = {})
    }
}
