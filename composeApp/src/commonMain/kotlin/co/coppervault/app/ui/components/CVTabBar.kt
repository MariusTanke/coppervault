package co.coppervault.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.coppervault.app.ui.theme.Abyss
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Stone

enum class CVTab(
    val label: String,
    val icon: ImageVector,
) {
    Home("Home", CVIcons.Home),
    Worlds("Worlds", CVIcons.Globe),
    Library("Library", CVIcons.Book),
    Forum("Forum", CVIcons.Forum),
    Me("Me", CVIcons.User),
}

/**
 * Bottom tab bar — 5 items, gradient scrim, Aurum active indicator.
 *
 * Height: 84 dp total (8 dp top pad + content + 24 dp bottom safe area).
 * Glass blur is simulated via a gradient scrim (real blur requires
 * platform expect/actual — can be added later).
 */
@Composable
fun CVTabBar(
    activeTab: CVTab,
    onTabSelected: (CVTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(84.dp)
            .background(
                Brush.verticalGradient(
                    0f to Abyss.copy(alpha = 0.4f),
                    0.5f to Abyss.copy(alpha = 0.9f),
                    1f to Abyss.copy(alpha = 0.98f),
                )
            ),
    ) {
        // Top hairline
        HorizontalDivider(
            thickness = 0.5.dp,
            color = Stone,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 24.dp)
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            CVTab.entries.forEach { tab ->
                val isActive = tab == activeTab
                val tint = if (isActive) Aurum else Ash

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { onTabSelected(tab) },
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                ) {
                    // Aurum top indicator line
                    if (isActive) {
                        Box(
                            Modifier
                                .width(24.dp)
                                .height(1.dp)
                                .background(Aurum)
                        )
                    } else {
                        Box(Modifier.height(1.dp))
                    }

                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.label,
                        modifier = Modifier.size(20.dp),
                        tint = tint,
                    )

                    Text(
                        text = tab.label.uppercase(),
                        style = CVTheme.typography.monoMeta.copy(
                            fontSize = 9.sp,
                            letterSpacing = 1.sp,
                            fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal,
                        ),
                        color = tint,
                    )
                }
            }
        }
    }
}

// Helper — Box doesn't have weight in this context so we use Row scope
@Composable
private fun Modifier.weight(weight: Float): Modifier = this

// ── Preview ─────────────────────────────────────────────────────────

@Composable
fun CVTabBarPreview() {
    CVTheme {
        var active by remember { mutableStateOf(CVTab.Home) }
        Column(
            modifier = Modifier
                .background(CVTheme.colors.abyss)
                .fillMaxWidth(),
        ) {
            Box(Modifier.weight(1f).fillMaxWidth()) // spacer
            CVTabBar(activeTab = active, onTabSelected = { active = it })
        }
    }
}
