package co.coppervault.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Roshar
import co.coppervault.app.ui.theme.Scadrial
import co.coppervault.app.ui.theme.Sel
import co.coppervault.app.ui.theme.Abyss

@Composable
fun CVChip(
    label: String,
    modifier: Modifier = Modifier,
    accent: Color = Aurum,
    filled: Boolean = false,
) {
    val shape = RoundedCornerShape(2.dp)

    Box(
        modifier = modifier
            .then(
                if (filled) {
                    Modifier.background(accent, shape)
                } else {
                    Modifier
                        .border(0.5.dp, accent, shape)
                        .drawBehind {
                            // 2 dp accent border-left
                            drawRect(
                                color = accent,
                                topLeft = Offset.Zero,
                                size = Size(2.dp.toPx(), size.height),
                            )
                        }
                }
            )
            .padding(vertical = 3.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = label.uppercase(),
            style = CVTheme.typography.monoMeta.copy(
                fontWeight = FontWeight.Medium,
                letterSpacing = CVTheme.typography.monoMeta.letterSpacing,
            ),
            color = if (filled) Abyss else accent,
        )
    }
}

// ── Preview ��──────────────────────��────────────────────────���────────

@Composable
fun CVChipPreview() {
    CVTheme {
        Column(
            modifier = Modifier
                .background(CVTheme.colors.abyss)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                CVChip("Roshar", accent = Roshar)
                CVChip("Scadrial", accent = Scadrial)
                CVChip("Sel", accent = Sel)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                CVChip("Stormlight", accent = Aurum, filled = true)
                CVChip("Era 2", accent = Scadrial, filled = true)
            }
        }
    }
}
