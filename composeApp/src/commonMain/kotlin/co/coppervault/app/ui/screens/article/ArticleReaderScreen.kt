package co.coppervault.app.ui.screens.article

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.coppervault.app.data.Article
import co.coppervault.app.data.ArticleSection
import co.coppervault.app.data.Articles
import co.coppervault.app.data.Worlds
import co.coppervault.app.ui.components.CVDivider
import co.coppervault.app.ui.components.CVIcons
import co.coppervault.app.ui.components.CVKicker
import co.coppervault.app.ui.strings.CVStrings
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog
import co.coppervault.app.ui.theme.Linen
import co.coppervault.app.ui.theme.Mist
import co.coppervault.app.ui.theme.Parchment
import co.coppervault.app.ui.theme.Stone

class ArticleReaderScreen(
    private val article: Article = Articles.sample,
) : Screen {

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val t = CVStrings.current
        val accent = Worlds.byKey[article.worldKey]?.accent ?: Aurum

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(CVTheme.colors.abyss),
            contentPadding = PaddingValues(bottom = 100.dp),
        ) {
            // ── Top bar ───────────────────────────────────
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 54.dp, start = 20.dp, end = 20.dp, bottom = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Icon(
                        CVIcons.Back,
                        contentDescription = null,
                        tint = Fog,
                        modifier = Modifier
                            .size(18.dp)
                            .clickable { navigator.pop() },
                    )
                    Icon(
                        CVIcons.More,
                        contentDescription = null,
                        tint = Fog,
                        modifier = Modifier.size(18.dp),
                    )
                }
            }

            // ── Hero section ──────────────────────────────
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .drawBehind {
                            drawRect(
                                brush = Brush.radialGradient(
                                    colors = listOf(accent.copy(alpha = 0.12f), Color.Transparent),
                                    center = Offset(size.width * 0.5f, 0f),
                                    radius = size.width * 0.8f,
                                ),
                            )
                        }
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                ) {
                    // World kicker
                    CVKicker(
                        text = article.worldKey.uppercase(),
                        color = accent,
                        size = 10,
                    )

                    Spacer(Modifier.height(12.dp))

                    // Title
                    Text(
                        text = article.title,
                        style = CVTheme.typography.displayL.copy(
                            fontSize = 26.sp,
                            lineHeight = (26 * 1.15).sp,
                        ),
                        color = Parchment,
                    )

                    Spacer(Modifier.height(8.dp))

                    // Subtitle
                    Text(
                        text = article.subtitle,
                        style = CVTheme.typography.body.copy(
                            fontSize = 15.sp,
                            fontStyle = FontStyle.Italic,
                            lineHeight = (15 * 1.4).sp,
                        ),
                        color = Fog,
                    )

                    Spacer(Modifier.height(16.dp))

                    // Meta row
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            text = article.author.uppercase(),
                            style = CVTheme.typography.monoMeta.copy(
                                fontSize = 10.sp,
                                letterSpacing = 1.sp,
                            ),
                            color = Ash,
                        )
                        Text(
                            text = "\u00B7",
                            style = CVTheme.typography.monoMeta.copy(fontSize = 10.sp),
                            color = Ash,
                        )
                        Text(
                            text = "${article.readMin} ${t.minRead}",
                            style = CVTheme.typography.monoMeta.copy(
                                fontSize = 10.sp,
                                letterSpacing = 1.sp,
                            ),
                            color = Ash,
                        )
                    }

                    Spacer(Modifier.height(10.dp))

                    // Spoiler strip
                    Row(
                        modifier = Modifier
                            .background(accent.copy(alpha = 0.08f))
                            .border(0.5.dp, accent.copy(alpha = 0.3f))
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        Icon(
                            CVIcons.Warn,
                            contentDescription = null,
                            tint = accent,
                            modifier = Modifier.size(12.dp),
                        )
                        Text(
                            text = "${t.spoilers}: ${article.spoilerLevel}".uppercase(),
                            style = CVTheme.typography.monoMeta.copy(
                                fontSize = 10.sp,
                                letterSpacing = 1.sp,
                            ),
                            color = accent,
                        )
                    }
                }

                CVDivider()
            }

            // ── Body sections ─────────────────────────────
            items(article.sections) { section ->
                when (section) {
                    is ArticleSection.Body -> {
                        Text(
                            text = section.text,
                            style = CVTheme.typography.body.copy(
                                fontSize = 16.5.sp,
                                lineHeight = (16.5 * 1.7).sp,
                            ),
                            color = Linen,
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                        )
                    }
                    is ArticleSection.Heading -> {
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = section.text,
                            style = CVTheme.typography.displayM.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                            ),
                            color = Parchment,
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp),
                        )
                    }
                    is ArticleSection.PullQuote -> {
                        PullQuoteBlock(
                            text = section.text,
                            attribution = section.attribution,
                            accent = accent,
                        )
                    }
                }
            }

            // ── Tags footer ───────────────────────────────
            item {
                Spacer(Modifier.height(16.dp))
                CVDivider()
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
                ) {
                    CVKicker("Tags", color = Ash, size = 11)
                    Spacer(Modifier.height(10.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        article.tags.forEach { tag ->
                            Box(
                                modifier = Modifier
                                    .border(0.5.dp, Mist)
                                    .padding(horizontal = 10.dp, vertical = 5.dp),
                            ) {
                                Text(
                                    text = tag,
                                    style = CVTheme.typography.uiS.copy(fontSize = 12.sp),
                                    color = Fog,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PullQuoteBlock(
    text: String,
    attribution: String,
    accent: Color,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .drawBehind {
                // Left accent bar
                drawLine(
                    color = accent,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = 2.dp.toPx(),
                )
            }
            .padding(start = 16.dp),
    ) {
        Text(
            text = text,
            style = CVTheme.typography.displayM.copy(
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                lineHeight = (16 * 1.5).sp,
            ),
            color = Parchment,
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = "\u2014 $attribution",
            style = CVTheme.typography.monoMeta.copy(
                fontSize = 10.sp,
                letterSpacing = 1.sp,
            ),
            color = Ash,
        )
    }
}
