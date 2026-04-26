# 05 · Home feed

## Purpose
Personalized landing after auth. Today's page + continue reading + forum highlights.

## Layout (scrollable)
### Header strip (54 dp top pad)
- `CVKicker` "Home · Abyssal Light" (9 sp, `Ash`, letter-spacing 3)
- Display "Good storms" greeting 28 sp
- Meta row: `CVChip` Roshar + time "Since last: 2 days"

### Today's Page card
- Full-width card, `Ink` bg, 2 dp `Roshar` accent border-left
- `CVChip(Roshar)` row
- `CVKicker(s.todaysPage)` 9 sp, `Aurum`, letter-spacing 3
- Display L: `s.rhythmOf` + italic Aurum `s.lostLight` — 22 sp
- Body: `s.rhythmBody` — 12 sp `Fog`
- Meta: `CVSpoilerStrip("Era 2")` + mono "· 6 MIN READ"

### Continue section
- Section header: `CVKicker(s.continueL)` + right-aligned `s.seeAll` (9 sp `Ash`)
- Horizontal row of 3 reading cards (book cover + title + % progress bar)

### From the Forum section
- Section header: `CVKicker(s.fromForum)`
- 2-3 thread rows (title, world chip, meta: "238 REPLIES")

### Tab bar
`CVTabBar(active = home)` — glass blur 14, Aurum dot on active

## Behavior
- Pull-to-refresh refetches feed
- Each card click → detail route
- Tab bar bottom-anchored, 54 dp safe area bottom
