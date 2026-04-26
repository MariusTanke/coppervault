package co.coppervault.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Mist
import co.coppervault.app.ui.theme.Nalthis
import co.coppervault.app.ui.theme.Parchment

@Composable
fun CVInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    icon: ImageVector? = null,
    isPassword: Boolean = false,
    isError: Boolean = false,
    errorHint: String? = null,
    enabled: Boolean = true,
) {
    val shape = RoundedCornerShape(2.dp)
    var focused by remember { mutableStateOf(false) }

    val borderColor = when {
        isError  -> Nalthis
        focused  -> Aurum
        else     -> Mist
    }

    Column(modifier = modifier) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            singleLine = true,
            textStyle = TextStyle(
                color = Parchment,
                fontFamily = CVTheme.typography.kicker.fontFamily, // JetBrains Mono
                fontSize = 14.sp,
            ),
            cursorBrush = SolidColor(Aurum),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 44.dp)
                        .background(Mist.copy(alpha = 0.10f), shape)
                        .border(0.5.dp, borderColor, shape)
                        .padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (icon != null) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp).padding(end = 0.dp),
                            tint = Ash,
                        )
                        Box(Modifier.size(10.dp)) // gap
                    }
                    Box(Modifier.weight(1f)) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                style = CVTheme.typography.body,
                                color = Ash,
                            )
                        }
                        innerTextField()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focused = it.isFocused },
        )

        if (isError && errorHint != null) {
            Text(
                text = errorHint,
                style = CVTheme.typography.monoMeta,
                color = Nalthis,
                modifier = Modifier.padding(start = 14.dp, top = 4.dp),
            )
        }
    }
}

// ── Preview ──────���──────────────────────────────────────────────────

@Composable
fun CVInputPreview() {
    CVTheme {
        Column(
            modifier = Modifier
                .background(CVTheme.colors.abyss)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            CVInput(
                value = "",
                onValueChange = {},
                placeholder = "Email or username",
                icon = CVIcons.Mail,
            )
            CVInput(
                value = "kaladin@shattered.plain",
                onValueChange = {},
                icon = CVIcons.Mail,
            )
            CVInput(
                value = "",
                onValueChange = {},
                placeholder = "Password",
                icon = CVIcons.Lock,
                isPassword = true,
            )
            CVInput(
                value = "bad",
                onValueChange = {},
                icon = CVIcons.Lock,
                isError = true,
                errorHint = "Password must be at least 8 characters",
            )
        }
    }
}
