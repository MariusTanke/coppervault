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
import co.coppervault.app.ui.components.CVButtonVariant
import co.coppervault.app.ui.components.CVIcons
import co.coppervault.app.ui.components.CVInput
import co.coppervault.app.ui.components.CVKicker
import co.coppervault.app.ui.components.CVMistBg
import co.coppervault.app.ui.components.CVWordmark
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog
import co.coppervault.app.ui.theme.Linen
import co.coppervault.app.ui.theme.Mist
import co.coppervault.app.ui.theme.Parchment
import co.coppervault.app.ui.theme.Stone
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val scope = rememberCoroutineScope()

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var remember by remember { mutableStateOf(false) }
        var loading by remember { mutableStateOf(false) }
        var emailError by remember { mutableStateOf<String?>(null) }
        var passwordError by remember { mutableStateOf<String?>(null) }
        var submitted by remember { mutableStateOf(false) }

        fun validate(): Boolean {
            submitted = true
            emailError = if (email.isBlank()) "Required" else null
            passwordError = if (password.isBlank()) "Required" else null
            return emailError == null && passwordError == null
        }

        CVMistBg(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 28.dp, vertical = 54.dp),
            ) {
                // ── Top bar: wordmark + version ─────────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CVWordmark(size = 20)
                    CVKicker("v0.1 · Alpha", color = Ash, size = 9)
                }

                Spacer(Modifier.height(40.dp))

                // ── Header ──────────────────────────────────
                Text(
                    text = "Welcome back",
                    style = CVTheme.typography.displayXL,
                    color = Parchment,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Sign in to continue your reading journey",
                    style = CVTheme.typography.body,
                    color = Fog,
                )

                Spacer(Modifier.height(28.dp))

                // ── Form ────────────────────────────────────
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    CVInput(
                        value = email,
                        onValueChange = {
                            email = it
                            if (submitted) emailError = if (it.isBlank()) "Required" else null
                        },
                        placeholder = "Email or username",
                        icon = CVIcons.Mail,
                        isError = emailError != null,
                        errorHint = emailError,
                    )
                    CVInput(
                        value = password,
                        onValueChange = {
                            password = it
                            if (submitted) passwordError = if (it.isBlank()) "Required" else null
                        },
                        placeholder = "Password",
                        icon = CVIcons.Lock,
                        isPassword = true,
                        isError = passwordError != null,
                        errorHint = passwordError,
                    )
                }

                Spacer(Modifier.height(8.dp))

                // ── Remember + Forgot ───────────────────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    // Checkbox
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) { remember = !remember },
                    ) {
                        Box(
                            modifier = Modifier
                                .size(14.dp)
                                .then(
                                    if (remember) Modifier.background(Aurum, RoundedCornerShape(1.dp))
                                    else Modifier.border(1.dp, Mist, RoundedCornerShape(1.dp))
                                ),
                            contentAlignment = Alignment.Center,
                        ) {
                            if (remember) {
                                Text("✓", fontSize = 9.sp, color = CVTheme.colors.abyss)
                            }
                        }
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "Remember me",
                            style = CVTheme.typography.uiS,
                            color = Fog,
                        )
                    }

                    // Forgot link
                    Text(
                        text = "Forgot?",
                        style = CVTheme.typography.uiS.copy(fontWeight = FontWeight.Medium),
                        color = Aurum,
                        modifier = Modifier.clickable { navigator.push(ForgotScreen()) },
                    )
                }

                Spacer(Modifier.height(24.dp))

                // ── CTA ─────────────────────────────────────
                CVButton(
                    text = "Enter the Cosmere",
                    size = CVButtonSize.L,
                    fullWidth = true,
                    loading = loading,
                    onClick = {
                        if (validate()) {
                            loading = true
                            scope.launch {
                                delay(800)
                                FakeAuth.isLoggedIn = true
                                FakeAuth.email = email
                                navigator.replaceAll(MainScaffold())
                            }
                        }
                    },
                )

                Spacer(Modifier.height(22.dp))

                // ── Divider "OR" ────────────────────────────
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Box(Modifier.weight(1f).height(0.5.dp).background(Stone))
                    Text(
                        text = "  OR HOP VIA  ",
                        style = CVTheme.typography.monoMeta.copy(letterSpacing = 2.sp),
                        color = Ash,
                    )
                    Box(Modifier.weight(1f).height(0.5.dp).background(Stone))
                }

                Spacer(Modifier.height(22.dp))

                // ── Social buttons ──────────────────────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    CVButton(
                        text = "Google",
                        variant = CVButtonVariant.Ghost,
                        size = CVButtonSize.M,
                        onClick = {},
                        modifier = Modifier.weight(1f),
                    )
                    CVButton(
                        text = "Apple",
                        variant = CVButtonVariant.Ghost,
                        size = CVButtonSize.M,
                        onClick = {},
                        modifier = Modifier.weight(1f),
                    )
                }

                Spacer(Modifier.weight(1f))

                // ── Bottom link ─────────────────────────────
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "New to the Cosmere? ",
                        style = CVTheme.typography.uiL,
                        color = Fog,
                    )
                    Text(
                        text = "Create vault",
                        style = CVTheme.typography.uiL.copy(fontWeight = FontWeight.SemiBold),
                        color = Aurum,
                        modifier = Modifier.clickable { navigator.push(RegisterScreen()) },
                    )
                }
            }
        }
    }
}
