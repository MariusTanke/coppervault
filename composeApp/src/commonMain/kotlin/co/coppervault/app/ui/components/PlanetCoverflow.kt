package co.coppervault.app.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import co.coppervault.app.data.WorldMeta
import androidx.compose.ui.text.font.FontWeight
import co.coppervault.app.ui.theme.Ash
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Parchment
import co.coppervault.app.ui.theme.Stone
import co.coppervault.app.ui.theme.Void
import kotlin.math.absoluteValue
import kotlinx.coroutines.launch

private val RAIL_HEIGHT = 320.dp
private val CELL_WIDTH = 90.dp
private val MIST_WIDTH = 70.dp

private val BG_TOP = Color(0xFF05060A)
private val BG_BOTTOM = Color(0xFF0A0C12)

/**
 * Tarot-stack coverflow rail with parallax starfield, mist veils,
 * pivot indicator, and fan-rotated planet cards.
 */
@Composable
fun PlanetCoverflow(
    worlds: List<WorldMeta>,
    focusedIndex: Int,
    sagaNames: Map<String, String>,
    onFocusChange: (Int) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val cellWidthPx = with(density) { CELL_WIDTH.toPx() }

    val pagerState = rememberPagerState(
        initialPage = focusedIndex,
        pageCount = { worlds.size },
    )

    // Sync: exterior → pager
    LaunchedEffect(focusedIndex) {
        if (pagerState.currentPage != focusedIndex) {
            pagerState.animateScrollToPage(focusedIndex)
        }
    }

    // Sync: pager → exterior (on snap settle)
    LaunchedEffect(pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress &&
            pagerState.currentPage != focusedIndex
        ) {
            onFocusChange(pagerState.currentPage)
        }
    }

    // Animated accent for background glow
    val activeAccent by animateColorAsState(
        targetValue = worlds[pagerState.currentPage].accent,
        animationSpec = tween(500),
    )

    // Parallax scroll offset
    val scrollOffsetPx = (pagerState.currentPage + pagerState.currentPageOffsetFraction) * cellWidthPx

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(RAIL_HEIGHT)
            .drawBehind {
                // Base gradient
                drawRect(
                    brush = Brush.verticalGradient(listOf(BG_TOP, BG_BOTTOM)),
                )
                // Accent glow from bottom
                drawRect(
                    brush = Brush.radialGradient(
                        colors = listOf(activeAccent.copy(alpha = 0.07f), Color.Transparent),
                        center = Offset(size.width * 0.5f, size.height),
                        radius = size.width * 0.7f,
                    ),
                )
                // Bottom border
                drawLine(
                    color = Stone,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 0.5f,
                )
            },
    ) {
        val screenWidth = maxWidth

        // ── Starfield parallax layers ──
        SeededStarfield(
            count = 30, seed = 1001L,
            parallaxPx = -scrollOffsetPx * 0.02f,
        )
        SeededStarfield(
            count = 25, seed = 2002L,
            parallaxPx = -scrollOffsetPx * 0.08f,
        )
        SeededStarfield(
            count = 15, seed = 3003L,
            parallaxPx = -scrollOffsetPx * 0.16f,
        )

        // ── HorizontalPager ──
        val coroutineScope = rememberCoroutineScope()

        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fixed(CELL_WIDTH),
            contentPadding = PaddingValues(horizontal = (screenWidth - CELL_WIDTH) / 2),
            pageSpacing = 0.dp,
            flingBehavior = PagerDefaults.flingBehavior(
                state = pagerState,
                snapAnimationSpec = spring(
                    dampingRatio = 0.8f,
                    stiffness = Spring.StiffnessLow,
                ),
            ),
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) { page ->
            val pageOffset = (
                (pagerState.currentPage - page) +
                    pagerState.currentPageOffsetFraction
                ).coerceIn(-3f, 3f)
            val abs = pageOffset.absoluteValue

            val scale = (1f - abs * 0.18f).coerceAtLeast(0.55f)
            val pullPx = with(density) { 28.dp.toPx() }
            val dropPx = with(density) { 8.dp.toPx() }

            // Outer Box: zIndex + unbounded so card can overflow cell
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(unbounded = true)
                    .zIndex(100f - abs * 10f),
            ) {
                // Inner Box: graphicsLayer transforms, then clickable AFTER
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .graphicsLayer {
                            rotationZ = (-pageOffset * 12f).coerceIn(-50f, 50f)
                            transformOrigin = TransformOrigin(0.5f, 2.2f)
                            scaleX = scale
                            scaleY = scale
                            translationX = -pageOffset * pullPx
                            translationY = abs * abs * dropPx
                            alpha = (1f - abs * 0.3f).coerceAtLeast(0.3f)
                        }
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(page)
                            }
                        },
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        TarotPlanetCard(
                            world = worlds[page],
                            index = page,
                            sagaName = sagaNames[worlds[page].key] ?: "",
                            isActive = abs < 0.5f,
                        )

                        Spacer(Modifier.height(10.dp))

                        // World name below the card
                        Text(
                            text = worlds[page].name.uppercase(),
                            style = CVTheme.typography.monoMeta.copy(
                                fontSize = 9.sp,
                                letterSpacing = 1.5.sp,
                                fontWeight = if (abs < 0.5f) FontWeight.SemiBold else FontWeight.Normal,
                            ),
                            color = if (abs < 0.5f) worlds[page].accent else Ash,
                        )
                    }
                }
            }
        }

        // ── Mist veils (left + right) ──
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .width(MIST_WIDTH)
                .height(RAIL_HEIGHT)
                .drawBehind {
                    drawRect(
                        brush = Brush.horizontalGradient(
                            listOf(BG_TOP, Color.Transparent),
                        ),
                    )
                }
                .zIndex(5f),
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .width(MIST_WIDTH)
                .height(RAIL_HEIGHT)
                .drawBehind {
                    drawRect(
                        brush = Brush.horizontalGradient(
                            listOf(Color.Transparent, BG_TOP),
                        ),
                    )
                }
                .zIndex(5f),
        )

        // ── Pivot indicator line ──
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 28.dp)
                .width(80.dp)
                .height(1.dp)
                .drawBehind {
                    drawRect(
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color.Transparent,
                                activeAccent.copy(alpha = 0.5f),
                                Color.Transparent,
                            ),
                        ),
                    )
                }
                .zIndex(6f),
        )

        // ── Hint text ──
        Text(
            text = "\u2190 ${hint} \u2192",
            style = CVTheme.typography.monoMeta.copy(
                fontSize = 9.sp,
                letterSpacing = 1.5.sp,
            ),
            color = Ash.copy(alpha = 0.6f),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
                .zIndex(6f),
        )
    }
}
