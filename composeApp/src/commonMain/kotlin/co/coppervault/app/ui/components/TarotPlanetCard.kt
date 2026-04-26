package co.coppervault.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.coppervault.app.data.WorldMeta
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Ink
import co.coppervault.app.ui.theme.Parchment
import co.coppervault.app.ui.theme.Void

private val ROMAN = arrayOf("I", "II", "III", "IV", "V", "VI", "VII", "VIII")
private val CARD_SHAPE = RoundedCornerShape(8.dp)
private val INNER_SHAPE = RoundedCornerShape(4.dp)

/**
 * Tarot-style planet card for the coverflow rail.
 * 130dp × 200dp with decorative frame, planet sphere, name and saga.
 */
@Composable
fun TarotPlanetCard(
    world: WorldMeta,
    index: Int,
    sagaName: String,
    isActive: Boolean,
    modifier: Modifier = Modifier,
) {
    val accent = world.accent
    val borderColor = accent.copy(alpha = 0.66f)
    val ornamentColor = accent.copy(alpha = 0.5f)
    val innerBorderColor = accent.copy(alpha = 0.25f)

    Box(
        modifier = modifier
            .requiredSize(width = 140.dp, height = 220.dp)
            .clip(CARD_SHAPE)
            .background(
                brush = Brush.verticalGradient(listOf(Ink, Void)),
                shape = CARD_SHAPE,
            )
            .border(0.5.dp, borderColor, CARD_SHAPE),
    ) {
        // Inner frame
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
                .border(0.5.dp, innerBorderColor, INNER_SHAPE),
        )

        // Corner ornaments (offset 6dp from card edges)
        CornerOrnament(
            Corner.TopLeft, ornamentColor,
            modifier = Modifier.offset(x = 6.dp, y = 6.dp),
        )
        CornerOrnament(
            Corner.TopRight, ornamentColor,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-6).dp, y = 6.dp),
        )
        CornerOrnament(
            Corner.BottomLeft, ornamentColor,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = 6.dp, y = (-6).dp),
        )
        CornerOrnament(
            Corner.BottomRight, ornamentColor,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-6).dp, y = (-6).dp),
        )

        // Card content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, start = 12.dp, end = 12.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Roman numeral
            Text(
                text = ROMAN.getOrElse(index) { "${index + 1}" },
                style = CVTheme.typography.displayM.copy(
                    fontSize = 11.sp,
                    letterSpacing = 2.sp,
                ),
                color = accent.copy(alpha = 0.66f),
            )

            Spacer(Modifier.weight(1f))

            // Planet sphere
            PlanetSphere(
                accent = accent,
                sizeDp = 68.dp,
                glowing = isActive,
            )

            Spacer(Modifier.weight(1f))

            // World name
            Text(
                text = world.name,
                style = CVTheme.typography.displayM.copy(
                    fontSize = 15.sp,
                    letterSpacing = (-0.2).sp,
                    lineHeight = 15.sp,
                ),
                color = Parchment,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(4.dp))

            // Saga name
            Text(
                text = sagaName,
                style = CVTheme.typography.monoMeta.copy(
                    fontSize = 8.sp,
                    letterSpacing = 1.5.sp,
                    fontWeight = FontWeight.Normal,
                ),
                color = accent,
                textAlign = TextAlign.Center,
                maxLines = 2,
            )
        }
    }
}
