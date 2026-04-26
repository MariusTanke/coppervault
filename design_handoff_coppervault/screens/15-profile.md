# 15 · Profile / Me

## Layout
- Top hero (centered column, 80 dp top pad):
  - 72 dp avatar (radial Aurum gradient, 0.5 dp Aurum border)
  - Display name "Hoid" 26 sp
  - Order/title mono "The King's Wit" (Aurum)
  - Italic quote `s.quoteHoid` (Display italic 13, `Fog`, centered, max 240 dp)
- Stats row (3 cells): `s.booksRead/17`, `s.worldsVisited/6`, `s.threads/42`
  - Each cell: value (Display 24, Aurum) / label (mono 9, `Ash` uppercase)
- Section `CVKicker(s.worldProgress)`: 8 rows (one per world) with name + % progress bar (accent fill)
- Section `CVKicker(s.sealsEarned)`: chip row of `s.seals` (outline, Aurum text)
