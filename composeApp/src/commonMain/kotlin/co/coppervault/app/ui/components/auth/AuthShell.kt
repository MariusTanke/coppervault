package co.coppervault.app.ui.components.auth

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.coppervault.app.ui.components.CVMistBg
import co.coppervault.app.ui.theme.Aurum

/**
 * Auth screen shell matching the JSX AuthShell:
 * - CVMistBg full bleed
 * - 4 concentric Aurum rings centered (200/320/460/620 dp), overall opacity 0.25
 * - Content column with padding top=80, horizontal=28, bottom=40
 */
@Composable
fun AuthShell(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    CVMistBg(modifier = modifier.fillMaxSize()) {
        // Faint concentric rings
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            val ringAlpha = 0.25f
            val borderAlpha = 0.3f
            val ringColor = Aurum.copy(alpha = borderAlpha * ringAlpha)
            listOf(200, 320, 460, 620).forEach { s ->
                Box(
                    modifier = Modifier
                        .size(s.dp)
                        .border(0.5.dp, ringColor, CircleShape),
                )
            }
        }

        // Content column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = 28.dp, end = 28.dp, top = 80.dp, bottom = 40.dp),
            content = content,
        )
    }
}
