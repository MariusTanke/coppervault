package co.coppervault.app.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coppervault.composeapp.generated.resources.Res
import coppervault.composeapp.generated.resources.eb_garamond_italic
import coppervault.composeapp.generated.resources.eb_garamond_regular
import coppervault.composeapp.generated.resources.inter_regular
import coppervault.composeapp.generated.resources.jetbrains_mono_regular
import org.jetbrains.compose.resources.Font

// ── Font families ───────────────────────────────────────────────────
// Variable fonts: the weight is resolved by the renderer at runtime.

@Composable
fun EBGaramondFamily(): FontFamily = FontFamily(
    Font(Res.font.eb_garamond_regular, FontWeight.Normal, FontStyle.Normal),
    Font(Res.font.eb_garamond_regular, FontWeight.Medium, FontStyle.Normal),
    Font(Res.font.eb_garamond_regular, FontWeight.SemiBold, FontStyle.Normal),
    Font(Res.font.eb_garamond_regular, FontWeight.Bold, FontStyle.Normal),
    Font(Res.font.eb_garamond_italic, FontWeight.Normal, FontStyle.Italic),
    Font(Res.font.eb_garamond_italic, FontWeight.Medium, FontStyle.Italic),
    Font(Res.font.eb_garamond_italic, FontWeight.Bold, FontStyle.Italic),
)

@Composable
fun InterFamily(): FontFamily = FontFamily(
    Font(Res.font.inter_regular, FontWeight.Normal, FontStyle.Normal),
    Font(Res.font.inter_regular, FontWeight.Medium, FontStyle.Normal),
)

@Composable
fun JetBrainsMonoFamily(): FontFamily = FontFamily(
    Font(Res.font.jetbrains_mono_regular, FontWeight.Normal, FontStyle.Normal),
    Font(Res.font.jetbrains_mono_regular, FontWeight.Medium, FontStyle.Normal),
)

// ── Type scale ──────────────────────────────────────────────────────
// Values from README: size / letter-spacing / line-height factor

@Immutable
data class CVTypography(
    // Display — EB Garamond
    val displayXL: TextStyle,
    val displayL: TextStyle,
    val displayM: TextStyle,

    // UI — Inter
    val body: TextStyle,
    val uiL: TextStyle,
    val uiS: TextStyle,

    // Mono — JetBrains Mono
    val kicker: TextStyle,
    val monoMeta: TextStyle,
)

@Composable
fun cvTypography(): CVTypography {
    val garamond = EBGaramondFamily()
    val inter = InterFamily()
    val mono = JetBrainsMonoFamily()

    return CVTypography(
        // Display XL: 30 sp / -0.5 / line-height 1.1
        displayXL = TextStyle(
            fontFamily = garamond,
            fontWeight = FontWeight.Normal,
            fontSize = 30.sp,
            letterSpacing = (-0.5).sp,
            lineHeight = 33.sp,
        ),
        // Display L: 28 sp / -0.4 / 1.1
        displayL = TextStyle(
            fontFamily = garamond,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            letterSpacing = (-0.4).sp,
            lineHeight = 30.8.sp,
        ),
        // Display M: 22 sp / -0.3 / 1.25
        displayM = TextStyle(
            fontFamily = garamond,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            letterSpacing = (-0.3).sp,
            lineHeight = 27.5.sp,
        ),

        // Body: 14 sp / 0 / 1.55  (base 13 × 1.10)
        body = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 0.sp,
            lineHeight = 21.7.sp,
        ),
        // UI L: 13 sp / 0 / 1.4  (base 12 × 1.10)
        uiL = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            letterSpacing = 0.sp,
            lineHeight = 18.2.sp,
        ),
        // UI S: 12 sp / 0.2 / 1.4  (base 11 × 1.10)
        uiS = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            letterSpacing = 0.2.sp,
            lineHeight = 16.8.sp,
        ),

        // Kicker: 11 sp / letter-spacing 2 / uppercase  (base 10 × 1.10)
        kicker = TextStyle(
            fontFamily = mono,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            letterSpacing = 2.sp,
        ),
        // Mono meta: 11 sp / 0.5–1 / often uppercase
        monoMeta = TextStyle(
            fontFamily = mono,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            letterSpacing = 0.5.sp,
        ),
    )
}

internal val LocalCVTypography = staticCompositionLocalOf<CVTypography> {
    error("CVTypography not provided — wrap your UI in CVTheme")
}
