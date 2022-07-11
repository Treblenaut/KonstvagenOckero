package se.kulturforeningenkonstvagen.konstvagenockeroarna.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import se.kulturforeningenkonstvagen.konstvagenockeroarna.R

val Raleway = FontFamily(
    Font(R.font.raleway_black, FontWeight.Black),
    Font(R.font.raleway_blackitalic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.raleway_bold, FontWeight.Bold),
    Font(R.font.raleway_bolditalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.raleway_extrabold, FontWeight.ExtraBold),
    Font(R.font.raleway_extrabolditalic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.raleway_extralight, FontWeight.ExtraLight),
    Font(R.font.raleway_extralightitalic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.raleway_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.raleway_light, FontWeight.Light),
    Font(R.font.raleway_lightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.raleway_medium, FontWeight.Medium),
    Font(R.font.raleway_mediumitalic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.raleway_regular),
    Font(R.font.raleway_semibold, FontWeight.SemiBold),
    Font(R.font.raleway_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.raleway_thin, FontWeight.Thin),
    Font(R.font.raleway_thinitalic, FontWeight.Thin, FontStyle.Italic)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Light,
        fontSize = 131.sp,
        letterSpacing = -2.sp
    ),
    h2 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Light,
        fontSize = 81.sp,
        letterSpacing = -1.sp
    ),
    h3 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 65.sp,
        letterSpacing = 0.sp
    ),
    h4 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 47.sp,
        letterSpacing = 0.sp
    ),
    h5 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        letterSpacing = 0.sp
    ),
    h6 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Medium,
        fontSize = 27.sp,
        letterSpacing = 0.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 21.sp,
        letterSpacing = 0.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Medium,
        fontSize = 19.sp,
        letterSpacing = 0.sp
    ),
    body1 =  TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 21.sp,
        letterSpacing = 1.sp
    ),
    body2 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 19.sp,
        letterSpacing = 0.sp
    ),
    button = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Medium,
        fontSize = 19.sp,
        letterSpacing = 1.sp
    ),
    caption = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.sp
    ),
    overline = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        letterSpacing = 2.sp
    ),

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)