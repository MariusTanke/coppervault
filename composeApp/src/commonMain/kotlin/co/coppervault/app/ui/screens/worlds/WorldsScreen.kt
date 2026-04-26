package co.coppervault.app.ui.screens.worlds

import androidx.compose.foundation.layout.Box
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
import co.coppervault.app.ui.components.CVKicker
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Parchment

class WorldsScreen : Screen {

    @Composable
    override fun Content() {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CVKicker("Atlas", color = Aurum, size = 9)
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Worlds tab content",
                    style = CVTheme.typography.displayM,
                    color = Parchment,
                )
            }
        }
    }
}
