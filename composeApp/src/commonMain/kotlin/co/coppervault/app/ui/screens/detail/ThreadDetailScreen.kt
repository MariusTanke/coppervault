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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.coppervault.app.data.Forum
import co.coppervault.app.data.ThreadExtras
import co.coppervault.app.data.ThreadReply
import co.coppervault.app.data.Worlds
import co.coppervault.app.data.locale.LocaleController
import co.coppervault.app.data.locale.Language
import co.coppervault.app.ui.components.CVButton
import co.coppervault.app.ui.components.CVButtonSize
import co.coppervault.app.ui.components.CVDivider
import co.coppervault.app.ui.components.CVIcons
import co.coppervault.app.ui.components.CVKicker
import co.coppervault.app.ui.components.CVTextField
import co.coppervault.app.ui.strings.CVStrings
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog
import co.coppervault.app.ui.theme.Ink
import co.coppervault.app.ui.theme.Linen
import co.coppervault.app.ui.theme.Parchment
import co.coppervault.app.ui.theme.Stone

class ThreadDetailScreen(private val threadId: String) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val t = CVStrings.current
        val thread = Forum.threads.firstOrNull { it.id == threadId } ?: return
        val extras = ThreadExtras.byId[threadId]
        val accent = if (thread.worldKey == "cosmere") Aurum
        else Worlds.byKey[thread.worldKey]?.accent ?: Aurum
        val isEs = LocaleController.language.value == Language.Es
        val body = if (isEs) extras?.bodyEs else extras?.bodyEn
        val replies = extras?.replies ?: emptyList()

        var replyText by remember { mutableStateOf("") }
        var upvotedOP by remember { mutableStateOf(false) }

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
                        CVKicker(
                            "${thread.worldKey.uppercase()} \u00B7 ${thread.category.uppercase()}",
                            color = accent,
                            size = 11,
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = thread.title,
                            style = CVTheme.typography.displayM.copy(
                                fontSize = 22.sp,
                                letterSpacing = (-0.3).sp,
                                lineHeight = 26.sp,
                            ),
                            color = Parchment,
                            maxLines = 2,
                            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(start = 12.dp),
                    ) {
                        Icon(
                            CVIcons.Share,
                            contentDescription = null,
                            tint = Ash,
                            modifier = Modifier.size(18.dp),
                        )
                        Icon(
                            CVIcons.Bookmark,
                            contentDescription = null,
                            tint = Ash,
                            modifier = Modifier.size(18.dp),
                        )
                    }
                }
                CVDivider()
            }

            // ── Scrollable content ───────────────────────────
            Box(modifier = Modifier.weight(1f)) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 32.dp),
                ) {
                    // ── Original post ────────────────────────
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .drawBehind {
                                    drawRect(
                                        color = accent,
                                        topLeft = Offset.Zero,
                                        size = Size(2.dp.toPx(), size.height),
                                    )
                                }
                                .padding(start = 18.dp, end = 24.dp, top = 24.dp, bottom = 24.dp),
                        ) {
                            // World + Category
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = thread.worldKey.uppercase(),
                                    style = CVTheme.typography.monoMeta.copy(
                                        fontSize = 10.sp,
                                        letterSpacing = 1.5.sp,
                                    ),
                                    color = accent,
                                )
                                Text(
                                    text = "\u00B7",
                                    style = CVTheme.typography.monoMeta.copy(fontSize = 10.sp),
                                    color = Ash,
                                )
                                Text(
                                    text = thread.category.uppercase(),
                                    style = CVTheme.typography.monoMeta.copy(
                                        fontSize = 10.sp,
                                        letterSpacing = 1.5.sp,
                                    ),
                                    color = Ash,
                                )
                            }

                            Spacer(Modifier.height(8.dp))

                            // Title
                            Text(
                                text = thread.title,
                                style = CVTheme.typography.displayM.copy(
                                    fontSize = 24.sp,
                                    letterSpacing = (-0.3).sp,
                                    lineHeight = (24 * 1.15).sp,
                                ),
                                color = Parchment,
                            )

                            Spacer(Modifier.height(12.dp))

                            // Author row
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                // Avatar placeholder
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .background(
                                            brush = Brush.radialGradient(
                                                listOf(Stone.copy(alpha = 0.3f), Stone.copy(alpha = 0.1f)),
                                            ),
                                            shape = CircleShape,
                                        )
                                        .border(0.5.dp, Stone, CircleShape),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = thread.author.first().uppercase(),
                                        style = CVTheme.typography.displayM.copy(fontSize = 14.sp),
                                        color = Parchment,
                                    )
                                }
                                Column {
                                    Text(
                                        text = thread.author,
                                        style = CVTheme.typography.body.copy(fontSize = 13.sp),
                                        color = Linen,
                                    )
                                    Text(
                                        text = thread.timeAgo.uppercase(),
                                        style = CVTheme.typography.monoMeta.copy(
                                            fontSize = 9.sp,
                                            letterSpacing = 1.sp,
                                        ),
                                        color = Ash,
                                    )
                                }
                            }

                            // Body
                            if (body != null) {
                                Spacer(Modifier.height(14.dp))
                                Text(
                                    text = body,
                                    style = CVTheme.typography.body.copy(
                                        fontSize = 15.sp,
                                        lineHeight = (15 * 1.6).sp,
                                    ),
                                    color = Linen,
                                )
                            } else {
                                Spacer(Modifier.height(14.dp))
                                Text(
                                    text = "TODO: thread $threadId missing extras",
                                    style = CVTheme.typography.monoMeta.copy(fontSize = 11.sp),
                                    color = Ash,
                                )
                            }

                            // Metrics row
                            Spacer(Modifier.height(16.dp))
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(24.dp),
                            ) {
                                Text(
                                    text = "\u25B2 ${thread.votes}",
                                    style = CVTheme.typography.monoMeta.copy(
                                        fontSize = 10.sp,
                                        letterSpacing = 0.5.sp,
                                    ),
                                    color = if (upvotedOP) Aurum else Ash,
                                    modifier = Modifier.clickable { upvotedOP = !upvotedOP },
                                )
                                Text(
                                    text = "\uD83D\uDCAC ${thread.replies}",
                                    style = CVTheme.typography.monoMeta.copy(
                                        fontSize = 10.sp,
                                        letterSpacing = 0.5.sp,
                                    ),
                                    color = Ash,
                                )
                                Text(
                                    text = t.shareLabel.uppercase(),
                                    style = CVTheme.typography.monoMeta.copy(
                                        fontSize = 10.sp,
                                        letterSpacing = 0.5.sp,
                                    ),
                                    color = Ash,
                                )
                            }
                        }

                        Spacer(Modifier.height(16.dp))
                        CVDivider()
                        Spacer(Modifier.height(16.dp))
                    }

                    // ── Replies header ────────────────────────
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            CVKicker(
                                text = "${thread.replies} ${t.replies}",
                                color = Linen,
                                size = 10,
                            )
                            Text(
                                text = t.sortNewest.uppercase(),
                                style = CVTheme.typography.monoMeta.copy(
                                    fontSize = 10.sp,
                                    letterSpacing = 0.5.sp,
                                ),
                                color = Ash,
                                modifier = Modifier.clickable { /* TODO(p2): cycle sort */ },
                            )
                        }
                        Spacer(Modifier.height(12.dp))
                    }

                    // ── Reply items ──────────────────────────
                    items(replies, key = { it.id }) { reply ->
                        ReplyRow(reply = reply)
                        // Divider between root replies only
                        if (reply.depth == 0) {
                            CVDivider()
                        }
                    }
                }
            }

            // ── Composer bar (fixed bottom) ──────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        drawLine(
                            color = Stone,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            strokeWidth = 0.5f,
                        )
                    }
                    .background(Ink)
                    .padding(horizontal = 12.dp, vertical = 10.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    CVTextField(
                        value = replyText,
                        onValueChange = { replyText = it },
                        placeholder = t.replyPlaceholder,
                        modifier = Modifier.weight(1f),
                    )
                    CVButton(
                        text = t.sendLabel,
                        size = CVButtonSize.S,
                        enabled = replyText.isNotBlank(),
                        onClick = { /* TODO(p2): send reply */ },
                    )
                }
            }
        }
    }
}

@Composable
private fun ReplyRow(reply: ThreadReply) {
    var upvoted by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 24.dp + (reply.depth * 24).dp,
                end = 24.dp,
                top = 14.dp,
                bottom = 14.dp,
            )
            .then(
                if (reply.depth > 0) {
                    Modifier.drawBehind {
                        drawLine(
                            color = Stone,
                            start = Offset(0f, 0f),
                            end = Offset(0f, size.height),
                            strokeWidth = 1.dp.toPx(),
                        )
                    }.padding(start = 12.dp)
                } else Modifier
            ),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Top,
    ) {
        // Avatar
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(
                    brush = Brush.radialGradient(
                        listOf(Stone.copy(alpha = 0.3f), Stone.copy(alpha = 0.1f)),
                    ),
                    shape = CircleShape,
                )
                .border(0.5.dp, Stone, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = reply.author.first().uppercase(),
                style = CVTheme.typography.displayM.copy(fontSize = 11.sp),
                color = Parchment,
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            // Author + time
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = reply.author,
                    style = CVTheme.typography.body.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    color = Linen,
                )
                Text(
                    text = reply.timeAgo.uppercase(),
                    style = CVTheme.typography.monoMeta.copy(
                        fontSize = 9.sp,
                        letterSpacing = 0.5.sp,
                    ),
                    color = Ash,
                )
            }

            Spacer(Modifier.height(4.dp))

            // Body
            Text(
                text = reply.body,
                style = CVTheme.typography.body.copy(
                    fontSize = 14.sp,
                    lineHeight = (14 * 1.5).sp,
                ),
                color = Linen,
            )

            Spacer(Modifier.height(8.dp))

            // Actions
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = "\u25B2 ${reply.upvotes}",
                    style = CVTheme.typography.monoMeta.copy(
                        fontSize = 9.sp,
                        letterSpacing = 0.5.sp,
                    ),
                    color = if (upvoted) Aurum else Ash,
                    modifier = Modifier.clickable { upvoted = !upvoted },
                )
                Text(
                    text = CVStrings.current.replyLabel.uppercase(),
                    style = CVTheme.typography.monoMeta.copy(
                        fontSize = 9.sp,
                        letterSpacing = 0.5.sp,
                    ),
                    color = Ash,
                    modifier = Modifier.clickable { /* TODO(p2): reply to reply */ },
                )
            }
        }
    }
}
