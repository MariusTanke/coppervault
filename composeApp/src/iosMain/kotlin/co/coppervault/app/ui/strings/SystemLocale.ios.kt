package co.coppervault.app.ui.strings

import platform.Foundation.NSLocale
import platform.Foundation.preferredLanguages

actual fun getSystemLanguage(): String {
    val languages = NSLocale.preferredLanguages
    return (languages.firstOrNull() as? String) ?: "en"
}
