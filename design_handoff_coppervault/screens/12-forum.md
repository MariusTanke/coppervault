# 12 · Forum

## Layout
- Header row: left block (`CVKicker(s.gathering)` + Display `s.forum`) + right 38×38 Aurum square button with `+` glyph (shadow 0 4 14 aurum/30%)
- Filter row (horizontal scroll): `s.filters` — first chip filled Aurum, others outlined
- Thread list. Each row:
  - `CVChip(world)` + mono dot + tag uppercase
  - Title (Display 16, up to 2 lines)
  - Meta: (if spoiler ≠ None) `CVSpoilerStrip(spoiler)` + "▲ {votes}  ·  {replies} {s.repliesL}  ·  {author} · {time}"
  - Pinned threads have `Aurum/4%` background + star glyph top-right

## Seed
6 threads (see design-reference/screens-social.jsx).
