# 16 · Settings

## Layout
- Header: `CVKicker(s.arrangements)` + Display `s.settings`
- Sections (each with a header `CVKicker` + list rows):
  - `s.account`: `s.profile`, `s.email` (hoid@wit.co), `s.password`
  - `s.spoilers`: `s.spoilerLevel` ("Era 2 · SA5"), `s.hideByDefault` (toggle), `s.markRead`
  - `s.appearance`: `s.theme` (value "Abyssal"), `s.worldAccents` (toggle), `s.epigraphs` (toggle)
  - `s.about`: `s.compact`, `s.privacy`, `s.version` "1.0.0"
- Each row: left icon (16 dp, `Fog`) + label (UI 13) + right value (mono 10, `Ash`) OR toggle OR chevron
- Between sections: 18 dp gap + 0.5 dp `Stone` divider

## Language setting
Add a row in `s.appearance`: **Language / Idioma** with value Español / English → opens a bottom-sheet picker that calls `LocaleController.set(...)` and persists to DataStore.
