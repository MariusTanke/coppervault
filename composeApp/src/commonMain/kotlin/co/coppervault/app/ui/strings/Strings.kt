package co.coppervault.app.ui.strings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * All user-facing strings in Coppervault.
 * Source of truth: design_handoff_coppervault/design-reference/i18n.jsx
 */
interface Strings {

    // ── Splash ──────────────────────────────────────────
    val tagline: String
    val splashQuote: String
    val splashAttr: String

    // ── Auth ────────────────────────────────────────────
    val openPortal: String
    val emailOrUser: String
    val password: String
    val rememberMe: String
    val forgot: String
    val enterCosmere: String
    val orHopVia: String
    val newTraveler: String
    val beginJourney: String
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
    val lost: String
    val betweenWorlds: String
    val forgotBody: String
    val sendMissive: String
    val journeyBefore: String

    // ── Home ────────────────────────────────────────────
    val todaysPage: String
    val rhythmOf: String
    val lostLight: String
    val rhythmBody: String
    val minRead: String
    val continueL: String
    val seeAll: String
    val fromForum: String
    val replies: String

    // ── Tabs ────────────────────────────────────────────
    val tHome: String
    val tWorlds: String
    val tLibrary: String
    val tForum: String
    val tMe: String

    // ── Validation ──────────────────────────────────────
    val required: String
    val invalidEmail: String
    val nameLenRule: String
    val pwMinLength: String
    val pwNeedsNumber: String
    val pwNoMatch: String
}

// ── English (from i18n.jsx en) ──────────────────────────

object EnStrings : Strings {
    override val tagline = "A Companion to the Cosmere"
    override val splashQuote = "I walk between worlds, as is my wont."
    override val splashAttr = "From a traveler\u2019s journal"

    override val openPortal = "Open the Portal"
    override val emailOrUser = "Email or username"
    override val password = "Password"
    override val rememberMe = "Remember me"
    override val forgot = "Forgot?"
    override val enterCosmere = "Enter the Cosmere"
    override val orHopVia = "or hop via"
    override val newTraveler = "New traveler?"
    override val beginJourney = "Begin your journey"
    override val back = "Back"
    override val beginYour = "Begin your"
    override val journey = "journey."
    override val nameEtched = "A name will be etched upon the roll of travelers."
    override val travelerName = "Traveler name"
    override val email = "Email"
    override val confirmPw = "Confirm password"
    override val spoilerWarn = "This realm contains spoilers across the Cosmere. Forum posts require spoiler tags per saga."
    override val acceptCompact = "I accept the"
    override val compact = "Compact of Travelers"
    override val lost = "Lost"
    override val betweenWorlds = "between worlds?"
    override val forgotBody = "Tell us the path you entered by. A message will be sent \u2014 a way back."
    override val sendMissive = "Send the Missive"
    override val journeyBefore = "Journey before destination."

    override val todaysPage = "Today\u2019s Page"
    override val rhythmOf = "The Rhythm of"
    override val lostLight = "Lost Light"
    override val rhythmBody = "A reflection on Investiture transfer between Shards, drawn from the Coppermind archives."
    override val minRead = "MIN READ"
    override val continueL = "Continue"
    override val seeAll = "See all"
    override val fromForum = "From the Forum"
    override val replies = "REPLIES"

    override val tHome = "Home"
    override val tWorlds = "Worlds"
    override val tLibrary = "Library"
    override val tForum = "Forum"
    override val tMe = "Me"

    override val required = "Required"
    override val invalidEmail = "Invalid email"
    override val nameLenRule = "3-20 chars, letters/numbers/_"
    override val pwMinLength = "At least 8 characters"
    override val pwNeedsNumber = "Must contain a number"
    override val pwNoMatch = "Passwords don\u2019t match"
}

// ── Spanish (from i18n.jsx es) ──────────────────────────

object EsStrings : Strings {
    override val tagline = "Compa\u00F1ero del Cosmere"
    override val splashQuote = "Camino entre mundos, como es mi costumbre."
    override val splashAttr = "Del diario de un viajero"

    override val openPortal = "Abrir el Portal"
    override val emailOrUser = "Correo o usuario"
    override val password = "Contrase\u00F1a"
    override val rememberMe = "Recu\u00E9rdame"
    override val forgot = "\u00BFOlvidaste?"
    override val enterCosmere = "Entrar al Cosmere"
    override val orHopVia = "o salta con"
    override val newTraveler = "\u00BFNuevo viajero?"
    override val beginJourney = "Comienza tu traves\u00EDa"
    override val back = "Volver"
    override val beginYour = "Comienza tu"
    override val journey = "traves\u00EDa."
    override val nameEtched = "Tu nombre ser\u00E1 grabado en el registro de los viajeros."
    override val travelerName = "Nombre de viajero"
    override val email = "Correo"
    override val confirmPw = "Confirmar contrase\u00F1a"
    override val spoilerWarn = "Este reino contiene spoilers de todo el Cosmere. Los hilos del foro requieren etiqueta de spoiler por saga."
    override val acceptCompact = "Acepto el"
    override val compact = "Pacto de los Viajeros"
    override val lost = "\u00BFPerdido"
    override val betweenWorlds = "entre mundos?"
    override val forgotBody = "Dinos por qu\u00E9 sendero entraste. Una misiva te ser\u00E1 enviada \u2014 el camino de vuelta."
    override val sendMissive = "Enviar la Misiva"
    override val journeyBefore = "El viaje antes que el destino."

    override val todaysPage = "P\u00E1gina de hoy"
    override val rhythmOf = "El Ritmo de la"
    override val lostLight = "Luz Perdida"
    override val rhythmBody = "Una reflexi\u00F3n sobre la transferencia de Investidura entre Esquirlas, extra\u00EDda de los archivos del Coppermind."
    override val minRead = "MIN DE LECTURA"
    override val continueL = "Continuar"
    override val seeAll = "Ver todo"
    override val fromForum = "Desde el Foro"
    override val replies = "RESPUESTAS"

    override val tHome = "Inicio"
    override val tWorlds = "Mundos"
    override val tLibrary = "Biblioteca"
    override val tForum = "Foro"
    override val tMe = "Yo"

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
