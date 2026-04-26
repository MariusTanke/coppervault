# 06 · Worlds atlas (★ signature screen)

## Purpose
Explore the 8 known Cosmere worlds. Starfield rail + focused-planet detail panel + list. Hero of the app.

## Layout
### Header (54 dp top)
- `CVKicker(s.atlas)`
- Display: `s.theCosmere` + italic Aurum `s.cosmereItalic` — 28 sp
- Sub: `s.atlasSub` 11 sp `Fog`

### Starfield rail (280 dp tall)
- Background: radial Aurum 8% centered + blue 6% bottom + `Void`
- 40 seeded stars
- Horizontal `LazyRow` with `rememberSnapFlingBehavior`
- Cell width: **180 dp**, `scrollSnapAlign = center`
- Dashed orbital line crosses horizontally at center (0.5 dp, Aurum 40%, dashed 3/3)
- Each cell:
  - Active state: planet 56 dp + halo radial + 2 concentric dashed rings (90 / 130 dp) + glow shadow 24 `accent80`
  - Inactive: planet 28 dp + subtle shadow
  - Under the planet: world name in mono, 10 sp active / 8 sp inactive, accent color when active
- Hint at bottom: "← TAP A WORLD →" 8 sp mono 60%

### Focused world panel
- `Ink` bg with `accent/8%` gradient fade, 2 dp `accent` border-left
- `CVKicker(world.sub, accent)`
- Display world.name 26 sp
- 2-col grid 90 dp / 1fr, gap 8×14:
  - SHARDS | "Honor · Cultivation · Odium"
  - MAGIC  | "Surgebinding"
  - BOOKS  | "5"
- Ghost button "Roshar →" → navigate to world detail (v2)

### All worlds list
- Section header `CVKicker(s.allWorlds)`
- 8 rows of: world dot 28 dp + name (display 14) + subtitle mono 8 sp + chevron
- Active row has `Aurum/6%` bg

## Interaction
- Tap any rail cell OR list row → smooth `animateScrollTo(i * 180 - 90 + 40)` + update focus
- Detail panel + list re-render on focus change via `animate*AsState`

## Data
`world` rows for: Roshar, Scadrial, Nalthis, Sel, Taldain, Threnody, First (of the Sun), Yolen.
