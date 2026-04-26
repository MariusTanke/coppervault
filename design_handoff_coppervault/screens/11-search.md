# 11 · Global search

## Purpose
Unified entity search across worlds, characters, magic, timeline, books, threads.

## Layout
- Header row: `CVInput(s.searchP, value="hoid", icon=search-Aurum, height=40)` + right text button `s.cancel` (Aurum 11 sp)
- Section: `CVKicker(s.recent)` + chip row (`Hoid`, `Surgebinding`, `Atium`, `Nightblood`)
- Section: `CVKicker("5 " + s.results)`
- Result rows (gap 10):
  - `CVChip(type, Aurum)` — Character / Magic / Forum / World
  - Name (Display 15)
  - Sub (mono 9 `Ash`)

## Behavior
- Debounced 300 ms input → query all repositories in parallel
- Empty query shows Recent list
- Back button clears query + returns
