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
        val rDotOrbit = (rInner + rOuter) / 2f   // between the two rings
        val rCenter = s * 0.04f
        val dotBig = s * 0.045f                   // proportional dot sizes
        val dotSmall = s * 0.028f
        val halfPI = PI.toFloat() / 2f

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

        // 8 orbiting dots at 45° increments, first dot at 12 o'clock (-π/2)
        for (i in 0 until dots) {
            val angle = (i.toFloat() / dots) * 2f * PI.toFloat() - halfPI
            val x = cx + cos(angle) * rDotOrbit
            val y = cy + sin(angle) * rDotOrbit
            val isBig = i == 0
            drawCircle(
                color = color.copy(alpha = if (isBig) 1f else 0.75f),
                radius = if (isBig) dotBig else dotSmall,
                center = Offset(x, y),
            )
        }

        // 4 radial hairlines at 0°, 90°, 180°, 270° (every other dot)
        // Short segment (~6 dp) bridging inner ring → outer ring
        for (i in 0 until dots step 2) {
            val angle = (i.toFloat() / dots) * 2f * PI.toFloat() - halfPI
            val x1 = cx + cos(angle) * rInner
            val y1 = cy + sin(angle) * rInner
            val x2 = cx + cos(angle) * rOuter
            val y2 = cy + sin(angle) * rOuter
            drawLine(
                color = color.copy(alpha = 0.5f),
                start = Offset(x1, y1),
                end = Offset(x2, y2),
                strokeWidth = strokeWidth,
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
