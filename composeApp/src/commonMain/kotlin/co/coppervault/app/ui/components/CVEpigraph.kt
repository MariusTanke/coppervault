package co.coppervault.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog
import co.coppervault.app.ui.theme.Stone

@Composable
fun CVEpigraph(
    quote: String,
    modifier: Modifier = Modifier,
    attribution: String? = null,
    color: Color = Fog,
    size: Int = 12,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalDivider(
            modifier = Modifier.width(40.dp),
            thickness = 0.5.dp,
            color = Stone,
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = "\u201C$quote\u201D",
            style = CVTheme.typography.displayM.copy(
                fontSize = size.sp,
                lineHeight = (size * 1.5).sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Normal,
            ),
            color = color,
            textAlign = TextAlign.Center,
        )

        if (attribution != null) {
            Spacer(Modifier.height(6.dp))
            Text(
                text = "\u2014 $attribution".uppercase(),
                style = CVTheme.typography.monoMeta.copy(
                    fontSize = (size * 0.75).sp,
                    letterSpacing = (size * 0.12).sp,
                ),
                color = Ash.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
            )
        }

        Spacer(Modifier.height(10.dp))

        HorizontalDivider(
            modifier = Modifier.width(40.dp),
            thickness = 0.5.dp,
            color = Stone,
        )
    }
}

// ── Preview ─────────────────────────────────────────────────────────

@Composable
fun CVEpigraphPreview() {
    CVTheme {
        Column(
            modifier = Modifier
                .background(CVTheme.colors.abyss)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CVEpigraph(
                quote = "The most important step a man can take is the next one. Always the next one.",
                attribution = "Dalinar Kholin",
            )
            Spacer(Modifier.height(24.dp))
            CVEpigraph(
                quote = "I am a stick.",
                size = 11,
            )
        }
    }
}
