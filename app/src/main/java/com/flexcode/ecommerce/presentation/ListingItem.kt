package com.flexcode.ecommerce.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flexcode.ecommerce.designSystem.components.EcommerceResultButton
import com.flexcode.ecommerce.designSystem.components.EcommerceResultText
import com.flexcode.ecommerce.designSystem.components.FullWidthImageItem
import com.flexcode.ecommerce.designSystem.previews.EcommercePreview
import com.flexcode.ecommerce.designSystem.theme.Blue
import com.flexcode.ecommerce.designSystem.theme.buttonColor
import com.flexcode.ecommerce.designSystem.theme.primaryColor
import com.flexcode.ecommerce.designSystem.theme.secondaryColor
import com.flexcode.ecommerce.designSystem.theme.spacing
import com.flexcode.ecommerce.domain.model.ListingsResponse
import com.flexcode.ecommerce.domain.model.RatingResponse
import com.flexcode.ecommerce.shared.extensions.noRippleClick
import com.flexcode.ecommerce.R.string as AppString

@Composable
fun ListingItem(
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
    item: ListingsResponse?,
) {
    val isWishlisted = item?.wishListed ?: false
    val iconTint = if (isWishlisted) buttonColor else secondaryColor
    Column(
        modifier
            .background(secondaryColor, RoundedCornerShape(spacing().medium))
            .testTag("listing_item")
            .noRippleClick { onClick(item?.id ?: 0) },
        horizontalAlignment = Alignment.Start,
    ) {
        Box(
            contentAlignment = Alignment.TopEnd,
        ) {
            FullWidthImageItem(
                image = item?.image,
                contentDescription = AppString.app_name,
                height = 180.dp,
            )
            WishListIcon(tint = iconTint)
        }

        EcommerceResultText(
            text = item?.title ?: "",
            maxLines = 1,
            textColor = Color.White,
            fontSize = 17.sp,
            textPadding = spacing().small,
        )

        EcommerceResultText(
            text = "$${item?.price ?: ""}",
            maxLines = 1,
            textColor = Blue,
            fontSize = 15.5.sp,
            modifier = modifier.padding(start = spacing().small),
        )

        Spacer(modifier = modifier.height(spacing().medium))

        EcommerceResultButton(
            text = "Buy",
            textPadding = spacing().none,
            padding = spacing().small,
        ) {
            onClick(item?.id ?: 0)
        }
        Spacer(modifier = modifier.height(spacing().medium))
    }
}

@Composable
fun WishListIcon(tint: Color = buttonColor) {
    IconButton(
        onClick = { /*TODO*/ },
        colors = IconButtonDefaults.iconButtonColors(containerColor = primaryColor),
    ) {
        Icon(imageVector = Icons.Default.Favorite, contentDescription = null, tint = tint)
    }
}

@EcommercePreview
@Composable
fun WishListIconPreview() {
    WishListIcon()
}

@EcommercePreview
@Composable
fun ListingItemPreview() {
    ListingItem(item = sampleItem, onClick = {})
}

val sampleItem = ListingsResponse(
    id = 1,
    title = "Fjallraven",
    price = 109.95,
    description = "Your perfect pack for everyday use and walks in the forest. " +
        "Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
    category = "men's clothing",
    wishListed = true,
    image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
    rating = RatingResponse(
        count = 120,
        rate = 3.9,
    ),
)
