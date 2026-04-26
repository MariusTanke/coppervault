package co.coppervault.app.navigation

/**
 * In-memory auth placeholder. Resets on process death.
 * Will be replaced by a real AuthRepository in a later pass.
 */
object FakeAuth {
    var isLoggedIn: Boolean = false
    var displayName: String = ""
    var email: String = ""
    var spoilerCeiling: String = "EVERYTHING"
}
