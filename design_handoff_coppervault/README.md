# Handoff: Coppervault — A Companion to the Cosmere

## Overview

**Coppervault** is a mobile app for Brandon Sanderson's Cosmere fandom:
encyclopedia (worlds, characters, magic systems, cosmic timeline) + reading
tracker + discussion forum with per-saga spoiler tagging + personal theory
notes. Target platforms: **Android + iOS** via **Kotlin Multiplatform +
Compose Multiplatform**. The app ships **Spanish and English** from day one.

Aesthetic direction: dark, mystical, literary. Deep blacks + mist + aurum
(liquid-metal gold) + parchment highlights. The interface is meant to feel
like an old leather grimoire lit by starlight. Each Cosmere world carries a
single subtle accent color.

## About the Design Files

The files in `design-reference/` are **design references created in HTML/JSX
(React + Babel, rendered in-browser)**. They are high-fidelity prototypes
showing intended look, copy, layout, and behavior. **They are not production
code.** Do not ship them; do not transpile them.

Your task: **recreate these designs in Kotlin Multiplatform + Compose
Multiplatform**, following the token values, typography, spacing, and
interaction patterns documented below. The HTML is the source of truth for
visual detail; this README is the source of truth for structure.

Open `design-reference/Coppervault.html` in a browser to see the full canvas
— every screen is rendered inside both an iOS and an Android frame side by
side. Use the ES/EN toggle in the top-right of the canvas to see both
locales.

## Fidelity

**High-fidelity (hifi).** Pixel-accurate colors, typography, spacing, and
transitions. Recreate pixel-perfectly in Compose using the tokens and
component specs below. Deviations should be justified by platform-native
affordances (e.g. a Compose `Modifier.pointerInput` equivalent of a JS
gesture), never by shortcutting the visual system.

## Product scope (v1)

1. **Entry sequence**: Splash → Login → Register → Forgot password.
2. **Main (5-tab bottom nav)**: Home, Worlds, Library, Forum, Me.
3. **Archive**: Characters, Magic systems, Cosmic timeline, Global search.
4. **Discourse & personal**: Thread detail, Notes/theories, Settings.

Total: **16 screens**. See `screens/` subfolder of this handoff for a
per-screen spec.

## Tech stack (recommended)

| Concern           | Library                                                        |
|-------------------|----------------------------------------------------------------|
| UI                | Compose Multiplatform 1.7+                                     |
| Navigation        | Voyager 1.1 (simpler) OR Decompose 3.x (more robust)           |
| i18n              | Lyricist (typed strings per locale)                            |
| Networking        | Ktor Client 3.x                                                |
| Persistence       | SQLDelight 2.x (or Room KMP when stable for your targets)      |
| Serialization     | kotlinx.serialization                                          |
| Async             | Coroutines + Flow                                              |
| DI                | Koin 4.x                                                       |
| Image loading     | Coil 3.x (KMP support)                                         |
| Fonts             | `composeResources/font/` + `FontFamily(Font(Res.font.xxx))`    |

## Project structure

```
composeApp/
├── commonMain/kotlin/co/coppervault/
│   ├── App.kt
│   ├── ui/
│   │   ├── theme/
│   │   │   ├── Color.kt          — tokens (Abyss, Ink, Aurum, Parchment…)
│   │   │   ├── Typography.kt     — EB Garamond / Inter / JetBrains Mono
│   │   │   ├── Worlds.kt         — WorldAccent map
│   │   │   └── CVTheme.kt
│   │   ├── components/
│   │   │   ├── CVButton.kt  CVInput.kt  CVChip.kt  CVKicker.kt
│   │   │   ├── CVSpoilerStrip.kt  CVEpigraph.kt  CVMistBg.kt
│   │   │   ├── CVCosmereMark.kt  CVWordmark.kt
│   │   │   ├── CVTabBar.kt  CVStatusBar.kt
│   │   │   └── CVIcons.kt
│   │   └── screens/
│   │       ├── splash/   auth/   home/   worlds/   library/
│   │       ├── characters/   magic/   timeline/   search/
│   │       ├── forum/   thread/   notes/   profile/   settings/
│   ├── navigation/CVNavGraph.kt
│   ├── i18n/Strings.kt
│   └── data/
│       ├── model/    repository/    remote/    local/
├── androidMain/…
└── iosMain/…
```

## Design tokens — use these EXACT values

### Palette

| Token           | Hex         | Usage                                        |
|-----------------|-------------|----------------------------------------------|
| `Void`          | `#05060A`   | Absolute black (under status bars)           |
| `Abyss`         | `#0A0C12`   | App background                               |
| `Ink`           | `#101218`   | Cards, list rows                             |
| `Stone`         | `#1A1D24`   | Dividers, hairlines                          |
| `Mist`          | `#2A2E38`   | Input borders, outlines                      |
| `Slate`         | `#3A3F4A`   | Disabled, skeleton fills                     |
| `Ash`           | `#6A7080`   | Tertiary text, meta                          |
| `Fog`           | `#9AA0B0`   | Secondary text                               |
| `Linen`         | `#C9C5B8`   | Body text on dark                            |
| `Parchment`     | `#E8E4D6`   | Titles, primary text                         |
| `Aurum` ⭐      | `#C9A66B`   | Brand accent (buttons, active, links)        |
| `AurumHi`       | `#E4C896`   | Aurum highlight / gradient stop              |
| `AurumLo`       | `#8C7143`   | Aurum shadow / gradient stop                 |

### World accents (single accent per world)

| World      | Hex       | Saga                     |
|------------|-----------|--------------------------|
| Roshar     | `#5B8ED1` | Stormlight Archive       |
| Scadrial   | `#B8704F` | Mistborn (Era 1 + 2)     |
| Nalthis    | `#C94B6E` | Warbreaker               |
| Sel        | `#6FA889` | Elantris                 |
| Taldain    | `#E0B84A` | White Sand               |
| Threnody   | `#8E7FA1` | Shadows for Silence      |
| First (of the Sun) | `#D98A4F` | Sixth of the Dusk |
| Yolen      | `#9A8C6E` | Pre-Shattering (origin)  |

Use a single accent as border-left (2px) and as chip fill. Never as
background for large areas.

### Typography

| Role       | Family                 | Weight  | Usage                                            |
|------------|------------------------|---------|--------------------------------------------------|
| Display    | **EB Garamond**        | 400/500 | Titles, book titles, literary emphasis (italic)  |
| UI         | **Inter**              | 400/500 | Body, buttons, labels                            |
| Mono       | **JetBrains Mono**     | 400/500 | Kickers, metadata, timestamps, ALL-CAPS chrome   |

Scales (sp):

- Display XL: 30 / -0.5 / 1.1
- Display L:  28 / -0.4 / 1.1
- Display M:  22 / -0.3 / 1.25
- Body:       13 / 0 / 1.55
- UI L:       12 / 0 / 1.4
- UI S:       11 / 0.2 / 1.4
- Kicker:     10 / 2 (letter-spacing) / uppercase
- Mono meta:  9  / 0.5–1 / often uppercase

Download fonts from Google Fonts (all OFL-licensed) and drop `.ttf` files
into `composeApp/src/commonMain/composeResources/font/`.

### Spacing

4-based scale: 4, 8, 10, 12, 14, 16, 20, 24, 28, 32, 40, 54.
Screen edges: **20 dp horizontal** on phone. Header top padding: **54 dp**
(under status bar + a generous breath line).

### Radius

Deliberately minimal. Default **0 dp** (hard edges) for cards and inputs.
Use:
- 0 dp — cards, inputs, chips, buttons
- 2 dp — hint-rounded secondary buttons
- 50% — avatars, world dots, mark

### Shadows

Almost none. The aesthetic is flat + hairline. One exception:
- Primary Aurum CTA: `0 4px 14px rgba(201,166,107,0.3)`

### Hairlines

Use **0.5 dp** borders in Compose (`BorderStroke(0.5.dp, Stone)`). These are
crisp on retina/xxhdpi and they are a core part of the visual language.

## Branding

### Wordmark

`World` (italic, weight 400) + `hopper` (roman, weight 600). Replace with:
`Copper` (roman, weight 600) + `vault` (italic, weight 400, Aurum). Display
font: EB Garamond.

> Exact typographic pairing is documented in `design-reference/design-system.jsx`
> (`WHWordmark` component). Port it to a Compose `Row { Text(...); Text(...) }`.

### Cosmere Mark (logo)

Original mark — safe to use. Two concentric circle strokes + 8 small filled
dots orbiting at 45° increments, all in Aurum. Implementation: Compose
`Canvas` with `drawCircle(style = Stroke)` + 8 `drawCircle` for the dots.
See the reference in `design-system.jsx` → `WHCosmereMark`.

## Internationalization

Ship **Spanish (default) + English**. A typed string map lives in
`design-reference/i18n.jsx` — port 1:1 to a Lyricist `Strings` data class.

**Rule**: UI chrome is translated. **In-universe proper nouns stay in
English** (Kaladin, Surgebinding, book titles, forum thread titles written
by "users"). This matches how Cosmere fandoms actually talk across locales.

## Components to port (atoms → molecules)

Every component below already exists in `design-reference/design-system.jsx`
as a JSX function. Names map directly (WH → CV).

| JSX             | Compose                          | Notes                                 |
|-----------------|----------------------------------|---------------------------------------|
| `WHButton`      | `CVButton`                        | variants: primary, ghost, outline     |
| `WHInput`       | `CVInput`                         | 44 dp min height, mono placeholder    |
| `WHChip`        | `CVChip`                          | world accent border-left              |
| `WHKicker`      | `CVKicker`                        | mono / uppercase / letter-spacing 2   |
| `WHSpoilerStrip`| `CVSpoilerStrip`                  | border + tiny warning glyph           |
| `WHEpigraph`    | `CVEpigraph`                      | italic quote, hairline above          |
| `WHMistBg`      | `CVMistBg`                        | radial gradient + 50 seeded stars     |
| `WHLine`        | `Divider(color = Stone, thickness = 0.5.dp)` | |
| `WHCosmereMark` | `CVCosmereMark` (Canvas)          | see spec above                        |
| `WHWordmark`    | `CVWordmark`                      | Row of two Text                       |
| `WHTabBar`      | `CVTabBar`                        | 5 items, Aurum active, glass blur     |
| `IOSStatusBar`  | handled by system on iOS          | don't fake it on real iOS             |

### Icons

A tiny custom set is declared in `WHIcon` (design-system.jsx). Recreate
these in Compose as `ImageVector` (SVG-like declarative path) or use
**Lucide icons** (`com.composables:icons-lucide`) which has close
equivalents: `search`, `mail`, `lock`, `user`, `bell`, `bookmark`, `star`,
`heart`, `clock`, `globe`, `warning-triangle`, `sparkles`. Keep stroke
weight ~1.25 dp for consistency with the hairline aesthetic.

## Screens — index

| # | Screen       | Route                  | Bottom tab | Spec                          |
|---|--------------|------------------------|------------|-------------------------------|
| 1 | Splash       | `/splash`              | —          | `screens/01-splash.md`        |
| 2 | Login        | `/auth/login`          | —          | `screens/02-login.md`         |
| 3 | Register     | `/auth/register`       | —          | `screens/03-register.md`      |
| 4 | Forgot       | `/auth/forgot`         | —          | `screens/04-forgot.md`        |
| 5 | Home         | `/home`                | home       | `screens/05-home.md`          |
| 6 | Worlds atlas | `/worlds`              | worlds     | `screens/06-worlds.md`        |
| 7 | Library      | `/library`             | library    | `screens/07-library.md`       |
| 8 | Characters   | `/archive/characters`  | —          | `screens/08-characters.md`    |
| 9 | Magic        | `/archive/magic`       | —          | `screens/09-magic.md`         |
| 10| Timeline     | `/archive/timeline`    | —          | `screens/10-timeline.md`      |
| 11| Search       | `/search`              | —          | `screens/11-search.md`        |
| 12| Forum        | `/forum`               | forum      | `screens/12-forum.md`         |
| 13| Thread       | `/forum/thread/:id`    | —          | `screens/13-thread.md`        |
| 14| Notes        | `/notes`               | —          | `screens/14-notes.md`         |
| 15| Profile      | `/me`                  | me         | `screens/15-profile.md`       |
| 16| Settings     | `/settings`            | —          | `screens/16-settings.md`      |

## Global behaviors

### Spoilers (critical)

Every forum thread carries a **spoiler tag** (enum): `None`, `Elantris`,
`Warbreaker`, `Era 1`, `Era 2`, `SA1`…`SA5`, `Secret Projects`. On thread
creation it's **required**. When listing, render `CVSpoilerStrip` if tag ≠
None. User preference (`hideByDefault`) blurs spoiler-tagged bodies until
tapped.

### Navigation model

- **Entry flow** is a linear stack (Splash → Login → Register/Forgot).
- After auth success, replace with **Main** scaffold (Scaffold + CVTabBar).
- Tab switches retain each tab's back stack independently (Voyager's
  `TabNavigator` does this for free).
- Deep links target world/character/thread pages.

### Locale toggle

- Setting in Profile/Settings screen.
- Persist to DataStore (androidMain) / NSUserDefaults (iosMain) via
  `expect/actual`.
- Default: device locale if `es` or `en`, else `en`.

### Animations

| Where             | What                                                |
|-------------------|-----------------------------------------------------|
| Splash            | 4 concentric rings expanding outward, 6s loop       |
| Tab switch        | Fade crossfade 180ms                                |
| Thread push       | Slide-in from right (iOS) / shared axis X (Android) |
| Worlds atlas      | Horizontal snap scroll + focused-planet halo pulse  |
| Sheet/modal       | Bottom sheet with scrim                             |

Use `rememberInfiniteTransition` for the splash rings; `animate*AsState` for
focused-planet size/glow; `AnimatedContent` for tab content crossfades.

## Data layer (v1)

v1 can run **offline-only** with a seeded SQLDelight DB. The content is
fandom-style reference material (worlds, books, characters, magic systems,
events) — ship a JSON seed in `composeResources/files/seed.json`, import
into SQLDelight on first run.

Schema (SQLDelight):

```sql
CREATE TABLE world (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    subtitle TEXT NOT NULL,
    accent_hex TEXT NOT NULL,
    shards TEXT,
    magic_system TEXT,
    book_count INTEGER NOT NULL
);

CREATE TABLE book (
    id TEXT PRIMARY KEY,
    title TEXT NOT NULL,
    world_id TEXT NOT NULL REFERENCES world(id),
    series TEXT,
    series_index INTEGER,
    pub_year INTEGER,
    cover_color_hex TEXT
);

CREATE TABLE read_progress (
    book_id TEXT PRIMARY KEY REFERENCES book(id),
    percent INTEGER NOT NULL DEFAULT 0,
    updated_at INTEGER NOT NULL
);

CREATE TABLE character (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    role TEXT,
    world_id TEXT NOT NULL REFERENCES world(id)
);

CREATE TABLE magic_system (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    world_id TEXT NOT NULL REFERENCES world(id),
    description TEXT NOT NULL,
    powers_json TEXT NOT NULL   -- serialized list of strings
);

CREATE TABLE timeline_event (
    id TEXT PRIMARY KEY,
    era TEXT NOT NULL,
    year TEXT,
    title TEXT NOT NULL,
    place TEXT,
    world_id TEXT REFERENCES world(id)
);

CREATE TABLE note (
    id TEXT PRIMARY KEY,
    title TEXT NOT NULL,
    tag TEXT NOT NULL,
    preview TEXT NOT NULL,
    body TEXT,
    world_id TEXT REFERENCES world(id),
    created_at INTEGER NOT NULL,
    updated_at INTEGER NOT NULL
);

-- Forum is local-only in v1 (drafts). Real backend = v2.
CREATE TABLE thread_draft (
    id TEXT PRIMARY KEY,
    title TEXT NOT NULL,
    body TEXT NOT NULL,
    spoiler_level TEXT NOT NULL,
    world_id TEXT REFERENCES world(id),
    updated_at INTEGER NOT NULL
);
```

v2 adds Ktor + a real API for Forum + sync.

## Implementation order (suggested)

1. **Theme + tokens + typography** — 1 day
2. **Atoms**: CVButton, CVInput, CVChip, CVKicker — 1 day
3. **Molecules**: CVSpoilerStrip, CVEpigraph, CVMistBg, CVCosmereMark,
   CVWordmark — 1 day
4. **Navigation scaffold + CVTabBar** — 1 day
5. **Splash + Auth flow** (Login/Register/Forgot) — 1.5 days
6. **Home** — 1 day
7. **Worlds atlas** (horizontal snap + focus panel) — 2 days
8. **Library + Characters + Magic + Timeline** — 3 days
9. **Forum list + Thread detail** — 2 days
10. **Notes + Search + Profile + Settings** — 2 days
11. **i18n wiring + locale persistence** — 0.5 day
12. **Data seed + SQLDelight** — 1 day
13. **Polish pass + dark status bars + edge-to-edge** — 1 day

**Total ≈ 17 dev-days.**

## Assets

- **Fonts**: EB Garamond, Inter, JetBrains Mono — all OFL (Google Fonts).
- **Icons**: Lucide icons (MIT) or hand-authored `ImageVector` set.
- **Logo/mark**: original, included as Compose `Canvas` code — no bitmap.
- **No third-party imagery** is referenced. Avoid scraping Sanderson
  publisher cover art for v1; use solid color covers with typography (as
  the mock does).

## Files in this bundle

```
design_handoff_coppervault/
├── README.md                          ← this file
├── screens/                           ← per-screen specs
│   ├── 01-splash.md
│   ├── 02-login.md
│   ├── 03-register.md
│   ├── 04-forgot.md
│   ├── 05-home.md
│   ├── 06-worlds.md
│   ├── 07-library.md
│   ├── 08-characters.md
│   ├── 09-magic.md
│   ├── 10-timeline.md
│   ├── 11-search.md
│   ├── 12-forum.md
│   ├── 13-thread.md
│   ├── 14-notes.md
│   ├── 15-profile.md
│   └── 16-settings.md
└── design-reference/                  ← OPEN Coppervault.html IN A BROWSER
    ├── Coppervault.html               ← start here
    ├── design-canvas.jsx
    ├── ios-frame.jsx
    ├── android-frame.jsx
    ├── design-system.jsx              ← port every WH* component
    ├── i18n.jsx                       ← port to Lyricist Strings
    ├── screens-auth.jsx
    ├── screens-main.jsx
    └── screens-social.jsx
```

## Notes on rebranding

The HTML reference files still use the working name **Worldhopper** in many
strings and identifiers. That name is not available on Play Store — we
changed it to **Coppervault**. When porting:

- Search/replace `Worldhopper` → `Coppervault` in all strings.
- Search/replace `WH` prefix → `CV` prefix in component names.
- Wordmark: `World` + `hopper` → `Copper` + `vault` (italic on second
  half, Aurum color).
- The Cosmere Mark glyph is unchanged — it's an original design and it
  continues to work visually with the new name.

## Open questions for the dev

1. **Auth backend** — Firebase Auth? Supabase? Custom? v1 can mock login.
2. **Forum backend** — v2 only; v1 ships with seeded threads (read-only).
3. **Content licensing** — confirm with legal that a Cosmere companion
   app doesn't conflict with Dragonsteel's IP guidelines. The safer
   framing is "a fan-made reading tracker & note-taking app for a
   connected-universe fandom" without branding that implies official
   affiliation.
4. **Analytics** — not in scope for handoff.
5. **Paywall** — not in scope for handoff.
