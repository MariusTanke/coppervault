package co.coppervault.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog

@Composable
fun CVKicker(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Ash,
    size: Int = 10,
) {
    Text(
        text = text.uppercase(),
        modifier = modifier,
        style = CVTheme.typography.kicker.copy(
            fontSize = size.sp,
            letterSpacing = (size * 0.2).sp,
            fontWeight = FontWeight.Medium,
        ),
        color = color,
    )
}

// ── Preview ──��──────────────────────────���───────────────────────────

@Composable
fun CVKickerPreview() {
    CVTheme {
        Column(
            modifier = Modifier
                .background(CVTheme.colors.abyss)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            CVKicker("The Archive")
            CVKicker("Today's Page", color = Aurum, size = 9)
            CVKicker("Chronology · Cosmic", color = Fog, size = 8)
        }
    }
}
