package co.coppervault.app.data

import com.russhwolf.settings.Settings

/**
 * Persists the "remember me" session using multiplatform-settings.
 * On Android this wraps SharedPreferences, on iOS NSUserDefaults.
 */
object SessionPrefs {
    private val settings: Settings = Settings()

    private const val KEY_REMEMBERED = "session_remembered"
    private const val KEY_EMAIL = "session_email"

    var isRemembered: Boolean
        get() = settings.getBoolean(KEY_REMEMBERED, false)
        set(value) { settings.putBoolean(KEY_REMEMBERED, value) }

    var email: String
        get() = settings.getString(KEY_EMAIL, "")
        set(value) { settings.putString(KEY_EMAIL, value) }

    fun clear() {
        settings.remove(KEY_REMEMBERED)
        settings.remove(KEY_EMAIL)
    }
}
