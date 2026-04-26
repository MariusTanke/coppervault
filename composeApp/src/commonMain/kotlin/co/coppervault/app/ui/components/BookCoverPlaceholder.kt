package co.coppervault.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Parchment

/**
 * Placeholder book cover with world-tinted gradient and corner ornaments.
 * Reusable in LibraryScreen, BookDetail, and Home "Continue" carousel.
 */
@Composable
fun BookCoverPlaceholder(
    worldAccent: Color,
    label: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .aspectRatio(0.7f)
            .background(
                brush = Brush.verticalGradient(
                    0f to worldAccent.copy(alpha = 0.5f),
                    0.6f to worldAccent.copy(alpha = 0.2f),
                    1f to Color(0xFF05060A),
                ),
            )
            .border(0.5.dp, worldAccent.copy(alpha = 0.4f)),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = label.uppercase(),
            style = CVTheme.typography.displayM.copy(
                fontSize = 18.sp,
                letterSpacing = 2.sp,
            ),
            color = Parchment.copy(alpha = 0.55f),
        )

        CornerOrnament(
            corner = Corner.TopLeft,
            color = worldAccent.copy(alpha = 0.5f),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 6.dp, y = 6.dp),
        )
        CornerOrnament(
            corner = Corner.BottomRight,
            color = worldAccent.copy(alpha = 0.5f),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-6).dp, y = (-6).dp),
        )
    }
}
