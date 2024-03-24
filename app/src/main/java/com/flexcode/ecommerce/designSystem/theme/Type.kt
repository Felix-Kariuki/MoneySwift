package com.flexcode.ecommerce.designSystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.flexcode.ecommerce.R

// Set of Material typography styles to start with
val typography =
    Typography(
        bodyLarge =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.amsi_pro_semibold)),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.5.sp,
            lineHeight = 24.sp,
        ),
        bodyMedium =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.amsi_pro_regular)),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.sp,
            lineHeight = 24.sp,
        ),
        labelMedium =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.amsi_pro_regular)),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.sp,
            lineHeight = 16.sp,
        ),
        titleLarge =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.amsi_pro_semibold)),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            letterSpacing = 0.sp,
            lineHeight = 24.sp,
        ),
        titleMedium =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.amsi_pro_bold)),
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            letterSpacing = 0.sp,
            lineHeight = 24.sp,
        ),
        titleSmall =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.amsi_pro_bold)),
            fontWeight = FontWeight.ExtraLight,
            fontSize = 18.sp,
            letterSpacing = 0.sp,
            lineHeight = 24.sp,
        ),
        labelSmall =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.amsi_pro_regular)),
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 0.5.sp,
            lineHeight = 16.sp,
        ),
    )
