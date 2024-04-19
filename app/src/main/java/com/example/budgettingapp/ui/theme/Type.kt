package com.example.budgettingapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.budgettingapp.R

val Lettera = FontFamily(
        Font(R.font.lettera_regular)
)

val NDot = FontFamily(
        Font(R.font.ndot)
)

val NDot2 = FontFamily(
        Font(R.font.ndot_2)
)

// Set of Material typography styles to start with
val Typography = Typography(
        bodyLarge = TextStyle(
                fontFamily = NDot2,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp
        ),
        titleLarge = TextStyle(
                fontFamily = Lettera,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp
        ),
        labelSmall = TextStyle(
                fontFamily = Lettera,
                fontWeight = FontWeight.Medium,
                fontSize = 11.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp
        )
)

