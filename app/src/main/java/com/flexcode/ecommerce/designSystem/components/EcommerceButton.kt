package com.flexcode.ecommerce.designSystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flexcode.ecommerce.designSystem.previews.EcommercePreview
import com.flexcode.ecommerce.designSystem.theme.EcommerceTheme
import com.flexcode.ecommerce.designSystem.theme.buttonColor
import com.flexcode.ecommerce.designSystem.theme.spacing
import com.flexcode.ecommerce.R.string as AppString

/**
 * common button  a reusable button composable
 * @param modifier [Modifier] for additional styling of the composable
 * @param text the text of the button
 * @param textColor color of the text
 * @param containerColor color of the button
 * @param strokeColor color of the button stroke
 * @param padding padding of the button start and end
 * @param textPadding spacing of the button [text]
 * @param style [TextStyle] of the button [text]
 * @param clickable a boolean value to enable and disable the button
 * @param onClick a callback function for on click of the button
 * @param fontSize size of the text
 * @author Felix-Kariuki
 * @since 1.0.0
 */
@Composable
fun EcommerceButton(
    modifier: Modifier = Modifier,
    text: Int,
    textColor: Color = Color.White,
    containerColor: Color = buttonColor,
    strokeColor: Color = buttonColor,
    cornerRadius: Dp = 50.dp,
    padding: Dp = spacing().none,
    strokeSize: Dp = 1.5.dp,
    fontSize: TextUnit = 18.sp,
    textPadding: Dp = spacing().extraSmall,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    buttonElevation: Dp = 0.dp,
    clickable: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(start = padding, end = padding),
        colors =
        ButtonDefaults.buttonColors(
            containerColor = if (clickable) containerColor else textColor,
        ),
        border = BorderStroke(strokeSize, color = strokeColor),
        shape = RoundedCornerShape(cornerRadius),
        elevation = ButtonDefaults.buttonElevation(buttonElevation),
        enabled = clickable,
    ) {
        EcommerceText(
            text = text,
            modifier =
            modifier
                .padding(textPadding)
                .wrapContentWidth(align = Alignment.CenterHorizontally),
            textColor = textColor,
            style = style,
            textAlign = TextAlign.Center,
            fontSize = fontSize,
        )
    }
}

/**
 * common result button  a reusable button composable when the [text] is a string
 * @param modifier [Modifier] for additional styling of the composable
 * @param text the text of the button
 * @param textColor color of the text
 * @param containerColor color of the button
 * @param strokeColor color of the button stroke
 * @param padding padding of the button start and end
 * @param textPadding spacing of the button [text]
 * @param style [TextStyle] of the button [text]
 * @param clickable a boolean value to enable and disable the button
 * @param onClick a callback function for on click of the button
 * @param fontSize size of the text
 * @author Felix-Kariuki
 * @since 1.0.0
 */
@Composable
fun EcommerceResultButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.White,
    containerColor: Color = buttonColor,
    strokeColor: Color = buttonColor,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    cornerRadius: Dp = 50.dp,
    padding: Dp = spacing().medium,
    textPadding: Dp = spacing().buttonSpacing,
    borderWidth: Dp = 1.5.dp,
    fontSize: TextUnit = 16.sp,
    buttonElevation: Dp = 0.dp,
    clickable: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(start = padding, end = padding),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        border = BorderStroke(borderWidth, color = strokeColor),
        shape = RoundedCornerShape(cornerRadius),
        elevation = ButtonDefaults.buttonElevation(buttonElevation),
        enabled = clickable,
    ) {
        EcommerceResultText(
            text = text,
            modifier =
            modifier
                .padding(textPadding)
                .wrapContentWidth(align = Alignment.CenterHorizontally),
            textColor = textColor,
            style = style,
            fontSize = fontSize,
            textAlign = TextAlign.Center,
        )
    }
}

@EcommercePreview
@Composable
fun EcommerceButtonPreview() {
    EcommerceTheme {
        EcommerceButton(text = AppString.app_name) {
        }
    }
}

@EcommercePreview
@Composable
fun EcommerceResultButtonPreview() {
    EcommerceTheme {
        EcommerceResultButton(text = "Buy Now") {
        }
    }
}
