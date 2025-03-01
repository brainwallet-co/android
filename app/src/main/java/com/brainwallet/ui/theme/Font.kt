package com.brainwallet.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.brainwallet.R

class Font {
}

val openSauceOneFamily = FontFamily(
    Font(R.font.open_sauce_one_light, FontWeight.Light),
    Font(R.font.open_sauce_one_bold, FontWeight.Bold),
    Font(R.font.open_sauce_one_medium, FontWeight.Medium),
    Font(R.font.open_sauce_one_regular, FontWeight.Normal),
    Font(R.font.open_sauce_one_semi_bold, FontWeight.SemiBold)
)