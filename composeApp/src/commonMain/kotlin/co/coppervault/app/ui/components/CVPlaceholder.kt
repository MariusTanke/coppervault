package co.coppervault.app.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Ink
import co.coppervault.app.ui.theme.Linen
import co.coppervault.app.ui.theme.Stone

/**
 * Book cover placeholder — diagonal gradient tint over Ink,
 * subtle diagonal hairlines, centered label in mono.
 */
@Composable
fun CVPlaceholder(
    label: String,
    modifier: Modifier = Modifier,
    width: Dp = 120.dp,
    height: Dp = 160.dp,
    tint: Color = Stone,
) {
    Box(
        modifier = modifier
            .size(width, height)
            .drawBehind {
                // Background gradient: tint-tinted Ink
                drawRect(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            tint.copy(alpha = 0.4f),
                            tint.copy(alpha = 0.15f),
                        ),
                        start = Offset.Zero,
                        end = Offset(size.width, size.height),
                    ),
                )
                // Ink base underneath (blend)
                drawRect(color = Ink.copy(alpha = 0.6f))
                // Re-draw tint gradient on top
                drawRect(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            tint.copy(alpha = 0.35f),
                            tint.copy(alpha = 0.1f),
                        ),
                        start = Offset.Zero,
                        end = Offset(size.width, size.height),
                    ),
                )
                // 4 diagonal hairlines
                val lineColor = tint.copy(alpha = 0.3f)
                val sw = 0.5f
                for (i in 1..4) {
                    val frac = i / 5f
                    drawLine(
                        color = lineColor,
                        start = Offset(size.width * frac, 0f),
                        end = Offset(0f, size.height * frac),
                        strokeWidth = sw,
                    )
                }
            }
            .border(0.5.dp, Stone),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = label.uppercase(),
            style = CVTheme.typography.monoMeta.copy(
                fontSize = 12.sp,
                letterSpacing = 1.sp,
                fontWeight = FontWeight.Medium,
            ),
            color = Linen,
        )
    }
}
