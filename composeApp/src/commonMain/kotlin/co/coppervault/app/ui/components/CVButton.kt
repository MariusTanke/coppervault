package co.coppervault.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.coppervault.app.ui.theme.Abyss
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.AurumHi
import co.coppervault.app.ui.theme.AurumLo
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Mist
import co.coppervault.app.ui.theme.Parchment
import co.coppervault.app.ui.theme.Slate
import co.coppervault.app.ui.theme.Void

enum class CVButtonVariant { Primary, Ghost, Outline }

enum class CVButtonSize(val height: Dp, val hPad: Dp, val fontSize: Int) {
    S(32.dp, 12.dp, 13),
    M(44.dp, 18.dp, 14),
    L(52.dp, 22.dp, 15),
}

@Composable
fun CVButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: CVButtonVariant = CVButtonVariant.Primary,
    size: CVButtonSize = CVButtonSize.M,
    fullWidth: Boolean = false,
    loading: Boolean = false,
    enabled: Boolean = true,
) {
    val shape = RoundedCornerShape(2.dp)
    val interactionSource = remember { MutableInteractionSource() }

    val effectiveEnabled = enabled && !loading
    val alpha = if (effectiveEnabled) 1f else 0.45f

    val background: Brush
    val contentColor: Color
    val border: BorderStroke?

    when (variant) {
        CVButtonVariant.Primary -> {
            background = Brush.verticalGradient(listOf(AurumHi, Aurum, AurumLo))
            contentColor = Abyss
            border = null
        }
        CVButtonVariant.Ghost -> {
            background = Brush.linearGradient(listOf(Color.Transparent, Color.Transparent))
            contentColor = Parchment
            border = BorderStroke(0.5.dp, Mist)
        }
        CVButtonVariant.Outline -> {
            background = Brush.linearGradient(listOf(Color.Transparent, Color.Transparent))
            contentColor = Aurum
            border = BorderStroke(0.5.dp, Aurum.copy(alpha = 0.5f))
        }
    }

    Surface(
        modifier = modifier
            .then(if (fullWidth) Modifier.fillMaxWidth() else Modifier)
            .height(size.height)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = effectiveEnabled,
                onClick = onClick,
            ),
        shape = shape,
        border = border,
        color = Color.Transparent,
        shadowElevation = if (variant == CVButtonVariant.Primary) 4.dp else 0.dp,
    ) {
        Box(
            modifier = Modifier
                .background(background, shape)
                .fillMaxWidth()
                .height(size.height)
                .padding(horizontal = size.hPad),
            contentAlignment = Alignment.Center,
        ) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(14.dp),
                    color = contentColor.copy(alpha = alpha),
                    strokeWidth = 1.5.dp,
                )
            } else {
                Text(
                    text = text.uppercase(),
                    style = CVTheme.typography.uiS.copy(
                        color = contentColor.copy(alpha = alpha),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = size.fontSize.sp,
                        letterSpacing = (size.fontSize * 0.08).sp,
                        textAlign = TextAlign.Center,
                    ),
                )
            }
        }
    }
}

// ── Preview ─────��───────────────────────────────────────────────────

@Composable
fun CVButtonPreview() {
    CVTheme {
        Column(
            modifier = Modifier
                .background(CVTheme.colors.abyss)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            CVButton("Enter the Cosmere", onClick = {}, variant = CVButtonVariant.Primary, size = CVButtonSize.L, fullWidth = true)
            CVButton("Primary M", onClick = {})
            CVButton("Ghost", onClick = {}, variant = CVButtonVariant.Ghost)
            CVButton("Outline", onClick = {}, variant = CVButtonVariant.Outline)
            CVButton("Loading…", onClick = {}, loading = true)
            CVButton("Disabled", onClick = {}, enabled = false)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                CVButton("Small", onClick = {}, size = CVButtonSize.S)
                CVButton("Medium", onClick = {}, size = CVButtonSize.M, variant = CVButtonVariant.Ghost)
                CVButton("Large", onClick = {}, size = CVButtonSize.L, variant = CVButtonVariant.Outline)
            }
        }
    }
}
