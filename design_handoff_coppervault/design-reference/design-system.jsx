// Worldhopper Design System
// Aesthetic: dark, mystical — deep blacks, mist, liquid metal, cosmic.
// Original motifs only (no copyrighted glyphs).

const WH = {
  // core
  void:     '#05060a',  // absolute black
  abyss:    '#0a0c12',  // app bg
  ink:      '#101218',  // panel bg
  slate:    '#191c24',  // raised
  stone:    '#22262f',  // hairline borders / chips
  mist:     '#3a3f4d',  // muted borders
  ash:      '#6b7180',  // muted text
  fog:      '#9aa0b0',  // secondary text
  linen:    '#d8d5cb',  // primary text (warm off-white, "aged paper")
  parchment:'#e8e4d6',  // heading text
  // liquid metal (primary accent — universal "cosmere light")
  aurum:    '#c9a66b',  // base bronze-gold
  aurumHi:  '#e7cc92',  // highlight
  aurumLo:  '#8c7143',  // shadow
  // world accents (subtle tints)
  worlds: {
    roshar:   { accent: '#5b8ed1', name: 'Roshar',   sub: 'Stormlight' },
    scadrial: { accent: '#b8743a', name: 'Scadrial', sub: 'Mistborn' },
    nalthis:  { accent: '#a25cc1', name: 'Nalthis',  sub: 'Warbreaker' },
    sel:      { accent: '#d0b84a', name: 'Sel',      sub: 'Elantris' },
    taldain:  { accent: '#c99a5c', name: 'Taldain',  sub: 'White Sand' },
    threnody: { accent: '#8a9099', name: 'Threnody', sub: 'Shadows' },
    first:    { accent: '#6fb5a6', name: 'First of the Sun', sub: 'Sixth of the Dusk' },
    yolen:    { accent: '#9ea4b3', name: 'Yolen',    sub: 'Dragonsteel' },
  },
  // fonts
  fontDisplay: '"Cormorant Garamond", "EB Garamond", Georgia, serif',
  fontUI:      '"Inter", -apple-system, system-ui, sans-serif',
  fontMono:    '"JetBrains Mono", "SF Mono", ui-monospace, monospace',
};

// Google Fonts injection (once)
if (typeof document !== 'undefined' && !document.getElementById('wh-fonts')) {
  const l = document.createElement('link');
  l.id = 'wh-fonts';
  l.rel = 'stylesheet';
  l.href = 'https://fonts.googleapis.com/css2?family=Cormorant+Garamond:ital,wght@0,300;0,400;0,500;0,600;0,700;1,400;1,500&family=Inter:wght@300;400;500;600;700&family=JetBrains+Mono:wght@300;400;500&display=swap';
  document.head.appendChild(l);
}

// ─── Original symbol: concentric "cosmere" ring ───
// Eight small dots around two concentric circles — my abstract of "Shards orbiting Adonalsium's remnant".
function WHCosmereMark({ size = 56, color = WH.aurum, stroke = 1, dots = 8 }) {
  const cx = size / 2, cy = size / 2;
  const rOuter = size * 0.42;
  const rInner = size * 0.24;
  const rDotOrbit = size * 0.42;
  const arr = Array.from({ length: dots });
  return (
    <svg width={size} height={size} viewBox={`0 0 ${size} ${size}`} style={{ display: 'block' }}>
      <circle cx={cx} cy={cy} r={rOuter} fill="none" stroke={color} strokeWidth={stroke} opacity="0.85"/>
      <circle cx={cx} cy={cy} r={rInner} fill="none" stroke={color} strokeWidth={stroke * 0.7} opacity="0.55"/>
      <circle cx={cx} cy={cy} r={size * 0.04} fill={color} opacity="0.9"/>
      {arr.map((_, i) => {
        const a = (i / dots) * Math.PI * 2 - Math.PI / 2;
        const x = cx + Math.cos(a) * rDotOrbit;
        const y = cy + Math.sin(a) * rDotOrbit;
        const big = i === 0;
        return <circle key={i} cx={x} cy={y} r={big ? 2.4 : 1.4} fill={color} opacity={big ? 1 : 0.75}/>;
      })}
      {/* radial hairlines */}
      {arr.map((_, i) => {
        if (i % 2 !== 0) return null;
        const a = (i / dots) * Math.PI * 2 - Math.PI / 2;
        const x1 = cx + Math.cos(a) * rInner;
        const y1 = cy + Math.sin(a) * rInner;
        const x2 = cx + Math.cos(a) * rOuter;
        const y2 = cy + Math.sin(a) * rOuter;
        return <line key={`l${i}`} x1={x1} y1={y1} x2={x2} y2={y2} stroke={color} strokeWidth={stroke * 0.5} opacity="0.4"/>;
      })}
    </svg>
  );
}

// Wordmark
function WHWordmark({ size = 24, color = WH.parchment, tight = false }) {
  return (
    <div style={{
      fontFamily: WH.fontDisplay, fontSize: size, fontWeight: 500,
      color, letterSpacing: tight ? 0 : size * 0.04,
      fontVariant: 'small-caps', lineHeight: 1,
      display: 'inline-flex', alignItems: 'baseline', gap: size * 0.15,
    }}>
      <span style={{ fontStyle: 'italic', fontWeight: 400 }}>World</span>
      <span style={{ color: WH.aurum, fontWeight: 600 }}>hopper</span>
    </div>
  );
}

// Mist / grain backdrop — radial fog + noise via layered gradients
function WHMistBg({ children, style = {}, intensity = 1 }) {
  return (
    <div style={{
      position: 'relative',
      background: `
        radial-gradient(ellipse 80% 60% at 50% 20%, rgba(201,166,107,${0.09 * intensity}) 0%, transparent 55%),
        radial-gradient(ellipse 120% 80% at 50% 110%, rgba(91,142,209,${0.07 * intensity}) 0%, transparent 60%),
        radial-gradient(ellipse 60% 40% at 15% 80%, rgba(162,92,193,${0.05 * intensity}) 0%, transparent 55%),
        linear-gradient(180deg, #05060a 0%, #0a0c12 50%, #05060a 100%)
      `,
      overflow: 'hidden',
      ...style,
    }}>
      {/* grain */}
      <div style={{
        position: 'absolute', inset: 0, pointerEvents: 'none', opacity: 0.35,
        backgroundImage: `url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='200' height='200'%3E%3Cfilter id='n'%3E%3CfeTurbulence type='fractalNoise' baseFrequency='0.9' numOctaves='2' stitchTiles='stitch'/%3E%3CfeColorMatrix values='0 0 0 0 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0.08 0'/%3E%3C/filter%3E%3Crect width='200' height='200' filter='url(%23n)'/%3E%3C/svg%3E")`,
        mixBlendMode: 'overlay',
      }} />
      <div style={{ position: 'relative', height: '100%' }}>{children}</div>
    </div>
  );
}

// Hairline divider
function WHLine({ color = WH.stone, opacity = 1, style = {} }) {
  return <div style={{ height: 1, background: color, opacity, width: '100%', ...style }}/>;
}

// Small caps "kicker" label — for section headers, epigraph-style
function WHKicker({ children, color = WH.ash, size = 10, style = {} }) {
  return (
    <div style={{
      fontFamily: WH.fontUI, fontWeight: 500,
      fontSize: size, color, letterSpacing: size * 0.2,
      textTransform: 'uppercase',
      ...style,
    }}>{children}</div>
  );
}

// Tag/chip
function WHChip({ children, color = WH.aurum, filled = false, size = 10 }) {
  return (
    <span style={{
      display: 'inline-flex', alignItems: 'center', gap: 4,
      padding: `${size * 0.35}px ${size * 0.8}px`,
      fontFamily: WH.fontUI, fontSize: size, fontWeight: 500,
      letterSpacing: size * 0.08, textTransform: 'uppercase',
      color: filled ? WH.void : color,
      background: filled ? color : 'transparent',
      border: filled ? 'none' : `0.5px solid ${color}`,
      borderRadius: 2, lineHeight: 1,
    }}>{children}</span>
  );
}

// Button
function WHButton({ children, variant = 'primary', size = 'md', style = {}, icon }) {
  const sizes = {
    sm: { h: 32, px: 12, fs: 12 },
    md: { h: 44, px: 18, fs: 13 },
    lg: { h: 52, px: 22, fs: 14 },
  };
  const s = sizes[size];
  const variants = {
    primary: {
      background: `linear-gradient(180deg, ${WH.aurumHi} 0%, ${WH.aurum} 50%, ${WH.aurumLo} 100%)`,
      color: WH.void, border: 'none',
      boxShadow: `inset 0 1px 0 rgba(255,255,255,0.35), inset 0 -1px 0 rgba(0,0,0,0.25), 0 4px 16px rgba(201,166,107,0.18)`,
    },
    ghost: {
      background: 'transparent', color: WH.parchment,
      border: `0.5px solid ${WH.mist}`,
    },
    glass: {
      background: 'rgba(255,255,255,0.04)', color: WH.parchment,
      border: `0.5px solid rgba(255,255,255,0.08)`,
      backdropFilter: 'blur(8px)', WebkitBackdropFilter: 'blur(8px)',
    },
  };
  return (
    <button style={{
      height: s.h, padding: `0 ${s.px}px`,
      fontFamily: WH.fontUI, fontSize: s.fs, fontWeight: 600,
      letterSpacing: s.fs * 0.08, textTransform: 'uppercase',
      borderRadius: 2, cursor: 'pointer',
      display: 'inline-flex', alignItems: 'center', justifyContent: 'center', gap: 8,
      ...variants[variant], ...style,
    }}>
      {icon}
      {children}
    </button>
  );
}

// Input
function WHInput({ placeholder, type = 'text', value, icon, style = {} }) {
  return (
    <div style={{
      display: 'flex', alignItems: 'center', gap: 10,
      height: 48, padding: '0 14px',
      background: 'rgba(255,255,255,0.025)',
      border: `0.5px solid ${WH.mist}`,
      borderRadius: 2,
      ...style,
    }}>
      {icon && <div style={{ color: WH.ash, display: 'flex' }}>{icon}</div>}
      <div style={{
        flex: 1, fontFamily: WH.fontUI, fontSize: 14,
        color: value ? WH.parchment : WH.ash, fontWeight: 400,
      }}>{value || placeholder}</div>
    </div>
  );
}

// Simple icon set (stroke-based, 20px grid, original)
const WHIcon = {
  search: (c = 'currentColor') => <svg width="16" height="16" viewBox="0 0 20 20" fill="none"><circle cx="9" cy="9" r="6" stroke={c} strokeWidth="1.2"/><path d="M13.5 13.5L17 17" stroke={c} strokeWidth="1.2" strokeLinecap="round"/></svg>,
  mail:   (c = 'currentColor') => <svg width="16" height="16" viewBox="0 0 20 20" fill="none"><rect x="2.5" y="4.5" width="15" height="11" stroke={c} strokeWidth="1.2"/><path d="M2.5 5L10 11L17.5 5" stroke={c} strokeWidth="1.2"/></svg>,
  lock:   (c = 'currentColor') => <svg width="16" height="16" viewBox="0 0 20 20" fill="none"><rect x="3.5" y="9" width="13" height="8" stroke={c} strokeWidth="1.2"/><path d="M6 9V7a4 4 0 018 0v2" stroke={c} strokeWidth="1.2"/></svg>,
  user:   (c = 'currentColor') => <svg width="16" height="16" viewBox="0 0 20 20" fill="none"><circle cx="10" cy="7" r="3" stroke={c} strokeWidth="1.2"/><path d="M3 17c1.5-3.5 4-5 7-5s5.5 1.5 7 5" stroke={c} strokeWidth="1.2"/></svg>,
  home:   (c = 'currentColor') => <svg width="20" height="20" viewBox="0 0 20 20" fill="none"><path d="M3 9L10 3L17 9V17H12V12H8V17H3V9Z" stroke={c} strokeWidth="1.2" strokeLinejoin="round"/></svg>,
  globe:  (c = 'currentColor') => <svg width="20" height="20" viewBox="0 0 20 20" fill="none"><circle cx="10" cy="10" r="7" stroke={c} strokeWidth="1.2"/><ellipse cx="10" cy="10" rx="3" ry="7" stroke={c} strokeWidth="1.2"/><path d="M3 10h14" stroke={c} strokeWidth="1.2"/></svg>,
  book:   (c = 'currentColor') => <svg width="20" height="20" viewBox="0 0 20 20" fill="none"><path d="M4 4H16V17H4z" stroke={c} strokeWidth="1.2"/><path d="M4 4V17M16 4V17M4 10H16" stroke={c} strokeWidth="0.8" opacity="0.5"/></svg>,
  users:  (c = 'currentColor') => <svg width="20" height="20" viewBox="0 0 20 20" fill="none"><circle cx="7" cy="7" r="2.5" stroke={c} strokeWidth="1.2"/><circle cx="14" cy="8" r="2" stroke={c} strokeWidth="1.2"/><path d="M2 16c1-3 3-4.5 5-4.5s4 1.5 5 4.5M12 16c1-2.5 2-3.5 4-3.5" stroke={c} strokeWidth="1.2"/></svg>,
  spark:  (c = 'currentColor') => <svg width="20" height="20" viewBox="0 0 20 20" fill="none"><path d="M10 2V7M10 13V18M2 10H7M13 10H18M4.5 4.5L7.5 7.5M12.5 12.5L15.5 15.5M4.5 15.5L7.5 12.5M12.5 7.5L15.5 4.5" stroke={c} strokeWidth="1.2" strokeLinecap="round"/></svg>,
  clock:  (c = 'currentColor') => <svg width="20" height="20" viewBox="0 0 20 20" fill="none"><circle cx="10" cy="10" r="7" stroke={c} strokeWidth="1.2"/><path d="M10 6V10L13 12" stroke={c} strokeWidth="1.2" strokeLinecap="round"/></svg>,
  forum:  (c = 'currentColor') => <svg width="20" height="20" viewBox="0 0 20 20" fill="none"><path d="M3 5h11v8H8l-3 3v-3H3V5z" stroke={c} strokeWidth="1.2" strokeLinejoin="round"/><path d="M7 8H10M7 10H11" stroke={c} strokeWidth="1.2" strokeLinecap="round"/></svg>,
  note:   (c = 'currentColor') => <svg width="20" height="20" viewBox="0 0 20 20" fill="none"><path d="M4 3H14L16 5V17H4V3Z" stroke={c} strokeWidth="1.2" strokeLinejoin="round"/><path d="M7 8H13M7 11H13M7 14H10" stroke={c} strokeWidth="1.2" strokeLinecap="round"/></svg>,
  gear:   (c = 'currentColor') => <svg width="20" height="20" viewBox="0 0 20 20" fill="none"><circle cx="10" cy="10" r="2.5" stroke={c} strokeWidth="1.2"/><path d="M10 2V4M10 16V18M18 10H16M4 10H2M15.6 4.4L14.2 5.8M5.8 14.2L4.4 15.6M15.6 15.6L14.2 14.2M5.8 5.8L4.4 4.4" stroke={c} strokeWidth="1.2" strokeLinecap="round"/></svg>,
  chev:   (c = 'currentColor', dir = 'right') => {
    const rot = { right: 0, left: 180, down: 90, up: -90 }[dir];
    return <svg width="12" height="12" viewBox="0 0 12 12" fill="none" style={{ transform: `rotate(${rot}deg)` }}><path d="M4 2L8 6L4 10" stroke={c} strokeWidth="1.2" strokeLinecap="round" strokeLinejoin="round"/></svg>;
  },
  plus:   (c = 'currentColor') => <svg width="14" height="14" viewBox="0 0 14 14" fill="none"><path d="M7 2V12M2 7H12" stroke={c} strokeWidth="1.2" strokeLinecap="round"/></svg>,
  back:   (c = 'currentColor') => <svg width="16" height="16" viewBox="0 0 16 16" fill="none"><path d="M10 3L4 8L10 13" stroke={c} strokeWidth="1.2" strokeLinecap="round" strokeLinejoin="round"/></svg>,
  more:   (c = 'currentColor') => <svg width="16" height="4" viewBox="0 0 16 4" fill="none"><circle cx="2" cy="2" r="1.5" fill={c}/><circle cx="8" cy="2" r="1.5" fill={c}/><circle cx="14" cy="2" r="1.5" fill={c}/></svg>,
  bell:   (c = 'currentColor') => <svg width="18" height="18" viewBox="0 0 20 20" fill="none"><path d="M5 14V9a5 5 0 0110 0v5l1.5 2H3.5L5 14z" stroke={c} strokeWidth="1.2" strokeLinejoin="round"/><path d="M8 17a2 2 0 004 0" stroke={c} strokeWidth="1.2"/></svg>,
  star:   (c = 'currentColor', fill = 'none') => <svg width="14" height="14" viewBox="0 0 14 14" fill="none"><path d="M7 1l1.8 3.8L13 5.5l-3 3 .8 4.5L7 11l-3.8 2 .8-4.5-3-3 4.2-.7L7 1z" stroke={c} fill={fill} strokeWidth="1" strokeLinejoin="round"/></svg>,
  warn:   (c = 'currentColor') => <svg width="14" height="14" viewBox="0 0 14 14" fill="none"><path d="M7 1L13 12H1L7 1z" stroke={c} strokeWidth="1.2" strokeLinejoin="round"/><path d="M7 6V9M7 11V10.5" stroke={c} strokeWidth="1.2" strokeLinecap="round"/></svg>,
  heart:  (c = 'currentColor') => <svg width="14" height="14" viewBox="0 0 14 14" fill="none"><path d="M7 12C7 12 1.5 8.5 1.5 5C1.5 3.5 2.8 2.5 4 2.5C5.2 2.5 6.2 3.2 7 4.3C7.8 3.2 8.8 2.5 10 2.5C11.2 2.5 12.5 3.5 12.5 5C12.5 8.5 7 12 7 12Z" stroke={c} strokeWidth="1.2" strokeLinejoin="round"/></svg>,
};

// Epigraph — italic serif quote with attribution, used as decoration
function WHEpigraph({ quote, attr, color = WH.fog, size = 12 }) {
  return (
    <div style={{ fontFamily: WH.fontDisplay, fontStyle: 'italic', fontSize: size, color, lineHeight: 1.5, textAlign: 'center' }}>
      <div>"{quote}"</div>
      {attr && <div style={{ fontFamily: WH.fontUI, fontStyle: 'normal', fontSize: size * 0.75, letterSpacing: size * 0.12, textTransform: 'uppercase', marginTop: 6, color: WH.ash, opacity: 0.7 }}>— {attr}</div>}
    </div>
  );
}

// Spoiler warning strip
function WHSpoilerStrip({ level = 'Era 2', color = WH.aurum }) {
  return (
    <div style={{
      display: 'flex', alignItems: 'center', gap: 6,
      padding: '4px 8px',
      background: 'rgba(201,166,107,0.08)',
      border: `0.5px solid rgba(201,166,107,0.3)`,
      fontFamily: WH.fontMono, fontSize: 9, fontWeight: 500,
      letterSpacing: 0.8, textTransform: 'uppercase',
      color, borderRadius: 2,
    }}>
      {WHIcon.warn(color)} Spoilers · {level}
    </div>
  );
}

// Placeholder image — striped with a label (per design system guidance)
function WHPlaceholder({ label, w = '100%', h = 160, tint = WH.aurum, style = {} }) {
  return (
    <div style={{
      width: w, height: h, position: 'relative', overflow: 'hidden',
      background: `repeating-linear-gradient(135deg, rgba(255,255,255,0.03) 0px, rgba(255,255,255,0.03) 8px, rgba(255,255,255,0.015) 8px, rgba(255,255,255,0.015) 16px), linear-gradient(180deg, rgba(255,255,255,0.02), rgba(0,0,0,0.2))`,
      border: `0.5px solid ${WH.stone}`,
      display: 'flex', alignItems: 'center', justifyContent: 'center',
      ...style,
    }}>
      <div style={{
        fontFamily: WH.fontMono, fontSize: 9, letterSpacing: 1.5,
        textTransform: 'uppercase', color: WH.ash, opacity: 0.7,
        padding: '4px 8px', border: `0.5px solid ${WH.mist}`,
      }}>{label}</div>
      {tint && (
        <div style={{
          position: 'absolute', inset: 0, pointerEvents: 'none',
          background: `radial-gradient(ellipse at 50% 100%, ${tint}15, transparent 70%)`,
        }} />
      )}
    </div>
  );
}

// Tab bar (iOS-style, glass)
function WHTabBar({ active = 'home', onChange }) {
  const tabs = [
    { k: 'home',    icon: WHIcon.home,  label: 'Home'   },
    { k: 'worlds',  icon: WHIcon.globe, label: 'Worlds' },
    { k: 'library', icon: WHIcon.book,  label: 'Library'},
    { k: 'forum',   icon: WHIcon.forum, label: 'Forum'  },
    { k: 'me',      icon: WHIcon.user,  label: 'Me'     },
  ];
  return (
    <div style={{
      position: 'absolute', bottom: 0, left: 0, right: 0, zIndex: 30,
      height: 84, paddingBottom: 24, paddingTop: 8,
      display: 'flex', alignItems: 'stretch',
      background: 'linear-gradient(180deg, rgba(10,12,18,0.4) 0%, rgba(10,12,18,0.9) 50%, rgba(10,12,18,0.98) 100%)',
      backdropFilter: 'blur(16px)', WebkitBackdropFilter: 'blur(16px)',
      borderTop: `0.5px solid ${WH.stone}`,
    }}>
      {tabs.map(t => {
        const on = t.k === active;
        const color = on ? WH.aurum : WH.ash;
        return (
          <div key={t.k} onClick={() => onChange && onChange(t.k)} style={{
            flex: 1, display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center',
            gap: 3, cursor: 'pointer', position: 'relative',
          }}>
            {on && <div style={{ position: 'absolute', top: 0, left: '30%', right: '30%', height: 1, background: WH.aurum }}/>}
            <div style={{ color }}>{t.icon(color)}</div>
            <div style={{
              fontFamily: WH.fontUI, fontSize: 9, fontWeight: on ? 600 : 400,
              letterSpacing: 1, textTransform: 'uppercase', color,
            }}>{t.label}</div>
          </div>
        );
      })}
    </div>
  );
}

Object.assign(window, {
  WH, WHCosmereMark, WHWordmark, WHMistBg, WHLine, WHKicker, WHChip,
  WHButton, WHInput, WHIcon, WHEpigraph, WHSpoilerStrip, WHPlaceholder, WHTabBar,
});
