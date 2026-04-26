package co.coppervault.app.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// ── Color scheme wrapper ────────────────────────────────────────────
// Coppervault is dark-only. This wrapper gives a typed API and lets us
// swap palettes later (e.g. a per-world accent tint) via CompositionLocal.

@Immutable
data class CVColors(
    val void: Color = Void,
    val abyss: Color = Abyss,
    val ink: Color = Ink,
    val stone: Color = Stone,
    val mist: Color = Mist,
    val slate: Color = Slate,
    val ash: Color = Ash,
    val fog: Color = Fog,
    val linen: Color = Linen,
    val parchment: Color = Parchment,
    val aurum: Color = Aurum,
    val aurumHi: Color = AurumHi,
    val aurumLo: Color = AurumLo,
)

internal val LocalCVColors = staticCompositionLocalOf { CVColors() }

// ── Theme entry point ───────────────────────────────────────────────

@Composable
fun CVTheme(
    colors: CVColors = CVColors(),
    content: @Composable () -> Unit,
) {
    val typography = cvTypography()

    CompositionLocalProvider(
        LocalCVColors provides colors,
        LocalCVTypography provides typography,
        content = content,
    )
}

// ── Convenience accessor ────────────────────────────────────────────

object CVTheme {
    val colors: CVColors
        @Composable @ReadOnlyComposable
        get() = LocalCVColors.current

    val typography: CVTypography
        @Composable @ReadOnlyComposable
        get() = LocalCVTypography.current
}
