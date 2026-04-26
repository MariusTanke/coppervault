package co.coppervault.app.data

data class ForumThread(
    val id: String,
    val title: String,
    val worldKey: String,
    val category: String,
    val author: String,
    val timeAgo: String,
    val replies: Int,
    val votes: Int,
    val spoiler: String?,
    val pinned: Boolean = false,
)

object Forum {
    val threads = listOf(
        ForumThread("1", "Hoid\u2019s true objective across the Cosmere", "cosmere", "Theories", "stormwarden", "2h", 247, 412, "Era 2 \u00B7 SA5", pinned = true),
        ForumThread("2", "Weekly discussion: Stormlight 5 finale reactions", "roshar", "Discussion", "bridgefour_", "4h", 1204, 892, "SA5"),
        ForumThread("3", "Is Harmony compromised? [Lost Metal spoilers]", "scadrial", "Theories", "allomancer_22", "8h", 89, 156, "Era 2 finale"),
        ForumThread("4", "Warbreaker re-read \u2014 chapter 12 breakdown", "nalthis", "Re-read", "breath_hoarder", "1d", 34, 67, "Warbreaker"),
        ForumThread("5", "Fan art: a portrait of Dalinar Kholin", "roshar", "Art", "stormlight_ink", "1d", 52, 289, null),
        ForumThread("6", "AonDor geometry: mathematical analysis", "sel", "Lore", "elantris_scholar", "2d", 21, 78, "Elantris"),
    )
}
