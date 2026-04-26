package co.coppervault.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Parchment

/**
 * Brand wordmark: "Copper" (roman, weight 600, Parchment)
 *                + "vault"  (italic, weight 400, Aurum)
 *
 * Ported from WHWordmark (design-system.jsx) with rebrand:
 * "World" + "hopper" → "Copper" + "vault".
 */
@Composable
fun CVWordmark(
    modifier: Modifier = Modifier,
    size: Int = 24,
    tight: Boolean = false,
) {
    val gap = if (tight) (size * 0.08).dp else (size * 0.15).dp
    val ls = if (tight) (size * 0.01).sp else (size * 0.04).sp

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(
            text = "Copper",
            style = CVTheme.typography.displayXL.copy(
                fontSize = size.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                letterSpacing = ls,
                lineHeight = size.sp,
            ),
            color = Parchment,
        )
        Spacer(Modifier.width(gap))
        Text(
            text = "vault",
            style = CVTheme.typography.displayXL.copy(
                fontSize = size.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Italic,
                letterSpacing = ls,
                lineHeight = size.sp,
            ),
            color = Aurum,
        )
    }
}

// ── Preview ─────────────────────────────────────────────────────────

@Composable
fun CVWordmarkPreview() {
    CVTheme {
        Column(
            modifier = Modifier
                .background(CVTheme.colors.abyss)
                .padding(20.dp),
        ) {
            CVWordmark(size = 32)
            Spacer(Modifier.padding(top = 16.dp))
            CVWordmark(size = 26)
            Spacer(Modifier.padding(top = 16.dp))
            CVWordmark(size = 18)
        }
    }
}
