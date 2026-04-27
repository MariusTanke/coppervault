package co.coppervault.app.ui.screens.detail

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
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.coppervault.app.data.BookExtras
import co.coppervault.app.data.Forum
import co.coppervault.app.data.Library
import co.coppervault.app.data.Worlds
import co.coppervault.app.data.locale.LocaleController
import co.coppervault.app.data.locale.Language
import co.coppervault.app.ui.components.BookCoverPlaceholder
import co.coppervault.app.ui.components.CVButton
import co.coppervault.app.ui.components.CVButtonSize
import co.coppervault.app.ui.components.CVButtonVariant
import co.coppervault.app.ui.components.CVDivider
import co.coppervault.app.ui.components.CVIcons
import co.coppervault.app.ui.components.CVKicker
import co.coppervault.app.ui.components.ForumThreadRow
import co.coppervault.app.ui.strings.CVStrings
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog
import co.coppervault.app.ui.theme.Linen
import co.coppervault.app.ui.theme.Parchment
import co.coppervault.app.ui.theme.Stone
import co.coppervault.app.ui.theme.Void

class BookDetailScreen(private val bookId: String) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val t = CVStrings.current
        val book = Library.books.firstOrNull { it.id == bookId } ?: return
        val world = Worlds.byKey[book.worldKey]
        val accent = world?.accent ?: Aurum
        val extras = BookExtras.byId[bookId]
        val threads = Forum.threads.filter { it.worldKey == book.worldKey }.take(3)
        var bookmarked by remember { mutableStateOf(false) }
        val isEs = LocaleController.language.value == Language.Es

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CVTheme.colors.abyss),
        ) {
            // ── Header (fixed) ───────────────────────────────
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 54.dp, start = 20.dp, end = 20.dp, bottom = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Icon(
                            CVIcons.Back,
                            contentDescription = null,
                            tint = Ash,
                            modifier = Modifier
                                .size(18.dp)
                                .clickable { navigator.pop() },
                        )
                        Spacer(Modifier.height(10.dp))
                        CVKicker(world?.name ?: book.worldKey, color = accent, size = 11)
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = book.title,
                            style = CVTheme.typography.displayM.copy(
                                fontSize = 22.sp,
                                letterSpacing = (-0.3).sp,
                                lineHeight = 26.sp,
                            ),
                            color = Parchment,
                            maxLines = 1,
                            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(start = 12.dp),
                    ) {
                        Icon(
                            if (bookmarked) CVIcons.BookmarkFilled else CVIcons.Bookmark,
                            contentDescription = null,
                            tint = if (bookmarked) Aurum else Ash,
                            modifier = Modifier
                                .size(18.dp)
                                .clickable { bookmarked = !bookmarked },
                        )
                        Icon(
                            CVIcons.More,
                            contentDescription = null,
                            tint = Ash,
                            modifier = Modifier.size(18.dp),
                        )
                    }
                }
                CVDivider()
            }

            // ── Scrollable content ───────────────────────────
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 100.dp),
            ) {
            // ── Hero ─────────────────────────────────────────
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(380.dp)
                        .drawBehind {
                            drawRect(
                                brush = Brush.verticalGradient(
                                    0f to accent.copy(alpha = 0.4f),
                                    0.5f to accent.copy(alpha = 0.1f),
                                    1f to Void,
                                ),
                            )
                        },
                    contentAlignment = Alignment.TopCenter,
                ) {
                    Box(modifier = Modifier.padding(top = 24.dp)) {
                        BookCoverPlaceholder(
                            worldAccent = accent,
                            label = book.title.split(" ").first(),
                            modifier = Modifier.width(200.dp),
                        )

                        // Progress badge
                        if (book.progress in 1..100) {
                            val badgeText = if (book.progress == 100) {
                                t.finishedMark
                            } else {
                                "${book.progress}%"
                            }
                            Box(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(top = 8.dp, end = 8.dp)
                                    .background(Aurum.copy(alpha = 0.15f))
                                    .border(0.5.dp, Aurum)
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                            ) {
                                Text(
                                    text = badgeText.uppercase(),
                                    style = CVTheme.typography.monoMeta.copy(
                                        fontSize = 9.sp,
                                        letterSpacing = 0.5.sp,
                                    ),
                                    color = Aurum,
                                )
                            }
                        }
                    }
                }
                CVDivider()
            }

            // ── Title block ──────────────────────────────────
            item {
                Column(modifier = Modifier.padding(24.dp)) {
                    CVKicker(
                        text = book.orderLabel,
                        color = accent,
                        size = 10,
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = book.title,
                        style = CVTheme.typography.displayXL.copy(
                            fontSize = 32.sp,
                            letterSpacing = (-0.4).sp,
                            lineHeight = (32 * 1.05).sp,
                        ),
                        color = Parchment,
                    )
                    if (extras != null) {
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "${extras.author} \u00B7 ${extras.publishYear}",
                            style = CVTheme.typography.monoMeta.copy(
                                fontSize = 11.sp,
                                letterSpacing = 1.5.sp,
                            ),
                            color = Ash,
                        )
                    }
                }
            }

            // ── Primary action ───────────────────────────────
            item {
                Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                    when {
                        book.progress == 0 -> {
                            CVButton(
                                text = t.startReading,
                                size = CVButtonSize.L,
                                fullWidth = true,
                                onClick = { /* TODO(p2): start reading */ },
                            )
                        }
                        book.progress in 1..99 -> {
                            CVButton(
                                text = "${t.continueReading} \u00B7 ${book.progress}%",
                                size = CVButtonSize.L,
                                fullWidth = true,
                                onClick = { /* TODO(p2): continue reading */ },
                            )
                        }
                        else -> {
                            CVButton(
                                text = t.reread,
                                size = CVButtonSize.L,
                                variant = CVButtonVariant.Ghost,
                                fullWidth = true,
                                onClick = { /* TODO(p2): re-read */ },
                            )
                        }
                    }

                    // Progress bar
                    if (book.progress > 0) {
                        Spacer(Modifier.height(12.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Stone),
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(book.progress / 100f)
                                    .height(1.dp)
                                    .background(accent),
                            )
                        }
                    }
                }
                Spacer(Modifier.height(24.dp))
                CVDivider()
            }

            // ── Synopsis ─────────────────────────────────────
            if (extras != null) {
                item {
                    Spacer(Modifier.height(24.dp))
                    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                        CVKicker(t.synopsis, color = Linen)
                        Spacer(Modifier.height(12.dp))
                        Text(
                            text = if (isEs) extras.synopsisEs else extras.synopsisEn,
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
            }

            // ── Details grid ─────────────────────────────────
            item {
                Spacer(Modifier.height(24.dp))
                Column(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    CVKicker(t.detailsLabel, color = Linen)
                    Spacer(Modifier.height(4.dp))
                    DetailRow(
                        label = t.worldLabel,
                        value = world?.name ?: book.worldKey,
                        valueColor = Aurum,
                        onClick = { navigator.push(WorldDetailScreen(book.worldKey)) },
                    )
                    DetailRow(t.orderLabelDetail, book.orderLabel)
                    if (extras != null) {
                        DetailRow(t.pagesLabel, extras.pages.toString())
                        DetailRow(t.publishedLabel, extras.publishYear.toString())
                    }
                }
                Spacer(Modifier.height(24.dp))
                CVDivider()
            }

            // ── Debates about this book ──────────────────────
            if (threads.isNotEmpty()) {
                item {
                    Spacer(Modifier.height(24.dp))
                    Box(Modifier.padding(horizontal = 24.dp)) {
                        CVKicker(t.bookDebates, color = Linen)
                    }
                    Spacer(Modifier.height(12.dp))
                }

                items(threads.size) { i ->
                    ForumThreadRow(
                        thread = threads[i],
                        t = t,
                        onClick = {
                            navigator.push(ThreadDetailScreen(threads[i].id))
                        },
                    )
                    CVDivider()
                }
            }

            item { Spacer(Modifier.height(32.dp)) }
            }
        }
    }
}

@Composable
private fun DetailRow(
    label: String,
    value: String,
    valueColor: Color = Linen,
    onClick: (() -> Unit)? = null,
) {
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
            modifier = Modifier.width(110.dp),
        )
        Text(
            text = value,
            style = CVTheme.typography.uiS.copy(fontSize = 13.sp),
            color = valueColor,
            modifier = if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier,
        )
    }
}
