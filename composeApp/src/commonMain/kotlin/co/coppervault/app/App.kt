package co.coppervault.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import co.coppervault.app.data.locale.LocaleController
import co.coppervault.app.ui.screens.splash.SplashScreen
import co.coppervault.app.ui.strings.LocalStrings
import co.coppervault.app.ui.theme.CVTheme

@Composable
fun App() {
    val strings by LocaleController.strings.collectAsState()

    CVTheme {
        CompositionLocalProvider(LocalStrings provides strings) {
            Navigator(SplashScreen()) { navigator ->
                FadeTransition(navigator)
            }
        }
    }
}
