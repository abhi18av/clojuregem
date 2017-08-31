(ns user
  (:require [com.rpl.specter :as specter]
            [clojure.string :as string]
            [clojure.spec.alpha :as spec]
            [clojure.test.check.generators :as gen]))

;; LISTS

(def l-1 '(1 2 3 4))


(doseq [1 2 3] (print i))







;; VECTORS


;; HASH-MAP


;; SET

;;;;;;;;;;;;
;; SPEC
;;;;;;;;;;;;


(def suit? #{:club :diamond :heart :spade})
(def rank? (into #{:jack :queen :king :ace} (range 2 11)))
(def deck (for [suit suit? rank rank?] [rank suit]))

(spec/def ::card (spec/tuple rank? suit?))
(spec/def ::hand (spec/* ::card))

(spec/def ::name string?)
(spec/def ::score int?)
(spec/def ::player (spec/keys :req [::name ::score ::hand]))

(spec/def ::players (spec/* ::player))
(spec/def ::deck (spec/* ::card))
(spec/def ::game (spec/keys :req [::players ::deck]))

(def kenny
  {::name "Kenny Rogers"
   ::score 100
   ::hand []})
(spec/valid? ::player kenny)


(gen/sample (spec/gen string?))
;;=> ("" "" "" "" "8" "W" "" "G74SmCm" "K9sL9" "82vC")
(gen/sample (spec/gen #{:club :diamond :heart :spade}))
;;=> (:heart :diamond :heart :heart :heart :diamond :spade :spade :spade :club)

(gen/sample (spec/gen (spec/cat :k keyword? :ns (spec/+ number?))))
