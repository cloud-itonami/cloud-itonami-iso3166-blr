# cloud-itonami-iso3166-blr

**`:implemented`** for **BLR** (Republic of Belarus). Independent
public-sector market-entry & procurement compliance actor: a
MarketEntry-LLM advisor sealed behind a Market-Entry Compliance
Governor, a langgraph-clj StateGraph, and an append-only audit ledger
(see `orgs/cloud-itonami/cloud-itonami-iso3166-ago`'s `marketentry.*`
for this fleet's canonical structural template).

```
clojure -M:dev:test
```

## Three-way institutional split -- the single most important disambiguation

Belarus's own market-entry surface is genuinely a THREE-way split, not
one portal/one regulator like most sibling jurisdictions:

- **Policy regulator**: Ministry of Antimonopoly Regulation and Trade
  (MART, Министерство антимонопольного регулирования и торговли) --
  powers per Law № 419-З (13.07.2012, amended by № 354-З of
  31.01.2024) Art. 10, confirmed directly from MART's own pravo.by
  news post citing its own Art. 10 para. 4 powers.
- **TWO separately-authorized e-procurement platform operators**
  (Law № 419-З Art. 9 delegates platform designation to the Council of
  Ministers, which named two, not one): RUE "National Export Promotion
  Centre" / National Center for Marketing and Price Study
  (goszakupki.by, icetrade.by, gias.by) AND OJSC Belarusian Universal
  Commodity Exchange -- BUTB (zakupki.butb.by).
- **Business (state) registration** is performed by a THIRD kind of
  body again: the Main Directorate of Justice of the regional/Minsk
  City Executive Committee, under Presidential Decree № 1 of
  16.01.2009 -- a SEPARATE act from UNP/tax registration (Ministry of
  Taxes and Duties, automatic within 5 working days of state
  registration).

See [`docs/business-model.md`](docs/business-model.md) for the full
citation set and per-claim confidence notes (`src/marketentry/facts.cljc`
and `src/statute/facts.cljc` carry the same discipline in their own
namespace docstrings).

## Governor checks (all HARD, human-unoverridable) and their sources

1. **Spec-basis** -- a `:jurisdiction/assess`/`:filing/draft`/
   `:filing/submit` proposal must cite an official source
   (`marketentry.facts`), never an invented one.
2. **Evidence-checklist completeness** -- state-registration
   certificate + UNP tax registration + goszakupki.by/icetrade.by
   and/or zakupki.butb.by participant registration + authorized-
   representative confirmation must actually be on file before a
   filing draft/submit.
3. **Restricted-supplier-list membership (flagship check)** -- an
   engagement's own declared `:on-restricted-supplier-list?` is
   INDEPENDENTLY re-verified. Grounded in Law № 419-З Art. 1 (defines
   the supplier list temporarily barred from public-procurement
   participation) + Art. 10 (MART maintains it). UNCONDITIONAL --
   evaluated for every `:filing/submit`.
   Sources: <https://cis-legislation.com/document.fwx?rgn=53180> ,
   <https://pravo.by/novosti/analitika/2024/mart/77041/>
4. **Engagement-fee ground-truth recompute** -- this actor's own
   service fee (`base-fee + monthly-rate x monitoring-months`) is
   independently recomputed against the claimed total before submit.
5. **UNP unverified** -- when an engagement declares `:requires-unp?
   true`, INDEPENDENTLY check `:unp-verified?`. CONDITIONAL on the
   engagement's own ground truth. Grounded in the UNP (Учётный номер
   плательщика / Payer Account Number) the Ministry of Taxes and
   Duties assigns automatically within 5 working days of state
   registration.
   Sources: <https://www.belarus.by/en/business/companies> ,
   <https://www.belarus.by/en/business/legal-requirements> ,
   <https://www.nalog.gov.by/>
6. **Unfriendly-state investor unauthorized (NATIONALITY-CONDITIONAL)**
   -- when an engagement declares `:from-designated-unfriendly-state?
   true`, INDEPENDENTLY check `:special-government-permission?`. This
   check fires ONLY for engagements the ground truth itself flags as
   being from a designated 'unfriendly state' -- it must NEVER apply to
   foreign investors generically (see
   `test/marketentry/governor_contract_test.clj`). Grounded
   narratively in a July 2022 Belarusian government decision requiring
   special government permission before investors from designated
   'unfriendly states' (incl. the US) may sell/alienate shares,
   reorganize, or withdraw as participants -- no specific decree/law
   instrument number was independently verified this iteration, so
   this is cited narratively via the U.S. State Department's own
   Investment Climate Statement rather than a fabricated instrument
   number.
   Source: <https://www.state.gov/wp-content/uploads/2025/09/638719_2025-Belarus-Investment-Climate-Statement-Accessible-9.2.2025.pdf>
7. **Confidence floor / actuation gate** -- low advisor confidence, or
   the op being a real actuation (`:filing/draft`/`:filing/submit`),
   always escalates to a human -- see Actuation below.

Two more structural guards (not part of the numbered list above,
present in every actor this fleet has shipped): double-draft and
double-submit prevention off dedicated `:drafted?`/`:submitted?`
facts.

A restriction NOT modeled here: a mandatory resident/domestic-
representative requirement for public-tender participation
specifically. This iteration's research did not turn up comparably
strong, WebFetch-verified evidence of such a mandate for Belarus, so
`marketentry.facts/rep-spec-basis` deliberately returns `nil` for BLR
rather than guessing.

## Actuation -- `:filing/draft` and `:filing/submit` are ALWAYS human-gated

Drafting a real portal registration package
(goszakupki.by/icetrade.by and/or zakupki.butb.by) and actually
submitting one are the two real-world acts this actor performs. Two
independent layers agree neither ever auto-commits, at ANY rollout
phase (0 through 3):

- `marketentry.governor`'s `high-stakes` set (`:actuation/draft-filing`
  `:actuation/submit-filing`) always forces an `:escalate` disposition,
  even when every hard check is clean.
- `marketentry.phase`'s per-phase `:auto` sets deliberately never
  include `:filing/draft`/`:filing/submit`, including at phase 3
  (`supervised-auto`) -- see the namespace docstring's explicit note
  not to add them there.

`interrupt-before #{:request-approval}` (`marketentry.operation`)
pauses the StateGraph run at this point; only an explicit human
`{:status :approved}` resume lets the record commit to the SSoT.

AGPL-3.0-or-later.

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Belarus:

- `src/culture/facts.cljc` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
