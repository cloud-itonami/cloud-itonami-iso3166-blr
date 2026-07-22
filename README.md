# cloud-itonami-iso3166-blr

**BLR**: Republic of Belarus.

- Procurement: Ministry of Antimonopoly Regulation and Trade (MART,
  regulator, Law № 419-З) + goszakupki.by/icetrade.by (RUE National
  Export Promotion Centre) + zakupki.butb.by (Belarusian Universal
  Commodity Exchange) — two separately-authorized e-procurement
  platform operators, not one
- Business registration: executive-committee justice directorate
  (Decree № 1 of 16.01.2009) — a SEPARATE act from UNP/tax
  registration (Ministry of Taxes and Duties, automatic within 5
  working days of state registration)

See [`docs/business-model.md`](docs/business-model.md) for the full
citation set and confidence notes.

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
