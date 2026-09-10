(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest blr-has-spec-basis
  (let [sb (facts/spec-basis "BLR")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (some? (facts/corporate-number-spec-basis "BLR")))
    (is (some? (facts/business-registration-spec-basis "BLR")))
    (is (some? (facts/restricted-supplier-spec-basis "BLR")))))

(deftest blr-rep-spec-basis-is-honestly-absent
  (testing "no verified resident/domestic-representative mandate for BLR public-procurement participation -- deliberately not claimed"
    (is (nil? (facts/rep-spec-basis "BLR")))))

(deftest blr-business-registration-is-a-separate-body-from-tax-and-procurement
  (testing "business registration (executive-committee justice directorate) is a DIFFERENT body than the tax/UNP registrar and the procurement regulator -- see namespace docstring 'single act vs two acts' finding"
    (let [reg (facts/business-registration-spec-basis "BLR")
          tax (facts/corporate-number-spec-basis "BLR")]
      (is (some? reg))
      (is (some? tax))
      (is (not= (:business-registration-owner-authority reg)
                (:corporate-number-owner-authority tax))))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ")))
  (is (nil? (facts/business-registration-spec-basis "ATL")))
  (is (nil? (facts/restricted-supplier-spec-basis "ATL"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "BLR")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "BLR" all)))
    (is (not (facts/required-evidence-satisfied? "BLR" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["BLR" "USA" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 2 (:covered c)))
    (is (= ["ATL"] (:missing-jurisdictions c)))))
