package co.coppervault.app.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.coppervault.app.navigation.FakeAuth
import co.coppervault.app.navigation.MainScaffold
import co.coppervault.app.ui.components.CVButton
import co.coppervault.app.ui.components.CVButtonSize
import co.coppervault.app.ui.components.CVIcons
import co.coppervault.app.ui.components.CVInput
import co.coppervault.app.ui.components.CVKicker
import co.coppervault.app.ui.components.auth.AuthShell
import co.coppervault.app.ui.strings.CVStrings
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog
import co.coppervault.app.ui.theme.Linen
import co.coppervault.app.ui.theme.Parchment
import co.coppervault.app.ui.theme.Void
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val scope = rememberCoroutineScope()
        val t = CVStrings.current

        var displayName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirm by remember { mutableStateOf("") }
        var accepted by remember { mutableStateOf(false) }
        var loading by remember { mutableStateOf(false) }

        var nameErr by remember { mutableStateOf<String?>(null) }
        var emailErr by remember { mutableStateOf<String?>(null) }
        var pwErr by remember { mutableStateOf<String?>(null) }
        var confirmErr by remember { mutableStateOf<String?>(null) }

        val nameRegex = Regex("^[a-zA-Z0-9_]{3,20}$")
        val emailRegex = Regex("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")

        fun validateAll(): Boolean {
            nameErr = when {
                displayName.isBlank() -> t.required
                !nameRegex.matches(displayName) -> t.nameLenRule
                else -> null
            }
            emailErr = when {
                email.isBlank() -> t.required
                !emailRegex.matches(email) -> t.invalidEmail
                else -> null
            }
            pwErr = when {
                password.length < 8 -> t.pwMinLength
                !password.any { it.isDigit() } -> t.pwNeedsNumber
                else -> null
            }
            confirmErr = when {
                confirm != password -> t.pwNoMatch
                else -> null
            }
            return nameErr == null && emailErr == null && pwErr == null && confirmErr == null
        }

        AuthShell {
            // ── Back row ────────────────────────────────────
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { navigator.pop() },
            ) {
                Icon(CVIcons.Back, contentDescription = t.back, tint = Ash, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(8.dp))
                CVKicker(t.back, color = Fog, size = 11)
            }

            Spacer(Modifier.height(28.dp))

            // ── Header ──────────────────────────────────────
            Column {
                Text(
                    text = t.beginYour,
                    style = CVTheme.typography.displayXL.copy(
                        fontSize = 30.sp,
                        lineHeight = 33.sp,
                        letterSpacing = (-0.5).sp,
                    ),
                    color = Parchment,
                )
                Text(
                    text = t.journey,
                    style = CVTheme.typography.displayXL.copy(
                        fontSize = 30.sp,
                        lineHeight = 33.sp,
                        fontStyle = FontStyle.Italic,
                        letterSpacing = (-0.5).sp,
                    ),
                    color = Aurum,
                )
            }
            Spacer(Modifier.height(10.dp))
            Text(
                text = t.nameEtched,
                style = CVTheme.typography.uiS.copy(
                    fontSize = 13.sp,
                    lineHeight = 19.5.sp,
                ),
                color = Fog,
            )

            Spacer(Modifier.height(24.dp))

            // ── Form ────────────────────────────────────────
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                CVInput(
                    value = displayName,
                    onValueChange = { displayName = it; nameErr = null },
                    placeholder = t.travelerName,
                    icon = CVIcons.User,
                    isError = nameErr != null,
                    errorHint = nameErr,
                )
                CVInput(
                    value = email,
                    onValueChange = { email = it; emailErr = null },
                    placeholder = t.email,
                    icon = CVIcons.Mail,
                    isError = emailErr != null,
                    errorHint = emailErr,
                )
                CVInput(
                    value = password,
                    onValueChange = { password = it; pwErr = null },
                    placeholder = t.password,
                    icon = CVIcons.Lock,
                    isPassword = true,
                    isError = pwErr != null,
                    errorHint = pwErr,
                )
                CVInput(
                    value = confirm,
                    onValueChange = { confirm = it; confirmErr = null },
                    placeholder = t.confirmPw,
                    icon = CVIcons.Lock,
                    isPassword = true,
                    isError = confirmErr != null,
                    errorHint = confirmErr,
                )
            }

            Spacer(Modifier.height(18.dp))

            // ── Warning box ─────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Aurum.copy(alpha = 0.06f), RoundedCornerShape(2.dp))
                    .border(0.5.dp, Aurum.copy(alpha = 0.25f), RoundedCornerShape(2.dp))
                    .padding(12.dp),
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Icon(CVIcons.Warn, contentDescription = null, tint = Aurum, modifier = Modifier.size(14.dp))
                    Text(
                        text = t.spoilerWarn,
                        style = CVTheme.typography.uiS.copy(
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                        ),
                        color = Linen,
                    )
                }
            }

            Spacer(Modifier.height(14.dp))

            // ── Compact checkbox ────────────────────────────
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) { accepted = !accepted },
            ) {
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .then(
                            if (accepted) Modifier.background(Aurum, RoundedCornerShape(1.dp))
                            else Modifier.border(1.dp, Aurum, RoundedCornerShape(1.dp))
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    if (accepted) {
                        Text("\u2713", fontSize = 9.sp, color = Void)
                    }
                }
                Spacer(Modifier.width(10.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(color = Fog)) {
                            append(t.acceptCompact)
                            append(" ")
                        }
                        withStyle(SpanStyle(color = Aurum)) {
                            append(t.compact)
                        }
                    },
                    style = CVTheme.typography.uiS.copy(fontSize = 12.sp),
                )
            }

            Spacer(Modifier.height(20.dp))

            // ── CTA ─────────────────────────────────────────
            CVButton(
                text = t.openPortal,
                size = CVButtonSize.L,
                fullWidth = true,
                loading = loading,
                enabled = accepted,
                onClick = {
                    if (validateAll()) {
                        loading = true
                        scope.launch {
                            delay(800)
                            FakeAuth.isLoggedIn = true
                            FakeAuth.displayName = displayName
                            FakeAuth.email = email
                            navigator.replaceAll(MainScaffold())
                        }
                    }
                },
            )

            Spacer(Modifier.weight(1f))
        }
    }
}
