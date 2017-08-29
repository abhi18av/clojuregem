(ns clojuregem.rbrepl.rbir-dsl
    (:require [clojure.tools.analyzer.jvm :as ana.jvm]
              [clojure.tools.analyzer.passes.jvm.emit-form :as e]))

(ana.jvm/analyze '1)

(def expr-map
 (ana.jvm/analyze '(+ 1 2 (- 3 4))))

;;;;output

(e/emit-hygienic-form (ana.jvm/analyze '1))


(get-in expr-map [:args ] "Nope")



;(e/emit-form (ana.jvm/analyze '1))

(e/emit-hygienic-form (ana.jvm/analyze '1))

(def x1 (ana.jvm/analyze '1))

(comment


  {:op :const,
   :env
   {:context :ctx/expr,
    :locals {},
    :ns clojuregem.rbrepl.rbir-dsl,
    :file
    "/home/eklavya/ProjectEklavya/Mordor/Clojewel/clojuregem/src/clj/clojuregem/rbrepl/rbir_dsl.clj"},
   :type :number,
   :literal? true,
   :val 1,
   :form 1,
   :top-level true,
   :o-tag long,
   :tag long}

  )


(def  x2 (ana.jvm/analyze '(+ 1)))
(comment 



{:op :invoke,
 :form (+ 1),
 :env
 {:context :ctx/expr,
  :locals {},
  :ns clojuregem.rbrepl.rbir-dsl,
  :column 28,
  :line 36,
  :file
  "/home/eklavya/ProjectEklavya/Mordor/Clojewel/clojuregem/src/clj/clojuregem/rbrepl/rbir_dsl.clj"},
 :fn
 {:op :var,
  :assignable? false,
  :var #'clojure.core/+,
  :meta
  {:added "1.2",
   :ns #namespace[clojure.core],
   :name +,
   :file "clojure/core.clj",
   :inline-arities #function[clojure.core/>1?],
   :column 1,
   :line 976,
   :arglists ([] [x] [x y] [x y & more]),
   :doc
   "Returns the sum of nums. (+) returns 0. Does not auto-promote\n  longs, will throw on overflow. See also: +'",
   :inline #function[clojure.core/nary-inline/fn--5083]},
  :env
  {:context :ctx/expr,
   :locals {},
   :ns clojuregem.rbrepl.rbir-dsl,
   :column 28,
   :line 36,
   :file
   "/home/eklavya/ProjectEklavya/Mordor/Clojewel/clojuregem/src/clj/clojuregem/rbrepl/rbir_dsl.clj"},
  :form +,
  :o-tag java.lang.Object,
  :arglists ([] [x] [x y] [x y & more])},
 :args
 [{:op :const,
   :env
   {:context :ctx/expr,
    :locals {},
    :ns clojuregem.rbrepl.rbir-dsl,
    :column 28,
    :line 36,
    :file
    "/home/eklavya/ProjectEklavya/Mordor/Clojewel/clojuregem/src/clj/clojuregem/rbrepl/rbir_dsl.clj"},
   :type :number,
   :literal? true,
   :val 1,
   :form 1,
   :o-tag long,
   :tag java.lang.Long}],
 :meta {:line 36, :column 28},
 :children [:fn :args],
 :top-level true,
 :o-tag java.lang.Object}
clojuregem.rbrepl.rbir-dsl> 

)



(def  x3 (ana.jvm/analyze '(+ 1 1)))

(def  x4 (ana.jvm/analyze '(+ 1 1 1)))




(def x (ana.jvm/analyze '1))

(for [i ( keys x)]
  (i x ))


;; (for [i ( keys x)]
;;   (do
;;     (println i  " : " (i x))))




(e/emit-hygienic-form (ana.jvm/analyze '1))




      s(:begin) : %q{()}

      s(:kwbegin) : %q{begin end}

      s(:true) : %q{true}

      s(:false) : %q{false}

      s(:int, 42) : %q{42}

      s(:int, -42) : %q{-42}

      s(:float, 1.33) : %q{1.33}

      s(:float, -1.33) : %q{-1.33}

      s(:rational, Rational(42)):%q{42r}

      s(:rational, Rational(421, 10)): %q{42.1r}

      s(:complex, Complex(0, 42)): %q{42i}

      s(:complex, Complex(0, Rational(42))): %q{42ri}

      s(:complex, Complex(0, 42.1)): %q{42.1i}

      s(:complex, Complex(0, Rational(421, 10))): %q{42.1ri}


