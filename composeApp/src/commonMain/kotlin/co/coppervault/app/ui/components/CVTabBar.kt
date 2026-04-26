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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import co.coppervault.app.ui.strings.CVStrings
import co.coppervault.app.ui.strings.Strings
import co.coppervault.app.ui.theme.Abyss
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Stone

enum class CVTab(
    val icon: ImageVector,
    val labelKey: (Strings) -> String,
) {
    Home(CVIcons.Home, { it.tHome }),
    Worlds(CVIcons.Globe, { it.tWorlds }),
    Library(CVIcons.Book, { it.tLibrary }),
    Forum(CVIcons.Forum, { it.tForum }),
    Me(CVIcons.User, { it.tMe }),
}

/**
 * Bottom tab bar — 5 items, gradient scrim, Aurum active indicator.
 *
 * No Material Surface or Scaffold wrapper. Background is a pure gradient
 * scrim from Transparent → Abyss so it blends over CVMistBg.
 */
@Composable
fun CVTabBar(
    activeTab: CVTab,
    onTabSelected: (CVTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    0f to Color.Transparent,
                    0.35f to Abyss.copy(alpha = 0.75f),
                    1f to Abyss.copy(alpha = 0.92f),
                )
            ),
    ) {
        // Top hairline — plain Box, no Material Divider
        Box(
            Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(Stone)
        )

        val t = CVStrings.current

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top,
        ) {
            CVTab.entries.forEach { tab ->
                val isActive = tab == activeTab
                val tint = if (isActive) Aurum else Ash
                val label = tab.labelKey(t)

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
                        contentDescription = label,
                        modifier = Modifier.size(20.dp),
                        tint = tint,
                    )

                    Text(
                        text = label.uppercase(),
                        style = CVTheme.typography.monoMeta.copy(
                            fontSize = 10.sp,
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

// ── Preview ─────────────────────────────────────────────────────────

@Composable
fun CVTabBarPreview() {
    CVTheme {
        var active by remember { mutableStateOf(CVTab.Home) }
        CVMistBg(
            modifier = Modifier.fillMaxWidth().height(200.dp),
        ) {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {
                CVTabBar(activeTab = active, onTabSelected = { active = it })
            }
        }
    }
}
