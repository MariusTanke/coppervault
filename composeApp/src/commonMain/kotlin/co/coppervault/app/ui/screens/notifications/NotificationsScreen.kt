package co.coppervault.app.ui.screens.notifications

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import co.coppervault.app.data.AppNotification
import co.coppervault.app.data.NotifType
import co.coppervault.app.data.Notifications
import co.coppervault.app.data.Worlds
import co.coppervault.app.ui.components.CVDivider
import co.coppervault.app.ui.components.CVIcons
import co.coppervault.app.ui.strings.CVStrings
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog
import co.coppervault.app.ui.theme.Linen
import co.coppervault.app.ui.theme.Parchment
import co.coppervault.app.ui.theme.Stone
import co.coppervault.app.ui.theme.Void

class NotificationsScreen : Screen {

    @Composable
    override fun Content() {
        val t = CVStrings.current
        var selectedTab by remember { mutableStateOf(0) }

        val tabLabels = listOf(t.allTab, t.mentionsTab, t.repliesTab)
        val unreadCount = Notifications.all.count { it.unread }

        val filtered = when (selectedTab) {
            1 -> Notifications.all.filter { it.type == NotifType.MENTION }
            2 -> Notifications.all.filter { it.type == NotifType.REPLY }
            else -> Notifications.all
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CVTheme.colors.abyss),
        ) {
            // ── Top bar ────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 54.dp, start = 20.dp, end = 20.dp, bottom = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Icon(CVIcons.Back, contentDescription = null, tint = Fog, modifier = Modifier.size(18.dp))
                Text(
                    text = t.notifications,
                    style = CVTheme.typography.displayM.copy(fontSize = 22.sp),
                    color = Parchment,
                )
                Text(
                    text = t.markAllRead.uppercase(),
                    style = CVTheme.typography.monoMeta.copy(
                        fontSize = 10.sp,
                        letterSpacing = 1.5.sp,
                    ),
                    color = Aurum,
                    modifier = Modifier.clickable { /* no-op */ },
                )
            }

            // ── Tabs ───────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Top,
            ) {
                tabLabels.forEachIndexed { index, label ->
                    val isSelected = index == selectedTab
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
                            ) { selectedTab = index },
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
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
                            // Badge on "All" tab
                            if (index == 0 && unreadCount > 0) {
                                Box(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .background(Aurum, CircleShape),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = "$unreadCount",
                                        style = CVTheme.typography.monoMeta.copy(fontSize = 9.sp),
                                        color = Void,
                                    )
                                }
                            }
                        }
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

            // ── List ───────────────────────────────────────
            if (filtered.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = t.notifEmpty.uppercase(),
                            style = CVTheme.typography.monoMeta.copy(fontSize = 10.sp, letterSpacing = 2.sp),
                            color = Ash,
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(t.notifEmptySub, style = CVTheme.typography.body.copy(fontSize = 13.sp), color = Fog)
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 100.dp),
                ) {
                    items(filtered, key = { it.id }) { notif ->
                        NotifRow(notif)
                        CVDivider()
                    }
                }
            }
        }
    }
}

@Composable
private fun NotifRow(notif: AppNotification) {
    val accent = if (notif.worldKey == "cosmere") Aurum
    else Worlds.byKey[notif.worldKey]?.accent ?: Aurum

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (notif.unread) Modifier.background(Aurum.copy(alpha = 0.04f))
                else Modifier
            )
            .drawBehind {
                if (notif.unread) {
                    drawRect(
                        color = Aurum,
                        topLeft = Offset.Zero,
                        size = Size(2.dp.toPx(), size.height),
                    )
                }
            }
            .padding(horizontal = 14.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top,
    ) {
        // Avatar
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(Stone.copy(alpha = 0.3f), CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = notif.author.first().uppercase(),
                style = CVTheme.typography.monoMeta.copy(fontSize = 12.sp),
                color = Linen,
            )
        }

        // Content
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold, color = Linen)) {
                        append(notif.author)
                    }
                    withStyle(SpanStyle(fontWeight = FontWeight.Normal, color = Ash)) {
                        append(" ${notif.action} ")
                    }
                    withStyle(SpanStyle(fontStyle = FontStyle.Italic, color = Parchment)) {
                        append(notif.target)
                    }
                },
                style = CVTheme.typography.body.copy(fontSize = 14.sp, lineHeight = (14 * 1.3).sp),
            )
            Spacer(Modifier.height(3.dp))
            Text(
                text = "${notif.worldKey.uppercase()} \u00B7 ${notif.timeAgo}",
                style = CVTheme.typography.monoMeta.copy(fontSize = 9.sp, letterSpacing = 1.sp),
                color = Ash,
            )
        }

        // Type icon
        Text(
            text = when (notif.type) {
                NotifType.REPLY -> "\uD83D\uDCAC"
                NotifType.MENTION -> "@"
                NotifType.ARTICLE -> "\uD83D\uDCD6"
            },
            style = CVTheme.typography.monoMeta.copy(fontSize = 14.sp),
            color = Ash,
        )
    }
}
