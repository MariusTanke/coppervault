package co.coppervault.app.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Each Cosmere world carries a single subtle accent color.
 * Used as border-left (2 dp) on cards and as chip fill.
 * Never as background for large areas.
 */
enum class World(
    val displayName: String,
    val saga: String,
    val accent: Color,
) {
    ROSHAR   ("Roshar",            "Stormlight Archive",   Roshar),
    SCADRIAL ("Scadrial",          "Mistborn",             Scadrial),
    NALTHIS  ("Nalthis",           "Warbreaker",           Nalthis),
    SEL      ("Sel",               "Elantris",             Sel),
    TALDAIN  ("Taldain",           "White Sand",           Taldain),
    THRENODY ("Threnody",          "Shadows for Silence",  Threnody),
    FIRST    ("First of the Sun",  "Sixth of the Dusk",    First),
    YOLEN    ("Yolen",             "Pre-Shattering",       Yolen),
}
