package co.coppervault.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import co.coppervault.app.ui.theme.CVTheme

@Composable
fun App() {
    CVTheme {
        Box(
            modifier = Modifier
                .background(CVTheme.colors.abyss)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Coppervault",
                style = CVTheme.typography.displayXL,
                color = CVTheme.colors.parchment,
            )
        }
    }
}