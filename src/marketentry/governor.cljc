(ns marketentry.governor
  "Market-Entry Compliance Governor -- the independent compliance layer
  that earns the MarketEntry-LLM the right to commit. The LLM has no
  notion of Belarusian procurement law, whether a claimed engagement
  fee actually equals base + months x rate, whether an engagement's own
  declared restricted-supplier-list membership was actually checked,
  whether a UNP (Учётный номер плательщика / Payer Account Number) has
  been verified for a filing that requires it, or when a draft stops
  being a draft and becomes a real-world goszakupki.by/zakupki.butb.by
  portal submission, so this MUST be a separate system able to *reject*
  a proposal and fall back to HOLD.

  `:itonami.blueprint/governor` is `:market-entry-compliance-governor`
  (shared family keyword on blueprints).

  This blueprint's own text (docs/business-model.md Trust Controls:
  'any actual portal registration or filing submission requires
  Market-Entry Compliance Governor clearance and always escalates to
  human sign-off'; 'a false or fabricated regulatory-requirement claim
  is a HARD hold') names exactly the checks below.

  Seven checks, in priority order, ALL HARD violations: a human
  approver CANNOT override them. The confidence/actuation gate is
  SOFT: it asks a human to look (low confidence / actuation), and the
  human may approve -- but see `marketentry.phase`: for `:stake
  :actuation/draft-filing`/`:actuation/submit-filing` NO phase ever
  allows auto-commit either. Two independent layers agree that
  actuation is always a human call.

    1. Spec-basis                  -- did the jurisdiction proposal cite
                                       an OFFICIAL source
                                       (`marketentry.facts`), or invent
                                       one?
    2. Evidence incomplete         -- for `:filing/draft`/
                                       `:filing/submit`, has the
                                       jurisdiction actually been
                                       assessed with a full evidence
                                       checklist on file?
    3. Restricted-supplier list
       membership                    -- for `:filing/submit`,
                                       INDEPENDENTLY verify the
                                       engagement's own declared
                                       `:on-restricted-supplier-list?`
                                       is not true. FLAGSHIP check for
                                       this jurisdiction -- UNCONDITIONAL
                                       (evaluated for every
                                       `:filing/submit`, not gated
                                       behind a `:requires-X?` flag) but
                                       a boolean registry-membership
                                       check rather than a numeric
                                       threshold recompute. Grounded in
                                       Law № 419-З (13.07.2012) Art. 1
                                       (defines the supplier list
                                       temporarily barred from public-
                                       procurement participation) + Art.
                                       10 (MART maintains it).
    4. Engagement fee mismatch     -- for `:filing/submit`,
                                       INDEPENDENTLY recompute whether
                                       the engagement's own `:claimed-
                                       fee` equals `base-fee +
                                       monthly-rate x monitoring-
                                       months` -- honest reapplication
                                       of the ground-truth-recompute
                                       discipline sibling actors use.
    5. UNP unverified                -- for `:filing/submit`, when the
                                       engagement declares
                                       `:requires-unp? true`,
                                       INDEPENDENTLY check
                                       `:unp-verified?`. CONDITIONAL on
                                       the engagement's own ground
                                       truth. Grounded in the UNP
                                       (Учётный номер плательщика /
                                       Payer Account Number) the
                                       Ministry of Taxes and Duties
                                       assigns automatically following
                                       state registration (see
                                       `marketentry.facts`).
    6. Unfriendly-state investor
       unauthorized                  -- for `:filing/submit`, when the
                                       engagement declares
                                       `:from-designated-unfriendly-
                                       state? true`, INDEPENDENTLY
                                       check `:special-government-
                                       permission?`. NATIONALITY-
                                       CONDITIONAL -- fires ONLY for
                                       engagements the ground truth
                                       itself flags as being from a
                                       designated 'unfriendly state';
                                       it must NEVER apply to foreign
                                       investors generically. Grounded
                                       narratively in the July 2022
                                       Belarusian government decision
                                       restricting unfriendly-state
                                       investor share-sale/
                                       reorganization/withdrawal
                                       (State Dept ICS 2025 -- see
                                       `marketentry.facts` for the
                                       no-fabricated-instrument-number
                                       caveat).
    7. Confidence floor / actuation
       gate                          -- LLM confidence below threshold,
                                       OR the op is `:filing/draft`/
                                       `:filing/submit` (REAL acts)
                                       -> escalate.

  Two more guards, double-draft/double-submit prevention, are enforced
  off dedicated `:drafted?`/`:submitted?` facts (never a `:status`
  value)."
  (:require [marketentry.facts :as facts]
            [marketentry.registry :as registry]
            [marketentry.store :as store]))

(def confidence-floor 0.6)

(def high-stakes
  "Stakes grave enough to always require a human, even when clean.
  Drafting a real portal package and submitting a real portal
  registration are the two real-world actuation events this actor
  performs."
  #{:actuation/draft-filing :actuation/submit-filing})

;; ----------------------------- checks -----------------------------

(defn- spec-basis-violations
  "A `:jurisdiction/assess` (or `:filing/draft`/`:filing/submit`)
  proposal with no spec-basis citation is a HARD violation -- never
  invent a jurisdiction's market-entry requirements."
  [{:keys [op]} proposal]
  (when (contains? #{:jurisdiction/assess :filing/draft :filing/submit} op)
    (let [value (:value proposal)]
      (when (or (empty? (:cites proposal))
                (and (contains? value :spec-basis) (nil? (:spec-basis value))))
        [{:rule :no-spec-basis
          :detail "公式spec-basisの引用が無い提案は法域要件として扱えない"}]))))

(defn- evidence-incomplete-violations
  "For `:filing/draft`/`:filing/submit`, the jurisdiction's required
  registration evidence must actually be satisfied."
  [{:keys [op subject]} st]
  (when (contains? #{:filing/draft :filing/submit} op)
    (let [e (store/engagement st subject)
          assessment (store/assessment-of st subject)]
      (when-not (and assessment
                     (facts/required-evidence-satisfied?
                      (:jurisdiction e) (:checklist assessment)))
        [{:rule :evidence-incomplete
          :detail "法域の必要書類(UNP/goszakupki.by・zakupki.butb.by登録/代理人確認等)が充足していない状態での提案"}]))))

(defn- restricted-supplier-listed-violations
  "For `:filing/submit`, INDEPENDENTLY verify the engagement's own
  declared restricted-supplier-list membership is not true -- the
  flagship check this vertical adds. This is evaluated for EVERY
  `:filing/submit`, unconditionally (not gated behind a `:requires-X?`
  engagement flag), because list membership is itself a categorical
  bar to further state-procurement participation under Belarus's own
  Law № 419-З Art. 1 -- there is no engagement-declared precondition
  to check first."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when (true? (:on-restricted-supplier-list? e))
        [{:rule :restricted-supplier-listed
          :detail (str subject " は公共調達参加制限供給者リスト(Law No.419-З Art.1)に登録済み -- 提出提案は進められない")}]))))

(defn- engagement-fee-mismatch-violations
  "For `:filing/submit`, INDEPENDENTLY recompute whether the
  engagement's own claimed fee equals base + months x rate."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when-not (registry/engagement-fee-matches-claim? e)
        [{:rule :engagement-fee-mismatch
          :detail (str subject " の申告手数料(" (:claimed-fee e)
                      ")が独立再計算値(" (registry/compute-engagement-fee e) ")と一致しない")}]))))

(defn- unp-unverified-violations
  "For `:filing/submit`, when the engagement declares `:requires-unp?
  true`, INDEPENDENTLY check `:unp-verified?` -- CONDITIONAL on the
  engagement's own ground truth. Grounded in the UNP (Учётный номер
  плательщика / Payer Account Number) the Ministry of Taxes and Duties
  assigns automatically following state registration."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when (and (true? (:requires-unp? e))
                 (not (true? (:unp-verified? e))))
        [{:rule :unp-unverified
          :detail (str subject " はUNP(Учётный номер плательщика)確認を要するが未確認 -- 提出提案は進められない")}]))))

(defn- unfriendly-state-investor-violations
  "For `:filing/submit`, when the engagement declares
  `:from-designated-unfriendly-state? true`, INDEPENDENTLY verify
  `:special-government-permission?` is also true -- NATIONALITY-
  CONDITIONAL, grounded narratively in the July 2022 Belarusian
  government decision restricting designated-unfriendly-state
  investors' share-sale/reorganization/withdrawal without special
  government permission (State Dept ICS 2025, no fabricated decree
  number -- see `marketentry.facts`). This check is a structural
  no-op for every engagement that is not itself flagged
  `:from-designated-unfriendly-state? true` -- it must NEVER apply to
  foreign investors generically, only to those the engagement's own
  ground truth names as being from a designated 'unfriendly state'."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when (and (true? (:from-designated-unfriendly-state? e))
                 (not (true? (:special-government-permission? e))))
        [{:rule :unfriendly-state-investor-unauthorized
          :detail (str subject " は指定「非友好国」投資家に該当し特別政府許可が未取得 "
                      "(2022年7月ベラルーシ政府決定、State Dept ICS 2025) -- 提出提案は進められない")}]))))

(defn- already-drafted-violations
  "For `:filing/draft`, refuses to draft the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/draft)
    (when (store/engagement-already-drafted? st subject)
      [{:rule :already-drafted
        :detail (str subject " は既にドラフト済み")}])))

(defn- already-submitted-violations
  "For `:filing/submit`, refuses to submit the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (when (store/engagement-already-submitted? st subject)
      [{:rule :already-submitted
        :detail (str subject " は既に提出済み")}])))

(defn check
  "Censors a MarketEntry-LLM proposal against the governor rules.
  Returns {:ok? bool :violations [..] :confidence c :escalate? bool
  :high-stakes? bool :hard? bool}."
  [request _context proposal st]
  (let [hard (into []
                   (concat (spec-basis-violations request proposal)
                           (evidence-incomplete-violations request st)
                           (restricted-supplier-listed-violations request st)
                           (engagement-fee-mismatch-violations request st)
                           (unp-unverified-violations request st)
                           (unfriendly-state-investor-violations request st)
                           (already-drafted-violations request st)
                           (already-submitted-violations request st)))
        conf (:confidence proposal 0.0)
        low? (< conf confidence-floor)
        stakes? (boolean (high-stakes (:stake proposal)))
        hard? (boolean (seq hard))]
    {:ok?          (and (not hard?) (not low?) (not stakes?))
     :violations   hard
     :confidence   conf
     :hard?        hard?
     :escalate?    (and (not hard?) (or low? stakes?))
     :high-stakes? stakes?}))

(defn hold-fact
  "The audit fact written when a proposal is rejected (HOLD)."
  [request context verdict]
  {:t          :governor-hold
   :op         (:op request)
   :actor      (:actor-id context)
   :subject    (:subject request)
   :disposition :hold
   :basis      (mapv :rule (:violations verdict))
   :violations (:violations verdict)
   :confidence (:confidence verdict)})
