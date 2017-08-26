(ns clojuregem.rbrepl.scratch-analyzer
  (:require [clojure.tools.analyzer.jvm :as ana.jvm]
            [clojure.tools.analyzer.passes.jvm.emit-form :as e]))

(e/emit-hygienic-form (ana.jvm/analyze '(let [a 1 a a] a)))

(defn ast-magic-pprint [quoted-code]
 (clojure.pprint/pprint  (e/emit-hygienic-form (ana.jvm/analyze quoted-code))))

(defn ast-magic [quoted-code]
    (e/emit-hygienic-form (ana.jvm/analyze quoted-code)))


(ast-magic '(let [a 1 a a] a))


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

