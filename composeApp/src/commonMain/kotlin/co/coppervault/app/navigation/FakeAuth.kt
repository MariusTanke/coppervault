package co.coppervault.app.navigation

import co.coppervault.app.data.SessionPrefs

/**
 * In-memory auth state. If "remember me" was checked,
 * [SessionPrefs] restores the session on cold start.
 * Will be replaced by a real AuthRepository in a later pass.
 */
object FakeAuth {
    var isLoggedIn: Boolean = SessionPrefs.isRemembered
    var displayName: String = ""
    var email: String = SessionPrefs.email
    var spoilerCeiling: String = "EVERYTHING"

    fun login(email: String, remember: Boolean) {
        isLoggedIn = true
        this.email = email
        if (remember) {
            SessionPrefs.isRemembered = true
            SessionPrefs.email = email
        }
    }

    fun logout() {
        isLoggedIn = false
        displayName = ""
        email = ""
        SessionPrefs.clear()
    }
}
