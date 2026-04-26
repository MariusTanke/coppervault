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
    val accent: Color,
)

val Worlds: Map<String, WorldMeta> = mapOf(
    "roshar"   to WorldMeta("roshar",   "Roshar",          WorldAccents.roshar),
    "scadrial" to WorldMeta("scadrial", "Scadrial",        WorldAccents.scadrial),
    "nalthis"  to WorldMeta("nalthis",  "Nalthis",         WorldAccents.nalthis),
    "sel"      to WorldMeta("sel",      "Sel",             WorldAccents.sel),
    "taldain"  to WorldMeta("taldain",  "Taldain",         WorldAccents.taldain),
    "threnody" to WorldMeta("threnody", "Threnody",        WorldAccents.threnody),
    "first"    to WorldMeta("first",    "First of the Sun", WorldAccents.first),
    "yolen"    to WorldMeta("yolen",    "Yolen",           WorldAccents.yolen),
)

/** Resolve accent color for a world name. "Cosmere" maps to Aurum. */
fun worldAccentByName(name: String): Color {
    if (name.equals("Cosmere", ignoreCase = true)) return Color(0xFFC9A66B) // Aurum
    return Worlds.values.firstOrNull { it.name.equals(name, ignoreCase = true) }?.accent
        ?: Color(0xFFC9A66B) // Aurum fallback
}
