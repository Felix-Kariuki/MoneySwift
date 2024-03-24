package com.flexcode.ecommerce.designSystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.flexcode.ecommerce.designSystem.R.string
import com.flexcode.ecommerce.designSystem.previews.EcommercePreview
import com.flexcode.ecommerce.designSystem.theme.spacing

/**
 * common image composable a [AsyncImage] composable for loading cover images with a fixed width and height
 * @param image a nullable string of the image to be loaded
 * @param contentDescription desc of the image
 * @param modifier [Modifier]
 * @param height height in [Dp]
 * @param width width in [Dp]
 * @param padding image padding
 *
 */
@Composable
fun CommonImageItem(
    modifier: Modifier = Modifier,
    image: Any?,
    padding: Dp = spacing().none,
    cornerRadius: Dp = spacing().medium,
    height: Dp = 120.dp,
    width: Dp = 120.dp,
    contentDescription: Int,
) {
    AsyncImage(
        model =
        ImageRequest.Builder(LocalContext.current)
            .data(
                image ?: (
                    "https://www.compass.com/m/c4f0a392da0975bcf36b7bb89eaae6638d41ed11_img" +
                        "_0_9fad6/1500x1000.jpg"
                    ),
            )
            .crossfade(true)
            .build(),
        contentDescription = stringResource(contentDescription),
        contentScale = ContentScale.Crop,
        modifier =
        modifier
            .clip(RoundedCornerShape(cornerRadius))
            .height(height)
            .width(width)
            .padding(padding)
            .background(shimmerEffect()),
    )
}

/**
 * Full width image  a [AsyncImage] composable for loading full width sized images
 * @param image a nullable string of the image to be loaded
 * @param contentDescription desc of the image
 * @param modifier [Modifier]
 * @param topRadius top radius of the composable
 * @param bottomRadius radius bottom
 * @param height height in [Dp]
 * @param startPadding padding of the composable
 */
@Composable
fun FullWidthImageItem(
    image: Any?,
    modifier: Modifier = Modifier,
    contentDescription: Int = string.app_name,
    topRadius: Dp = spacing().medium,
    bottomRadius: Dp = spacing().medium,
    height: Dp = 380.dp,
    startPadding: Dp = spacing().none,
) {
    AsyncImage(
        model =
        ImageRequest.Builder(LocalContext.current)
            .data(
                image ?: (
                    "https://firebasestorage.googleapis.com/v0/b/leizen-frontend.appspot.com" +
                        "/o/android%2Fcover_photo_placeholder.webp?alt=media&token=d12e0f7f-" +
                        "3f7f-4296-ac11-815870434720"
                    ),
            )
            .crossfade(true)
            .build(),
        contentDescription = stringResource(contentDescription),
        contentScale = ContentScale.Crop,
        modifier =
        modifier
            .padding(start = startPadding, end = startPadding)
            .clip(
                RoundedCornerShape(
                    topStart = topRadius,
                    topEnd = topRadius,
                    bottomStart = bottomRadius,
                    bottomEnd = bottomRadius,
                ),
            )
            .fillMaxWidth()
            // .background(shimmerEffect())
            .height(height),
    )
}

@EcommercePreview
@Composable
fun CommonImageItemPreview() {
    CommonImageItem(image = null, contentDescription = string.app_name)
}

@EcommercePreview
@Composable
fun FullWidthImageItemPreview() {
    FullWidthImageItem(image = null, contentDescription = string.app_name)
}
