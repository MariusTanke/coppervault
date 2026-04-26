package co.coppervault.app.ui.screens.home

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
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
import co.coppervault.app.data.Articles
import co.coppervault.app.data.Forum
import co.coppervault.app.data.Worlds
import co.coppervault.app.ui.components.CVChip
import co.coppervault.app.ui.components.CVCosmereMark
import co.coppervault.app.ui.components.CVDivider
import co.coppervault.app.ui.components.CVIcons
import co.coppervault.app.ui.components.CVKicker
import co.coppervault.app.ui.components.CVPlaceholder
import co.coppervault.app.ui.components.CVSpoilerStrip
import co.coppervault.app.ui.components.CVWordmark
import co.coppervault.app.ui.components.ForumThreadRow
import co.coppervault.app.ui.screens.article.ArticleReaderScreen
import co.coppervault.app.ui.screens.detail.BookDetailScreen
import co.coppervault.app.ui.screens.detail.ThreadDetailScreen
import co.coppervault.app.ui.screens.notifications.NotificationsScreen
import co.coppervault.app.ui.screens.search.SearchScreen
import co.coppervault.app.ui.strings.CVStrings
import co.coppervault.app.ui.theme.Abyss
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog
import co.coppervault.app.ui.theme.Ink
import co.coppervault.app.ui.theme.Linen
import co.coppervault.app.ui.theme.Parchment
import co.coppervault.app.ui.theme.Stone

// ── Mock data ───────────────────────────────────────────

private data class HomeReadingMock(val bookId: String, val title: String, val world: String, val percent: Int)

private val readingMocks = listOf(
    HomeReadingMock("wor", "Words of Radiance", "roshar", 68),
    HomeReadingMock("woa", "The Well of Ascension", "scadrial", 34),
    HomeReadingMock("warbreaker", "Warbreaker", "nalthis", 12),
)

// ── Screen ──────────────────────────────────────────────

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val t = CVStrings.current

        Column(Modifier.fillMaxSize()) {
            // ── Top bar (non-scrolling) ─────────────────────
            HomeTopBar(
                onSearchClick = { navigator.push(SearchScreen()) },
                onNotificationsClick = { navigator.push(NotificationsScreen()) },
            )

            // ── Scrollable content ──────────────────────────
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 100.dp),
            ) {
                // 2.1 Today's Page hero
                item {
                    TodaysPageCard(
                        t = t,
                        onArticleClick = { navigator.push(ArticleReaderScreen(Articles.sample)) },
                    )
                }

                // 2.2 Continue reading header
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, top = 22.dp, bottom = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        CVKicker(t.continueL, color = Linen)
                        CVKicker(t.seeAll, color = Ash, size = 11)
                    }
                }

                // 2.2 Continue reading row
                item { ContinueReadingRow(onBookClick = { bookId -> navigator.push(BookDetailScreen(bookId)) }) }

                // 2.3 From the Forum header
                item {
                    Box(Modifier.padding(start = 20.dp, end = 20.dp, top = 22.dp, bottom = 10.dp)) {
                        CVKicker(t.fromForum, color = Linen)
                    }
                }

                // 2.3 Forum threads
                item { ForumThreads(t, onThreadClick = { threadId -> navigator.push(ThreadDetailScreen(threadId)) }) }
            }
        }
    }
}

// ── Top bar ─────────────────────────────────────────────

@Composable
private fun HomeTopBar(
    onSearchClick: () -> Unit,
    onNotificationsClick: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFC9A66B).copy(alpha = 0.04f),
                                Color.Transparent,
                            ),
                        ),
                    )
                }
                .padding(top = 54.dp, bottom = 16.dp, start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Left: CosmereMark + Wordmark
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                CVCosmereMark(size = 36.dp, strokeWidth = 0.6f)
                CVWordmark(size = 26, tight = true)
            }

            // Right: search + bell with notification dot
            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    CVIcons.Search,
                    contentDescription = "Search",
                    tint = Fog,
                    modifier = Modifier.size(26.dp).clickable { onSearchClick() },
                )
                Box(modifier = Modifier.clickable { onNotificationsClick() }) {
                    Icon(CVIcons.Bell, contentDescription = "Notifications", tint = Fog, modifier = Modifier.size(26.dp))
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(Aurum, CircleShape)
                            .align(Alignment.TopEnd)
                            .offset(x = 1.dp, y = (-1).dp),
                    )
                }
            }
        }
        CVDivider()
    }
}

// ── Today's Page hero card ──────────────────────────────

@Composable
private fun TodaysPageCard(
    t: co.coppervault.app.ui.strings.Strings,
    onArticleClick: () -> Unit = {},
) {
    val rosharAccent = Worlds.byKey["roshar"]!!.accent

    Box(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 16.dp)
            .fillMaxWidth()
            .clickable { onArticleClick() }
            .drawBehind {
                // Background: Ink → Abyss vertical
                drawRect(
                    brush = Brush.verticalGradient(
                        colors = listOf(Ink, Abyss),
                    ),
                )
                // Roshar radial glow top-right
                drawRect(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            rosharAccent.copy(alpha = 0.15f),
                            Color.Transparent,
                        ),
                        center = Offset(size.width * 0.8f, size.height * 0.2f),
                        radius = size.width * 0.6f,
                    ),
                )
                // Border
                drawRect(
                    color = Stone,
                    style = androidx.compose.ui.graphics.drawscope.Stroke(width = 0.5f),
                )
            }
            .padding(vertical = 22.dp, horizontal = 18.dp),
    ) {
        Column {
            CVKicker(
                text = t.todaysPage,
                color = Aurum,
                size = 10,
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Parchment)) {
                        append("${t.rhythmOf} ")
                    }
                    withStyle(SpanStyle(color = Aurum, fontStyle = FontStyle.Italic)) {
                        append(t.lostLight)
                    }
                },
                style = CVTheme.typography.displayM.copy(
                    fontSize = 22.sp,
                    lineHeight = 27.5.sp,
                    letterSpacing = (-0.3).sp,
                    fontWeight = FontWeight.Normal,
                ),
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = t.rhythmBody,
                style = CVTheme.typography.uiS.copy(
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                ),
                color = Fog,
            )

            Spacer(Modifier.height(14.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                CVSpoilerStrip(level = "Era 2")
                Text(
                    text = "\u00B7 6 ${t.minRead}",
                    style = CVTheme.typography.monoMeta.copy(
                        fontSize = 11.sp,
                        letterSpacing = 1.sp,
                    ),
                    color = Ash,
                )
            }
        }

        // Top-right chip
        CVChip(
            label = "Roshar",
            accent = rosharAccent,
            modifier = Modifier.align(Alignment.TopEnd),
        )
    }
}

// ── Continue reading row ────────────────────────────────

@Composable
private fun ContinueReadingRow(onBookClick: (String) -> Unit = {}) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(readingMocks) { book ->
            val world = Worlds.byKey[book.world]
            val accent = world?.accent ?: Aurum

            Column(modifier = Modifier.width(120.dp).clickable { onBookClick(book.bookId) }) {
                CVPlaceholder(
                    label = book.title.split(" ").first(),
                    tint = accent,
                    width = 120.dp,
                    height = 160.dp,
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = book.title,
                    style = CVTheme.typography.displayM.copy(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                    ),
                    color = Parchment,
                )

                Spacer(Modifier.height(6.dp))

                // Progress bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Stone),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(book.percent / 100f)
                            .height(1.dp)
                            .background(accent),
                    )
                }

                Spacer(Modifier.height(4.dp))

                Text(
                    text = "${book.percent}% \u00B7 ${world?.name ?: book.world}",
                    style = CVTheme.typography.monoMeta.copy(
                        fontSize = 11.sp,
                        letterSpacing = 0.5.sp,
                    ),
                    color = Ash,
                )
            }
        }
    }
}

// ── Forum threads ───────────────────────────────────────

@Composable
private fun ForumThreads(
    t: co.coppervault.app.ui.strings.Strings,
    onThreadClick: (String) -> Unit = {},
) {
    Column {
        Forum.threads.take(3).forEach { thread ->
            ForumThreadRow(
                thread = thread,
                t = t,
                onClick = { onThreadClick(thread.id) },
            )
            CVDivider()
        }
    }
}
