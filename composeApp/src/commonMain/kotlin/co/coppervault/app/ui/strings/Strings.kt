package co.coppervault.app.ui.strings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * All user-facing strings in Coppervault.
 * Each screen section is grouped for clarity.
 * Add new keys here + in [EnStrings] and [EsStrings].
 */
interface Strings {

    // ── Splash ──────────────────────────────────────────
    val tagline: String
    val splashQuote: String
    val splashAttr: String

    // ── Login ───────────────────────────────────────────
    val openPortal: String
    val emailOrUser: String
    val password: String
    val rememberMe: String
    val forgot: String
    val enterCosmere: String
    val orHopVia: String
    val newTraveler: String
    val beginJourney: String

    // ── Register ────────────────────────────────────────
    val back: String
    val beginYour: String
    val journey: String
    val nameEtched: String
    val travelerName: String
    val email: String
    val confirmPw: String
    val spoilerWarn: String
    val acceptCompact: String
    val compact: String

    // ── Forgot ──────────────────────────────────────────
    val lost: String
    val betweenWorlds: String
    val forgotBody: String
    val sendMissive: String
    val journeyBefore: String

    // ── Validation ─────────��────────────────────────────
    val required: String
    val invalidEmail: String
    val nameLenRule: String
    val pwMinLength: String
    val pwNeedsNumber: String
    val pwNoMatch: String
}

// ── English ─────────────���────────────────────���──────────

object EnStrings : Strings {
    // Splash
    override val tagline = "A Companion to the Cosmere"
    override val splashQuote = "The most important step a man can take is the next one."
    override val splashAttr = "Dalinar Kholin"

    // Login
    override val openPortal = "OPEN THE PORTAL"
    override val emailOrUser = "Email or username"
    override val password = "Password"
    override val rememberMe = "Remember me"
    override val forgot = "Forgot?"
    override val enterCosmere = "Enter the Cosmere"
    override val orHopVia = "OR HOP VIA"
    override val newTraveler = "New traveler? "
    override val beginJourney = "Begin journey"

    // Register
    override val back = "Back"
    override val beginYour = "Begin your"
    override val journey = "journey"
    override val nameEtched = "Your name will be etched in the chronicle"
    override val travelerName = "Traveler name"
    override val email = "Email"
    override val confirmPw = "Confirm password"
    override val spoilerWarn = "Coppervault contains community discussion that may include spoilers. You can adjust your ceiling at any time in Settings."
    override val acceptCompact = "I accept the "
    override val compact = "Compact"

    // Forgot
    override val lost = "Lost"
    override val betweenWorlds = "between worlds"
    override val forgotBody = "Drop your email and we\u2019ll send you a thread back."
    override val sendMissive = "Send Missive"
    override val journeyBefore = "The journey before the destination."

    // Validation
    override val required = "Required"
    override val invalidEmail = "Invalid email"
    override val nameLenRule = "3-20 chars, letters/numbers/_"
    override val pwMinLength = "At least 8 characters"
    override val pwNeedsNumber = "Must contain a number"
    override val pwNoMatch = "Passwords don't match"
}

// ── Spanish ────────────────────────────────────��────────

object EsStrings : Strings {
    // Splash
    override val tagline = "Una gu\u00EDa del Cosmere"
    override val splashQuote = "El paso m\u00E1s importante que puede dar un hombre es el siguiente."
    override val splashAttr = "Dalinar Kholin"

    // Login
    override val openPortal = "ABRE EL PORTAL"
    override val emailOrUser = "Email o usuario"
    override val password = "Contrase\u00F1a"
    override val rememberMe = "Recordarme"
    override val forgot = "\u00BFOlvidaste?"
    override val enterCosmere = "Entrar al Cosmere"
    override val orHopVia = "O SALTA V\u00CDA"
    override val newTraveler = "\u00BFNuevo viajero? "
    override val beginJourney = "Comienza el viaje"

    // Register
    override val back = "Volver"
    override val beginYour = "Comienza tu"
    override val journey = "viaje"
    override val nameEtched = "Tu nombre ser\u00E1 grabado en la cr\u00F3nica"
    override val travelerName = "Nombre de viajero"
    override val email = "Email"
    override val confirmPw = "Confirmar contrase\u00F1a"
    override val spoilerWarn = "Coppervault contiene discusiones de la comunidad que pueden incluir spoilers. Puedes ajustar tu techo en cualquier momento en Ajustes."
    override val acceptCompact = "Acepto el "
    override val compact = "Compacto"

    // Forgot
    override val lost = "Perdido"
    override val betweenWorlds = "entre mundos"
    override val forgotBody = "D\u00E9janos tu email y te enviaremos un hilo de vuelta."
    override val sendMissive = "Enviar Misiva"
    override val journeyBefore = "El viaje antes que el destino."

    // Validation
    override val required = "Obligatorio"
    override val invalidEmail = "Email inv\u00E1lido"
    override val nameLenRule = "3-20 caracteres, letras/n\u00FAmeros/_"
    override val pwMinLength = "M\u00EDnimo 8 caracteres"
    override val pwNeedsNumber = "Debe contener un n\u00FAmero"
    override val pwNoMatch = "Las contrase\u00F1as no coinciden"
}

// ── CompositionLocal ────────────────────────────────────

val LocalStrings = staticCompositionLocalOf<Strings> { EnStrings }

/** Shorthand accessor: `val t = CVStrings.current` */
object CVStrings {
    val current: Strings
        @Composable @ReadOnlyComposable
        get() = LocalStrings.current
}
