# 01 · Splash

## Purpose
First screen shown on cold start. Portal metaphor. Sets the tone (dark, mystical, literary) before auth.

## Layout (402×874, centered)
- **WHMistBg** full-bleed (`Abyss` → radial `Aurum` 6% at center → `Void` edges)
- 50 seeded stars (deterministic PRNG, not random per frame)
- 4 concentric `Aurum` rings expanding outward, each offset by 0.25 cycle of a 6s loop
- Centered column:
  - `CVCosmereMark` (72 dp, Aurum)
  - `CVWordmark` (32 sp: `Copper` 600 roman + `vault` 400 italic Aurum)
  - `CVKicker` (9 sp, letter-spacing 4, color `Fog`) — `s.tagline`
- Bottom quote: `CVEpigraph` with `s.splashQuote` + `s.splashAttr`
- Hairline loading bar: 60×1 dp, `Stone` track, `Aurum` 40% fill translated left→right

## Behavior
- Auto-advance to `/auth/login` after **2.5s** (or when a persisted session check completes)
- If session exists, replace with `/home` instead
- Honor system reduced-motion: skip ring animation, show static state

## State
None externally. Internal `time` state for ring phase. Collect auth-session flow in a `LaunchedEffect`.
