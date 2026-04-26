package co.coppervault.app.ui.screens.forum

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import co.coppervault.app.data.Forum
import co.coppervault.app.data.ForumThread
import co.coppervault.app.data.Worlds
import co.coppervault.app.data.worldAccentByName
import co.coppervault.app.ui.components.CVChip
import co.coppervault.app.ui.components.CVDivider
import co.coppervault.app.ui.components.CVKicker
import co.coppervault.app.ui.components.CVSpoilerStrip
import co.coppervault.app.ui.components.ForumThreadRow
import co.coppervault.app.ui.strings.CVStrings
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog
import co.coppervault.app.ui.theme.Ink
import co.coppervault.app.ui.theme.Mist
import co.coppervault.app.ui.theme.Parchment
import co.coppervault.app.ui.theme.Void

class ForumScreen : Screen {

    @Composable
    override fun Content() {
        val t = CVStrings.current
        var selectedFilter by remember { mutableStateOf(0) }

        val filtered = if (selectedFilter == 0) {
            Forum.threads
        } else {
            val filterLabel = t.filters[selectedFilter]
            // Match against English category names since data uses English
            val enFilters = listOf("All", "Theories", "Discussion", "Art", "Lore", "Re-read")
            val enLabel = enFilters.getOrElse(selectedFilter) { "" }
            Forum.threads.filter { it.category.equals(enLabel, ignoreCase = true) }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CVTheme.colors.abyss),
        ) {
            // ── Header ─────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 54.dp, start = 20.dp, end = 20.dp, bottom = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
            ) {
                Column {
                    CVKicker(t.gathering, color = Ash, size = 11)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = t.forum,
                        style = CVTheme.typography.displayM.copy(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Normal,
                            letterSpacing = (-0.4).sp,
                        ),
                        color = Parchment,
                    )
                }
                // FAB + button
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .background(Aurum),
                    contentAlignment = Alignment.Center,
                ) {
                    Text("+", style = CVTheme.typography.displayM.copy(fontSize = 20.sp), color = Void)
                }
            }
            CVDivider()

            // ── Filter chips (scrollable) ──────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                t.filters.forEachIndexed { index, label ->
                    val isSelected = index == selectedFilter
                    CVChip(
                        label = label,
                        accent = if (isSelected) Aurum else Mist,
                        filled = isSelected,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) { selectedFilter = index },
                    )
                }
            }
            CVDivider()

            // ── Thread list ────────────────────────────────
            if (filtered.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = t.notifEmpty.uppercase(),
                            style = CVTheme.typography.monoMeta.copy(fontSize = 11.sp, letterSpacing = 2.sp),
                            color = Ash,
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = t.notifEmptySub,
                            style = CVTheme.typography.body.copy(fontSize = 14.sp),
                            color = Fog,
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(bottom = 100.dp),
                ) {
                    items(filtered, key = { it.id }) { thread ->
                        ForumThreadRow(thread = thread, t = t, onClick = {
                            // TODO(p2): navigate to ThreadDetailScreen
                        })
                        CVDivider()
                    }
                }
            }
        }
    }
}

