(ns clojuregem.rbrepl.scratch-analyzer
  (:require [clojure.tools.analyzer.jvm :as ana.jvm]
            [clojure.tools.analyzer.passes.jvm.emit-form :as e]))

(e/emit-hygienic-form (ana.jvm/analyze '(let [a 1 a a] a)))

(defn ast-magic-pprint [quoted-code]
 (clojure.pprint/pprint  (e/emit-hygienic-form (ana.jvm/analyze quoted-code))))

(defn ast-magic [quoted-code]
    (e/emit-hygienic-form (ana.jvm/analyze quoted-code)))


(ast-magic '(let [a 1 a a] a))





(ast-magic '(defn- checked-aset
   ([array idx val]
    (try
      (assert (or (array? array) (js/goog.isArrayLike array)))
      (assert (number? idx))
      (assert (not (neg? idx)))
      (assert (< idx (alength array)))
      (catch :default e
        (maybe-warn e)))
    (unchecked-set array idx val))
   ([array idx idx2 & idxv]
    (apply checked-aset (checked-aget array idx) idx2 idxv))))


(ast-magic '(defn is_proto_
   [x]
   (identical? (.-prototype (.-constructor x)) x)))




(def add9-ast (ast-magic    '(defn add9 ([x] (+ x 9))
                           ([x y] (+ x y 9)))))



(-> '(let [a a] a)
    (ana.jvm/analyze (assoc (ana.jvm/empty-env)
                            :locals '{a {:op    :binding
                                         :name  a
                                         :form  a
                                         :local :let}}))
    e/emit-hygienic-form)

(ast-magic '(+ 1 1 (- 1 2)))

(ana.jvm/analyze add9)

