(ns association.facts-test
  (:require [clojure.test :refer [deftest is]]
            [association.facts :as facts]))

(deftest ahla-has-spec-basis
  (let [sb (facts/spec-basis "ahla")]
    (is (= 2 (count sb)))
    (is (every? #(= "5510" (:association-rule/isic %)) sb))
    (is (every? #(= "USA" (:association-rule/country %)) sb))))

(deftest unknown-association-has-no-spec-basis
  (is (nil? (facts/spec-basis "jhra")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["ahla" "jhra"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["jhra"] (:missing-associations c)))))

(deftest by-topic-filters
  (is (= ["ahla.5-star-promise"]
         (mapv :association-rule/id (facts/by-topic "ahla" :worker-safety))))
  (is (empty? (facts/by-topic "ahla" :labor)))
  (is (empty? (facts/by-topic "jhra" :governance))))
