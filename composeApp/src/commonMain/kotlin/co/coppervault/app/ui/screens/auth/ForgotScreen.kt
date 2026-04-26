package co.coppervault.app.ui.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.coppervault.app.ui.components.CVButton
import co.coppervault.app.ui.components.CVButtonSize
import co.coppervault.app.ui.components.CVEpigraph
import co.coppervault.app.ui.components.CVIcons
import co.coppervault.app.ui.components.CVInput
import co.coppervault.app.ui.components.CVKicker
import co.coppervault.app.ui.components.auth.AuthShell
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog
import co.coppervault.app.ui.theme.Parchment

class ForgotScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var email by remember { mutableStateOf("") }
        var emailErr by remember { mutableStateOf<String?>(null) }

        AuthShell {
            // ── Back row ────────────────────────────────────
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { navigator.pop() },
            ) {
                Icon(CVIcons.Back, contentDescription = "Back", tint = Ash, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(8.dp))
                CVKicker("Back", color = Fog, size = 10)
            }

            Spacer(Modifier.height(28.dp))

            // ── Header ──────────────────────────────────────
            Column {
                Text(
                    text = "Lost",
                    style = CVTheme.typography.displayXL.copy(
                        fontSize = 30.sp,
                        lineHeight = 33.sp,
                        letterSpacing = (-0.5).sp,
                    ),
                    color = Parchment,
                )
                Text(
                    text = "between worlds",
                    style = CVTheme.typography.displayXL.copy(
                        fontSize = 30.sp,
                        lineHeight = 33.sp,
                        fontStyle = FontStyle.Italic,
                        letterSpacing = (-0.5).sp,
                    ),
                    color = Aurum,
                )
            }

            Spacer(Modifier.height(12.dp))
            Text(
                text = "Drop your email and we\u2019ll send you a thread back.",
                style = CVTheme.typography.uiL.copy(
                    fontSize = 13.sp,
                    lineHeight = 20.8.sp,
                ),
                color = Fog,
            )

            Spacer(Modifier.height(28.dp))

            // ── Email input ─────────────────────────────────
            CVInput(
                value = email,
                onValueChange = { email = it; emailErr = null },
                placeholder = "Email",
                icon = CVIcons.Mail,
                isError = emailErr != null,
                errorHint = emailErr,
            )

            Spacer(Modifier.height(20.dp))

            // ── CTA ─────────────────────────────────────────
            CVButton(
                text = "Send Missive",
                size = CVButtonSize.L,
                fullWidth = true,
                onClick = {
                    if (email.isBlank()) {
                        emailErr = "Required"
                    } else {
                        navigator.pop()
                    }
                },
            )

            Spacer(Modifier.weight(1f))

            // ── Bottom epigraph ─────────────────────────────
            CVEpigraph(
                quote = "The journey before the destination.",
                size = 11,
                modifier = Modifier.padding(top = 40.dp).align(Alignment.CenterHorizontally),
            )
        }
    }
}
