package co.coppervault.app.data.locale

import co.coppervault.app.ui.strings.EnStrings
import co.coppervault.app.ui.strings.EsStrings
import co.coppervault.app.ui.strings.Strings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class Language(val code: String) {
    En("en"),
    Es("es"),
    ;

    val strings: Strings
        get() = when (this) {
            En -> EnStrings
            Es -> EsStrings
        }
}

/**
 * Single source of truth for the active language.
 * Default: Spanish. Call [setLanguage] to switch at runtime.
 * Collect [strings] in the root composable to propagate via CompositionLocal.
 */
object LocaleController {
    private val _language = MutableStateFlow(Language.Es)
    val language: StateFlow<Language> = _language.asStateFlow()

    private val _strings = MutableStateFlow<Strings>(EsStrings)
    val strings: StateFlow<Strings> = _strings.asStateFlow()

    fun setLanguage(lang: Language) {
        _language.value = lang
        _strings.value = lang.strings
    }
}
