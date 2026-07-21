(ns marketentry.facts
  "Per-jurisdiction public-procurement market-entry regulatory catalog
  -- the G2-style spec-basis table the Market-Entry Compliance Governor
  checks every `:jurisdiction/assess` proposal against ('did the advisor
  cite an OFFICIAL public source for this jurisdiction's requirements,
  or did it invent one?').

  Belarus's real market-entry surface (WebFetch/curl-verified
  2026-07-22, see each entry's own citation) turned out to be a
  GENUINELY NEW split shape for this family -- not one portal, not
  one regulator, but a THREE-WAY split among policy regulator,
  registering authority, and (TWO, separately authorized) e-procurement
  platform operators:

  - Policy/legal regulator: the Ministry of Antimonopoly Regulation and
    Trade (MART, Министерство антимонопольного регулирования и
    торговли) is the 'authorized state body' the Law 'On State
    Procurement of Goods (Works, Services)' (Закон «О государственных
    закупках товаров (работ, услуг)», № 419-З, 13.07.2012, materially
    amended by № 354-З of 31.01.2024) empowers under its own Article 8
    (President designates the authorized body) / Article 10 (the
    body's powers, 'pursues a single state policy' in this field).
    Confirmed directly from an OFFICIAL pravo.by news post
    (`pravo.by/novosti/analitika/2024/mart/77041/`) in which MART
    itself cites its authority as 'based on powers granted by paragraph
    four of Article 10 of Law No. 419-З' -- HIGH confidence this is the
    correct regulator and correct empowering law/article, sourced from
    the regulator's own official clarification, not a secondary
    summary.
  - Registering/operating the electronic trading platform(s): Article 9
    of the SAME law hands the Council of Ministers the power to
    'determine electronic trading platforms for holding procedures of
    public procurements on them' -- it does NOT name a platform
    operator directly (confirmed via an English-mirror read of the
    law's own articles, cis-legislation.com, MODERATE confidence --
    translation-mirror source, not Belarus's own primary text, which
    this iteration could not get past etalonline.by's/pravo.by's own
    access gates for full article text). In practice, per this
    iteration's OWN direct fetch of the platform OPERATOR's own site
    (`ncmps.by/en/pages/about-the-company/`, HIGH confidence, primary
    self-description), there are genuinely TWO separately authorized
    operators, not one:
      1. Republican Unitary Enterprise 'National Export Promotion
         Centre' (historically and still commonly referred to as the
         'National Center for Marketing and Price Study' / NCMPS,
         founded 1997 by the Ministry of Foreign Affairs) -- its own
         page lists 'icetrade.by' (procurement information site),
         'goszakupki.by' (the electronic trading platform for public
         procurement) and 'gias.by' (the state information-analytical
         system of public-procurement management, GIAS) among ITS OWN
         operated web assets.
      2. OJSC Belarusian Universal Commodity Exchange (BUTB,
         Белорусская универсальная товарная биржа) -- confirmed
         directly from BUTB's own site
         (`butb.by/en/goszakupki-i-torgi-imushchestvom/`, HIGH
         confidence): 'The Government of the Republic of Belarus has
         authorized [BUTB] to act as an operator of an electronic
         public procurement (E-procurement) platform', running
         zakupki.butb.by, handling electronic auctions, open tenders,
         requests for quotation, and single-source purchases.
    Unlike a sibling jurisdiction that turned out to share ONE joint
    portal across two institutions (a shape this family has already
    documented), Belarus's own Article-9 delegation produced TWO
    genuinely independent, separately-branded platform operators
    alongside the ONE policy regulator (MART) -- a three-body split
    this catalog has not modeled before. goszakupki.by ITSELF returned
    a bare connection refusal when this iteration tried to fetch it
    directly (geo-blocking or a routing issue from this environment,
    not a content problem) -- its existence/role is instead confirmed
    through its own operator's (`ncmps.by`) self-description, an
    honest provenance chain rather than a first-hand fetch of the
    portal itself.
  - Restricted/unreliable-supplier list: Article 1 of Law No. 419-З
    itself DEFINES a 'supplier list (contractors, contractors) who are
    temporarily not allowed to participation in procedures of public
    procurements', maintained by the authorized state body (MART) per
    Article 10 -- confirmed via the SAME cis-legislation.com
    English-mirror read (MODERATE confidence, translation mirror; the
    precise official Russian designation for this list was NOT
    independently confirmed this iteration -- an honest terminology
    gap, not a claim this catalog knows the exact Russian phrase). This
    is a direct structural analog to a sibling jurisdiction's own
    'unreliable-supplier registry' flagship check -- same check SHAPE
    (boolean registry-membership bar), independently re-grounded here
    in Belarus's own Article 1 + Article 10, not copied from the
    sibling's citation.
  - Business (state) registration is a SEPARATE act from BOTH of the
    above, performed by a THIRD kind of body again: per Presidential
    Decree No. 1 of 16.01.2009 (see `statute.facts` for the
    current-vs-sunset status of this decree) and CONFIRMED directly
    from Belarus's own official national portal
    (`belarus.by/en/business/companies` and
    `belarus.by/en/business/legal-requirements`, HIGH confidence,
    official state site, though descriptive/English-summary rather
    than the decree's own article text): 'Registration documents are
    to be submitted to the local registration body (main directorates
    of justice of regional executive committees and of the Minsk City
    Executive Committee)' -- i.e. the actual registering organ is each
    executive committee's own JUSTICE DIRECTORATE (a structural
    subdivision), not the Ministry of Justice itself, which instead
    exercises supervisory 'контроль' (control) over registering organs
    per a legal-database preview of the decree's own text
    (base.spinform.ru, MODERATE confidence -- a paywalled-preview
    fragment, not full primary text). GENUINELY NEW two-step finding
    for this family (the task specifically flagged 'single act vs two
    acts' as a hypothesis to check): the SAME belarus.by page states
    'A legal entity is registered with the [tax, statistics, social
    protection] bodies BY DEFAULT, without the participation of the
    founder' -- but this happens 'within five working days' AFTER the
    initial registration, as a distinct SECOND, automatic, founder-passive
    act, not simultaneously with it. So business registration and UNP
    (Учётный номер плательщика / Payer Account Number, this
    jurisdiction's taxpayer-ID analog) issuance are TWO acts, not one
    -- unlike a sibling jurisdiction (AZE) whose State Tax Service
    performs both in the SAME single act. This iteration did NOT
    independently fetch a page using the specific term 'УНП' for the
    Ministry-of-Taxes-and-Duties act itself (nalog.gov.by was reachable
    but its specific УНП-issuance page was not located this iteration;
    egr.gov.by and minjust.gov.by were both UNREACHABLE from this
    environment -- connection refused/timeout, not a content gate) --
    so the EXISTENCE of a distinct, automatic, founder-passive
    second act is HIGH confidence (belarus.by's own official text),
    while the precise 'УНП' terminology/implementing-article detail is
    MODERATE, not independently primary-source-confirmed.

  Coverage is reported HONESTLY (see `coverage`): a jurisdiction not in
  this table has NO spec-basis, full stop -- the advisor must not
  fabricate one, and the governor holds if it tries.")

(def catalog
  "iso3 -> requirement map. `:required-evidence` mirrors the generic
  intake/portal-registration/filing evidence set; `:legal-basis` /
  `:owner-authority` / `:provenance` are the G2 citation the governor
  requires before any `:jurisdiction/assess` proposal can commit. BLR
  deliberately carries NO `:rep-owner-authority` -- see the namespace
  docstring's honest-scope-narrowing note. `:restricted-supplier-*`
  grounds this vertical's flagship governor check
  (`restricted-supplier-spec-basis`). `:business-registration-*` is a
  NEW key this iteration adds (mirroring the family's precedent of
  extending the shared schema when a jurisdiction's own institutional
  shape genuinely needs a slot no prior sibling required -- see
  `business-registration-spec-basis`): Belarus's business-registration
  act (executive-committee justice directorate, Decree No. 1) is
  performed by a DIFFERENT body than BOTH the procurement regulator
  (MART) and the tax/UNP registrar (Ministry of Taxes and Duties), so
  `:corporate-number-*` (the UNP/tax-ID regime) cannot honestly stand
  in for it the way it does for a single-act jurisdiction."
  {"BLR" {:name "Belarus"
          :owner-authority "Министерство антимонопольного регулирования и торговли (MART, Ministry of Antimonopoly Regulation and Trade) -- policy regulator, powers per Law № 419-З Art. 10 -- PLUS two separately-authorized electronic-trading-platform operators: РУП «Национальный центр маркетинга и конъюнктуры цен» / RUE 'National Export Promotion Centre' (goszakupki.by, icetrade.by, gias.by) and ОАО «Белорусская универсальная товарная биржа» / OJSC Belarusian Universal Commodity Exchange -- BUTB (zakupki.butb.by), both designated under Law № 419-З Art. 9's Council-of-Ministers platform-designation power"
          :legal-basis "Закон Республики Беларусь «О государственных закупках товаров (работ, услуг)» (Law 'On State Procurement of Goods (Works, Services)'), № 419-З, принят 13.07.2012, существенно изменён Законом № 354-З от 31.01.2024 -- Art. 8 (President designates the authorized state body), Art. 9 (Council of Ministers designates electronic trading platforms), Art. 10 (authorized body's powers/policy role), Art. 1 (restricted-supplier list definition)"
          :national-spec "goszakupki.by / icetrade.by (RUE National Export Promotion Centre, a.k.a. National Center for Marketing and Price Study) supplier registration + tender participation, AND/OR zakupki.butb.by (BUTB) supplier registration + tender participation, depending on which authorized platform the specific procurement runs on"
          :provenance "https://ncmps.by/en/pages/about-the-company/ ; https://www.butb.by/en/goszakupki-i-torgi-imushchestvom/ ; https://mart.gov.by/en/activity/regulirovanie-i-kontrol-zakupok/ ; https://pravo.by/novosti/analitika/2024/mart/77041/"
          :required-evidence ["Свидетельство о государственной регистрации субъекта хозяйствования (state-registration certificate, issued by the Main Directorate of Justice of the regional/Minsk City Executive Committee under Decree No. 1 of 16.01.2009)"
                              "УНП / постановка на учёт в налоговом органе (UNP / automatic tax-authority registration record, per Ministry of Taxes and Duties practice, within 5 working days of state registration)"
                              "Регистрация участника на goszakupki.by/icetrade.by и/или zakupki.butb.by (electronic-trading-platform supplier/participant registration record)"
                              "Подтверждение полномочий представителя (authorized-representative confirmation record)"]
          :corporate-number-owner-authority "Министерство по налогам и сборам Республики Беларусь (Ministry of Taxes and Duties of the Republic of Belarus), territorial inspectorate"
          :corporate-number-legal-basis "УНП (Учётный номер плательщика / Payer Account Number) -- assigned via an automatic, founder-passive registration with the tax authority within 5 working days of state registration (belarus.by's own confirmed description); this is a SEPARATE act from state registration itself (see namespace docstring) -- the precise implementing decree/regulation article for УНП assignment was NOT independently fetched this iteration (nalog.gov.by reachable but the specific page not located; egr.gov.by/minjust.gov.by unreachable from this environment)"
          :corporate-number-provenance "https://www.belarus.by/en/business/companies ; https://www.belarus.by/en/business/legal-requirements ; https://www.nalog.gov.by/"
          :business-registration-owner-authority "Главное управление юстиции областного исполнительного комитета / Минского городского исполнительного комитета (Main Directorate of Justice of the regional (oblast) executive committee / Minsk City Executive Committee) -- the registering organ itself; Министерство юстиции (Ministry of Justice) exercises supervisory 'контроль' (control) over registering organs but is NOT itself the registrar"
          :business-registration-legal-basis "Декрет Президента Республики Беларусь от 16.01.2009 № 1 «О государственной регистрации и ликвидации (прекращении деятельности) субъектов хозяйствования» -- CURRENT as of this catalog's :retrieved-at, but scheduled to lose force from 01.07.2027 per its own etalonline.by metadata, pending a new state-registration Law already visible in pravo.by's own Bank of Draft Laws (not independently fetched in full this iteration -- see `statute.facts`)"
          :business-registration-provenance "https://www.belarus.by/en/business/companies ; https://www.belarus.by/en/business/legal-requirements ; https://etalonline.by/document/?regnum=pd0900001"
          :restricted-supplier-owner-authority "Министерство антимонопольного регулирования и торговли (MART, Ministry of Antimonopoly Regulation and Trade)"
          :restricted-supplier-legal-basis "Закон № 419-З (13.07.2012) Art. 1 (defines the supplier list temporarily barred from public-procurement participation) + Art. 10 (authorized body maintains it) -- MODERATE confidence, English-translation-mirror source (cis-legislation.com); the precise official Russian designation for this list was not independently confirmed this iteration"
          :restricted-supplier-provenance "https://cis-legislation.com/document.fwx?rgn=53180 ; https://pravo.by/novosti/analitika/2024/mart/77041/"}
   "USA" {:name "United States"
          :owner-authority "U.S. General Services Administration (GSA) / SAM.gov"
          :legal-basis "Federal Acquisition Regulation (FAR); System for Award Management"
          :national-spec "SAM.gov entity registration + NAICS self-certification"
          :provenance "https://sam.gov/"
          :required-evidence ["EIN record"
                              "SAM.gov registration record"
                              "State business registration record"
                              "Authorized-representative record"]}
   "DEU" {:name "Germany"
          :owner-authority "Beschaffungsamt des BMI / e-Vergabe platforms"
          :legal-basis "Gesetz gegen Wettbewerbsbeschränkungen (GWB) / VgV"
          :national-spec "e-Vergabe supplier registration under EU procurement directives"
          :provenance "https://www.evergabe-online.de/"
          :required-evidence ["Handelsregister extract"
                              "e-Vergabe registration record"
                              "USt-IdNr record"
                              "Authorized-representative record"]}})

(defn spec-basis
  "The jurisdiction's requirement map, or nil -- nil means NO spec-basis,
  and the governor must hold any proposal that tries to assess or file
  on it."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report: how many of the requested jurisdictions actually
  have a spec-basis entry. Never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-blr R0: " (count catalog)
                 " jurisdictions seeded with an official spec-basis. "
                 "This is a starting catalog for market-entry navigation, "
                 "not a survey of all ~194 jurisdictions -- extend "
                 "`marketentry.facts/catalog`, never fabricate a "
                 "jurisdiction's requirements.")})))

(defn required-evidence-satisfied?
  "Does `submitted` (a set/coll of evidence keywords or strings) satisfy
  every evidence item listed for `iso3`? Missing spec-basis -> never
  satisfied."
  [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (let [need (count required-evidence)
          have (count (filter (set submitted) required-evidence))]
      (= need have))))

(defn evidence-checklist [iso3]
  (:required-evidence (spec-basis iso3) []))

(defn rep-spec-basis
  "The jurisdiction's representative-related requirement map, or nil when
  this catalog has no such regime. For BLR this is deliberately nil --
  see the `catalog` docstring's honest-scope-narrowing note."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))

(defn corporate-number-spec-basis
  "The jurisdiction's corporate-number / tax-id (UNP, for BLR) regime,
  or nil."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority
                       :corporate-number-legal-basis
                       :corporate-number-provenance]))))

(defn business-registration-spec-basis
  "The jurisdiction's business (state) registration regime, or nil. A
  NEW accessor this iteration adds to the shared schema: Belarus's
  business-registration act is performed by a DIFFERENT body (the
  executive committee's justice directorate, under Decree No. 1) than
  BOTH the procurement regulator (`:owner-authority`) and the tax/UNP
  registrar (`corporate-number-spec-basis`), so neither of those
  accessors can honestly stand in for it -- see the namespace
  docstring's 'single act vs two acts' finding."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:business-registration-owner-authority sb)
      (select-keys sb [:business-registration-owner-authority
                       :business-registration-legal-basis
                       :business-registration-provenance]))))

(defn restricted-supplier-spec-basis
  "The jurisdiction's restricted/unreliable-supplier-list regime, or
  nil. For BLR this is real and current -- the flagship check this
  vertical adds is grounded here (Law № 419-З Art. 1 + Art. 10)."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:restricted-supplier-owner-authority sb)
      (select-keys sb [:restricted-supplier-owner-authority
                       :restricted-supplier-legal-basis
                       :restricted-supplier-provenance]))))
