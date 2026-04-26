# 04 · Forgot password

## Purpose
Send a reset missive.

## Layout
- `AuthShell`
- Back row: back glyph + `CVKicker(s.back)`
- Title: `s.lost` (`Parchment`) + linebreak + `s.betweenWorlds` (italic `Aurum`) — 30 sp
- Body: `s.forgotBody` (13 sp, `Fog`, line-height 1.6)
- `CVInput` `s.email` icon mail
- CTA: `CVButton(s.sendMissive, Primary, size=L)`
- Bottom-anchored `CVEpigraph` with `s.journeyBefore`, size 11

## Behavior
- Submits email, shows success toast + returns to login
