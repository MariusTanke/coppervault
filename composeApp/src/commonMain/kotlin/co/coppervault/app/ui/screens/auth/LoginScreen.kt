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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import co.coppervault.app.ui.components.CVCosmereMark
import co.coppervault.app.ui.components.CVIcons
import co.coppervault.app.ui.components.CVInput
import co.coppervault.app.ui.components.CVWordmark
import co.coppervault.app.ui.components.auth.AuthShell
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog
import co.coppervault.app.ui.theme.Linen
import co.coppervault.app.ui.theme.Mist
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
        var rememberMe by remember { mutableStateOf(false) }
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

        AuthShell {
            // ── Header: CosmereMark + Wordmark + Kicker ────
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CVCosmereMark(size = 44.dp)
                Spacer(Modifier.height(16.dp))
                CVWordmark(size = 26)
                Spacer(Modifier.height(10.dp))
                Text(
                    text = "OPEN THE PORTAL",
                    style = CVTheme.typography.monoMeta.copy(
                        fontSize = 9.sp,
                        letterSpacing = 3.sp,
                        fontWeight = FontWeight.Medium,
                    ),
                    color = Fog,
                )
            }

            Spacer(Modifier.height(36.dp))

            // ── Form ────────────────────────────────────────
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

            // ── Remember + Forgot row ───────────────────────
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) { rememberMe = !rememberMe },
                ) {
                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .then(
                                if (rememberMe) Modifier.background(Aurum, RoundedCornerShape(1.dp))
                                else Modifier.border(1.dp, Mist, RoundedCornerShape(1.dp))
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        if (rememberMe) {
                            Text("✓", fontSize = 9.sp, color = CVTheme.colors.abyss)
                        }
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Remember me",
                        style = CVTheme.typography.uiS.copy(fontSize = 11.sp),
                        color = Fog,
                    )
                }

                Text(
                    text = "Forgot?",
                    style = CVTheme.typography.uiS.copy(fontSize = 11.sp, letterSpacing = 0.3.sp),
                    color = Aurum,
                    modifier = Modifier.clickable { navigator.push(ForgotScreen()) },
                )
            }

            Spacer(Modifier.height(24.dp))

            // ── CTA ─────────────────────────────────────────
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

            // ── Divider "OR HOP VIA" ────────────────────────
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(vertical = 22.dp),
            ) {
                Box(Modifier.weight(1f).height(0.5.dp).background(Stone))
                Text(
                    text = "  OR HOP VIA  ",
                    style = CVTheme.typography.monoMeta.copy(
                        fontSize = 9.sp,
                        letterSpacing = 2.sp,
                    ),
                    color = Ash,
                )
                Box(Modifier.weight(1f).height(0.5.dp).background(Stone))
            }

            // ── Social row ──────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                // Google button
                SocialButton(label = "Google", glyph = "G", modifier = Modifier.weight(1f))
                // Apple button — U+F8FF Apple logo, fallback "A"
                SocialButton(label = "Apple", glyph = "\uF8FF", modifier = Modifier.weight(1f))
            }

            Spacer(Modifier.weight(1f))

            // ── Bottom link ─────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "New traveler? ",
                    style = CVTheme.typography.uiS.copy(fontSize = 12.sp),
                    color = Fog,
                )
                Text(
                    text = "Begin journey",
                    style = CVTheme.typography.uiS.copy(fontSize = 12.sp, fontWeight = FontWeight.Bold),
                    color = Aurum,
                    modifier = Modifier.clickable { navigator.push(RegisterScreen()) },
                )
            }
        }
    }
}

@Composable
private fun SocialButton(
    label: String,
    glyph: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .height(44.dp)
            .border(0.5.dp, Mist, RoundedCornerShape(2.dp))
            .background(Linen.copy(alpha = 0.02f), RoundedCornerShape(2.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = glyph,
                style = CVTheme.typography.monoMeta.copy(fontSize = 14.sp),
                color = Aurum,
            )
            Text(
                text = label,
                style = CVTheme.typography.uiS.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                ),
                color = Linen,
            )
        }
    }
}
