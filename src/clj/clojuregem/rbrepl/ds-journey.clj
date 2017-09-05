(ns user
  (:require [com.rpl.specter :as specter]
            [clojure.string :as string]
            [clojure.spec.alpha :as spec]
            [clojure.test.check.generators :as gen]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; LISTS

(def l-1 '(1 2 3))

(into '() l-1)
(into [] l-1)
;; Convert list to a map
;(into {} l-1)
(into #{} l-1)


(doseq [l l-1]
  (prn (inc l)))




(doseq [[x y] (map list [1 2 3] [1 2 3])]
  (prn (* x y)))


(first l-1)

(second l-1)

(nth l-1 0)

(nthrest l-1 0)

(nthnext l-1 1)

(drop 1 l-1 )


(seq l-1)

(conj l-1 1)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; VECTORS

(def v-1 [1 2 3])

(into '() v-1)
(into [] v-1)
;; Convert vector to a map
;(into {} v-1)
(into #{} v-1)

(seq v-1)



(conj v-1 1)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; HASH-MAP

(def m-1 {:1 1 :2 2 :3 3})



(into '() m-1)
(into [] m-1)
(into {} m-1)
(into #{} m-1)



(def m-2 {:a "a" :b "b" :c {:1 1 :2 2}})



(:a m-2)
(m-2 :a)
(get m-2 :a)


(get-in m-2 [:c :1] "Not found")

(->
 m-2
 :c
 :1)


(seq m-1)
(seq? m-1)



(assoc m-1 :g "g")



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; SET


(def s-1 #{1 2 3})

(into '() s-1)
(into [] s-1)
;; Convert set to a map
;(into {} s-1)
(into #{} s-1)



(first s-1)

(second s-1)




(seq s-1)
























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

(gen/sample (spec/gen #{:club :diamond :heart :spade}))

(gen/sample (spec/gen (spec/cat :k keyword? :ns (spec/+ number?))))
