package co.coppervault.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.coppervault.app.ui.theme.Abyss
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Void
import kotlin.math.abs
import kotlin.math.sin

/**
 * Seeded pseudo-random number generator (Lehmer LCG).
 * Deterministic: same seed → same stars every frame.
 */
private class SeededRandom(seed: Long) {
    private var state = seed or 1L
    fun nextFloat(): Float {
        state = state * 48271L % 2147483647L
        return (state.toFloat() / 2147483647f)
    }
}

private data class StarSpec(
    val x: Float, // 0..1
    val y: Float, // 0..1
    val radius: Float, // dp
    val alpha: Float,
)

@Composable
fun CVMistBg(
    modifier: Modifier = Modifier,
    starCount: Int = 50,
    seed: Long = 42L,
    intensity: Float = 1f,
    content: @Composable BoxScope.() -> Unit = {},
) {
    val stars = remember(seed, starCount) {
        val rng = SeededRandom(seed)
        List(starCount) {
            StarSpec(
                x = rng.nextFloat(),
                y = rng.nextFloat(),
                radius = 0.4f + rng.nextFloat() * 1.0f,
                alpha = 0.25f + rng.nextFloat() * 0.55f,
            )
        }
    }

    Box(modifier = modifier) {
        // Layered radial gradients matching design-system.jsx WHMistBg
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Base: Void → Abyss → Void vertical
            drawRect(
                brush = Brush.verticalGradient(
                    0f to Void,
                    0.5f to Abyss,
                    1f to Void,
                )
            )

            // Aurum glow (top center, elliptical) — 9% intensity
            drawRect(
                brush = Brush.radialGradient(
                    0f to Aurum.copy(alpha = 0.09f * intensity),
                    0.55f to Color.Transparent,
                    center = Offset(size.width * 0.5f, size.height * 0.2f),
                    radius = size.width * 0.8f,
                )
            )

            // Roshar blue (bottom center) — 7%
            drawRect(
                brush = Brush.radialGradient(
                    0f to Color(0xFF5B8ED1).copy(alpha = 0.07f * intensity),
                    0.6f to Color.Transparent,
                    center = Offset(size.width * 0.5f, size.height * 1.1f),
                    radius = size.width * 1.2f,
                )
            )

            // Purple tint (lower-left) — 5%
            drawRect(
                brush = Brush.radialGradient(
                    0f to Color(0xFFA25CC1).copy(alpha = 0.05f * intensity),
                    0.55f to Color.Transparent,
                    center = Offset(size.width * 0.15f, size.height * 0.8f),
                    radius = size.width * 0.6f,
                )
            )

            // Seeded stars
            for (star in stars) {
                drawCircle(
                    color = Color.White.copy(alpha = star.alpha),
                    radius = star.radius,
                    center = Offset(star.x * size.width, star.y * size.height),
                )
            }
        }

        // Content on top
        content()
    }
}

// ── Preview ─────────────────────────────────────────────────────────

@Composable
fun CVMistBgPreview() {
    CVTheme {
        CVMistBg(
            modifier = Modifier.size(360.dp, 640.dp),
        )
    }
}
