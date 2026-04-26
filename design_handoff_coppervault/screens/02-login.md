# 02 · Login

## Purpose
Return traveler enters. Email/username + password + social + remember me.

## Layout
- `AuthShell` (mist bg + faint 4 bg rings at 25% opacity)
- Top header block (centered):
  - `CVCosmereMark` 44 dp
  - `CVWordmark` 26 sp
  - `CVKicker` `s.openPortal`
- Form (gap 12 dp):
  - `CVInput` — placeholder `s.emailOrUser`, icon `mail`, prefilled "kaladin@shattered.plain" in mock (real: empty)
  - `CVInput` — placeholder `s.password`, icon `lock`, `isPassword = true`
  - Row: checkbox + `s.remember` (left) · text button `s.forgot` (right, Aurum)
- Primary CTA full-width: `CVButton(s.enterCosmere, Primary, size=L)`
- Divider with `s.orHopVia` inline
- `SocialRow` — two 44 dp outline buttons: Google (G glyph) + Apple ( glyph)
- Bottom-anchored text: `s.newTraveler` + Aurum `s.beginJourney` link → Register

## Behavior
- Validates on submit: email/username non-empty + password ≥ 8 chars
- Show `CVInput` error state (Nalthis red border-left) with inline hint
- Loading: disable CTA, replace label with spinning ring 14 dp
- Success: clear stack, `nav -> /home`

## State
- `email: String`, `password: String`, `remember: Boolean`, `loading: Boolean`, `error: String?`
