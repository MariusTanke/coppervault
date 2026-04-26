// i18n — ES / EN strings for Worldhopper
// Global locale state with a simple subscribe mechanism so any component can re-render on change.

const WH_STRINGS = {
  en: {
    tagline: 'A Companion to the Cosmere',
    splashQuote: 'I walk between worlds, as is my wont.',
    splashAttr:  "From a traveler's journal",

    // auth
    openPortal: 'Open the Portal',
    emailOrUser: 'Email or username',
    password: 'Password',
    remember: 'Remember me',
    forgot: 'Forgot?',
    enterCosmere: 'Enter the Cosmere',
    orHopVia: 'or hop via',
    newTraveler: 'New traveler?',
    beginJourney: 'Begin your journey',
    back: 'Back',
    beginYour: 'Begin your',
    journey: 'journey.',
    nameEtched: 'A name will be etched upon the roll of travelers.',
    travelerName: 'Traveler name',
    email: 'Email',
    confirmPw: 'Confirm password',
    spoilerWarn: 'This realm contains spoilers across the Cosmere. Forum posts require spoiler tags per saga.',
    acceptCompact: 'I accept the',
    compact: 'Compact of Travelers',
    lost: 'Lost',
    betweenWorlds: 'between worlds?',
    forgotBody: 'Tell us the path you entered by. A message will be sent — a way back.',
    sendMissive: 'Send the Missive',
    journeyBefore: 'Journey before destination.',

    // home
    todaysPage: "Today's Page",
    rhythmOf: 'The Rhythm of',
    lostLight: 'Lost Light',
    rhythmBody: 'A reflection on Investiture transfer between Shards, drawn from the Coppermind archives.',
    minRead: 'MIN READ',
    continueL: 'Continue',
    seeAll: 'See all',
    fromForum: 'From the Forum',
    replies: 'REPLIES',

    // worlds
    atlas: 'Atlas',
    theCosmere: 'The',
    cosmereItalic: 'Cosmere',
    atlasSub: 'Eight known worlds · sixteen Shards',
    allWorlds: 'All Worlds',
    tapToExplore: 'Tap a world to explore',

    // library
    theArchive: 'The Archive',
    library: 'Library',
    reading: 'Reading',
    toRead: 'To Read',
    finished: 'Finished',
    all: 'All',
    finishedMark: '✓ Finished',
    unreadMark: '— Unread',

    // characters
    dramatis: 'Dramatis Personae',
    characters: 'Characters',
    searchChars: 'Search by name, order, or world…',
    worldhoppers: 'Worldhoppers',

    // magic
    arsArcanum: 'Ars Arcanum',
    systemsOf: 'Systems of',
    magicItalic: 'Magic',

    // timeline
    chronology: 'Chronology',
    theCosmic: 'The Cosmic',
    timelineItalic: 'Timeline',

    // forum
    gathering: 'The Gathering',
    forum: 'Forum',
    filters: ['All', 'Theories', 'Discussion', 'Art', 'Lore', 'Re-read'],
    repliesL: 'replies',
    replyHint: 'Reply to this thread…',

    // notes
    marginalia: 'Marginalia',
    notes: 'Notes',

    // profile
    booksRead: 'Books read',
    worldsVisited: 'Worlds visited',
    threads: 'Threads',
    worldProgress: 'World progress',
    sealsEarned: 'Seals earned',
    seals: ['First Oath', 'Worldhopper', 'Theorist', 'Re-reader', 'Archivist'],
    quoteHoid: '"I am, of course, a storyteller."',

    // settings
    arrangements: 'Arrangements',
    settings: 'Settings',
    account: 'Account',
    profile: 'Profile',
    spoilers: 'Spoilers',
    spoilerLevel: 'Spoiler level',
    hideByDefault: 'Hide by default',
    markRead: 'Mark books read',
    appearance: 'Appearance',
    theme: 'Theme',
    themeAbyssal: 'Abyssal',
    worldAccents: 'World accents',
    epigraphs: 'Epigraphs',
    about: 'About',
    privacy: 'Privacy',
    version: 'Version',

    // search
    searchP: 'Search the Cosmere…',
    cancel: 'Cancel',
    recent: 'Recent',
    results: 'results',

    // tabs
    tHome: 'Home', tWorlds: 'Worlds', tLibrary: 'Library', tForum: 'Forum', tMe: 'Me',
  },
  es: {
    tagline: 'Compañero del Cosmere',
    splashQuote: 'Camino entre mundos, como es mi costumbre.',
    splashAttr:  'Del diario de un viajero',

    openPortal: 'Abrir el Portal',
    emailOrUser: 'Correo o usuario',
    password: 'Contraseña',
    remember: 'Recuérdame',
    forgot: '¿Olvidaste?',
    enterCosmere: 'Entrar al Cosmere',
    orHopVia: 'o salta con',
    newTraveler: '¿Nuevo viajero?',
    beginJourney: 'Comienza tu travesía',
    back: 'Volver',
    beginYour: 'Comienza tu',
    journey: 'travesía.',
    nameEtched: 'Tu nombre será grabado en el registro de los viajeros.',
    travelerName: 'Nombre de viajero',
    email: 'Correo',
    confirmPw: 'Confirmar contraseña',
    spoilerWarn: 'Este reino contiene spoilers de todo el Cosmere. Los hilos del foro requieren etiqueta de spoiler por saga.',
    acceptCompact: 'Acepto el',
    compact: 'Pacto de los Viajeros',
    lost: '¿Perdido',
    betweenWorlds: 'entre mundos?',
    forgotBody: 'Dinos por qué sendero entraste. Una misiva te será enviada — el camino de vuelta.',
    sendMissive: 'Enviar la Misiva',
    journeyBefore: 'El viaje antes que el destino.',

    todaysPage: 'Página de hoy',
    rhythmOf: 'El Ritmo de la',
    lostLight: 'Luz Perdida',
    rhythmBody: 'Una reflexión sobre la transferencia de Investidura entre Esquirlas, extraída de los archivos del Coppermind.',
    minRead: 'MIN DE LECTURA',
    continueL: 'Continuar',
    seeAll: 'Ver todo',
    fromForum: 'Desde el Foro',
    replies: 'RESPUESTAS',

    atlas: 'Atlas',
    theCosmere: 'El',
    cosmereItalic: 'Cosmere',
    atlasSub: 'Ocho mundos conocidos · dieciséis Esquirlas',
    allWorlds: 'Todos los mundos',
    tapToExplore: 'Pulsa un mundo para explorarlo',

    theArchive: 'El Archivo',
    library: 'Biblioteca',
    reading: 'Leyendo',
    toRead: 'Por leer',
    finished: 'Terminados',
    all: 'Todo',
    finishedMark: '✓ Terminado',
    unreadMark: '— Sin leer',

    dramatis: 'Dramatis Personae',
    characters: 'Personajes',
    searchChars: 'Busca por nombre, orden o mundo…',
    worldhoppers: 'Worldhoppers',

    arsArcanum: 'Ars Arcanum',
    systemsOf: 'Sistemas de',
    magicItalic: 'Magia',

    chronology: 'Cronología',
    theCosmic: 'La línea',
    timelineItalic: 'cósmica',

    gathering: 'La Reunión',
    forum: 'Foro',
    filters: ['Todo', 'Teorías', 'Debate', 'Arte', 'Lore', 'Relectura'],
    repliesL: 'respuestas',
    replyHint: 'Responder al hilo…',

    marginalia: 'Marginalia',
    notes: 'Notas',

    booksRead: 'Libros leídos',
    worldsVisited: 'Mundos visitados',
    threads: 'Hilos',
    worldProgress: 'Progreso por mundo',
    sealsEarned: 'Sellos obtenidos',
    seals: ['Primer Juramento', 'Worldhopper', 'Teórico', 'Relector', 'Archivista'],
    quoteHoid: '"Soy, por supuesto, un narrador."',

    arrangements: 'Arreglos',
    settings: 'Ajustes',
    account: 'Cuenta',
    profile: 'Perfil',
    spoilers: 'Spoilers',
    spoilerLevel: 'Nivel de spoiler',
    hideByDefault: 'Ocultar por defecto',
    markRead: 'Marcar libros leídos',
    appearance: 'Apariencia',
    theme: 'Tema',
    themeAbyssal: 'Abismal',
    worldAccents: 'Acentos por mundo',
    epigraphs: 'Epígrafes',
    about: 'Acerca de',
    privacy: 'Privacidad',
    version: 'Versión',

    searchP: 'Buscar en el Cosmere…',
    cancel: 'Cancelar',
    recent: 'Reciente',
    results: 'resultados',

    tHome: 'Inicio', tWorlds: 'Mundos', tLibrary: 'Biblioteca', tForum: 'Foro', tMe: 'Yo',
  },
};

const WH_LOCALE = { current: 'es', subs: new Set() };
function useLocale() {
  const [, force] = React.useReducer(x => x + 1, 0);
  React.useEffect(() => {
    WH_LOCALE.subs.add(force);
    return () => WH_LOCALE.subs.delete(force);
  }, []);
  return {
    lang: WH_LOCALE.current,
    t: WH_STRINGS[WH_LOCALE.current],
    setLang: (l) => { WH_LOCALE.current = l; WH_LOCALE.subs.forEach(f => f()); },
  };
}

Object.assign(window, { WH_STRINGS, WH_LOCALE, useLocale });
