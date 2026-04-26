package co.coppervault.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme

@Composable
fun CVSpoilerStrip(
    level: String,
    modifier: Modifier = Modifier,
    color: Color = Aurum,
) {
    val shape = RoundedCornerShape(2.dp)

    Row(
        modifier = modifier
            .background(color.copy(alpha = 0.08f), shape)
            .border(0.5.dp, color.copy(alpha = 0.30f), shape)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Icon(
            imageVector = CVIcons.Warn,
            contentDescription = null,
            modifier = Modifier.size(12.dp),
            tint = color,
        )
        Text(
            text = "Spoilers · ${level}".uppercase(),
            style = CVTheme.typography.monoMeta.copy(
                fontWeight = FontWeight.Medium,
                letterSpacing = CVTheme.typography.monoMeta.letterSpacing,
            ),
            color = color,
        )
    }
}

// ── Preview ─────────────────────────────────────────────────────────

@Composable
fun CVSpoilerStripPreview() {
    CVTheme {
        Column(
            modifier = Modifier
                .background(CVTheme.colors.abyss)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            CVSpoilerStrip("Era 2")
            CVSpoilerStrip("SA5")
            CVSpoilerStrip("Secret Projects", color = Color(0xFFC94B6E))
        }
    }
}
