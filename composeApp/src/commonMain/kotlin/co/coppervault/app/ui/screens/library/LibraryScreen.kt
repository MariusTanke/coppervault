package co.coppervault.app.ui.screens.library

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import co.coppervault.app.data.Library
import co.coppervault.app.data.LibraryBook
import co.coppervault.app.data.Worlds
import co.coppervault.app.ui.components.BookCoverPlaceholder
import co.coppervault.app.ui.components.CVDivider
import co.coppervault.app.ui.components.CVKicker
import co.coppervault.app.ui.strings.CVStrings
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog
import co.coppervault.app.ui.theme.Parchment
import co.coppervault.app.ui.theme.Stone

class LibraryScreen : Screen {

    @Composable
    override fun Content() {
        val t = CVStrings.current
        var selectedFilter by remember { mutableStateOf(0) }

        val filterLabels = listOf(t.reading, t.toRead, t.finished, t.all)

        val filtered = when (selectedFilter) {
            0 -> Library.books.filter { it.progress in 1..99 }
            1 -> Library.books.filter { it.progress == 0 }
            2 -> Library.books.filter { it.progress == 100 }
            else -> Library.books
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CVTheme.colors.abyss),
        ) {
            // ── Header ─────────────────────────────────────
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 54.dp, start = 20.dp, end = 20.dp, bottom = 14.dp),
            ) {
                CVKicker(t.theArchive, color = Ash, size = 10)
                Spacer(Modifier.height(4.dp))
                Text(
                    text = t.library,
                    style = CVTheme.typography.displayM.copy(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = (-0.4).sp,
                    ),
                    color = Parchment,
                )
            }
            CVDivider()

            // ── Filter tabs ────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Top,
            ) {
                filterLabels.forEachIndexed { index, label ->
                    val isSelected = index == selectedFilter
                    val color by animateColorAsState(
                        targetValue = if (isSelected) Aurum else Ash,
                        animationSpec = tween(200),
                    )

                    Column(
                        modifier = Modifier
                            .width(IntrinsicSize.Max)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) { selectedFilter = index },
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = label.uppercase(),
                            style = CVTheme.typography.monoMeta.copy(
                                fontSize = 11.sp,
                                letterSpacing = 1.5.sp,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                            ),
                            color = color,
                            maxLines = 1,
                            softWrap = false,
                        )
                        Spacer(Modifier.height(6.dp))
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(if (isSelected) Aurum else Color.Transparent),
                        )
                    }
                }
            }
            CVDivider()

            // ── Grid or empty state ────────────────────────
            if (filtered.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = t.emptyLibrary.uppercase(),
                            style = CVTheme.typography.monoMeta.copy(
                                fontSize = 10.sp,
                                letterSpacing = 2.sp,
                            ),
                            color = Ash,
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = t.emptyLibrarySub,
                            style = CVTheme.typography.body.copy(
                                fontSize = 13.sp,
                            ),
                            color = Fog,
                        )
                    }
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        end = 20.dp,
                        top = 16.dp,
                        bottom = 100.dp,
                    ),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(filtered, key = { it.id }) { book ->
                        val accent = Worlds.byKey[book.worldKey]?.accent ?: Aurum
                        BookCard(
                            book = book,
                            worldAccent = accent,
                            onClick = { /* P2: navigate to BookDetail */ },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BookCard(
    book: LibraryBook,
    worldAccent: androidx.compose.ui.graphics.Color,
    onClick: () -> Unit,
) {
    val t = CVStrings.current

    Column(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            ),
    ) {
        // Cover placeholder
        BookCoverPlaceholder(
            worldAccent = worldAccent,
            label = book.title.split(" ").first(),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(8.dp))

        // Order label
        Text(
            text = book.orderLabel.uppercase(),
            style = CVTheme.typography.monoMeta.copy(
                fontSize = 9.sp,
                letterSpacing = 1.sp,
                textDecoration = TextDecoration.None,
            ),
            color = worldAccent,
        )

        Spacer(Modifier.height(3.dp))

        // Title
        Text(
            text = book.title,
            style = CVTheme.typography.displayM.copy(
                fontSize = 15.sp,
                lineHeight = (15 * 1.15).sp,
                textDecoration = TextDecoration.None,
            ),
            color = Parchment,
        )

        Spacer(Modifier.height(6.dp))

        // Progress / status
        when {
            book.progress in 1..99 -> {
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
                            .background(worldAccent),
                    )
                }
            }
            book.progress == 100 -> {
                Text(
                    text = t.finishedMark.uppercase(),
                    style = CVTheme.typography.monoMeta.copy(
                        fontSize = 9.sp,
                        letterSpacing = 1.sp,
                    ),
                    color = Ash,
                )
            }
            else -> {
                Text(
                    text = t.unreadMark.uppercase(),
                    style = CVTheme.typography.monoMeta.copy(
                        fontSize = 9.sp,
                        letterSpacing = 1.sp,
                    ),
                    color = Ash,
                )
            }
        }
    }
}
