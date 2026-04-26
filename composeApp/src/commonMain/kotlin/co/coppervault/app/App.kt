package co.coppervault.app

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import co.coppervault.app.ui.screens.splash.SplashScreen
import co.coppervault.app.ui.theme.CVTheme

@Composable
fun App() {
    CVTheme {
        Navigator(SplashScreen()) { navigator ->
            FadeTransition(navigator)
        }
    }
}
