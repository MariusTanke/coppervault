package co.coppervault.app.ui.strings

/**
 * Returns the system language tag (e.g. "en", "es", "es-MX").
 * Platform-specific implementations in androidMain / iosMain.
 */
expect fun getSystemLanguage(): String
