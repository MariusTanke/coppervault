package co.coppervault.app.ui.screens.worlds

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.coppervault.app.data.WorldDetailsCopy
import co.coppervault.app.data.WorldMeta
import co.coppervault.app.data.Worlds
import co.coppervault.app.ui.components.CVButton
import co.coppervault.app.ui.components.CVButtonSize
import co.coppervault.app.ui.components.CVButtonVariant
import co.coppervault.app.ui.components.CVDivider
import co.coppervault.app.ui.components.CVIcons
import co.coppervault.app.ui.components.CVKicker
import co.coppervault.app.ui.components.PlanetCoverflow
import co.coppervault.app.ui.components.PlanetSphere
import co.coppervault.app.ui.screens.detail.WorldDetailScreen
import co.coppervault.app.ui.strings.CVStrings
import co.coppervault.app.ui.strings.Strings
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog
import co.coppervault.app.ui.theme.Ink
import co.coppervault.app.ui.theme.Linen
import co.coppervault.app.ui.theme.Parchment
import co.coppervault.app.ui.theme.Stone

class WorldsScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val t = CVStrings.current
        val worlds = Worlds.all
        var focus by remember { mutableIntStateOf(0) }

        Column(Modifier.fillMaxSize()) {
            // ── 1. Header (fixed) ───────────────────────────
            WorldsHeader(t)

            // ── 2. Tarot coverflow rail (fixed, 360dp) ─────
            PlanetCoverflow(
                worlds = worlds,
                focusedIndex = focus,
                sagaNames = t.worldSagaName,
                onFocusChange = { focus = it },
                hint = t.tapWorldOrSwipe,
            )

            // ── 3. Panel + list (scrollable) ────────────────
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 100.dp),
            ) {
                item {
                    WorldDetailPanel(
                        world = worlds[focus],
                        saga = t.worldSagaName[worlds[focus].key] ?: "",
                        details = t.worldDetails[worlds[focus].key],
                        t = t,
                        onExplore = { navigator.push(WorldDetailScreen(worlds[focus].key)) },
                    )
                }

                item {
                    Box(Modifier.padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 8.dp)) {
                        CVKicker(t.allWorlds, color = Linen)
                    }
                }

                itemsIndexed(worlds) { i, world ->
                    WorldRow(
                        world = world,
                        saga = t.worldSagaName[world.key] ?: "",
                        isActive = i == focus,
                        onClick = { navigator.push(WorldDetailScreen(world.key)) },
                    )
                }
            }
        }
    }
}

// ── Header ──────────────────────────────────────────────

@Composable
private fun WorldsHeader(t: Strings) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 54.dp, start = 20.dp, end = 20.dp, bottom = 14.dp),
        ) {
            CVKicker(t.atlas, color = Ash, size = 11)
            Spacer(Modifier.height(4.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Parchment)) {
                        append("${t.theCosmere} ")
                    }
                    withStyle(SpanStyle(color = Aurum, fontStyle = FontStyle.Italic)) {
                        append(t.cosmereItalic)
                    }
                },
                style = CVTheme.typography.displayXL.copy(
                    fontSize = 28.sp,
                    letterSpacing = (-0.4).sp,
                    fontWeight = FontWeight.Normal,
                ),
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = t.atlasSub,
                style = CVTheme.typography.uiS.copy(fontSize = 14.sp),
                color = Fog,
            )
        }
        CVDivider()
    }
}

// ── World detail panel ──────────────────────────────────

@Composable
private fun WorldDetailPanel(world: WorldMeta, saga: String, details: WorldDetailsCopy?, t: Strings, onExplore: () -> Unit = {}) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawRect(
                        brush = Brush.verticalGradient(
                            listOf(world.accent.copy(alpha = 0.05f), Color.Transparent),
                        ),
                    )
                    drawRect(
                        color = world.accent,
                        topLeft = Offset.Zero,
                        size = Size(2.dp.toPx(), size.height),
                    )
                }
                .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 22.dp),
        ) {
            CVKicker(saga, color = world.accent, size = 11)
            Spacer(Modifier.height(6.dp))
            Text(
                text = world.name,
                style = CVTheme.typography.displayXL.copy(
                    fontSize = 26.sp,
                    letterSpacing = (-0.3).sp,
                    lineHeight = 26.sp,
                ),
                color = Parchment,
            )

            if (details != null) {
                Spacer(Modifier.height(14.dp))
                DetailRow(t.shardsLabel, details.shards)
                Spacer(Modifier.height(8.dp))
                DetailRow(t.magicLabel, details.magic)
                Spacer(Modifier.height(8.dp))
                DetailRow(t.booksLabel, if (details.books > 0) details.books.toString() else "\u2014")
                Spacer(Modifier.height(16.dp))
                CVButton(
                    text = "${world.name} \u2192",
                    variant = CVButtonVariant.Ghost,
                    size = CVButtonSize.S,
                    onClick = onExplore,
                )
            }
        }
        CVDivider()
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            text = label,
            style = CVTheme.typography.monoMeta.copy(fontSize = 11.sp, letterSpacing = 1.sp),
            color = Ash,
            modifier = Modifier.width(90.dp),
        )
        Text(
            text = value,
            style = CVTheme.typography.uiS.copy(fontSize = 14.sp),
            color = Linen,
        )
    }
}

// ── World list row ──────────────────────────────────────

@Composable
private fun WorldRow(world: WorldMeta, saga: String, isActive: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 8.dp)
            .background(if (isActive) Aurum.copy(alpha = 0.06f) else Ink)
            .border(0.5.dp, Stone)
            .drawBehind {
                drawRect(
                    color = world.accent,
                    topLeft = Offset.Zero,
                    size = Size(2.dp.toPx(), size.height),
                )
            }
            .clickable(onClick = onClick)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        PlanetSphere(accent = world.accent, sizeDp = 28.dp)

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = world.name,
                style = CVTheme.typography.displayM.copy(fontSize = 15.sp, lineHeight = 18.sp),
                color = Parchment,
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = saga,
                style = CVTheme.typography.monoMeta.copy(fontSize = 10.sp, letterSpacing = 1.sp),
                color = Ash,
            )
        }

        Icon(CVIcons.ChevronRight, contentDescription = null, tint = Ash, modifier = Modifier.size(14.dp))
    }
}
