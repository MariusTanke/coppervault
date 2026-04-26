package co.coppervault.app.ui.screens.splash

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.coppervault.app.navigation.FakeAuth
import co.coppervault.app.navigation.MainScaffold
import co.coppervault.app.ui.components.CVCosmereMark
import co.coppervault.app.ui.components.CVKicker
import co.coppervault.app.ui.components.CVMistBg
import co.coppervault.app.ui.components.CVWordmark
import co.coppervault.app.ui.screens.auth.LoginScreen
import co.coppervault.app.ui.theme.Fog
import kotlinx.coroutines.delay

class SplashScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        // Auto-navigate after 2.5 s
        LaunchedEffect(Unit) {
            delay(2500)
            if (FakeAuth.isLoggedIn) {
                navigator.replaceAll(MainScaffold())
            } else {
                navigator.replaceAll(LoginScreen())
            }
        }

        // ── Animations ──────────────────────────────────────────────
        // CosmereMark: slow orbital rotation, 360° in 4 s, linear
        val infiniteTransition = rememberInfiniteTransition()
        val rotation by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 4000, easing = LinearEasing),
            ),
        )

        // Wordmark + kicker: fade-in from 800 ms to 1200 ms
        var showText by remember { mutableStateOf(false) }
        LaunchedEffect(Unit) {
            delay(800)
            showText = true
        }
        val textAlpha by animateFloatAsState(
            targetValue = if (showText) 1f else 0f,
            animationSpec = tween(durationMillis = 400),
        )

        // ── Layout ──────────────────────────────────────────────────
        CVMistBg(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                // Content at ~60 % vertical height → offset up from center
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.Center)
                        .alpha(1f), // container always visible
                ) {
                    Spacer(Modifier.weight(0.45f)) // push down to ~45% from top → visual 60% area

                    // Rotating CosmereMark
                    CVCosmereMark(
                        size = 72.dp,
                        modifier = Modifier.rotate(rotation),
                    )

                    Spacer(Modifier.height(20.dp))

                    // Wordmark (fade-in)
                    CVWordmark(
                        size = 32,
                        modifier = Modifier.alpha(textAlpha),
                    )

                    Spacer(Modifier.height(12.dp))

                    // Tagline kicker (fade-in with wordmark)
                    CVKicker(
                        text = "A Companion to the Cosmere",
                        color = Fog,
                        size = 9,
                        modifier = Modifier.alpha(textAlpha),
                    )

                    Spacer(Modifier.weight(0.55f))
                }
            }
        }
    }
}
