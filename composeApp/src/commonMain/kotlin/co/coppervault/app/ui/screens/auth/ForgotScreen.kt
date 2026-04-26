package co.coppervault.app.ui.screens.auth

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.coppervault.app.ui.components.CVButton
import co.coppervault.app.ui.components.CVButtonSize
import co.coppervault.app.ui.components.CVButtonVariant
import co.coppervault.app.ui.components.CVEpigraph
import co.coppervault.app.ui.components.CVIcons
import co.coppervault.app.ui.components.CVInput
import co.coppervault.app.ui.components.CVKicker
import co.coppervault.app.ui.components.CVMistBg
import co.coppervault.app.ui.components.CVWordmark
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
        var sent by remember { mutableStateOf(false) }

        CVMistBg(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 28.dp, vertical = 54.dp),
            ) {
                // ── Back row + Wordmark ─────────────────────
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { navigator.pop() },
                ) {
                    Icon(CVIcons.Back, contentDescription = "Back", tint = Ash, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(8.dp))
                    CVKicker("Back", color = Fog, size = 10)
                }

                Spacer(Modifier.height(12.dp))
                CVWordmark(size = 20)

                Spacer(Modifier.height(28.dp))

                if (!sent) {
                    // ── Form state ──────────────────────────
                    // Header
                    Column {
                        Text(
                            text = "Lost the way?",
                            style = CVTheme.typography.displayXL,
                            color = Parchment,
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "Drop your email and we'll send you a thread back.",
                        style = CVTheme.typography.body,
                        color = Fog,
                    )

                    Spacer(Modifier.height(28.dp))

                    CVInput(
                        value = email,
                        onValueChange = { email = it; emailErr = null },
                        placeholder = "Email",
                        icon = CVIcons.Mail,
                        isError = emailErr != null,
                        errorHint = emailErr,
                    )

                    Spacer(Modifier.height(20.dp))

                    CVButton(
                        text = "Send Breadcrumb",
                        size = CVButtonSize.L,
                        fullWidth = true,
                        onClick = {
                            if (email.isBlank()) {
                                emailErr = "Required"
                            } else {
                                sent = true
                            }
                        },
                    )

                    Spacer(Modifier.weight(1f))

                    // Bottom epigraph
                    CVEpigraph(
                        quote = "The journey before the destination.",
                        size = 11,
                        modifier = Modifier.padding(top = 40.dp),
                    )
                } else {
                    // ── Sent confirmation ───────────────────
                    Spacer(Modifier.height(60.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        // Checkmark circle in Aurum
                        Canvas(modifier = Modifier.size(48.dp)) {
                            val cx = size.width / 2f
                            val cy = size.height / 2f
                            val r = size.minDimension / 2f - 2f
                            drawCircle(
                                color = Aurum,
                                radius = r,
                                center = Offset(cx, cy),
                                style = Stroke(width = 1.5f),
                            )
                            // Checkmark
                            drawLine(
                                color = Aurum,
                                start = Offset(cx - r * 0.3f, cy),
                                end = Offset(cx - r * 0.05f, cy + r * 0.3f),
                                strokeWidth = 2f,
                                cap = StrokeCap.Round,
                            )
                            drawLine(
                                color = Aurum,
                                start = Offset(cx - r * 0.05f, cy + r * 0.3f),
                                end = Offset(cx + r * 0.35f, cy - r * 0.25f),
                                strokeWidth = 2f,
                                cap = StrokeCap.Round,
                            )
                        }

                        Spacer(Modifier.height(20.dp))

                        Text(
                            text = "Sent",
                            style = CVTheme.typography.displayL,
                            color = Parchment,
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "If that email exists, you'll get a link in the next few minutes.",
                            style = CVTheme.typography.body,
                            color = Fog,
                            modifier = Modifier.padding(horizontal = 20.dp),
                        )

                        Spacer(Modifier.height(28.dp))

                        CVButton(
                            text = "Back to sign in",
                            variant = CVButtonVariant.Outline,
                            size = CVButtonSize.M,
                            onClick = { navigator.pop() },
                        )
                    }
                }
            }
        }
    }
}
