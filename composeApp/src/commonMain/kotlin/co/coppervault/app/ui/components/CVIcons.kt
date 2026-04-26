package co.coppervault.app.ui.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

/**
 * Hand-authored icon set ported from WHIcon (design-system.jsx).
 * 20×20 grid, stroke weight ~1.2 dp to match the hairline aesthetic.
 */
object CVIcons {

    val Search: ImageVector by lazy {
        ImageVector.Builder("Search", 20.dp, 20.dp, 20f, 20f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineCap = StrokeCap.Round,
            ) {
                // circle r=6 at (9,9)
                addOval(centerX = 9f, centerY = 9f, radiusX = 6f, radiusY = 6f)
            }
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineCap = StrokeCap.Round,
            ) {
                moveTo(13.5f, 13.5f); lineTo(17f, 17f)
            }
        }.build()
    }

    val Mail: ImageVector by lazy {
        ImageVector.Builder("Mail", 20.dp, 20.dp, 20f, 20f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
            ) {
                moveTo(2.5f, 4.5f)
                lineTo(17.5f, 4.5f); lineTo(17.5f, 15.5f)
                lineTo(2.5f, 15.5f); close()
            }
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
            ) {
                moveTo(2.5f, 5f); lineTo(10f, 11f); lineTo(17.5f, 5f)
            }
        }.build()
    }

    val Lock: ImageVector by lazy {
        ImageVector.Builder("Lock", 20.dp, 20.dp, 20f, 20f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
            ) {
                moveTo(3.5f, 9f); lineTo(16.5f, 9f)
                lineTo(16.5f, 17f); lineTo(3.5f, 17f); close()
            }
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
            ) {
                moveTo(6f, 9f); lineTo(6f, 7f)
                arcTo(4f, 4f, 0f, isMoreThanHalf = true, isPositiveArc = true, 14f, 7f)
                lineTo(14f, 9f)
            }
        }.build()
    }

    val User: ImageVector by lazy {
        ImageVector.Builder("User", 20.dp, 20.dp, 20f, 20f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
            ) {
                addOval(centerX = 10f, centerY = 7f, radiusX = 3f, radiusY = 3f)
            }
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
            ) {
                moveTo(3f, 17f)
                curveTo(4.5f, 13.5f, 7f, 12f, 10f, 12f)
                curveTo(13f, 12f, 15.5f, 13.5f, 17f, 17f)
            }
        }.build()
    }

    val Home: ImageVector by lazy {
        ImageVector.Builder("Home", 20.dp, 20.dp, 20f, 20f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(3f, 9f); lineTo(10f, 3f); lineTo(17f, 9f)
                lineTo(17f, 17f); lineTo(12f, 17f); lineTo(12f, 12f)
                lineTo(8f, 12f); lineTo(8f, 17f); lineTo(3f, 17f)
                close()
            }
        }.build()
    }

    val Globe: ImageVector by lazy {
        ImageVector.Builder("Globe", 20.dp, 20.dp, 20f, 20f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
            ) {
                addOval(centerX = 10f, centerY = 10f, radiusX = 7f, radiusY = 7f)
            }
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
            ) {
                addOval(centerX = 10f, centerY = 10f, radiusX = 3f, radiusY = 7f)
            }
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
            ) {
                moveTo(3f, 10f); lineTo(17f, 10f)
            }
        }.build()
    }

    val Book: ImageVector by lazy {
        ImageVector.Builder("Book", 20.dp, 20.dp, 20f, 20f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
            ) {
                moveTo(4f, 4f); lineTo(16f, 4f)
                lineTo(16f, 17f); lineTo(4f, 17f); close()
            }
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 0.8f,
                strokeAlpha = 0.5f,
            ) {
                moveTo(4f, 10f); lineTo(16f, 10f)
            }
        }.build()
    }

    val Forum: ImageVector by lazy {
        ImageVector.Builder("Forum", 20.dp, 20.dp, 20f, 20f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(3f, 5f); lineTo(14f, 5f)
                lineTo(14f, 13f); lineTo(8f, 13f)
                lineTo(5f, 16f); lineTo(5f, 13f)
                lineTo(3f, 13f); close()
            }
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineCap = StrokeCap.Round,
            ) {
                moveTo(7f, 8f); lineTo(10f, 8f)
                moveTo(7f, 10f); lineTo(11f, 10f)
            }
        }.build()
    }

    val Warn: ImageVector by lazy {
        ImageVector.Builder("Warn", 14.dp, 14.dp, 14f, 14f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(7f, 1f); lineTo(13f, 12f); lineTo(1f, 12f); close()
            }
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineCap = StrokeCap.Round,
            ) {
                moveTo(7f, 6f); lineTo(7f, 9f)
                moveTo(7f, 11f); lineTo(7f, 10.5f)
            }
        }.build()
    }

    val Star: ImageVector by lazy {
        ImageVector.Builder("Star", 14.dp, 14.dp, 14f, 14f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1f,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(7f, 1f); lineTo(8.8f, 4.8f); lineTo(13f, 5.5f)
                lineTo(10f, 8.5f); lineTo(10.8f, 13f); lineTo(7f, 11f)
                lineTo(3.2f, 13f); lineTo(4f, 8.5f); lineTo(1f, 5.5f)
                lineTo(5.2f, 4.8f); close()
            }
        }.build()
    }

    val Back: ImageVector by lazy {
        ImageVector.Builder("Back", 16.dp, 16.dp, 16f, 16f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineCap = StrokeCap.Round, strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(10f, 3f); lineTo(4f, 8f); lineTo(10f, 13f)
            }
        }.build()
    }

    val Plus: ImageVector by lazy {
        ImageVector.Builder("Plus", 14.dp, 14.dp, 14f, 14f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineCap = StrokeCap.Round,
            ) {
                moveTo(7f, 2f); lineTo(7f, 12f)
                moveTo(2f, 7f); lineTo(12f, 7f)
            }
        }.build()
    }

    val Bell: ImageVector by lazy {
        ImageVector.Builder("Bell", 20.dp, 20.dp, 20f, 20f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(5f, 14f); lineTo(5f, 9f)
                arcTo(5f, 5f, 0f, isMoreThanHalf = true, isPositiveArc = true, 15f, 9f)
                lineTo(15f, 14f); lineTo(16.5f, 16f)
                lineTo(3.5f, 16f); close()
            }
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
            ) {
                moveTo(8f, 17f)
                arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 12f, 17f)
            }
        }.build()
    }

    val Heart: ImageVector by lazy {
        ImageVector.Builder("Heart", 14.dp, 14.dp, 14f, 14f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(7f, 12f)
                curveTo(7f, 12f, 1.5f, 8.5f, 1.5f, 5f)
                curveTo(1.5f, 3.5f, 2.8f, 2.5f, 4f, 2.5f)
                curveTo(5.2f, 2.5f, 6.2f, 3.2f, 7f, 4.3f)
                curveTo(7.8f, 3.2f, 8.8f, 2.5f, 10f, 2.5f)
                curveTo(11.2f, 2.5f, 12.5f, 3.5f, 12.5f, 5f)
                curveTo(12.5f, 8.5f, 7f, 12f, 7f, 12f)
                close()
            }
        }.build()
    }

    val Note: ImageVector by lazy {
        ImageVector.Builder("Note", 20.dp, 20.dp, 20f, 20f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(4f, 3f); lineTo(14f, 3f); lineTo(16f, 5f)
                lineTo(16f, 17f); lineTo(4f, 17f); close()
            }
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineCap = StrokeCap.Round,
            ) {
                moveTo(7f, 8f); lineTo(13f, 8f)
                moveTo(7f, 11f); lineTo(13f, 11f)
                moveTo(7f, 14f); lineTo(10f, 14f)
            }
        }.build()
    }

    val Gear: ImageVector by lazy {
        ImageVector.Builder("Gear", 20.dp, 20.dp, 20f, 20f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
            ) {
                addOval(centerX = 10f, centerY = 10f, radiusX = 2.5f, radiusY = 2.5f)
            }
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineCap = StrokeCap.Round,
            ) {
                moveTo(10f, 2f); lineTo(10f, 4f)
                moveTo(10f, 16f); lineTo(10f, 18f)
                moveTo(18f, 10f); lineTo(16f, 10f)
                moveTo(4f, 10f); lineTo(2f, 10f)
                moveTo(15.6f, 4.4f); lineTo(14.2f, 5.8f)
                moveTo(5.8f, 14.2f); lineTo(4.4f, 15.6f)
                moveTo(15.6f, 15.6f); lineTo(14.2f, 14.2f)
                moveTo(5.8f, 5.8f); lineTo(4.4f, 4.4f)
            }
        }.build()
    }

    val Clock: ImageVector by lazy {
        ImageVector.Builder("Clock", 20.dp, 20.dp, 20f, 20f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
            ) {
                addOval(centerX = 10f, centerY = 10f, radiusX = 7f, radiusY = 7f)
            }
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineCap = StrokeCap.Round,
            ) {
                moveTo(10f, 6f); lineTo(10f, 10f); lineTo(13f, 12f)
            }
        }.build()
    }

    val ChevronRight: ImageVector by lazy {
        ImageVector.Builder("ChevronRight", 12.dp, 12.dp, 12f, 12f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineCap = StrokeCap.Round, strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(4f, 2f); lineTo(8f, 6f); lineTo(4f, 10f)
            }
        }.build()
    }

    val Bookmark: ImageVector by lazy {
        ImageVector.Builder("Bookmark", 16.dp, 20.dp, 16f, 20f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(2f, 1f); lineTo(14f, 1f); lineTo(14f, 19f)
                lineTo(8f, 14f); lineTo(2f, 19f); close()
            }
        }.build()
    }

    val BookmarkFilled: ImageVector by lazy {
        ImageVector.Builder("BookmarkFilled", 16.dp, 20.dp, 16f, 20f).apply {
            path(
                fill = SolidColor(Color.White),
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(2f, 1f); lineTo(14f, 1f); lineTo(14f, 19f)
                lineTo(8f, 14f); lineTo(2f, 19f); close()
            }
        }.build()
    }

    val Share: ImageVector by lazy {
        ImageVector.Builder("Share", 18.dp, 18.dp, 18f, 18f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineCap = StrokeCap.Round, strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(6f, 10f); lineTo(12f, 7f)
                moveTo(6f, 10f); lineTo(12f, 13f)
            }
            path(stroke = SolidColor(Color.White), strokeLineWidth = 1.2f) {
                addOval(centerX = 4.5f, centerY = 10f, radiusX = 2f, radiusY = 2f)
                addOval(centerX = 13.5f, centerY = 5.5f, radiusX = 2f, radiusY = 2f)
                addOval(centerX = 13.5f, centerY = 14.5f, radiusX = 2f, radiusY = 2f)
            }
        }.build()
    }

    val Send: ImageVector by lazy {
        ImageVector.Builder("Send", 18.dp, 18.dp, 18f, 18f).apply {
            path(
                stroke = SolidColor(Color.White), strokeLineWidth = 1.2f,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(2f, 2f); lineTo(16f, 9f); lineTo(2f, 16f)
                lineTo(2f, 10f); lineTo(10f, 9f); lineTo(2f, 8f)
                close()
            }
        }.build()
    }

    val More: ImageVector by lazy {
        ImageVector.Builder("More", 16.dp, 4.dp, 16f, 4f).apply {
            path(fill = SolidColor(Color.White)) {
                addOval(centerX = 2f, centerY = 2f, radiusX = 1.5f, radiusY = 1.5f)
                addOval(centerX = 8f, centerY = 2f, radiusX = 1.5f, radiusY = 1.5f)
                addOval(centerX = 14f, centerY = 2f, radiusX = 1.5f, radiusY = 1.5f)
            }
        }.build()
    }
}

// Helper: add oval to current path
private fun androidx.compose.ui.graphics.vector.PathBuilder.addOval(
    centerX: Float, centerY: Float, radiusX: Float, radiusY: Float,
) {
    moveTo(centerX - radiusX, centerY)
    arcTo(radiusX, radiusY, 0f, isMoreThanHalf = true, isPositiveArc = true, centerX + radiusX, centerY)
    arcTo(radiusX, radiusY, 0f, isMoreThanHalf = true, isPositiveArc = true, centerX - radiusX, centerY)
    close()
}
