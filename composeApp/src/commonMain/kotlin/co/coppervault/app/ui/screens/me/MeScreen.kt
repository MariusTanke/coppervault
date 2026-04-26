package co.coppervault.app.ui.screens.me

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import co.coppervault.app.data.User
import co.coppervault.app.data.Worlds
import co.coppervault.app.ui.components.CVCosmereMark
import co.coppervault.app.ui.components.CVDivider
import co.coppervault.app.ui.components.CVIcons
import co.coppervault.app.ui.components.CVKicker
import co.coppervault.app.ui.strings.CVStrings
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.AurumHi
import co.coppervault.app.ui.theme.AurumLo
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog
import co.coppervault.app.ui.theme.Ink
import co.coppervault.app.ui.theme.Linen
import co.coppervault.app.ui.theme.Mist
import co.coppervault.app.ui.theme.Parchment
import co.coppervault.app.ui.theme.Stone
import co.coppervault.app.ui.theme.Void

private val Crimson = Color(0xFFB7563E)

class MeScreen : Screen {

    @Composable
    override fun Content() {
        val t = CVStrings.current

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(CVTheme.colors.abyss),
            contentPadding = PaddingValues(bottom = 100.dp),
        ) {
            // ── Profile header ─────────────────────────────
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .drawBehind {
                            drawRect(
                                brush = Brush.radialGradient(
                                    colors = listOf(Aurum.copy(alpha = 0.15f), Color.Transparent),
                                    center = Offset(size.width * 0.5f, 0f),
                                    radius = size.width * 0.7f,
                                ),
                            )
                        }
                        .padding(top = 54.dp, bottom = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    // Settings gear top-right
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(end = 20.dp),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Icon(CVIcons.Gear, contentDescription = null, tint = Fog, modifier = Modifier.size(18.dp))
                    }

                    Spacer(Modifier.height(4.dp))

                    // Avatar
                    Box(contentAlignment = Alignment.Center) {
                        Box(
                            modifier = Modifier
                                .size(84.dp)
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = listOf(AurumHi, AurumLo, Void),
                                    ),
                                    shape = CircleShape,
                                )
                                .border(0.5.dp, Aurum, CircleShape),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "H",
                                style = CVTheme.typography.displayXL.copy(
                                    fontSize = 36.sp,
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.Medium,
                                ),
                                color = Void,
                            )
                        }
                        CVCosmereMark(size = 104.dp, strokeWidth = 0.5f)
                    }

                    Spacer(Modifier.height(14.dp))

                    // Username
                    Text(
                        text = User.name,
                        style = CVTheme.typography.displayM.copy(
                            fontSize = 22.sp,
                            letterSpacing = (-0.2).sp,
                        ),
                        color = Parchment,
                    )

                    // Rank
                    Text(
                        text = User.handle.uppercase(),
                        style = CVTheme.typography.monoMeta.copy(
                            fontSize = 11.sp,
                            letterSpacing = 1.5.sp,
                        ),
                        color = Aurum,
                    )

                    Spacer(Modifier.height(6.dp))

                    // Quote
                    Text(
                        text = t.quoteHoid,
                        style = CVTheme.typography.displayM.copy(
                            fontSize = 13.sp,
                            fontStyle = FontStyle.Italic,
                            lineHeight = (13 * 1.5).sp,
                        ),
                        color = Fog,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 60.dp),
                    )

                    Spacer(Modifier.height(24.dp))

                    // Stats row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        StatItem(User.booksRead.toString(), t.booksReadStat)
                        StatItem(User.worldsVisited.toString(), t.worldsVisited)
                        StatItem(User.threads.toString(), t.threads)
                    }
                }
                CVDivider()
            }

            // ── World progress ──────────────────────────────
            item {
                Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 18.dp, bottom = 10.dp)) {
                    CVKicker(t.worldProgress, color = Linen)
                }
            }

            item {
                val progressData = listOf(
                    "roshar" to 68,
                    "scadrial" to 54,
                    "nalthis" to 100,
                    "sel" to 100,
                    "taldain" to 0,
                )
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    progressData.forEach { (key, progress) ->
                        val world = Worlds.byKey[key] ?: return@forEach
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            Text(
                                text = world.name,
                                style = CVTheme.typography.uiS.copy(fontSize = 12.sp),
                                color = Linen,
                                modifier = Modifier.width(70.dp),
                            )
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(1.dp)
                                    .background(Stone),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(progress / 100f)
                                        .height(1.dp)
                                        .background(world.accent),
                                )
                            }
                            Text(
                                text = "$progress%",
                                style = CVTheme.typography.monoMeta.copy(fontSize = 11.sp),
                                color = Ash,
                                modifier = Modifier.width(30.dp),
                                textAlign = TextAlign.End,
                            )
                        }
                    }
                }
            }

            // ── Seals ───────────────────────────────────────
            item {
                Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 22.dp, bottom = 10.dp)) {
                    CVKicker(t.sealsEarned, color = Linen)
                }
            }

            item {
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    t.seals.chunked(4).forEach { row ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            row.forEach { seal ->
                                val index = t.seals.indexOf(seal)
                                val active = index < 3
                                SealCard(
                                    label = seal,
                                    active = active,
                                    modifier = Modifier.weight(1f),
                                )
                            }
                            repeat(4 - row.size) {
                                Spacer(Modifier.weight(1f))
                            }
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun SealCard(
    label: String,
    active: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .aspectRatio(1f)
            .border(0.5.dp, Mist)
            .then(
                if (active) Modifier.background(Aurum.copy(alpha = 0.06f))
                else Modifier
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CVCosmereMark(
            size = 28.dp,
            color = if (active) Aurum else Ash,
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = label.uppercase(),
            style = CVTheme.typography.monoMeta.copy(
                fontSize = 9.sp,
                letterSpacing = 0.5.sp,
            ),
            color = if (active) Aurum else Ash,
            textAlign = TextAlign.Center,
            maxLines = 2,
            softWrap = true,
        )
    }
}

@Composable
private fun StatItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = CVTheme.typography.displayM.copy(
                fontSize = 22.sp,
                lineHeight = 22.sp,
            ),
            color = Parchment,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = label.uppercase(),
            style = CVTheme.typography.monoMeta.copy(
                fontSize = 10.sp,
                letterSpacing = 1.sp,
            ),
            color = Ash,
        )
    }
}
