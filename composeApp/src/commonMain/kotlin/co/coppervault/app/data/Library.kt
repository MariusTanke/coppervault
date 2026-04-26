package co.coppervault.app.data

data class LibraryBook(
    val id: String,
    val title: String,
    val worldKey: String,
    val progress: Int,
    val orderLabel: String,
)

object Library {
    val books = listOf(
        LibraryBook("wok", "The Way of Kings", "roshar", 100, "Stormlight \u00B7 1"),
        LibraryBook("wor", "Words of Radiance", "roshar", 68, "Stormlight \u00B7 2"),
        LibraryBook("mistborn", "Mistborn: The Final Empire", "scadrial", 100, "Era 1 \u00B7 1"),
        LibraryBook("woa", "The Well of Ascension", "scadrial", 34, "Era 1 \u00B7 2"),
        LibraryBook("warbreaker", "Warbreaker", "nalthis", 12, "Standalone"),
        LibraryBook("elantris", "Elantris", "sel", 0, "Standalone"),
    )
}
