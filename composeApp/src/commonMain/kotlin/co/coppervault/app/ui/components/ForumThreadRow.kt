package co.coppervault.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.coppervault.app.data.ForumThread
import co.coppervault.app.data.Worlds
import co.coppervault.app.ui.strings.Strings
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Parchment

/**
 * Shared forum thread row used in ForumScreen, WorldDetailScreen, BookDetailScreen.
 */
@Composable
fun ForumThreadRow(
    thread: ForumThread,
    t: Strings,
    onClick: () -> Unit = {},
) {
    val accent = if (thread.worldKey == "cosmere") Aurum
    else Worlds.byKey[thread.worldKey]?.accent ?: Aurum

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (thread.pinned) Modifier.background(Aurum.copy(alpha = 0.04f))
                else Modifier
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 14.dp),
    ) {
        Column {
            // World chip + tag
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                CVChip(
                    label = thread.worldKey.replaceFirstChar { it.uppercase() },
                    accent = accent,
                )
                Text(
                    text = "\u00B7 ${thread.category.uppercase()}",
                    style = CVTheme.typography.monoMeta.copy(fontSize = 10.sp, letterSpacing = 0.5.sp),
                    color = Ash,
                )
            }

            Spacer(Modifier.height(6.dp))

            // Title
            Text(
                text = thread.title,
                style = CVTheme.typography.displayM.copy(
                    fontSize = 16.sp,
                    lineHeight = (16 * 1.3).sp,
                    letterSpacing = (-0.1).sp,
                ),
                color = Parchment,
                modifier = Modifier.padding(end = if (thread.pinned) 24.dp else 0.dp),
            )

            Spacer(Modifier.height(8.dp))

            // Meta row: spoiler strip + stats
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                if (thread.spoiler != null) {
                    CVSpoilerStrip(level = thread.spoiler, color = accent)
                }
                Text(
                    text = "\u25B2 ${thread.votes}  \u00B7  ${thread.replies} ${t.repliesL}  \u00B7  ${thread.author} \u00B7 ${thread.timeAgo}",
                    style = CVTheme.typography.monoMeta.copy(fontSize = 10.sp, letterSpacing = 0.5.sp),
                    color = Ash,
                )
            }
        }

        // Pinned star
        if (thread.pinned) {
            Text(
                text = "\u2605",
                style = CVTheme.typography.monoMeta.copy(fontSize = 15.sp),
                color = Aurum,
                modifier = Modifier.align(Alignment.TopEnd),
            )
        }
    }
}
