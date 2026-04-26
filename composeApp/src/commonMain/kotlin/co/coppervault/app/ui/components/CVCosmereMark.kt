package co.coppervault.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Original Cosmere Mark: two concentric circle strokes + center dot
 * + 8 filled dots at 45° increments + 4 radial hairlines (every 90°).
 *
 * Ported from WHCosmereMark (design-system.jsx).
 */
@Composable
fun CVCosmereMark(
    modifier: Modifier = Modifier,
    size: Dp = 56.dp,
    color: Color = Aurum,
    strokeWidth: Float = 1f,
    dots: Int = 8,
) {
    Canvas(modifier = modifier.size(size)) {
        val cx = this.size.width / 2f
        val cy = this.size.height / 2f
        val s = this.size.minDimension

        val rOuter = s * 0.42f
        val rInner = s * 0.24f
        val rDotOrbit = s * 0.42f
        val rCenter = s * 0.04f

        // Outer ring (0.85 alpha)
        drawCircle(
            color = color.copy(alpha = 0.85f),
            radius = rOuter,
            center = Offset(cx, cy),
            style = Stroke(width = strokeWidth),
        )

        // Inner ring (0.55 alpha, thinner)
        drawCircle(
            color = color.copy(alpha = 0.55f),
            radius = rInner,
            center = Offset(cx, cy),
            style = Stroke(width = strokeWidth * 0.7f),
        )

        // Center dot
        drawCircle(
            color = color.copy(alpha = 0.9f),
            radius = rCenter,
            center = Offset(cx, cy),
        )

        // Orbiting dots
        for (i in 0 until dots) {
            val angle = (i.toFloat() / dots) * 2f * PI.toFloat() - PI.toFloat() / 2f
            val x = cx + cos(angle) * rDotOrbit
            val y = cy + sin(angle) * rDotOrbit
            val isBig = i == 0
            drawCircle(
                color = color.copy(alpha = if (isBig) 1f else 0.75f),
                radius = if (isBig) 2.4f else 1.4f,
                center = Offset(x, y),
            )
        }

        // Radial hairlines (every 90°, i.e. every other dot for 8 dots)
        for (i in 0 until dots) {
            if (i % 2 != 0) continue
            val angle = (i.toFloat() / dots) * 2f * PI.toFloat() - PI.toFloat() / 2f
            val x1 = cx + cos(angle) * rInner
            val y1 = cy + sin(angle) * rInner
            val x2 = cx + cos(angle) * rOuter
            val y2 = cy + sin(angle) * rOuter
            drawLine(
                color = color.copy(alpha = 0.4f),
                start = Offset(x1, y1),
                end = Offset(x2, y2),
                strokeWidth = strokeWidth * 0.5f,
            )
        }
    }
}

// ── Preview ─────────────────────────────────────────────────────────

@Composable
fun CVCosmereMarkPreview() {
    CVTheme {
        Column(
            modifier = Modifier
                .background(CVTheme.colors.abyss)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            CVCosmereMark(size = 72.dp)
            CVCosmereMark(size = 44.dp, strokeWidth = 0.8f)
            CVCosmereMark(size = 28.dp, color = Color(0xFF5B8ED1), strokeWidth = 0.6f)
        }
    }
}
