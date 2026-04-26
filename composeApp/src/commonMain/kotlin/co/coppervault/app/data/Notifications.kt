package co.coppervault.app.data

enum class NotifType { REPLY, MENTION, ARTICLE }

data class AppNotification(
    val id: String,
    val type: NotifType,
    val author: String,
    val action: String,
    val target: String,
    val worldKey: String,
    val timeAgo: String,
    val unread: Boolean,
)

object Notifications {
    val all = listOf(
        AppNotification("1", NotifType.REPLY, "bridgefour_", "replied to your thread", "Hoid\u2019s true objective", "roshar", "2h", true),
        AppNotification("2", NotifType.MENTION, "allomancer_22", "mentioned you in", "Is Harmony compromised?", "scadrial", "4h", true),
        AppNotification("3", NotifType.REPLY, "breath_hoarder", "replied to", "Warbreaker re-read ch12", "nalthis", "8h", true),
        AppNotification("4", NotifType.ARTICLE, "coppermind", "published", "The Rhythm of Lost Light", "roshar", "1d", false),
        AppNotification("5", NotifType.MENTION, "stormlight_ink", "tagged you in", "Fan art: Dalinar Kholin", "roshar", "1d", false),
        AppNotification("6", NotifType.REPLY, "elantris_scholar", "replied to", "AonDor geometry", "sel", "2d", false),
        AppNotification("7", NotifType.ARTICLE, "coppermind", "published", "Investiture transfer theory", "cosmere", "3d", false),
        AppNotification("8", NotifType.MENTION, "bridgefour_", "mentioned you in", "Stormlight 5 reactions", "roshar", "3d", false),
    )
}
