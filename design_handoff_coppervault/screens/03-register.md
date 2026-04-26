# 03 · Register

## Purpose
New traveler creates an account.

## Layout
- `AuthShell`
- Back row at top: back glyph + `CVKicker(s.back)`
- Header block (left-aligned):
  - Display title: `s.beginYour` (`Parchment` 400) + line break + `s.journey` (italic `Aurum`) — 30 sp, letter-spacing -0.5
  - Body: `s.nameEtched` (12 sp, `Fog`, line-height 1.5)
- Form (gap 12):
  - `CVInput` `s.travelerName` icon user
  - `CVInput` `s.email` icon mail
  - `CVInput` `s.password` icon lock
  - `CVInput` `s.confirmPw` icon lock
- Notice block: `Aurum/6%` bg + `Aurum/25%` hairline border, warning glyph + `s.spoilerWarn` (11 sp, `Linen`, line-height 1.5)
- Row: filled checkbox (Aurum) + `s.acceptCompact` + Aurum `s.compact` link
- Primary CTA: `CVButton(s.openPortal, Primary, size=L)` full-width

## Behavior
- Validates: username 3-24 chars [a-z0-9_], email regex, password ≥ 8, confirm matches, compact accepted
- On success → `/home`
