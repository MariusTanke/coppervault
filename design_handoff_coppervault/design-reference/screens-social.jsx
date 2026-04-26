// Worldhopper — Forum, Notes, Profile, Settings, Search, Onboarding

// ─── Forum list ───
function ForumScreen() {
  const { t } = useLocale();
  const threads = [
    { title: 'Hoid\'s true objective across the Cosmere', tag: 'Theories', world: 'Cosmere', replies: 247, votes: 412, author: 'stormwarden', time: '2h', spoiler: 'Era 2 · SA5', pinned: true },
    { title: 'Weekly discussion: Stormlight 5 finale reactions', tag: 'Discussion', world: 'Roshar', replies: 1204, votes: 892, author: 'bridgefour_', time: '4h', spoiler: 'SA5' },
    { title: 'Is Harmony compromised? [Lost Metal spoilers]', tag: 'Theories', world: 'Scadrial', replies: 89, votes: 156, author: 'allomancer_22', time: '8h', spoiler: 'Era 2 finale' },
    { title: 'Warbreaker re-read — chapter 12 breakdown', tag: 'Re-read', world: 'Nalthis', replies: 34, votes: 67, author: 'breath_hoarder', time: '1d', spoiler: 'Warbreaker' },
    { title: 'Fan art: a portrait of Dalinar Kholin', tag: 'Art', world: 'Roshar', replies: 52, votes: 289, author: 'stormlight_ink', time: '1d', spoiler: 'None' },
    { title: 'AonDor geometry: mathematical analysis', tag: 'Lore', world: 'Sel', replies: 21, votes: 78, author: 'elantris_scholar', time: '2d', spoiler: 'Elantris' },
  ];
  return (
    <div style={{ background: WH.abyss, height: '100%', overflow: 'hidden', display: 'flex', flexDirection: 'column' }}>
      <div style={{ padding: '54px 20px 14px', display: 'flex', justifyContent: 'space-between', alignItems: 'flex-end', borderBottom: `0.5px solid ${WH.stone}` }}>
        <div>
          <WHKicker color={WH.ash} size={9} style={{ letterSpacing: 3 }}>{t.gathering}</WHKicker>
          <div style={{ marginTop: 4, fontFamily: WH.fontDisplay, fontSize: 28, fontWeight: 400, color: WH.parchment, letterSpacing: -0.4 }}>{t.forum}</div>
        </div>
        <div style={{
          width: 38, height: 38, background: WH.aurum,
          display: 'flex', alignItems: 'center', justifyContent: 'center',
          boxShadow: `0 4px 14px rgba(201,166,107,0.3)`,
        }}>{WHIcon.plus(WH.void)}</div>
      </div>

      {/* filter row */}
      <div style={{ display: 'flex', gap: 8, padding: '12px 20px', borderBottom: `0.5px solid ${WH.stone}`, overflowX: 'auto' }}>
        {t.filters.map((label, i) => (
          <div key={label} style={{
            padding: '5px 10px',
            fontFamily: WH.fontUI, fontSize: 10, fontWeight: 500,
            letterSpacing: 1, textTransform: 'uppercase',
            color: i === 0 ? WH.void : WH.fog,
            background: i === 0 ? WH.aurum : 'transparent',
            border: `0.5px solid ${i === 0 ? WH.aurum : WH.mist}`,
            whiteSpace: 'nowrap',
          }}>{label}</div>
        ))}
      </div>

      <div style={{ flex: 1, overflowY: 'auto', paddingBottom: 100 }}>
        {threads.map((th, i) => {
          const worldKey = Object.entries(WH.worlds).find(([_, v]) => v.name === th.world)?.[0];
          const accent = worldKey ? WH.worlds[worldKey].accent : WH.aurum;
          return (
            <div key={i} style={{
              padding: '14px 20px',
              borderBottom: `0.5px solid ${WH.stone}`,
              position: 'relative',
              background: th.pinned ? 'rgba(201,166,107,0.04)' : 'transparent',
            }}>
              {th.pinned && (
                <div style={{ position: 'absolute', top: 12, right: 14 }}>
                  {WHIcon.star(WH.aurum, WH.aurum)}
                </div>
              )}
              <div style={{ display: 'flex', gap: 8, alignItems: 'center', marginBottom: 6 }}>
                <WHChip color={accent}>{th.world}</WHChip>
                <span style={{ fontFamily: WH.fontMono, fontSize: 9, color: WH.ash, letterSpacing: 0.5 }}>· {th.tag.toUpperCase()}</span>
              </div>
              <div style={{ fontFamily: WH.fontDisplay, fontSize: 16, color: WH.parchment, lineHeight: 1.3, letterSpacing: -0.1, paddingRight: 24 }}>{th.title}</div>
              <div style={{ marginTop: 8, display: 'flex', alignItems: 'center', gap: 10, flexWrap: 'wrap' }}>
                {th.spoiler !== 'None' && <WHSpoilerStrip level={th.spoiler} color={accent}/>}
                <span style={{ fontFamily: WH.fontMono, fontSize: 9, color: WH.ash, letterSpacing: 0.5 }}>
                  ▲ {th.votes}  ·  {th.replies} {t.repliesL}  ·  {th.author} · {th.time}
                </span>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}

// ─── Forum thread detail ───
function ThreadScreen() {
  const { t } = useLocale();
  return (
    <div style={{ background: WH.abyss, height: '100%', overflow: 'hidden', display: 'flex', flexDirection: 'column' }}>
      <div style={{
        padding: '54px 20px 12px', display: 'flex', alignItems: 'center', gap: 12,
        borderBottom: `0.5px solid ${WH.stone}`,
      }}>
        {WHIcon.back(WH.fog)}
        <div style={{ flex: 1 }}>
          <WHKicker size={9} color={WH.worlds.roshar.accent}>Roshar · Theories</WHKicker>
        </div>
        {WHIcon.more(WH.fog)}
      </div>

      <div style={{ flex: 1, overflowY: 'auto', paddingBottom: 90 }}>
        {/* OP */}
        <div style={{ padding: '16px 20px', borderBottom: `0.5px solid ${WH.stone}` }}>
          <div style={{ fontFamily: WH.fontDisplay, fontSize: 20, color: WH.parchment, lineHeight: 1.25, letterSpacing: -0.3 }}>
            Hoid's true objective across the Cosmere
          </div>
          <div style={{ marginTop: 10, display: 'flex', alignItems: 'center', gap: 10 }}>
            <div style={{ width: 28, height: 28, borderRadius: '50%', background: `radial-gradient(circle at 30% 30%, ${WH.aurumHi}, ${WH.aurumLo})`, border: `0.5px solid ${WH.aurum}` }}/>
            <div>
              <div style={{ fontFamily: WH.fontUI, fontSize: 12, color: WH.linen, fontWeight: 500 }}>stormwarden</div>
              <div style={{ fontFamily: WH.fontMono, fontSize: 9, color: WH.ash, letterSpacing: 0.5 }}>Elsecaller · 4th Ideal · 2h ago</div>
            </div>
          </div>
          <div style={{ marginTop: 12 }}>
            <WHSpoilerStrip level="Era 2 · SA5"/>
          </div>
          <div style={{ marginTop: 14, fontFamily: WH.fontUI, fontSize: 13, color: WH.linen, lineHeight: 1.65 }}>
            Across every book, he's present — the King's Wit, the beggar flutist, the storyteller. I've been tracking his appearances and I believe his goal isn't simply to oppose Odium, but to <span style={{ color: WH.aurum }}>reassemble something lost</span>. Consider his behaviour at the end of Rhythm of War…
          </div>
          <div style={{ marginTop: 14, display: 'flex', gap: 20, alignItems: 'center' }}>
            <span style={{ fontFamily: WH.fontMono, fontSize: 11, color: WH.aurum, letterSpacing: 0.5 }}>▲ 412</span>
            <span style={{ fontFamily: WH.fontMono, fontSize: 11, color: WH.ash, letterSpacing: 0.5 }}>247 replies</span>
            <div style={{ flex: 1 }}/>
            <div style={{ color: WH.ash }}>{WHIcon.heart(WH.ash)}</div>
          </div>
        </div>

        {/* replies */}
        {[
          { a: 'bridgefour_', r: 'Windrunner · 3rd Ideal', t: '1h', msg: 'The Liar of Partinel fragment suggests this very thing. He had Breaths in Warbreaker…', votes: 89 },
          { a: 'allomancer_22', r: 'Misting · Steel', t: '45m', msg: 'Counterpoint: he explicitly tells Dalinar he can\'t help. If he\'s assembling something, it\'s indirect.', votes: 34 },
        ].map((reply, i) => (
          <div key={i} style={{ padding: '14px 20px 14px 32px', borderBottom: `0.5px solid ${WH.stone}`, position: 'relative' }}>
            <div style={{ position: 'absolute', left: 20, top: 14, bottom: 14, width: 1, background: WH.stone }}/>
            <div style={{ display: 'flex', alignItems: 'center', gap: 8, marginBottom: 6 }}>
              <div style={{ width: 20, height: 20, borderRadius: '50%', background: WH.slate, border: `0.5px solid ${WH.mist}` }}/>
              <span style={{ fontFamily: WH.fontUI, fontSize: 11, color: WH.linen, fontWeight: 500 }}>{reply.a}</span>
              <span style={{ fontFamily: WH.fontMono, fontSize: 9, color: WH.ash }}>{reply.t}</span>
            </div>
            <div style={{ fontFamily: WH.fontUI, fontSize: 12, color: WH.linen, lineHeight: 1.55 }}>{reply.msg}</div>
            <div style={{ marginTop: 8, fontFamily: WH.fontMono, fontSize: 10, color: WH.aurum, letterSpacing: 0.5 }}>▲ {reply.votes}  ·  reply</div>
          </div>
        ))}
      </div>

      {/* reply bar */}
      <div style={{
        position: 'absolute', bottom: 0, left: 0, right: 0, zIndex: 20,
        padding: '10px 14px 28px',
        background: 'rgba(10,12,18,0.95)',
        backdropFilter: 'blur(14px)', WebkitBackdropFilter: 'blur(14px)',
        borderTop: `0.5px solid ${WH.stone}`,
        display: 'flex', gap: 8, alignItems: 'center',
      }}>
        <div style={{ flex: 1, height: 38, border: `0.5px solid ${WH.mist}`, padding: '0 12px', display: 'flex', alignItems: 'center', fontFamily: WH.fontUI, fontSize: 12, color: WH.ash }}>
          {t.replyHint}
        </div>
        <div style={{ width: 38, height: 38, background: WH.aurum, display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
          <svg width="14" height="14" viewBox="0 0 14 14"><path d="M1 7L13 1L9 13L7 8L1 7Z" fill={WH.void}/></svg>
        </div>
      </div>
    </div>
  );
}

// ─── Notes / theories ───
function NotesScreen() {
  const { t } = useLocale();
  const notes = [
    { title: 'Shardworld linkages', tag: 'Theory', preview: 'Map of documented worldhopper appearances. Cross-reference with Invested-object transfers…', date: '22 Sep', color: WH.aurum },
    { title: 'Stormlight vocabulary', tag: 'Glossary', preview: 'Windrunner, Skybreaker, Edgedancer… ordered by the surges they share.', date: '18 Sep', color: WH.worlds.roshar.accent },
    { title: 'Metal atium — duplicate?', tag: 'Question', preview: 'If atium is an allomantic alloy of Ruin\'s bead, what happened after…', date: '14 Sep', color: WH.worlds.scadrial.accent },
    { title: 'Warbreaker re-read notes', tag: 'Chapter', preview: 'Chapter 1: Vivenna/Siri contrast established through clothing & ritual.', date: '11 Sep', color: WH.worlds.nalthis.accent },
  ];
  return (
    <div style={{ background: WH.abyss, height: '100%', overflow: 'hidden', display: 'flex', flexDirection: 'column' }}>
      <div style={{ padding: '54px 20px 14px', display: 'flex', justifyContent: 'space-between', alignItems: 'flex-end', borderBottom: `0.5px solid ${WH.stone}` }}>
        <div>
          <WHKicker color={WH.ash} size={9} style={{ letterSpacing: 3 }}>{t.marginalia}</WHKicker>
          <div style={{ marginTop: 4, fontFamily: WH.fontDisplay, fontSize: 28, fontWeight: 400, color: WH.parchment, letterSpacing: -0.4 }}>{t.notes}</div>
        </div>
        <div style={{ width: 38, height: 38, background: WH.aurum, display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
          {WHIcon.plus(WH.void)}
        </div>
      </div>

      <div style={{ flex: 1, overflowY: 'auto', padding: '16px 20px', paddingBottom: 100, display: 'flex', flexDirection: 'column', gap: 12 }}>
        {notes.map((n, i) => (
          <div key={i} style={{
            padding: 16,
            background: WH.ink,
            border: `0.5px solid ${WH.stone}`,
            borderLeft: `2px solid ${n.color}`,
            position: 'relative',
          }}>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'baseline', marginBottom: 6 }}>
              <WHKicker size={9} color={n.color} style={{ letterSpacing: 2 }}>{n.tag}</WHKicker>
              <span style={{ fontFamily: WH.fontMono, fontSize: 9, color: WH.ash, letterSpacing: 0.5 }}>{n.date}</span>
            </div>
            <div style={{ fontFamily: WH.fontDisplay, fontSize: 17, color: WH.parchment, lineHeight: 1.2, letterSpacing: -0.2 }}>{n.title}</div>
            <div style={{ marginTop: 6, fontFamily: WH.fontUI, fontSize: 12, color: WH.fog, lineHeight: 1.55 }}>{n.preview}</div>
          </div>
        ))}
      </div>
    </div>
  );
}

// ─── Profile ───
function ProfileScreen() {
  const { t } = useLocale();
  return (
    <div style={{ background: WH.abyss, height: '100%', overflow: 'hidden', display: 'flex', flexDirection: 'column' }}>
      <div style={{ flex: 1, overflowY: 'auto', paddingBottom: 100 }}>
        {/* header */}
        <div style={{
          padding: '54px 20px 24px',
          background: `radial-gradient(ellipse at 50% 0%, rgba(201,166,107,0.15), transparent 70%), ${WH.abyss}`,
          borderBottom: `0.5px solid ${WH.stone}`,
        }}>
          <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
            <div style={{ color: WH.fog }}>{WHIcon.gear(WH.fog)}</div>
          </div>
          <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', gap: 10, marginTop: 4 }}>
            <div style={{
              width: 84, height: 84, position: 'relative',
            }}>
              <div style={{
                width: 84, height: 84,
                background: `radial-gradient(circle at 30% 30%, ${WH.aurumHi}, ${WH.aurumLo} 70%, ${WH.void})`,
                border: `0.5px solid ${WH.aurum}`,
                display: 'flex', alignItems: 'center', justifyContent: 'center',
                fontFamily: WH.fontDisplay, fontSize: 36, color: WH.void, fontStyle: 'italic', fontWeight: 500,
              }}>H</div>
              {/* cosmere ring */}
              <div style={{ position: 'absolute', inset: -10 }}>
                <WHCosmereMark size={104} color={WH.aurum} stroke={0.5} dots={8}/>
              </div>
            </div>
            <div style={{ fontFamily: WH.fontDisplay, fontSize: 22, color: WH.parchment, marginTop: 14, letterSpacing: -0.2 }}>
              hoid_the_wit
            </div>
            <div style={{ fontFamily: WH.fontMono, fontSize: 10, color: WH.aurum, letterSpacing: 1.5, textTransform: 'uppercase' }}>
              Elsecaller · 4th Ideal
            </div>
            <div style={{ fontFamily: WH.fontDisplay, fontStyle: 'italic', fontSize: 13, color: WH.fog, textAlign: 'center', maxWidth: 240, marginTop: 6, lineHeight: 1.5 }}>
              {t.quoteHoid}
            </div>
          </div>

          {/* stats */}
          <div style={{ display: 'flex', justifyContent: 'space-around', marginTop: 24 }}>
            {[
              [t.booksRead, '17'],
              [t.worldsVisited, '6'],
              [t.threads, '42'],
            ].map(([l, v]) => (
              <div key={l} style={{ textAlign: 'center' }}>
                <div style={{ fontFamily: WH.fontDisplay, fontSize: 22, color: WH.parchment, lineHeight: 1 }}>{v}</div>
                <div style={{ marginTop: 4, fontFamily: WH.fontMono, fontSize: 9, color: WH.ash, letterSpacing: 1, textTransform: 'uppercase' }}>{l}</div>
              </div>
            ))}
          </div>
        </div>

        {/* reading progress per world */}
        <div style={{ padding: '18px 20px 10px' }}>
          <WHKicker color={WH.linen}>{t.worldProgress}</WHKicker>
        </div>
        <div style={{ padding: '0 20px', display: 'flex', flexDirection: 'column', gap: 10 }}>
          {[
            ['roshar',   68],
            ['scadrial', 54],
            ['nalthis',  100],
            ['sel',      100],
            ['taldain',  0],
          ].map(([k, p]) => {
            const w = WH.worlds[k];
            return (
              <div key={k} style={{ display: 'flex', alignItems: 'center', gap: 12 }}>
                <div style={{ width: 70, fontFamily: WH.fontUI, fontSize: 11, color: WH.linen }}>{w.name}</div>
                <div style={{ flex: 1, height: 1, background: WH.stone }}>
                  <div style={{ width: `${p}%`, height: 1, background: w.accent }}/>
                </div>
                <div style={{ width: 30, fontFamily: WH.fontMono, fontSize: 10, color: WH.ash, textAlign: 'right' }}>{p}%</div>
              </div>
            );
          })}
        </div>

        {/* achievements */}
        <div style={{ padding: '22px 20px 10px' }}>
          <WHKicker color={WH.linen}>{t.sealsEarned}</WHKicker>
        </div>
        <div style={{ padding: '0 20px', display: 'flex', gap: 10, flexWrap: 'wrap' }}>
          {t.seals.map((a, i) => (
            <div key={a} style={{
              padding: '8px 10px 10px',
              border: `0.5px solid ${WH.mist}`,
              display: 'flex', flexDirection: 'column', alignItems: 'center', gap: 6,
              width: 74,
              background: i < 3 ? 'rgba(201,166,107,0.06)' : 'transparent',
              opacity: i < 3 ? 1 : 0.45,
            }}>
              <WHCosmereMark size={28} color={i < 3 ? WH.aurum : WH.ash} dots={6}/>
              <div style={{ fontFamily: WH.fontMono, fontSize: 8, color: i < 3 ? WH.aurum : WH.ash, letterSpacing: 0.5, textTransform: 'uppercase', textAlign: 'center' }}>{a}</div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

// ─── Settings ───
function SettingsScreen() {
  const { t } = useLocale();
  const group = (header, rows) => (
    <div style={{ marginBottom: 22 }}>
      <WHKicker color={WH.ash} style={{ padding: '0 20px 8px' }}>{header}</WHKicker>
      <div style={{ background: WH.ink, borderTop: `0.5px solid ${WH.stone}`, borderBottom: `0.5px solid ${WH.stone}` }}>
        {rows.map((r, i) => (
          <div key={i} style={{
            display: 'flex', alignItems: 'center', gap: 14,
            padding: '14px 20px',
            borderBottom: i < rows.length - 1 ? `0.5px solid ${WH.stone}` : 'none',
          }}>
            <div style={{ color: WH.fog, width: 20 }}>{r.icon}</div>
            <div style={{ flex: 1, fontFamily: WH.fontUI, fontSize: 13, color: WH.linen }}>{r.label}</div>
            {r.value && <span style={{ fontFamily: WH.fontMono, fontSize: 10, color: WH.ash, letterSpacing: 0.5 }}>{r.value}</span>}
            {r.toggle !== undefined && (
              <div style={{
                width: 36, height: 20, borderRadius: 100,
                background: r.toggle ? WH.aurum : WH.stone,
                position: 'relative', transition: '0.2s',
              }}>
                <div style={{
                  position: 'absolute', top: 2, left: r.toggle ? 18 : 2,
                  width: 16, height: 16, borderRadius: '50%', background: WH.parchment,
                  transition: '0.2s',
                }}/>
              </div>
            )}
            {r.chev && WHIcon.chev(WH.ash)}
          </div>
        ))}
      </div>
    </div>
  );
  return (
    <div style={{ background: WH.abyss, height: '100%', overflow: 'hidden', display: 'flex', flexDirection: 'column' }}>
      <div style={{ padding: '54px 20px 14px', borderBottom: `0.5px solid ${WH.stone}` }}>
        <WHKicker color={WH.ash} size={9} style={{ letterSpacing: 3 }}>{t.arrangements}</WHKicker>
        <div style={{ marginTop: 4, fontFamily: WH.fontDisplay, fontSize: 28, fontWeight: 400, color: WH.parchment, letterSpacing: -0.4 }}>{t.settings}</div>
      </div>
      <div style={{ flex: 1, overflowY: 'auto', paddingTop: 16, paddingBottom: 100 }}>
        {group(t.account, [
          { icon: WHIcon.user(WH.fog),  label: t.profile,         chev: true },
          { icon: WHIcon.mail(WH.fog),  label: t.email,           value: 'hoid@wit.co', chev: true },
          { icon: WHIcon.lock(WH.fog),  label: t.password,        chev: true },
        ])}
        {group(t.spoilers, [
          { icon: WHIcon.warn(WH.fog),  label: t.spoilerLevel,    value: 'Era 2 · SA5', chev: true },
          { icon: WHIcon.spark(WH.fog), label: t.hideByDefault,   toggle: true },
          { icon: WHIcon.book(WH.fog),  label: t.markRead,        chev: true },
        ])}
        {group(t.appearance, [
          { icon: WHIcon.globe(WH.fog), label: t.theme,           value: t.themeAbyssal, chev: true },
          { icon: WHIcon.spark(WH.fog), label: t.worldAccents,    toggle: true },
          { icon: WHIcon.note(WH.fog),  label: t.epigraphs,       toggle: true },
        ])}
        {group(t.about, [
          { icon: WHIcon.note(WH.fog),  label: t.compact,         chev: true },
          { icon: WHIcon.warn(WH.fog),  label: t.privacy,         chev: true },
          { icon: WHIcon.clock(WH.fog), label: t.version,         value: '1.0.0' },
        ])}
      </div>
    </div>
  );
}

// ─── Global search ───
function SearchScreen() {
  const { t } = useLocale();
  const recents = ['Hoid', 'Surgebinding', 'Atium', 'Nightblood'];
  const results = [
    { type: 'Character', name: 'Hoid', sub: 'The Wit · Worldhopper', w: 'first' },
    { type: 'Book',      name: 'Words of Radiance', sub: 'Stormlight Archive · 2', w: 'roshar' },
    { type: 'Magic',     name: 'Surgebinding', sub: 'Ten surges · Stormlight', w: 'roshar' },
    { type: 'Forum',     name: "Hoid's true objective across the Cosmere", sub: '247 replies · 2h', w: 'roshar' },
    { type: 'World',     name: 'Roshar', sub: 'Stormlight Archive', w: 'roshar' },
  ];
  return (
    <div style={{ background: WH.abyss, height: '100%', overflow: 'hidden', display: 'flex', flexDirection: 'column' }}>
      <div style={{ padding: '54px 20px 12px', display: 'flex', gap: 10, alignItems: 'center', borderBottom: `0.5px solid ${WH.stone}` }}>
        <div style={{ flex: 1 }}>
          <WHInput placeholder={t.searchP} value="hoid" icon={WHIcon.search(WH.aurum)} style={{ height: 40 }}/>
        </div>
        <span style={{ fontFamily: WH.fontUI, fontSize: 11, color: WH.aurum, letterSpacing: 0.5 }}>{t.cancel}</span>
      </div>

      <div style={{ flex: 1, overflowY: 'auto', paddingBottom: 100 }}>
        <div style={{ padding: '12px 20px 6px' }}>
          <WHKicker color={WH.ash}>{t.recent}</WHKicker>
        </div>
        <div style={{ padding: '4px 20px 10px', display: 'flex', gap: 6, flexWrap: 'wrap' }}>
          {recents.map(r => (
            <div key={r} style={{
              padding: '5px 10px',
              border: `0.5px solid ${WH.mist}`,
              fontFamily: WH.fontUI, fontSize: 11, color: WH.linen,
            }}>{r}</div>
          ))}
        </div>
        <WHLine color={WH.stone}/>

        <div style={{ padding: '14px 20px 6px' }}>
          <WHKicker color={WH.linen}>5 {t.results}</WHKicker>
        </div>
        {results.map((r, i) => {
          const w = WH.worlds[r.w];
          return (
            <div key={i} style={{
              padding: '12px 20px',
              borderBottom: `0.5px solid ${WH.stone}`,
              display: 'flex', alignItems: 'center', gap: 12,
            }}>
              <div style={{
                width: 34, height: 34, flexShrink: 0,
                background: `radial-gradient(circle, ${w.accent}30, transparent 70%)`,
                border: `0.5px solid ${w.accent}`,
                display: 'flex', alignItems: 'center', justifyContent: 'center',
                fontFamily: WH.fontMono, fontSize: 8, color: w.accent, letterSpacing: 0.5,
              }}>{r.type.slice(0, 3).toUpperCase()}</div>
              <div style={{ flex: 1 }}>
                <div style={{ fontFamily: WH.fontDisplay, fontSize: 14, color: WH.parchment, lineHeight: 1.25 }}>{r.name}</div>
                <div style={{ marginTop: 2, fontFamily: WH.fontUI, fontSize: 11, color: WH.fog }}>{r.sub}</div>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}

Object.assign(window, { ForumScreen, ThreadScreen, NotesScreen, ProfileScreen, SettingsScreen, SearchScreen });
