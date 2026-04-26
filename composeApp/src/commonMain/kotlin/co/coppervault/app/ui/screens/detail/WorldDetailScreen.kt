package co.coppervault.app.ui.screens.detail

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.coppervault.app.data.Forum
import co.coppervault.app.data.Library
import co.coppervault.app.data.Worlds
import co.coppervault.app.ui.components.BookCoverPlaceholder
import co.coppervault.app.ui.components.CVDivider
import co.coppervault.app.ui.components.CVIcons
import co.coppervault.app.ui.components.CVKicker
import co.coppervault.app.ui.components.ForumThreadRow
import co.coppervault.app.ui.components.PlanetSphere
import co.coppervault.app.ui.components.SeededStarfield
import co.coppervault.app.ui.strings.CVStrings
import co.coppervault.app.ui.strings.Strings
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog
import co.coppervault.app.ui.theme.Linen
import co.coppervault.app.ui.theme.Parchment
import co.coppervault.app.ui.theme.Abyss
import co.coppervault.app.ui.theme.Void

class WorldDetailScreen(private val worldKey: String) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val t = CVStrings.current
        val world = Worlds.byKey[worldKey] ?: return
        val details = t.worldDetails[worldKey]
        val accent = world.accent
        val saga = t.worldSagaName[worldKey] ?: ""
        val books = Library.books.filter { it.worldKey == worldKey }
        val threads = Forum.threads.filter { it.worldKey == worldKey }.take(3)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(CVTheme.colors.abyss),
            contentPadding = PaddingValues(bottom = 100.dp),
        ) {
            // ── Top app bar ──────────────────────────────────
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 54.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Icon(
                        CVIcons.Back,
                        contentDescription = null,
                        tint = Ash,
                        modifier = Modifier
                            .size(18.dp)
                            .clickable { navigator.pop() },
                    )
                    Icon(
                        CVIcons.More,
                        contentDescription = null,
                        tint = Ash,
                        modifier = Modifier.size(18.dp),
                    )
                }
                CVDivider()
            }

            // ── Hero ─────────────────────────────────────────
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(360.dp)
                        .drawBehind {
                            // Base gradient Abyss → Void
                            drawRect(
                                brush = Brush.verticalGradient(
                                    listOf(Abyss, Void),
                                ),
                            )
                            // World accent radial glow
                            drawRect(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        accent.copy(alpha = 0.18f),
                                        Color.Transparent,
                                    ),
                                    center = Offset(size.width * 0.5f, size.height * 0.5f),
                                    radius = size.width * 0.65f,
                                ),
                            )
                            // Halo rings
                            val cx = size.width / 2f
                            val cy = size.height / 2f
                            val dash = PathEffect.dashPathEffect(floatArrayOf(6f, 6f), 0f)
                            drawCircle(
                                color = accent.copy(alpha = 0.4f),
                                radius = 100.dp.toPx(),
                                center = Offset(cx, cy),
                                style = Stroke(width = 0.5f, pathEffect = dash),
                            )
                            drawCircle(
                                color = accent.copy(alpha = 0.15f),
                                radius = 140.dp.toPx(),
                                center = Offset(cx, cy),
                                style = Stroke(width = 0.5f, pathEffect = dash),
                            )
                        }
                        .padding(24.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    SeededStarfield(
                        count = 30,
                        seed = worldKey.hashCode().toLong(),
                        parallaxPx = 0f,
                    )
                    PlanetSphere(
                        accent = accent,
                        sizeDp = 140.dp,
                        glowing = true,
                    )
                }
                CVDivider()
            }

            // ── Title block ──────────────────────────────────
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                ) {
                    CVKicker(
                        text = saga,
                        color = accent,
                        size = 10,
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = world.name,
                        style = CVTheme.typography.displayXL.copy(
                            fontSize = 36.sp,
                            letterSpacing = (-0.4).sp,
                            lineHeight = 36.sp,
                        ),
                        color = Parchment,
                    )
                }
            }

            // ── Stats grid ───────────────────────────────────
            if (details != null) {
                item {
                    Column(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                    ) {
                        StatRow(t.shardsLabel, details.shards)
                        StatRow(t.magicLabel, details.magic)
                        StatRow(
                            t.booksLabel,
                            if (details.books > 0) details.books.toString() else "\u2014",
                        )
                    }
                    Spacer(Modifier.height(24.dp))
                    CVDivider()
                }
            }

            // ── Featured Lore ────────────────────────────────
            item {
                Spacer(Modifier.height(24.dp))
                Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                    CVKicker(t.featuredLore, color = Linen)
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = t.worldLore[worldKey]
                            ?: "${details?.shards ?: ""} \u00B7 ${details?.magic ?: ""}",
                        style = CVTheme.typography.body.copy(
                            fontSize = 15.sp,
                            lineHeight = (15 * 1.6).sp,
                        ),
                        color = Linen,
                    )
                }
                Spacer(Modifier.height(24.dp))
                CVDivider()
            }

            // ── Books from this world ────────────────────────
            if (books.isNotEmpty()) {
                item {
                    Spacer(Modifier.height(24.dp))
                    Box(Modifier.padding(horizontal = 24.dp)) {
                        CVKicker(t.booksFromWorld, color = Linen)
                    }
                    Spacer(Modifier.height(12.dp))

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 24.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        items(books) { book ->
                            Column(
                                modifier = Modifier
                                    .width(130.dp)
                                    .clickable {
                                        navigator.push(BookDetailScreen(book.id))
                                    },
                            ) {
                                BookCoverPlaceholder(
                                    worldAccent = accent,
                                    label = book.title.split(" ").first(),
                                    modifier = Modifier.fillMaxWidth(),
                                )
                                Spacer(Modifier.height(8.dp))
                                Text(
                                    text = book.title,
                                    style = CVTheme.typography.displayM.copy(
                                        fontSize = 13.sp,
                                        lineHeight = (13 * 1.15).sp,
                                    ),
                                    color = Parchment,
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = book.orderLabel.uppercase(),
                                    style = CVTheme.typography.monoMeta.copy(
                                        fontSize = 9.sp,
                                        letterSpacing = 0.5.sp,
                                    ),
                                    color = accent,
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(24.dp))
                    CVDivider()
                }
            }

            // ── Recent debates ────────────────────────────────
            if (threads.isNotEmpty()) {
                item {
                    Spacer(Modifier.height(24.dp))
                    Box(Modifier.padding(horizontal = 24.dp)) {
                        CVKicker(t.recentDebates, color = Linen)
                    }
                    Spacer(Modifier.height(12.dp))
                }

                items(threads, key = { it.id }) { thread ->
                    ForumThreadRow(
                        thread = thread,
                        t = t,
                        onClick = {
                            navigator.push(ThreadDetailScreen(thread.id))
                        },
                    )
                    CVDivider()
                }
            }

            item { Spacer(Modifier.height(32.dp)) }
        }
    }
}

@Composable
private fun StatRow(label: String, value: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            text = label,
            style = CVTheme.typography.monoMeta.copy(
                fontSize = 10.sp,
                letterSpacing = 1.5.sp,
            ),
            color = Ash,
            modifier = Modifier.width(100.dp),
        )
        Text(
            text = value,
            style = CVTheme.typography.uiS.copy(fontSize = 13.sp),
            color = Linen,
        )
    }
}
