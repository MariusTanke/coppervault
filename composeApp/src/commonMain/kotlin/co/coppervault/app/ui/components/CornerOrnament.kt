package co.coppervault.app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class Corner { TopLeft, TopRight, BottomLeft, BottomRight }

@Composable
fun CornerOrnament(
    corner: Corner,
    color: Color,
    size: Dp = 8.dp,
    strokeWidth: Float = 0.5f,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(size)
            .drawBehind {
                val w = this.size.width
                val h = this.size.height
                when (corner) {
                    Corner.TopLeft -> {
                        // top edge
                        drawLine(color, Offset(0f, 0f), Offset(w, 0f), strokeWidth)
                        // left edge
                        drawLine(color, Offset(0f, 0f), Offset(0f, h), strokeWidth)
                    }
                    Corner.TopRight -> {
                        drawLine(color, Offset(0f, 0f), Offset(w, 0f), strokeWidth)
                        drawLine(color, Offset(w, 0f), Offset(w, h), strokeWidth)
                    }
                    Corner.BottomLeft -> {
                        drawLine(color, Offset(0f, 0f), Offset(0f, h), strokeWidth)
                        drawLine(color, Offset(0f, h), Offset(w, h), strokeWidth)
                    }
                    Corner.BottomRight -> {
                        drawLine(color, Offset(w, 0f), Offset(w, h), strokeWidth)
                        drawLine(color, Offset(0f, h), Offset(w, h), strokeWidth)
                    }
                }
            },
    )
}
