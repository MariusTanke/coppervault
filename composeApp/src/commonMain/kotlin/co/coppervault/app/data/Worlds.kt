package co.coppervault.app.data

import androidx.compose.ui.graphics.Color

object WorldAccents {
    val roshar   = Color(0xFF5B8ED1)
    val scadrial = Color(0xFFB7563E)
    val nalthis  = Color(0xFFD9B45C)
    val sel      = Color(0xFF6FA88A)
    val taldain  = Color(0xFFE0B070)
    val threnody = Color(0xFF7C7480)
    val first    = Color(0xFF8FA38F)
    val yolen    = Color(0xFFB8B8B8)
}

data class WorldMeta(
    val key: String,
    val name: String,
    val railLabel: String,
    val accent: Color,
)

data class WorldDetailsCopy(
    val shards: String,
    val magic: String,
    val books: Int,
)

object Worlds {
    val all: List<WorldMeta> = listOf(
        WorldMeta("roshar",   "Roshar",           "ROSHAR",           WorldAccents.roshar),
        WorldMeta("scadrial", "Scadrial",         "SCADRIAL",         WorldAccents.scadrial),
        WorldMeta("nalthis",  "Nalthis",          "NALTHIS",          WorldAccents.nalthis),
        WorldMeta("sel",      "Sel",              "SEL",              WorldAccents.sel),
        WorldMeta("taldain",  "Taldain",          "TALDAIN",          WorldAccents.taldain),
        WorldMeta("threnody", "Threnody",         "THRENODY",         WorldAccents.threnody),
        WorldMeta("first",    "First of the Sun", "FIRST OF THE SUN", WorldAccents.first),
        WorldMeta("yolen",    "Yolen",            "YOLEN",            WorldAccents.yolen),
    )

    val byKey: Map<String, WorldMeta> = all.associateBy { it.key }
}

/** Resolve accent color for a world name. "Cosmere" maps to Aurum. */
fun worldAccentByName(name: String): Color {
    if (name.equals("Cosmere", ignoreCase = true)) return Color(0xFFC9A66B)
    return Worlds.all.firstOrNull { it.name.equals(name, ignoreCase = true) }?.accent
        ?: Color(0xFFC9A66B)
}
