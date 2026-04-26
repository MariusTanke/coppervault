package co.coppervault.app.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.coppervault.app.navigation.FakeAuth
import co.coppervault.app.navigation.MainScaffold
import co.coppervault.app.ui.components.CVButton
import co.coppervault.app.ui.components.CVButtonSize
import co.coppervault.app.ui.components.CVMistBg
import co.coppervault.app.ui.components.CVWordmark
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog

class RegisterScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        CVMistBg(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                CVWordmark(size = 26)

                Spacer(Modifier.height(32.dp))

                Text(
                    text = "Register (stub)",
                    style = CVTheme.typography.body,
                    color = Fog,
                )

                Spacer(Modifier.height(24.dp))

                CVButton(
                    text = "Begin Journey",
                    size = CVButtonSize.L,
                    onClick = {
                        FakeAuth.isLoggedIn = true
                        navigator.replaceAll(MainScaffold())
                    },
                )
            }
        }
    }
}
