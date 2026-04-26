package co.coppervault.app.ui.strings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import co.coppervault.app.data.WorldDetailsCopy

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

    // ── Worlds ──────────────────────────────────────────
    val atlas: String
    val theCosmere: String
    val cosmereItalic: String
    val atlasSub: String
    val allWorlds: String
    val tapToExplore: String
    val tapWorldOrSwipe: String
    val worldSagaName: Map<String, String>
    val worldDetails: Map<String, WorldDetailsCopy>
    val shardsLabel: String
    val magicLabel: String
    val booksLabel: String

    // ── Library ─────────────────────────────────────────
    val theArchive: String
    val library: String
    val reading: String
    val toRead: String
    val finished: String
    val all: String
    val finishedMark: String
    val unreadMark: String
    val emptyLibrary: String
    val emptyLibrarySub: String

    // ── Forum ───────────────────────────────────────────
    val gathering: String
    val forum: String
    val filters: List<String>
    val repliesL: String
    val replyHint: String

    // ── Me / Profile ────────────────────────────────────
    val me: String
    val booksReadStat: String
    val worldsVisited: String
    val threads: String
    val worldProgress: String
    val sealsEarned: String
    val seals: List<String>
    val quoteHoid: String
    val arrangements: String
    val settings: String
    val account: String
    val profileLabel: String
    val spoilersLabel: String
    val spoilerLevel: String
    val hideByDefault: String
    val markRead: String
    val appearance: String
    val theme: String
    val themeAbyssal: String
    val worldAccents: String
    val epigraphs: String
    val aboutLabel: String
    val privacy: String
    val version: String
    val logOut: String

    // ── Notifications ───────────────────────────────────
    val notifications: String
    val markAllRead: String
    val allTab: String
    val mentionsTab: String
    val repliesTab: String
    val notifEmpty: String
    val notifEmptySub: String

    // ── Search ──────────────────────────────────────────
    val searchP: String
    val cancel: String
    val recent: String
    val results: String
    val nothingFound: String
    val nothingFoundSub: String

    // ── Article ─────────────────────────────────────────
    val spoilers: String

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

    override val atlas = "Atlas"
    override val theCosmere = "The"
    override val cosmereItalic = "Cosmere"
    override val atlasSub = "Eight worlds. Sixteen Shards. One thread."
    override val allWorlds = "All Worlds"
    override val tapToExplore = "Tap a world to explore"
    override val tapWorldOrSwipe = "TAP A WORLD OR SWIPE"
    override val worldSagaName = mapOf(
        "roshar" to "STORMLIGHT",
        "scadrial" to "MISTBORN",
        "nalthis" to "WARBREAKER",
        "sel" to "ELANTRIS",
        "taldain" to "WHITE SAND",
        "threnody" to "SHADOWS",
        "first" to "SIXTH OF DUSK",
        "yolen" to "DRAGONSTEEL",
    )
    override val worldDetails = mapOf(
        "roshar" to WorldDetailsCopy("Honor \u00B7 Cultivation \u00B7 Odium", "Surgebinding", 5),
        "scadrial" to WorldDetailsCopy("Preservation \u00B7 Ruin (Harmony)", "Allomancy \u00B7 Feruchemy", 7),
        "nalthis" to WorldDetailsCopy("Endowment", "BioChromatic Breath", 1),
        "sel" to WorldDetailsCopy("Devotion \u00B7 Dominion", "AonDor \u00B7 ChayShan", 1),
        "taldain" to WorldDetailsCopy("Autonomy", "Sand Mastery", 1),
        "threnody" to WorldDetailsCopy("\u2014", "Cognitive Shadows", 1),
        "first" to WorldDetailsCopy("Patji / Autonomy", "Aviar bonds", 1),
        "yolen" to WorldDetailsCopy("Adonalsium (shattered)", "Unknown", 0),
    )
    override val shardsLabel = "SHARDS"
    override val magicLabel = "MAGIC"
    override val booksLabel = "BOOKS"

    override val theArchive = "The Archive"
    override val library = "Library"
    override val reading = "Reading"
    override val toRead = "To Read"
    override val finished = "Finished"
    override val all = "All"
    override val finishedMark = "\u2713 Finished"
    override val unreadMark = "\u2014 Unread"
    override val emptyLibrary = "Nothing here yet"
    override val emptyLibrarySub = "Add books to your library."

    override val gathering = "The Gathering"
    override val forum = "Forum"
    override val filters = listOf("All", "Theories", "Discussion", "Art", "Lore", "Re-read")
    override val repliesL = "replies"
    override val replyHint = "Reply to this thread\u2026"

    override val me = "Me"
    override val booksReadStat = "Books read"
    override val worldsVisited = "Worlds visited"
    override val threads = "Threads"
    override val worldProgress = "World progress"
    override val sealsEarned = "Seals earned"
    override val seals = listOf("First Oath", "Worldhopper", "Theorist", "Re-reader", "Archivist")
    override val quoteHoid = "\u201CI am, of course, a storyteller.\u201D"
    override val arrangements = "Arrangements"
    override val settings = "Settings"
    override val account = "Account"
    override val profileLabel = "Profile"
    override val spoilersLabel = "Spoilers"
    override val spoilerLevel = "Spoiler level"
    override val hideByDefault = "Hide by default"
    override val markRead = "Mark books read"
    override val appearance = "Appearance"
    override val theme = "Theme"
    override val themeAbyssal = "Abyssal"
    override val worldAccents = "World accents"
    override val epigraphs = "Epigraphs"
    override val aboutLabel = "About"
    override val privacy = "Privacy"
    override val version = "Version"
    override val logOut = "Log out"

    override val notifications = "Notifications"
    override val markAllRead = "Mark all read"
    override val allTab = "All"
    override val mentionsTab = "Mentions"
    override val repliesTab = "Replies"
    override val notifEmpty = "Nothing here yet"
    override val notifEmptySub = "Notifications will appear here."

    override val searchP = "Search the Cosmere\u2026"
    override val cancel = "Cancel"
    override val recent = "Recent"
    override val results = "results"
    override val nothingFound = "Nothing found"
    override val nothingFoundSub = "Try a different search."

    override val spoilers = "Spoilers"

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

    override val atlas = "Atlas"
    override val theCosmere = "El"
    override val cosmereItalic = "Cosmere"
    override val atlasSub = "Ocho mundos. Diecis\u00E9is Esquirlas. Un solo hilo."
    override val allWorlds = "Todos los mundos"
    override val tapToExplore = "Pulsa un mundo para explorarlo"
    override val tapWorldOrSwipe = "TOCA UN MUNDO O DESLIZA"
    override val worldSagaName = mapOf(
        "roshar" to "EL ARCHIVO DE LAS TORMENTAS",
        "scadrial" to "NACIDOS DE LA BRUMA",
        "nalthis" to "EL ALIENTO DE LOS DIOSES",
        "sel" to "ELANTRIS",
        "taldain" to "ARENA BLANCA",
        "threnody" to "TRENODIA",
        "first" to "EL SEXTO DEL OCASO",
        "yolen" to "DRAGONSTEEL",
    )
    override val worldDetails = mapOf(
        "roshar" to WorldDetailsCopy("Honor \u00B7 Cultivaci\u00F3n \u00B7 Odium", "Potenciaci\u00F3n", 5),
        "scadrial" to WorldDetailsCopy("Conservaci\u00F3n \u00B7 Ruina (Armon\u00EDa)", "Alomancia \u00B7 Feruquimia", 7),
        "nalthis" to WorldDetailsCopy("Dotaci\u00F3n", "Aliento Biocrom\u00E1tico", 1),
        "sel" to WorldDetailsCopy("Devoci\u00F3n \u00B7 Dominio", "AonDor \u00B7 ChayShan", 1),
        "taldain" to WorldDetailsCopy("Autonom\u00EDa", "Dominio de la Arena", 1),
        "threnody" to WorldDetailsCopy("\u2014", "Sombras Cognitivas", 1),
        "first" to WorldDetailsCopy("Patji / Autonom\u00EDa", "V\u00EDnculos Aviar", 1),
        "yolen" to WorldDetailsCopy("Adonalsium (fragmentado)", "Desconocido", 0),
    )
    override val shardsLabel = "ESQUIRLAS"
    override val magicLabel = "MAGIA"
    override val booksLabel = "LIBROS"

    override val theArchive = "El Archivo"
    override val library = "Biblioteca"
    override val reading = "Leyendo"
    override val toRead = "Por leer"
    override val finished = "Terminados"
    override val all = "Todo"
    override val finishedMark = "\u2713 Terminado"
    override val unreadMark = "\u2014 Sin leer"
    override val emptyLibrary = "A\u00FAn nada aqu\u00ED"
    override val emptyLibrarySub = "A\u00F1ade libros a tu biblioteca."

    override val gathering = "La Reuni\u00F3n"
    override val forum = "Foro"
    override val filters = listOf("Todo", "Teor\u00EDas", "Debate", "Arte", "Lore", "Relectura")
    override val repliesL = "respuestas"
    override val replyHint = "Responder al hilo\u2026"

    override val me = "Yo"
    override val booksReadStat = "Libros le\u00EDdos"
    override val worldsVisited = "Mundos visitados"
    override val threads = "Hilos"
    override val worldProgress = "Progreso por mundo"
    override val sealsEarned = "Sellos obtenidos"
    override val seals = listOf("Primer Juramento", "Worldhopper", "Te\u00F3rico", "Relector", "Archivista")
    override val quoteHoid = "\u201CSoy, por supuesto, un narrador.\u201D"
    override val arrangements = "Arreglos"
    override val settings = "Ajustes"
    override val account = "Cuenta"
    override val profileLabel = "Perfil"
    override val spoilersLabel = "Spoilers"
    override val spoilerLevel = "Nivel de spoiler"
    override val hideByDefault = "Ocultar por defecto"
    override val markRead = "Marcar libros le\u00EDdos"
    override val appearance = "Apariencia"
    override val theme = "Tema"
    override val themeAbyssal = "Abismal"
    override val worldAccents = "Acentos por mundo"
    override val epigraphs = "Ep\u00EDgrafes"
    override val aboutLabel = "Acerca de"
    override val privacy = "Privacidad"
    override val version = "Versi\u00F3n"
    override val logOut = "Cerrar sesi\u00F3n"

    override val notifications = "Notificaciones"
    override val markAllRead = "Marcar todo le\u00EDdo"
    override val allTab = "Todo"
    override val mentionsTab = "Menciones"
    override val repliesTab = "Respuestas"
    override val notifEmpty = "A\u00FAn nada aqu\u00ED"
    override val notifEmptySub = "Las notificaciones aparecer\u00E1n aqu\u00ED."

    override val searchP = "Buscar en el Cosmere\u2026"
    override val cancel = "Cancelar"
    override val recent = "Reciente"
    override val results = "resultados"
    override val nothingFound = "Nada encontrado"
    override val nothingFoundSub = "Prueba con otra b\u00FAsqueda."

    override val spoilers = "Spoilers"

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
