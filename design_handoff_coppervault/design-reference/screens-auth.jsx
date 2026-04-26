// Worldhopper — Splash + Auth screens
// Portal metaphor: concentric metal rings expand outward from the wordmark.

// ─── Splash ───
function SplashScreen({ animate = true }) {
  const { t } = useLocale();
  const [time, setTime] = React.useState(0);
  React.useEffect(() => {
    if (!animate) return;
    let raf, start = performance.now();
    const loop = (now) => {
      setTime(((now - start) / 1000) % 6);
      raf = requestAnimationFrame(loop);
    };
    raf = requestAnimationFrame(loop);
    return () => cancelAnimationFrame(raf);
  }, [animate]);

  // ring phase 0..1
  const rings = [0, 1, 2, 3].map(i => {
    const phase = ((time / 6) * 4 + i / 4) % 1;
    return { scale: 0.3 + phase * 2.2, opacity: 0.5 * (1 - phase) };
  });

  return (
    <WHMistBg style={{ width: '100%', height: '100%' }}>
      {/* cosmic starfield — small dots */}
      <div style={{ position: 'absolute', inset: 0 }}>
        {Array.from({ length: 50 }).map((_, i) => {
          const seed = (i * 9301 + 49297) % 233280;
          const x = (seed / 233280) * 100;
          const y = ((seed * 7) % 233280 / 233280) * 100;
          const s = ((seed * 3) % 100) / 100;
          return (
            <div key={i} style={{
              position: 'absolute', left: `${x}%`, top: `${y}%`,
              width: s < 0.3 ? 2 : 1, height: s < 0.3 ? 2 : 1,
              background: WH.linen, borderRadius: '50%',
              opacity: 0.15 + s * 0.5,
            }}/>
          );
        })}
      </div>

      {/* expanding portal rings */}
      <div style={{ position: 'absolute', inset: 0, display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
        {rings.map((r, i) => (
          <div key={i} style={{
            position: 'absolute',
            width: 200, height: 200, borderRadius: '50%',
            border: `0.5px solid ${WH.aurum}`,
            transform: `scale(${r.scale})`,
            opacity: r.opacity,
          }}/>
        ))}
      </div>

      {/* center wordmark + mark */}
      <div style={{
        position: 'relative', height: '100%',
        display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center',
        gap: 20, padding: 40,
      }}>
        <WHCosmereMark size={72} color={WH.aurum}/>
        <div style={{ textAlign: 'center' }}>
          <WHWordmark size={32}/>
          <div style={{ marginTop: 14 }}>
            <WHKicker size={9} color={WH.fog} style={{ letterSpacing: 4 }}>{t.tagline}</WHKicker>
          </div>
        </div>

        {/* bottom epigraph */}
        <div style={{ position: 'absolute', bottom: 100, left: 32, right: 32 }}>
          <WHEpigraph quote={t.splashQuote} attr={t.splashAttr}/>
        </div>

        {/* loading hairline */}
        <div style={{
          position: 'absolute', bottom: 70, left: '50%', transform: 'translateX(-50%)',
          width: 60, height: 1, background: WH.stone, overflow: 'hidden',
        }}>
          <div style={{
            width: '40%', height: '100%', background: WH.aurum,
            transform: `translateX(${(time / 6) * 150 - 50}%)`,
          }}/>
        </div>
      </div>
    </WHMistBg>
  );
}

// ─── Auth card shell — mist bg + centered card ───
function AuthShell({ children }) {
  return (
    <WHMistBg style={{ width: '100%', height: '100%' }}>
      {/* faint concentric rings in bg */}
      <div style={{ position: 'absolute', inset: 0, display: 'flex', alignItems: 'center', justifyContent: 'center', opacity: 0.25 }}>
        {[200, 320, 460, 620].map(s => (
          <div key={s} style={{
            position: 'absolute', width: s, height: s, borderRadius: '50%',
            border: `0.5px solid ${WH.aurum}`, opacity: 0.3,
          }}/>
        ))}
      </div>
      <div style={{
        position: 'relative', height: '100%',
        padding: '80px 28px 40px',
        display: 'flex', flexDirection: 'column',
      }}>
        {children}
      </div>
    </WHMistBg>
  );
}

function SocialRow() {
  const btn = (label, glyph) => (
    <div style={{
      flex: 1, height: 44, border: `0.5px solid ${WH.mist}`, borderRadius: 2,
      background: 'rgba(255,255,255,0.02)',
      display: 'flex', alignItems: 'center', justifyContent: 'center', gap: 8,
      fontFamily: WH.fontUI, fontSize: 12, fontWeight: 500, color: WH.linen,
    }}>
      <span style={{ fontFamily: WH.fontMono, fontSize: 14, color: WH.aurum }}>{glyph}</span>
      {label}
    </div>
  );
  return (
    <div style={{ display: 'flex', gap: 10 }}>
      {btn('Google', 'G')}
      {btn('Apple', '')}
    </div>
  );
}

// ─── Login ───
function LoginScreen() {
  const { t } = useLocale();
  return (
    <AuthShell>
      <div style={{ textAlign: 'center', marginBottom: 36 }}>
        <div style={{ display: 'inline-block', marginBottom: 16 }}>
          <WHCosmereMark size={44} color={WH.aurum}/>
        </div>
        <WHWordmark size={26}/>
        <div style={{ marginTop: 10 }}>
          <WHKicker size={9} color={WH.fog} style={{ letterSpacing: 3 }}>{t.openPortal}</WHKicker>
        </div>
      </div>

      <div style={{ display: 'flex', flexDirection: 'column', gap: 12 }}>
        <WHInput placeholder={t.emailOrUser} value="kaladin@shattered.plain" icon={WHIcon.mail(WH.ash)}/>
        <WHInput placeholder={t.password} value="••••••••••" icon={WHIcon.lock(WH.ash)}/>
        <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginTop: -4 }}>
          <div style={{ display: 'flex', alignItems: 'center', gap: 8 }}>
            <div style={{ width: 14, height: 14, border: `1px solid ${WH.mist}`, borderRadius: 1, background: 'transparent' }}/>
            <span style={{ fontFamily: WH.fontUI, fontSize: 11, color: WH.fog }}>{t.remember}</span>
          </div>
          <span style={{ fontFamily: WH.fontUI, fontSize: 11, color: WH.aurum, letterSpacing: 0.3 }}>{t.forgot}</span>
        </div>
      </div>

      <div style={{ marginTop: 24 }}>
        <WHButton variant="primary" size="lg" style={{ width: '100%' }}>{t.enterCosmere}</WHButton>
      </div>

      <div style={{ display: 'flex', alignItems: 'center', gap: 12, margin: '22px 0' }}>
        <WHLine color={WH.stone}/>
        <span style={{ fontFamily: WH.fontMono, fontSize: 9, color: WH.ash, letterSpacing: 2, textTransform: 'uppercase', whiteSpace: 'nowrap' }}>{t.orHopVia}</span>
        <WHLine color={WH.stone}/>
      </div>

      <SocialRow/>

      <div style={{ flex: 1 }}/>

      <div style={{ textAlign: 'center', marginTop: 24 }}>
        <span style={{ fontFamily: WH.fontUI, fontSize: 12, color: WH.fog }}>
          {t.newTraveler} <span style={{ color: WH.aurum, fontWeight: 600 }}>{t.beginJourney}</span>
        </span>
      </div>
    </AuthShell>
  );
}

// ─── Register ───
function RegisterScreen() {
  const { t } = useLocale();
  return (
    <AuthShell>
      <div style={{ display: 'flex', alignItems: 'center', gap: 8, marginBottom: 28 }}>
        <div style={{ color: WH.ash }}>{WHIcon.back(WH.ash)}</div>
        <WHKicker size={10} color={WH.fog}>{t.back}</WHKicker>
      </div>

      <div style={{ marginBottom: 24 }}>
        <div style={{ fontFamily: WH.fontDisplay, fontSize: 30, fontWeight: 400, color: WH.parchment, lineHeight: 1.1, letterSpacing: -0.5 }}>
          {t.beginYour}<br/>
          <span style={{ fontStyle: 'italic', color: WH.aurum }}>{t.journey}</span>
        </div>
        <div style={{ marginTop: 10, fontFamily: WH.fontUI, fontSize: 12, color: WH.fog, lineHeight: 1.5 }}>
          {t.nameEtched}
        </div>
      </div>

      <div style={{ display: 'flex', flexDirection: 'column', gap: 12 }}>
        <WHInput placeholder={t.travelerName} value="hoid_the_wit" icon={WHIcon.user(WH.ash)}/>
        <WHInput placeholder={t.email} icon={WHIcon.mail(WH.ash)}/>
        <WHInput placeholder={t.password} icon={WHIcon.lock(WH.ash)}/>
        <WHInput placeholder={t.confirmPw} icon={WHIcon.lock(WH.ash)}/>
      </div>

      <div style={{
        marginTop: 18, padding: 12,
        background: 'rgba(201,166,107,0.06)',
        border: `0.5px solid rgba(201,166,107,0.25)`,
      }}>
        <div style={{ display: 'flex', gap: 10, alignItems: 'flex-start' }}>
          <div style={{ marginTop: 2 }}>{WHIcon.warn(WH.aurum)}</div>
          <div style={{ fontFamily: WH.fontUI, fontSize: 11, color: WH.linen, lineHeight: 1.5 }}>
            {t.spoilerWarn}
          </div>
        </div>
      </div>

      <div style={{ display: 'flex', alignItems: 'center', gap: 10, marginTop: 14 }}>
        <div style={{ width: 14, height: 14, border: `1px solid ${WH.aurum}`, background: WH.aurum, display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
          <svg width="8" height="8" viewBox="0 0 8 8"><path d="M1 4L3 6L7 2" stroke={WH.void} strokeWidth="1.5" fill="none" strokeLinecap="round"/></svg>
        </div>
        <span style={{ fontFamily: WH.fontUI, fontSize: 11, color: WH.fog }}>
          {t.acceptCompact} <span style={{ color: WH.aurum }}>{t.compact}</span>
        </span>
      </div>

      <div style={{ marginTop: 20 }}>
        <WHButton variant="primary" size="lg" style={{ width: '100%' }}>{t.openPortal}</WHButton>
      </div>

      <div style={{ flex: 1 }}/>
    </AuthShell>
  );
}

// ─── Forgot password ───
function ForgotScreen() {
  const { t } = useLocale();
  return (
    <AuthShell>
      <div style={{ display: 'flex', alignItems: 'center', gap: 8, marginBottom: 28 }}>
        <div style={{ color: WH.ash }}>{WHIcon.back(WH.ash)}</div>
        <WHKicker size={10} color={WH.fog}>{t.back}</WHKicker>
      </div>

      <div style={{ marginBottom: 28 }}>
        <div style={{ fontFamily: WH.fontDisplay, fontSize: 30, fontWeight: 400, color: WH.parchment, lineHeight: 1.1, letterSpacing: -0.5 }}>
          {t.lost}<br/>
          <span style={{ fontStyle: 'italic', color: WH.aurum }}>{t.betweenWorlds}</span>
        </div>
        <div style={{ marginTop: 12, fontFamily: WH.fontUI, fontSize: 13, color: WH.fog, lineHeight: 1.6 }}>
          {t.forgotBody}
        </div>
      </div>

      <WHInput placeholder={t.email} value="kaladin@shattered.plain" icon={WHIcon.mail(WH.ash)}/>

      <div style={{ marginTop: 20 }}>
        <WHButton variant="primary" size="lg" style={{ width: '100%' }}>{t.sendMissive}</WHButton>
      </div>

      <div style={{ flex: 1 }}/>

      <div style={{ marginTop: 40, textAlign: 'center' }}>
        <WHEpigraph quote={t.journeyBefore} size={11}/>
      </div>
    </AuthShell>
  );
}

Object.assign(window, { SplashScreen, LoginScreen, RegisterScreen, ForgotScreen });
