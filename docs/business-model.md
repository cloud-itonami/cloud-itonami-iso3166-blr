# Republic of Belarus

Citations verified 2026-07-22 (WebFetch/curl, `pdftotext`/HTML-text
extraction where needed -- see `src/marketentry/facts.cljc` and
`src/statute/facts.cljc` namespace docstrings for the full per-claim
confidence breakdown):

- **Procurement regulator**: Ministry of Antimonopoly Regulation and
  Trade (MART) -- Law "On State Procurement of Goods (Works,
  Services)" № 419-З (13.07.2012, amended by № 354-З of 31.01.2024),
  Art. 8/9/10. Confirmed via an official pravo.by news post in which
  MART cites its own Art. 10 para. 4 powers (HIGH confidence).
- **E-procurement platforms -- genuinely TWO, not one**:
  goszakupki.by / icetrade.by / gias.by, operated by RUE "National
  Export Promotion Centre" (a.k.a. National Center for Marketing and
  Price Study, ncmps.by) -- confirmed directly from the operator's own
  site (HIGH confidence). AND zakupki.butb.by, operated by the
  Belarusian Universal Commodity Exchange (BUTB) under a separate
  Council-of-Ministers authorization -- confirmed directly from BUTB's
  own site (HIGH confidence). goszakupki.by itself was unreachable
  (connection refused) from this session's environment; its role is
  confirmed through its own operator's page instead.
- **Business registration**: Main Directorate of Justice of the
  regional (oblast) / Minsk City Executive Committee, under
  Presidential Decree № 1 of 16.01.2009 -- CURRENT, but scheduled to
  lose force from 01.07.2027 pending a new Law already visible in
  pravo.by's own Bank of Draft Laws (not independently fetched in full
  this iteration). Confirmed via Belarus's own official portal,
  belarus.by (HIGH confidence, though descriptive rather than the
  decree's own article text).
- **UNP (tax ID)**: Ministry of Taxes and Duties -- a SEPARATE,
  automatic, founder-passive act within 5 working days of state
  registration (belarus.by, HIGH confidence for the two-act structure;
  MODERATE for the precise implementing-article detail, which this
  iteration could not independently fetch -- egr.gov.by/minjust.gov.by
  were unreachable from this environment).
- **Restricted-supplier list**: Law № 419-З Art. 1 + Art. 10, maintained
  by MART (MODERATE confidence -- English-translation-mirror source,
  cis-legislation.com, not Belarus's own primary text, which was gated
  behind a "demonstration mode" login wall on etalonline.by/pravo.by).
- **"Unfriendly states" foreign-investor restriction**: a July 2022
  Belarusian government decision prohibits investors from designated
  "unfriendly states" (incl. the US) from selling/alienating shares,
  reorganizing, or withdrawing as participants without special
  government permission (initially ~190 designated entities, expanded
  to 1,849 by January 2023); January 2023 added "temporary external
  management" (up to 18 months) and state-seizure grounds for
  unfriendly-states-linked property. NO specific decree/law instrument
  number was independently verified this iteration -- cited
  narratively via the U.S. State Department's own 2025 Belarus
  Investment Climate Statement, not a fabricated instrument number.
  NATIONALITY-CONDITIONAL, not a uniform foreign-investor bar --
  `marketentry.governor`'s corresponding check fires only when an
  engagement is itself flagged `:from-designated-unfriendly-state?
  true`. Source:
  https://www.state.gov/wp-content/uploads/2025/09/638719_2025-Belarus-Investment-Climate-Statement-Accessible-9.2.2025.pdf
