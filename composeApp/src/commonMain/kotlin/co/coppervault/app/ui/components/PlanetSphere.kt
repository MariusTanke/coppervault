package co.coppervault.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.coppervault.app.ui.theme.Void

/**
 * Planet sphere with 3D radial gradient (light at 30% 30%),
 * accent border, outer glow, and inner shadow on the dark side.
 */
@Composable
fun PlanetSphere(
    accent: Color,
    sizeDp: Dp,
    modifier: Modifier = Modifier,
    glowing: Boolean = false,
) {
    val glowAlpha = if (glowing) 0.5f else 0.25f
    val glowRadius = if (glowing) sizeDp * 0.8f else sizeDp * 0.4f

    Box(
        modifier = modifier
            // Outer glow layer
            .drawBehind {
                val center = Offset(size.width / 2f, size.height / 2f)
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            accent.copy(alpha = glowAlpha),
                            Color.Transparent,
                        ),
                        center = center,
                        radius = glowRadius.toPx(),
                    ),
                    radius = glowRadius.toPx(),
                    center = center,
                )
            }
            .size(sizeDp)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(accent, Void),
                    center = Offset(
                        sizeDp.value * 0.3f * 2f,  // roughly 30% of diameter → px-like
                        sizeDp.value * 0.3f * 2f,
                    ),
                    radius = sizeDp.value * 0.85f * 2f,
                ),
                shape = CircleShape,
            )
            .border(0.5.dp, accent, CircleShape)
            // Inner shadow (dark side, bottom-right)
            .drawBehind {
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.5f),
                        ),
                        center = Offset(size.width * 0.7f, size.height * 0.7f),
                        radius = size.minDimension * 0.6f,
                    ),
                    radius = size.minDimension / 2f,
                    center = Offset(size.width / 2f, size.height / 2f),
                )
            },
    )
}
