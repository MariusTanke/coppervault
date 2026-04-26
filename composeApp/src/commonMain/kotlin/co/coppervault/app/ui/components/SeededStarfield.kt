package co.coppervault.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import co.coppervault.app.ui.theme.Linen

private class StarLCG(seed: Long) {
    private var state = seed or 1L
    fun nextFloat(): Float {
        state = state * 48271L % 2147483647L
        return (state.toFloat() / 2147483647f)
    }
}

private data class Star(
    val x: Float,
    val y: Float,
    val s: Float,
)

/**
 * Seeded starfield layer with horizontal parallax offset.
 * Uses the same LCG as CVMistBg for deterministic placement.
 */
@Composable
fun SeededStarfield(
    count: Int,
    seed: Long,
    parallaxPx: Float,
    modifier: Modifier = Modifier,
    color: Color = Linen,
) {
    val stars = remember(seed, count) {
        val rng = StarLCG(seed)
        List(count) {
            Star(
                x = rng.nextFloat(),
                y = rng.nextFloat(),
                s = rng.nextFloat(),
            )
        }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        for (star in stars) {
            val starSize = if (star.s < 0.3f) 2f else 1f
            val alpha = 0.15f + star.s * 0.45f
            val x = star.x * size.width + parallaxPx
            // wrap horizontally
            val wrappedX = ((x % size.width) + size.width) % size.width
            drawRect(
                color = color.copy(alpha = alpha),
                topLeft = Offset(wrappedX, star.y * size.height),
                size = Size(starSize, starSize),
            )
        }
    }
}
