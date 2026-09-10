(ns culture.facts
  "Country-level regional-culture catalog for Belarus (BLR) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"BLR"
   [{:culture/id "blr.dish.draniki"
     :culture/name "Draniki"
     :culture/name-local "дранікі"
     :culture/country "BLR"
     :culture/kind :dish
     :culture/summary "Potato pancakes, known in Belarusian as draniki; the potato pancake is the national dish of Belarus (as well as Ukraine, Russia and Slovakia), particularly popular in Belarus and traditionally eaten with sour cream."
     :culture/url "https://en.wikipedia.org/wiki/Potato_pancake"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "blr.dish.kalduny"
     :culture/name "Kalduny"
     :culture/name-local "калдуны"
     :culture/country "BLR"
     :culture/kind :dish
     :culture/summary "Dumplings stuffed with meat, mushrooms or other ingredients, made in Belarusian, Lithuanian and Polish cuisines; kalduny with smoked ham and mushrooms were long considered Belarus's 'visiting card'."
     :culture/url "https://en.wikipedia.org/wiki/Kalduny"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "blr.dish.potato-babka"
     :culture/name "Potato babka"
     :culture/name-local "бабка"
     :culture/country "BLR"
     :culture/kind :dish
     :culture/summary "Savoury baked dish of grated potatoes with eggs, onions and bacon, popular especially in Belarus and northeastern Poland."
     :culture/url "https://en.wikipedia.org/wiki/Potato_babka"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "blr.beverage.kvass"
     :culture/name "Kvass"
     :culture/name-local "квас"
     :culture/country "BLR"
     :culture/kind :beverage
     :culture/summary "Fermented, low-alcoholic beverage of northeastern Europe with a sweet-sour taste; known in Belarus, which has several kvass-producing breweries and kvass tasting festivals."
     :culture/url "https://en.wikipedia.org/wiki/Kvass"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "blr.beverage.krambambula"
     :culture/name "Krambambula"
     :culture/name-local "крамбамбуля"
     :culture/country "BLR"
     :culture/kind :beverage
     :culture/summary "Belarusian alcoholic mix drink or cocktail typically consisting of red wine and various kinds of liquor, such as gin, vodka or rum."
     :culture/url "https://en.wikipedia.org/wiki/Krambambula_(drink)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "blr.craft.slutsk-sash"
     :culture/name "Slutsk sash"
     :culture/name-local "слуцкі пояс"
     :culture/country "BLR"
     :culture/kind :craft
     :culture/summary "Kontusz sashes manufactured in Slutsk (now in Belarus), known as Slutsk sashes; woven with silk and gold thread, they were the most desirable and most expensive sashes of Polish-Lithuanian noble dress."
     :culture/url "https://en.wikipedia.org/wiki/Kontusz_sash"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "blr.festival.kupala-night"
     :culture/name "Kupala Night"
     :culture/name-local "Купалле"
     :culture/country "BLR"
     :culture/kind :festival
     :culture/summary "Slavic summer-solstice folk holiday with night rituals of water, fire and herbs, celebrated on the night of 6-7 July in Belarus; researchers note the holiday is preserved in its most archaic form by the Belarusians."
     :culture/url "https://en.wikipedia.org/wiki/Kupala_Night"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "blr.festival.slavianski-bazaar"
     :culture/name "Slavianski Bazaar in Vitebsk"
     :culture/name-local "Славянскі базар у Віцебску"
     :culture/country "BLR"
     :culture/kind :festival
     :culture/summary "International Festival of Arts held annually in Vitebsk, Belarus since 1992, featuring Slavic music genres and singing contests."
     :culture/url "https://en.wikipedia.org/wiki/Slavianski_Bazaar"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "blr.heritage.mir-castle"
     :culture/name "Mir Castle Complex"
     :culture/name-local "Мірскі замак"
     :culture/country "BLR"
     :culture/kind :heritage
     :culture/summary "Historic fortified castle in the town of Mir, Belarus, erected in the 16th century in late Brick Gothic style; designated a UNESCO World Heritage Site in December 2000."
     :culture/url "https://en.wikipedia.org/wiki/Mir_Castle_Complex"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-blr culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "BLR"))
                 " BLR entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
