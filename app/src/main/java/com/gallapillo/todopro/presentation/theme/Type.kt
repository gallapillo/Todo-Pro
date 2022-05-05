package com.gallapillo.todopro.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gallapillo.todopro.R

val GoogleSansBold = FontFamily(Font((R.font.googlesansbold)))
val GoogleSansMedium = FontFamily(Font((R.font.googlesansmedium)))
val GoogleSansRegular = FontFamily(Font((R.font.googlesansregular)))

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)