package co.coppervault.app.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import co.coppervault.app.data.Forum
import co.coppervault.app.data.Library
import co.coppervault.app.data.Worlds
import co.coppervault.app.ui.components.CVDivider
import co.coppervault.app.ui.components.CVIcons
import co.coppervault.app.ui.components.CVKicker
import co.coppervault.app.ui.components.CVTextField
import co.coppervault.app.ui.components.PlanetSphere
import co.coppervault.app.ui.strings.CVStrings
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog
import co.coppervault.app.ui.theme.Linen
import co.coppervault.app.ui.theme.Mist
import co.coppervault.app.ui.theme.Parchment
import co.coppervault.app.ui.theme.Stone

private val recentSearches = listOf("Hoid", "Surgebinding", "Atium", "Nightblood")

class SearchScreen : Screen {

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    override fun Content() {
        val t = CVStrings.current
        var query by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CVTheme.colors.abyss),
        ) {
            // ── Top bar with search input ──────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 54.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                CVTextField(
                    value = query,
                    onValueChange = { query = it },
                    placeholder = t.searchP,
                    autoFocus = true,
                    modifier = Modifier.weight(1f),
                )
                if (query.isNotEmpty()) {
                    Text(
                        text = "\u2715",
                        style = CVTheme.typography.monoMeta.copy(fontSize = 14.sp),
                        color = Ash,
                        modifier = Modifier.clickable { query = "" },
                    )
                }
                Text(
                    text = t.cancel,
                    style = CVTheme.typography.uiS.copy(fontSize = 11.sp, letterSpacing = 0.5.sp),
                    color = Aurum,
                    modifier = Modifier.clickable { /* TODO(p2): navigate back */ },
                )
            }
            CVDivider()

            if (query.isEmpty()) {
                // ── Recents ────────────────────────────────
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 100.dp),
                ) {
                    item {
                        Box(Modifier.padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 6.dp)) {
                            CVKicker(t.recent, color = Ash)
                        }
                    }
                    item {
                        FlowRow(
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                        ) {
                            recentSearches.forEach { term ->
                                Box(
                                    modifier = Modifier
                                        .border(0.5.dp, Mist)
                                        .clickable { query = term }
                                        .padding(horizontal = 10.dp, vertical = 5.dp),
                                ) {
                                    Text(
                                        text = term,
                                        style = CVTheme.typography.uiS.copy(fontSize = 11.sp),
                                        color = Linen,
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                // ── Search results ─────────────────────────
                val q = query.lowercase()
                val worldMatches = Worlds.all.filter { it.name.lowercase().contains(q) }
                val bookMatches = Library.books.filter { it.title.lowercase().contains(q) }
                val threadMatches = Forum.threads.filter { it.title.lowercase().contains(q) }
                val totalCount = worldMatches.size + bookMatches.size + threadMatches.size

                if (totalCount == 0) {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = t.nothingFound.uppercase(),
                                style = CVTheme.typography.monoMeta.copy(fontSize = 10.sp, letterSpacing = 2.sp),
                                color = Ash,
                            )
                            Spacer(Modifier.height(6.dp))
                            Text(t.nothingFoundSub, style = CVTheme.typography.body.copy(fontSize = 13.sp), color = Fog)
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 100.dp),
                    ) {
                        item {
                            Box(Modifier.padding(start = 20.dp, end = 20.dp, top = 14.dp, bottom = 6.dp)) {
                                CVKicker("$totalCount ${t.results}", color = Linen)
                            }
                        }

                        if (worldMatches.isNotEmpty()) {
                            items(worldMatches) { world ->
                                SearchResultRow(
                                    icon = { PlanetSphere(accent = world.accent, sizeDp = 32.dp) },
                                    name = world.name,
                                    sub = world.railLabel,
                                    accent = world.accent,
                                )
                                CVDivider()
                            }
                        }

                        if (bookMatches.isNotEmpty()) {
                            items(bookMatches) { book ->
                                val accent = Worlds.byKey[book.worldKey]?.accent ?: Aurum
                                SearchResultRow(
                                    icon = {
                                        Box(
                                            modifier = Modifier
                                                .size(width = 24.dp, height = 34.dp)
                                                .background(
                                                    brush = Brush.verticalGradient(
                                                        listOf(accent.copy(alpha = 0.5f), accent.copy(alpha = 0.2f)),
                                                    ),
                                                )
                                                .border(0.5.dp, accent.copy(alpha = 0.4f)),
                                        )
                                    },
                                    name = book.title,
                                    sub = book.orderLabel,
                                    accent = accent,
                                )
                                CVDivider()
                            }
                        }

                        if (threadMatches.isNotEmpty()) {
                            items(threadMatches) { thread ->
                                val accent = Worlds.byKey[thread.worldKey]?.accent ?: Aurum
                                SearchResultRow(
                                    icon = {
                                        Box(
                                            modifier = Modifier.size(8.dp).background(accent, CircleShape),
                                        )
                                    },
                                    name = thread.title,
                                    sub = "${thread.worldKey.uppercase()} \u00B7 ${thread.author}",
                                    accent = accent,
                                )
                                CVDivider()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchResultRow(
    icon: @Composable () -> Unit,
    name: String,
    sub: String,
    accent: Color,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO(p2): navigate to detail */ }
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        icon()
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                style = CVTheme.typography.displayM.copy(fontSize = 14.sp, lineHeight = (14 * 1.25).sp),
                color = Parchment,
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = sub,
                style = CVTheme.typography.uiS.copy(fontSize = 11.sp),
                color = Fog,
            )
        }
    }
}
