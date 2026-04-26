package co.coppervault.app.ui.strings

/** Supported app locales. */
enum class CVLocale(val code: String) {
    EN("en"),
    ES("es"),
    ;

    val strings: Strings
        get() = when (this) {
            EN -> EnStrings
            ES -> EsStrings
        }
}

/** Resolve locale from a language tag (e.g. "es", "es-MX", "en-US"). */
fun resolveLocale(languageTag: String): CVLocale {
    val lang = languageTag.substringBefore('-').substringBefore('_').lowercase()
    return when (lang) {
        "es" -> CVLocale.ES
        else -> CVLocale.EN
    }
}
