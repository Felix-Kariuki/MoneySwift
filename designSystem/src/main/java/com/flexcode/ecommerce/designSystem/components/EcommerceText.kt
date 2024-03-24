package com.flexcode.ecommerce.designSystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.flexcode.ecommerce.designSystem.previews.EcommercePreview
import com.flexcode.ecommerce.designSystem.theme.EcommerceTheme
import com.flexcode.ecommerce.designSystem.theme.spacing
import com.flexcode.ecommerce.designSystem.R.string as AppString

/**
 * Ecommerce  text composable - used in the entire app whenever we have
 * @param modifier [Modifier]
 * @param text the text to be displayed
 * @param textColor color of the text
 * @param style the [TextStyle] of the text
 * @param overflow defines the [TextOverflow] of the composable
 * @param maxLines  the maxLines of the composable
 * @param textPadding padding of the text
 * @param fontSize size of the text [TextUnit]
 * @param textDecoration defines the [TextDecoration]
 * @param textAlign definess the [TextAlign] of the composable
 */
@Composable
fun EcommerceText(
    modifier: Modifier = Modifier,
    text: Int,
    textColor: Color = Color.Black,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = Int.MAX_VALUE,
    fontSize: TextUnit = 16.sp,
    textPadding: Dp = spacing().none,
    textDecoration: TextDecoration = TextDecoration.None,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = stringResource(id = text),
        style = style,
        color = textColor,
        maxLines = maxLines,
        overflow = overflow,
        fontSize = fontSize,
        modifier = modifier.padding(textPadding),
        textDecoration = textDecoration,
        textAlign = textAlign,
    )
}

/**
 * Result text composable - used in the entire app whenever we have a string text
 * @param modifier [Modifier]
 * @param text the text to be displayed
 * @param textColor color of the text
 * @param style the [TextStyle] of the text
 * @param overflow defines the [TextOverflow] of the composable
 * @param maxLines  the maxLines of the composable
 * @param textPadding padding of the text
 * @param textDecoration defines the [TextDecoration]
 * @param textAlign defines the [TextAlign] of the composable
 * @param backColor background color
 * @param shapeRadius the radius in [Dp] of the composable
 */
@Composable
fun EcommerceResultText(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.Black,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = Int.MAX_VALUE,
    fontSize: TextUnit = 15.8.sp,
    textDecoration: TextDecoration = TextDecoration.None,
    textAlign: TextAlign = TextAlign.Start,
    textPadding: Dp = spacing().none,
    backColor: Color = Color.Transparent,
    shapeRadius: Dp = spacing().none,
) {
    Text(
        text = text,
        style = style,
        color = textColor,
        maxLines = maxLines,
        overflow = overflow, fontSize = fontSize,
        textAlign = textAlign,
        textDecoration = textDecoration,
        modifier =
        modifier
            .background(
                color = backColor,
                shape = RoundedCornerShape(shapeRadius),
            )
            .padding(textPadding),
    )
}

@EcommercePreview
@Composable
fun EcommerceTextPreview() {
    EcommerceTheme {
        EcommerceText(text = AppString.app_name)
    }
}

@EcommercePreview
@Composable
fun EcommerceResultTextPreview() {
    EcommerceTheme {
        EcommerceResultText(text = "Ecommerce application")
    }
}
