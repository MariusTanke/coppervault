# 07 · Library

## Purpose
Reading tracker. Filter tabs + list of books with per-world accent covers.

## Layout
- Header: `CVKicker(s.theArchive)` + Display `s.library` 28 sp
- Filter tabs (scrollable row, no pill — just underline): `s.reading`, `s.toRead`, `s.finished`, `s.all` — Aurum underline on active
- Book list (`LazyColumn`), each row:
  - 52×76 cover: solid world accent gradient (from accent to accent·40%), book title set in Display italic across cover
  - Right: title (Display 15 sp), series+index (mono 9 sp), progress bar (1 dp track `Stone`, `accent` fill) + % text
  - Status tag (mono 8 sp): "✓ FINISHED" or "— UNREAD" depending on %

## Data
Seed 7 books: Way of Kings, Words of Radiance, Oathbringer, Rhythm of War (100%), Wind and Truth (current), Warbreaker (100%), Mistborn: Final Empire (42%).
