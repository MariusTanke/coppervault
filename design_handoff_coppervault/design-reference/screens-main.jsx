// Worldhopper — Main app screens (Home, Worlds, Library, Characters, Magic, Timeline)

// ─── Home feed ───
function HomeScreen() {
  const { t } = useLocale();
  return (
    <div style={{ background: WH.abyss, height: '100%', overflow: 'hidden', display: 'flex', flexDirection: 'column' }}>
      {/* top bar */}
      <div style={{
        padding: '54px 20px 14px', display: 'flex', alignItems: 'center', justifyContent: 'space-between',
        borderBottom: `0.5px solid ${WH.stone}`,
        background: 'linear-gradient(180deg, rgba(201,166,107,0.04) 0%, transparent 100%)',
      }}>
        <div style={{ display: 'flex', alignItems: 'center', gap: 10 }}>
          <WHCosmereMark size={22} color={WH.aurum}/>
          <WHWordmark size={17} tight/>
        </div>
        <div style={{ display: 'flex', gap: 14, color: WH.fog }}>
          {WHIcon.search(WH.fog)}
          <div style={{ position: 'relative' }}>
            {WHIcon.bell(WH.fog)}
            <div style={{ position: 'absolute', top: -1, right: -1, width: 6, height: 6, background: WH.aurum, borderRadius: '50%' }}/>
          </div>
        </div>
      </div>

      <div style={{ flex: 1, overflowY: 'auto', paddingBottom: 100 }}>
        {/* featured epigraph hero */}
        <div style={{
          margin: '16px 20px 0', padding: '22px 18px',
          background: `
            radial-gradient(ellipse at 80% 20%, rgba(91,142,209,0.15), transparent 60%),
            linear-gradient(180deg, ${WH.ink}, ${WH.abyss})
          `,
          border: `0.5px solid ${WH.stone}`,
          position: 'relative', overflow: 'hidden',
        }}>
          <div style={{ position: 'absolute', top: 10, right: 10 }}>
            <WHChip color={WH.worlds.roshar.accent}>Roshar</WHChip>
          </div>
          <WHKicker size={9} color={WH.aurum} style={{ letterSpacing: 3 }}>{t.todaysPage}</WHKicker>
          <div style={{ marginTop: 10, fontFamily: WH.fontDisplay, fontSize: 22, fontWeight: 400, color: WH.parchment, lineHeight: 1.25, letterSpacing: -0.3 }}>
            {t.rhythmOf} <span style={{ fontStyle: 'italic', color: WH.aurum }}>{t.lostLight}</span>
          </div>
          <div style={{ marginTop: 8, fontFamily: WH.fontUI, fontSize: 12, color: WH.fog, lineHeight: 1.5 }}>
            {t.rhythmBody}
          </div>
          <div style={{ marginTop: 14, display: 'flex', alignItems: 'center', gap: 10 }}>
            <WHSpoilerStrip level="Era 2"/>
            <span style={{ fontFamily: WH.fontMono, fontSize: 9, color: WH.ash, letterSpacing: 1 }}>· 6 {t.minRead}</span>
          </div>
        </div>

        {/* Continue reading */}
        <div style={{ padding: '22px 20px 10px', display: 'flex', justifyContent: 'space-between', alignItems: 'baseline' }}>
          <WHKicker color={WH.linen}>{t.continueL}</WHKicker>
          <WHKicker color={WH.ash} size={9}>{t.seeAll}</WHKicker>
        </div>
        <div style={{ padding: '0 20px', display: 'flex', gap: 12, overflow: 'hidden' }}>
          {[
            { t: 'Words of Radiance', w: 'roshar', p: 68 },
            { t: 'The Well of Ascension', w: 'scadrial', p: 34 },
            { t: 'Warbreaker', w: 'nalthis', p: 12 },
          ].map((b, i) => (
            <div key={i} style={{ width: 120, flexShrink: 0 }}>
              <WHPlaceholder label={b.t.split(' ')[0]} h={160} tint={WH.worlds[b.w].accent}/>
              <div style={{ marginTop: 8, fontFamily: WH.fontDisplay, fontSize: 13, color: WH.parchment, lineHeight: 1.2 }}>{b.t}</div>
              <div style={{ marginTop: 6, height: 1, background: WH.stone, position: 'relative' }}>
                <div style={{ position: 'absolute', left: 0, top: 0, height: 1, width: `${b.p}%`, background: WH.worlds[b.w].accent }}/>
              </div>
              <div style={{ marginTop: 4, fontFamily: WH.fontMono, fontSize: 9, color: WH.ash, letterSpacing: 0.5 }}>{b.p}% · {WH.worlds[b.w].name}</div>
            </div>
          ))}
        </div>

        {/* Forum activity */}
        <div style={{ padding: '22px 20px 10px' }}>
          <WHKicker color={WH.linen}>{t.fromForum}</WHKicker>
        </div>
        <div style={{ padding: '0 20px', display: 'flex', flexDirection: 'column', gap: 1, background: WH.stone }}>
          {[
            { title: 'Is Hoid present in every Cosmere novel?', replies: 247, world: 'Cosmere', author: 'stormwarden', time: '2h' },
            { title: 'Theory: Shards of Dominion and Devotion', replies: 89, world: 'Sel', author: 'elantris_scholar', time: '5h' },
            { title: 'On the metallurgy of Harmonium', replies: 142, world: 'Scadrial', author: 'allomancer_22', time: '8h' },
          ].map((post, i) => {
            const worldKey = post.world === 'Cosmere' ? null : Object.entries(WH.worlds).find(([_, v]) => v.name === post.world)?.[0];
            const accent = worldKey ? WH.worlds[worldKey].accent : WH.aurum;
            return (
              <div key={i} style={{ background: WH.abyss, padding: '14px 0', display: 'flex', gap: 12 }}>
                <div style={{ width: 2, alignSelf: 'stretch', background: accent, opacity: 0.8, flexShrink: 0 }}/>
                <div style={{ flex: 1 }}>
                  <div style={{ fontFamily: WH.fontDisplay, fontSize: 15, color: WH.parchment, lineHeight: 1.3 }}>{post.title}</div>
                  <div style={{ marginTop: 6, display: 'flex', alignItems: 'center', gap: 8, fontFamily: WH.fontMono, fontSize: 9, color: WH.ash, letterSpacing: 0.5 }}>
                    <span style={{ color: accent }}>{post.world.toUpperCase()}</span>
                    <span>·</span>
                    <span>{post.author}</span>
                    <span>·</span>
                    <span>{post.time}</span>
                    <span style={{ marginLeft: 'auto' }}>{post.replies} {t.replies}</span>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}

// ─── Worlds map — horizontal scroll atlas + tap-to-focus ───
function WorldsScreen() {
  const { t } = useLocale();
  const worlds = Object.entries(WH.worlds);
  const [focus, setFocus] = React.useState(0);
  const scrollRef = React.useRef(null);

  // width of each "planet cell" in the horizontal rail
  const CELL = 180;

  const scrollTo = (i) => {
    setFocus(i);
    if (scrollRef.current) {
      scrollRef.current.scrollTo({ left: i * CELL - CELL / 2 + 40, behavior: 'smooth' });
    }
  };

  // detailed data per world (for the focused panel)
  const details = {
    roshar:   { shards: 'Honor · Cultivation · Odium', magic: 'Surgebinding', books: 5 },
    scadrial: { shards: 'Preservation · Ruin (Harmony)', magic: 'Allomancy · Feruchemy', books: 7 },
    nalthis:  { shards: 'Endowment',                   magic: 'BioChromatic Breath', books: 1 },
    sel:      { shards: 'Devotion · Dominion',          magic: 'AonDor · ChayShan',    books: 1 },
    taldain:  { shards: 'Autonomy',                     magic: 'Sand Mastery',         books: 1 },
    threnody: { shards: '—',                            magic: 'Cognitive Shadows',    books: 1 },
    first:    { shards: 'Patji / Autonomy',             magic: 'Aviar bonds',          books: 1 },
    yolen:    { shards: 'Adonalsium (shattered)',       magic: 'Unknown',              books: 0 },
  };

  const w = worlds[focus][1];
  const k = worlds[focus][0];
  const d = details[k];

  return (
    <div style={{ background: WH.abyss, height: '100%', overflow: 'hidden', display: 'flex', flexDirection: 'column' }}>
      <div style={{ padding: '54px 20px 14px', borderBottom: `0.5px solid ${WH.stone}` }}>
        <WHKicker color={WH.ash} size={9} style={{ letterSpacing: 3 }}>{t.atlas}</WHKicker>
        <div style={{ marginTop: 4, fontFamily: WH.fontDisplay, fontSize: 28, fontWeight: 400, color: WH.parchment, letterSpacing: -0.4 }}>
          {t.theCosmere} <span style={{ fontStyle: 'italic', color: WH.aurum }}>{t.cosmereItalic}</span>
        </div>
        <div style={{ marginTop: 2, fontFamily: WH.fontUI, fontSize: 11, color: WH.fog }}>{t.atlasSub}</div>
      </div>

      {/* Starfield rail */}
      <div style={{
        position: 'relative',
        height: 280,
        background: `
          radial-gradient(ellipse 60% 80% at 50% 50%, rgba(201,166,107,0.08), transparent 60%),
          radial-gradient(ellipse 100% 50% at 50% 100%, rgba(91,142,209,0.06), transparent 70%),
          linear-gradient(180deg, #05060a, #0a0c12)
        `,
        borderBottom: `0.5px solid ${WH.stone}`,
        overflow: 'hidden',
      }}>
        {/* starfield */}
        {Array.from({ length: 40 }).map((_, i) => {
          const seed = (i * 9301 + 49297) % 233280;
          const x = (seed / 233280) * 100;
          const y = ((seed * 7) % 233280 / 233280) * 100;
          const s = ((seed * 3) % 100) / 100;
          return (
            <div key={i} style={{
              position: 'absolute', left: `${x}%`, top: `${y}%`,
              width: s < 0.3 ? 2 : 1, height: s < 0.3 ? 2 : 1,
              background: WH.linen, opacity: 0.15 + s * 0.45,
            }}/>
          );
        })}

        {/* scrollable rail */}
        <div
          ref={scrollRef}
          style={{
            position: 'absolute', inset: 0,
            overflowX: 'auto', overflowY: 'hidden',
            scrollSnapType: 'x mandatory',
            WebkitOverflowScrolling: 'touch',
            display: 'flex', alignItems: 'center',
            paddingLeft: 40, paddingRight: 40,
          }}
          className="hide-scrollbar"
        >
          {/* orbital dashed track */}
          <div style={{
            position: 'absolute', left: 0, right: 0, top: '50%',
            height: 1, borderTop: `0.5px dashed ${WH.aurumLo}`, opacity: 0.4,
            transform: 'translateY(-0.5px)',
            width: worlds.length * CELL + 80,
          }}/>

          {worlds.map(([wk, ww], i) => {
            const active = i === focus;
            const size = active ? 56 : 28;
            return (
              <div
                key={wk}
                onClick={() => scrollTo(i)}
                style={{
                  width: CELL, flexShrink: 0, scrollSnapAlign: 'center',
                  display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center',
                  height: '100%', cursor: 'pointer', position: 'relative',
                  transition: 'all 0.3s ease',
                }}
              >
                {/* glow ring around active */}
                {active && (
                  <div style={{
                    position: 'absolute',
                    width: 120, height: 120, borderRadius: '50%',
                    background: `radial-gradient(circle, ${ww.accent}30, transparent 65%)`,
                  }}/>
                )}
                {active && [90, 130].map(s => (
                  <div key={s} style={{
                    position: 'absolute', width: s, height: s, borderRadius: '50%',
                    border: `0.5px solid ${ww.accent}`, opacity: s === 90 ? 0.6 : 0.25,
                  }}/>
                ))}

                <div style={{
                  width: size, height: size, borderRadius: '50%',
                  background: `radial-gradient(circle at 30% 30%, ${ww.accent}, ${WH.void} 85%)`,
                  boxShadow: `0 0 ${active ? 24 : 8}px ${ww.accent}${active ? '80' : '40'}, inset -3px -3px 10px rgba(0,0,0,0.5)`,
                  border: `0.5px solid ${ww.accent}`,
                  transition: 'all 0.3s ease',
                }}/>
                <div style={{
                  marginTop: 12,
                  fontFamily: WH.fontMono, fontSize: active ? 10 : 8,
                  color: active ? ww.accent : WH.ash,
                  letterSpacing: active ? 2 : 1, textTransform: 'uppercase',
                  fontWeight: active ? 600 : 400,
                  transition: 'all 0.3s ease',
                }}>
                  {ww.name}
                </div>
              </div>
            );
          })}
        </div>

        {/* hint */}
        <div style={{
          position: 'absolute', bottom: 10, left: 0, right: 0,
          textAlign: 'center',
          fontFamily: WH.fontMono, fontSize: 8, color: WH.ash, opacity: 0.6,
          letterSpacing: 1.5, textTransform: 'uppercase',
        }}>
          ← {t.tapToExplore} →
        </div>
      </div>

      {/* Focused world panel */}
      <div style={{ flex: 1, overflowY: 'auto', paddingBottom: 100 }}>
        <div style={{
          padding: '20px 20px 22px',
          borderBottom: `0.5px solid ${WH.stone}`,
          background: `linear-gradient(180deg, ${w.accent}08, transparent)`,
          borderLeft: `2px solid ${w.accent}`,
          marginLeft: 0,
        }}>
          <WHKicker size={9} color={w.accent} style={{ letterSpacing: 3 }}>{w.sub}</WHKicker>
          <div style={{ marginTop: 6, fontFamily: WH.fontDisplay, fontSize: 26, color: WH.parchment, letterSpacing: -0.3, lineHeight: 1 }}>
            {w.name}
          </div>
          <div style={{ marginTop: 14, display: 'grid', gridTemplateColumns: '90px 1fr', gap: '8px 14px', fontFamily: WH.fontUI, fontSize: 11 }}>
            <div style={{ color: WH.ash, textTransform: 'uppercase', letterSpacing: 1, fontSize: 9 }}>Shards</div>
            <div style={{ color: WH.linen }}>{d.shards}</div>
            <div style={{ color: WH.ash, textTransform: 'uppercase', letterSpacing: 1, fontSize: 9 }}>Magic</div>
            <div style={{ color: WH.linen }}>{d.magic}</div>
            <div style={{ color: WH.ash, textTransform: 'uppercase', letterSpacing: 1, fontSize: 9 }}>Books</div>
            <div style={{ color: WH.linen }}>{d.books}</div>
          </div>
          <div style={{ marginTop: 16, display: 'flex', gap: 8 }}>
            <WHButton variant="ghost" size="sm">{w.name} →</WHButton>
          </div>
        </div>

        {/* list */}
        <div style={{ padding: '16px 20px 8px' }}>
          <WHKicker color={WH.linen}>{t.allWorlds}</WHKicker>
        </div>
        <div style={{ padding: '0 20px', display: 'flex', flexDirection: 'column', gap: 8 }}>
          {worlds.map(([wk, ww], i) => (
            <div
              key={wk}
              onClick={() => scrollTo(i)}
              style={{
                padding: 12, background: i === focus ? 'rgba(201,166,107,0.06)' : WH.ink,
                border: `0.5px solid ${WH.stone}`, borderLeft: `2px solid ${ww.accent}`,
                display: 'flex', alignItems: 'center', gap: 12, cursor: 'pointer',
              }}
            >
              <div style={{
                width: 28, height: 28, borderRadius: '50%',
                background: `radial-gradient(circle at 30% 30%, ${ww.accent}, ${WH.void})`,
                border: `0.5px solid ${ww.accent}`,
              }}/>
              <div style={{ flex: 1 }}>
                <div style={{ fontFamily: WH.fontDisplay, fontSize: 14, color: WH.parchment, lineHeight: 1.1 }}>{ww.name}</div>
                <div style={{ fontFamily: WH.fontMono, fontSize: 8, color: WH.ash, letterSpacing: 1, textTransform: 'uppercase', marginTop: 2 }}>{ww.sub}</div>
              </div>
              {WHIcon.chev(WH.ash)}
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

// ─── Library ───
function LibraryScreen() {
  const { t } = useLocale();
  const books = [
    { t: 'The Way of Kings', w: 'roshar', p: 100, order: 'Stormlight · 1' },
    { t: 'Words of Radiance', w: 'roshar', p: 68, order: 'Stormlight · 2' },
    { t: 'Mistborn: The Final Empire', w: 'scadrial', p: 100, order: 'Era 1 · 1' },
    { t: 'The Well of Ascension', w: 'scadrial', p: 34, order: 'Era 1 · 2' },
    { t: 'Warbreaker', w: 'nalthis', p: 12, order: 'Standalone' },
    { t: 'Elantris', w: 'sel', p: 0, order: 'Standalone' },
  ];
  return (
    <div style={{ background: WH.abyss, height: '100%', overflow: 'hidden', display: 'flex', flexDirection: 'column' }}>
      <div style={{ padding: '54px 20px 14px', borderBottom: `0.5px solid ${WH.stone}` }}>
        <WHKicker color={WH.ash} size={9} style={{ letterSpacing: 3 }}>{t.theArchive}</WHKicker>
        <div style={{ marginTop: 4, fontFamily: WH.fontDisplay, fontSize: 28, fontWeight: 400, color: WH.parchment, letterSpacing: -0.4 }}>
          {t.library}
        </div>
      </div>

      {/* filter tabs */}
      <div style={{ display: 'flex', gap: 18, padding: '14px 20px', borderBottom: `0.5px solid ${WH.stone}` }}>
        {[t.reading, t.toRead, t.finished, t.all].map((label, i) => (
          <div key={label} style={{
            fontFamily: WH.fontUI, fontSize: 11, fontWeight: i === 0 ? 600 : 400,
            letterSpacing: 1.5, textTransform: 'uppercase',
            color: i === 0 ? WH.aurum : WH.ash,
            borderBottom: i === 0 ? `1px solid ${WH.aurum}` : 'none',
            paddingBottom: 6,
          }}>{label}</div>
        ))}
      </div>

      <div style={{ flex: 1, overflowY: 'auto', padding: '16px 20px', paddingBottom: 100 }}>
        <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: 16 }}>
          {books.map((b, i) => (
            <div key={i}>
              <WHPlaceholder label={b.t.split(' ')[0]} h={220} tint={WH.worlds[b.w].accent}/>
              <div style={{ marginTop: 8, fontFamily: WH.fontMono, fontSize: 8, color: WH.worlds[b.w].accent, letterSpacing: 1, textTransform: 'uppercase' }}>{b.order}</div>
              <div style={{ marginTop: 3, fontFamily: WH.fontDisplay, fontSize: 14, color: WH.parchment, lineHeight: 1.15 }}>{b.t}</div>
              {b.p > 0 && b.p < 100 && (
                <div style={{ marginTop: 6, height: 1, background: WH.stone }}>
                  <div style={{ width: `${b.p}%`, height: 1, background: WH.worlds[b.w].accent }}/>
                </div>
              )}
              {b.p === 100 && (
                <div style={{ marginTop: 6, fontFamily: WH.fontMono, fontSize: 8, color: WH.ash, letterSpacing: 1, textTransform: 'uppercase' }}>{t.finishedMark}</div>
              )}
              {b.p === 0 && (
                <div style={{ marginTop: 6, fontFamily: WH.fontMono, fontSize: 8, color: WH.ash, letterSpacing: 1, textTransform: 'uppercase' }}>{t.unreadMark}</div>
              )}
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

// ─── Characters ───
function CharactersScreen() {
  const { t } = useLocale();
  const chars = [
    { n: 'Kaladin Stormblessed', r: 'Windrunner', w: 'roshar' },
    { n: 'Vin', r: 'Mistborn · Hero of Ages', w: 'scadrial' },
    { n: 'Hoid', r: 'The Wit · Worldhopper', w: 'first' },
    { n: 'Shallan Davar', r: 'Lightweaver', w: 'roshar' },
    { n: 'Kelsier', r: 'Survivor of Hathsin', w: 'scadrial' },
    { n: 'Vasher', r: 'Returned', w: 'nalthis' },
    { n: 'Raoden', r: 'Prince of Arelon', w: 'sel' },
    { n: 'Siri', r: 'God King\'s bride', w: 'nalthis' },
  ];
  return (
    <div style={{ background: WH.abyss, height: '100%', overflow: 'hidden', display: 'flex', flexDirection: 'column' }}>
      <div style={{ padding: '54px 20px 14px', borderBottom: `0.5px solid ${WH.stone}` }}>
        <WHKicker color={WH.ash} size={9} style={{ letterSpacing: 3 }}>{t.dramatis}</WHKicker>
        <div style={{ marginTop: 4, fontFamily: WH.fontDisplay, fontSize: 28, fontWeight: 400, color: WH.parchment, letterSpacing: -0.4 }}>
          {t.characters}
        </div>
      </div>

      <div style={{ padding: '12px 20px', borderBottom: `0.5px solid ${WH.stone}` }}>
        <WHInput placeholder={t.searchChars} icon={WHIcon.search(WH.ash)} style={{ height: 38 }}/>
      </div>

      <div style={{ flex: 1, overflowY: 'auto', padding: '12px 20px', paddingBottom: 100 }}>
        <WHKicker color={WH.linen} style={{ marginBottom: 10 }}>{t.worldhoppers}</WHKicker>
        {chars.map((c, i) => {
          const w = WH.worlds[c.w];
          return (
            <div key={i} style={{ display: 'flex', alignItems: 'center', gap: 14, padding: '12px 0', borderBottom: `0.5px solid ${WH.stone}` }}>
              <div style={{
                width: 44, height: 44, flexShrink: 0,
                background: `
                  radial-gradient(circle at 30% 30%, ${w.accent}60, ${WH.ink} 70%)
                `,
                border: `0.5px solid ${w.accent}`,
                display: 'flex', alignItems: 'center', justifyContent: 'center',
                fontFamily: WH.fontDisplay, fontSize: 18, color: WH.parchment, fontStyle: 'italic',
              }}>{c.n[0]}</div>
              <div style={{ flex: 1 }}>
                <div style={{ fontFamily: WH.fontDisplay, fontSize: 15, color: WH.parchment, lineHeight: 1.15 }}>{c.n}</div>
                <div style={{ marginTop: 2, fontFamily: WH.fontUI, fontSize: 11, color: WH.fog }}>{c.r}</div>
              </div>
              <WHChip color={w.accent}>{w.name}</WHChip>
            </div>
          );
        })}
      </div>
    </div>
  );
}

// ─── Magic systems ───
function MagicScreen() {
  const { t } = useLocale();
  const systems = [
    { n: 'Surgebinding', w: 'roshar', desc: 'Ten Surges, drawn from Stormlight. Bonded through oaths.', powers: ['Gravitation', 'Adhesion', 'Division', 'Illumination'] },
    { n: 'Allomancy', w: 'scadrial', desc: 'Burn metals, each granting a distinct power. Hereditary.', powers: ['Iron', 'Steel', 'Tin', 'Pewter'] },
    { n: 'Feruchemy', w: 'scadrial', desc: 'Store attributes in metalminds, draw on them later.', powers: ['Copper', 'Bronze', 'Zinc', 'Brass'] },
    { n: 'BioChromatic Breath', w: 'nalthis', desc: 'Command Breath grants Awakening and colour perception.', powers: ['Awakening', 'Lifesense', 'Heightening'] },
    { n: 'AonDor', w: 'sel', desc: 'Geometric runes drawing on the Dor, tied to the land.', powers: ['Aon Rao', 'Aon Ehe', 'Aon Ien'] },
  ];
  return (
    <div style={{ background: WH.abyss, height: '100%', overflow: 'hidden', display: 'flex', flexDirection: 'column' }}>
      <div style={{ padding: '54px 20px 14px', borderBottom: `0.5px solid ${WH.stone}` }}>
        <WHKicker color={WH.ash} size={9} style={{ letterSpacing: 3 }}>{t.arsArcanum}</WHKicker>
        <div style={{ marginTop: 4, fontFamily: WH.fontDisplay, fontSize: 28, fontWeight: 400, color: WH.parchment, letterSpacing: -0.4 }}>
          {t.systemsOf} <span style={{ fontStyle: 'italic', color: WH.aurum }}>{t.magicItalic}</span>
        </div>
      </div>

      <div style={{ flex: 1, overflowY: 'auto', padding: '16px 20px', paddingBottom: 100, display: 'flex', flexDirection: 'column', gap: 14 }}>
        {systems.map((s, i) => {
          const w = WH.worlds[s.w];
          return (
            <div key={i} style={{
              padding: 16, background: WH.ink,
              border: `0.5px solid ${WH.stone}`, borderLeft: `2px solid ${w.accent}`,
              position: 'relative', overflow: 'hidden',
            }}>
              <div style={{ position: 'absolute', top: 10, right: 10, opacity: 0.25 }}>
                <WHCosmereMark size={42} color={w.accent} dots={6}/>
              </div>
              <WHKicker size={9} color={w.accent} style={{ letterSpacing: 2 }}>{w.name}</WHKicker>
              <div style={{ marginTop: 4, fontFamily: WH.fontDisplay, fontSize: 20, fontStyle: 'italic', color: WH.parchment, letterSpacing: -0.2 }}>
                {s.n}
              </div>
              <div style={{ marginTop: 8, fontFamily: WH.fontUI, fontSize: 12, color: WH.fog, lineHeight: 1.5, maxWidth: '85%' }}>
                {s.desc}
              </div>
              <div style={{ marginTop: 12, display: 'flex', gap: 6, flexWrap: 'wrap' }}>
                {s.powers.map(p => <WHChip key={p} color={w.accent}>{p}</WHChip>)}
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}

// ─── Timeline ───
function TimelineScreen() {
  const { t } = useLocale();
  const events = [
    { era: 'Dawn', y: '—', title: 'Shattering of Adonalsium', place: 'Yolen', w: 'yolen' },
    { era: 'Ancient', y: '—', title: 'Founding of the Knights Radiant', place: 'Roshar', w: 'roshar' },
    { era: 'Recreance', y: '—', title: 'The Knights abandon their oaths', place: 'Roshar', w: 'roshar' },
    { era: 'Era 1', y: '1024 FE', title: 'The Final Empire falls', place: 'Scadrial', w: 'scadrial' },
    { era: 'Era 2', y: '341 PC', title: 'The Catacendre & Ascension of Harmony', place: 'Scadrial', w: 'scadrial' },
    { era: 'Return', y: '—', title: 'Vasher arrives on Nalthis', place: 'Nalthis', w: 'nalthis' },
    { era: 'Modern', y: '1173', title: 'True Desolation begins', place: 'Roshar', w: 'roshar' },
  ];
  return (
    <div style={{ background: WH.abyss, height: '100%', overflow: 'hidden', display: 'flex', flexDirection: 'column' }}>
      <div style={{ padding: '54px 20px 14px', borderBottom: `0.5px solid ${WH.stone}` }}>
        <WHKicker color={WH.ash} size={9} style={{ letterSpacing: 3 }}>{t.chronology}</WHKicker>
        <div style={{ marginTop: 4, fontFamily: WH.fontDisplay, fontSize: 28, fontWeight: 400, color: WH.parchment, letterSpacing: -0.4 }}>
          {t.theCosmic} <span style={{ fontStyle: 'italic', color: WH.aurum }}>{t.timelineItalic}</span>
        </div>
      </div>

      <div style={{ flex: 1, overflowY: 'auto', padding: '16px 20px 100px' }}>
        <div style={{ position: 'relative', paddingLeft: 32 }}>
          {/* vertical line */}
          <div style={{ position: 'absolute', left: 12, top: 0, bottom: 0, width: 1, background: `linear-gradient(180deg, transparent, ${WH.aurumLo} 20%, ${WH.aurumLo} 80%, transparent)` }}/>
          {events.map((e, i) => {
            const w = WH.worlds[e.w];
            return (
              <div key={i} style={{ position: 'relative', paddingBottom: 20 }}>
                {/* dot */}
                <div style={{
                  position: 'absolute', left: -26, top: 4,
                  width: 10, height: 10, borderRadius: '50%',
                  background: w.accent,
                  boxShadow: `0 0 10px ${w.accent}`,
                  border: `1.5px solid ${WH.abyss}`,
                }}/>
                <div style={{ display: 'flex', alignItems: 'baseline', gap: 10, marginBottom: 2 }}>
                  <WHKicker size={9} color={w.accent} style={{ letterSpacing: 2 }}>{e.era}</WHKicker>
                  <span style={{ fontFamily: WH.fontMono, fontSize: 10, color: WH.ash }}>{e.y}</span>
                </div>
                <div style={{ fontFamily: WH.fontDisplay, fontSize: 15, color: WH.parchment, lineHeight: 1.25 }}>{e.title}</div>
                <div style={{ marginTop: 4, fontFamily: WH.fontUI, fontSize: 11, color: WH.fog }}>{e.place}</div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}

Object.assign(window, { HomeScreen, WorldsScreen, LibraryScreen, CharactersScreen, MagicScreen, TimelineScreen });
