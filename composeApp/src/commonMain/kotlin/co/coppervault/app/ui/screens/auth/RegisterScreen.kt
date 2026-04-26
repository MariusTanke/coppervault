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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.coppervault.app.navigation.FakeAuth
import co.coppervault.app.navigation.MainScaffold
import co.coppervault.app.ui.components.CVButton
import co.coppervault.app.ui.components.CVButtonSize
import co.coppervault.app.ui.components.CVChip
import co.coppervault.app.ui.components.CVIcons
import co.coppervault.app.ui.components.CVInput
import co.coppervault.app.ui.components.CVKicker
import co.coppervault.app.ui.components.CVMistBg
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog
import co.coppervault.app.ui.theme.Linen
import co.coppervault.app.ui.theme.Mist
import co.coppervault.app.ui.theme.Parchment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val scope = rememberCoroutineScope()

        var displayName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirm by remember { mutableStateOf("") }
        var spoiler by remember { mutableStateOf("EVERYTHING") }
        var accepted by remember { mutableStateOf(false) }
        var loading by remember { mutableStateOf(false) }

        // Errors
        var nameErr by remember { mutableStateOf<String?>(null) }
        var emailErr by remember { mutableStateOf<String?>(null) }
        var pwErr by remember { mutableStateOf<String?>(null) }
        var confirmErr by remember { mutableStateOf<String?>(null) }

        val nameRegex = Regex("^[a-zA-Z0-9_]{3,20}$")
        val emailRegex = Regex("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")

        fun validateAll(): Boolean {
            nameErr = when {
                displayName.isBlank() -> "Required"
                !nameRegex.matches(displayName) -> "3-20 chars, letters/numbers/_"
                else -> null
            }
            emailErr = when {
                email.isBlank() -> "Required"
                !emailRegex.matches(email) -> "Invalid email"
                else -> null
            }
            pwErr = when {
                password.length < 8 -> "At least 8 characters"
                !password.any { it.isDigit() } -> "Must contain a number"
                else -> null
            }
            confirmErr = when {
                confirm != password -> "Passwords don't match"
                else -> null
            }
            return nameErr == null && emailErr == null && pwErr == null && confirmErr == null
        }

        val formValid = accepted

        CVMistBg(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 28.dp, vertical = 54.dp),
            ) {
                // ── Back row ────────────────────────────────
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { navigator.pop() },
                ) {
                    Icon(CVIcons.Back, contentDescription = "Back", tint = Ash, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(8.dp))
                    CVKicker("Back", color = Fog, size = 10)
                }

                Spacer(Modifier.height(28.dp))

                // ── Header ──────────────────────────────────
                Column {
                    Text(
                        text = "Found your",
                        style = CVTheme.typography.displayXL,
                        color = Parchment,
                    )
                    Text(
                        text = "vault",
                        style = CVTheme.typography.displayXL.copy(fontStyle = FontStyle.Italic),
                        color = Aurum,
                    )
                }
                Spacer(Modifier.height(10.dp))
                Text(
                    text = "Track your readings, theories and notes.",
                    style = CVTheme.typography.uiL,
                    color = Fog,
                )

                Spacer(Modifier.height(24.dp))

                // ── Form ────────────────────────────────────
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    CVInput(
                        value = displayName,
                        onValueChange = { displayName = it; nameErr = null },
                        placeholder = "Display name",
                        icon = CVIcons.User,
                        isError = nameErr != null,
                        errorHint = nameErr,
                    )
                    CVInput(
                        value = email,
                        onValueChange = { email = it; emailErr = null },
                        placeholder = "Email",
                        icon = CVIcons.Mail,
                        isError = emailErr != null,
                        errorHint = emailErr,
                    )
                    CVInput(
                        value = password,
                        onValueChange = { password = it; pwErr = null },
                        placeholder = "Password",
                        icon = CVIcons.Lock,
                        isPassword = true,
                        isError = pwErr != null,
                        errorHint = pwErr,
                    )
                    CVInput(
                        value = confirm,
                        onValueChange = { confirm = it; confirmErr = null },
                        placeholder = "Confirm password",
                        icon = CVIcons.Lock,
                        isPassword = true,
                        isError = confirmErr != null,
                        errorHint = confirmErr,
                    )
                }

                Spacer(Modifier.height(18.dp))

                // ── Spoiler ceiling ─────────────────────────
                CVKicker("Spoiler ceiling", color = Aurum, size = 9)
                Spacer(Modifier.height(8.dp))

                val spoilerOptions = listOf("ALL", "ERA 1", "SA1-3", "EVERYTHING")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    spoilerOptions.forEach { opt ->
                        CVChip(
                            label = opt,
                            accent = Aurum,
                            filled = spoiler == opt,
                            modifier = Modifier.clickable { spoiler = opt },
                        )
                    }
                }

                Spacer(Modifier.height(18.dp))

                // ── Warning box ─────────────────────────────
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
                            text = "Coppervault contains community discussion that may include spoilers. You can adjust your ceiling at any time in Settings.",
                            style = CVTheme.typography.uiS,
                            color = Linen,
                        )
                    }
                }

                Spacer(Modifier.height(14.dp))

                // ── Checkbox ────────────────────────────────
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
                                else Modifier.border(1.dp, Mist, RoundedCornerShape(1.dp))
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        if (accepted) {
                            Text("✓", fontSize = 9.sp, color = CVTheme.colors.abyss)
                        }
                    }
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "I understand this is a fan-made app, not affiliated with Brandon Sanderson or Dragonsteel",
                        style = CVTheme.typography.uiS,
                        color = Fog,
                    )
                }

                Spacer(Modifier.height(20.dp))

                // ── CTA ─────────────────────────────────────
                CVButton(
                    text = "Forge the Vault",
                    size = CVButtonSize.L,
                    fullWidth = true,
                    loading = loading,
                    enabled = formValid,
                    onClick = {
                        if (validateAll()) {
                            loading = true
                            scope.launch {
                                delay(800)
                                FakeAuth.isLoggedIn = true
                                FakeAuth.displayName = displayName
                                FakeAuth.email = email
                                FakeAuth.spoilerCeiling = spoiler
                                navigator.replaceAll(MainScaffold())
                            }
                        }
                    },
                )

                Spacer(Modifier.weight(1f))

                // ── Bottom link ─────────────────────────────
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "Already part of the Cosmere? ",
                        style = CVTheme.typography.uiL,
                        color = Fog,
                    )
                    Text(
                        text = "Sign in",
                        style = CVTheme.typography.uiL.copy(fontWeight = FontWeight.SemiBold),
                        color = Aurum,
                        modifier = Modifier.clickable { navigator.pop() },
                    )
                }
            }
        }
    }
}
