(ns clojuregem.rbrepl.rbir-dsl
    (:require [clojure.tools.analyzer.jvm :as ana.jvm]
              [clojure.tools.analyzer.passes.jvm.emit-form :as e]
              [com.rpl.specter :as specter]
              [medley.core :as medley]))





;      medley.core])


;; (def expr-map
;;  (ana.jvm/analyze '(+ 1 2 (- 3 4))))



;; ;;;;output
;; (comment
;;   (:args :children :method :op :env :o-tag :class :top-level :form :tag :validated? :raw-forms)


;; )

;; ;;;;


;; (e/emit-hygienic-form (ana.jvm/analyze '1))


;; (get-in expr-map [:args ] "Nope")



;; ;(e/emit-form (ana.jvm/analyze '1))

;; (e/emit-hygienic-form (ana.jvm/analyze '1))

(comment

  ;; DONE: I need to learn how to capture the output value into a list or a similar sequence


); end of comment




;{7 :a, 1 :a, 4 :a, 6 :a, 3 :a, 2 :a, 9 :a, 5 :a, 10 :a, 8 :a}
(defn -alpha-nums [word num-range]
  (zipmap
   (range 1 (+ 1 num-range)  1)
   (repeat num-range (keyword word))))


; (join-key-val [:b 2])
(defn -join-key-val [pair]
  (str  (first (reverse pair))
        (second  (reverse pair))))

; [[1 :a] [2 :a] [3 :a] [4 :a] [5 :a] [6 :a] [7 :a] [8 :a] [9 :a] [10 :a]]
(def -alpha-nums-seq
  (into [] (sort(-alpha-nums "a" 10))))

; (":a1" ":a2" ":a3" ":a4" ":a5" ":a6" ":a7" ":a8" ":a9" ":a10")
(map -join-key-val -alpha-nums-seq)


;; TODO: (gen-pairs n alphabet )



(def x {
:x1 (ana.jvm/analyze '1)
:x2 (ana.jvm/analyze '(+ 1))
:x3 (ana.jvm/analyze '(+ 1 1))
:x4 (ana.jvm/analyze '(+ 1 1 1))
:x5 (ana.jvm/analyze '(+ 1 1 1 (- 1)))
:x6 (ana.jvm/analyze '(+ 1 1 1 (- 1 1)))})





;;; Working on the actual results of analysis i.e. << x >>





(def kx {
:kx1 (keys (:x1 x))
:kx2 (keys (:x2 x))
:kx3 (keys (:x3 x))
:kx4 (keys (:x4 x))
:kx5 (keys (:x5 x))
:kx6 (keys (:x6 x))})







;; ;; Using set-theoretic functions for analysis

;; (clojure.set/difference (set kx1 ) (set kx2))

;; (clojure.set/difference (set kx1 ) (set kx2) (set kx3) (set kx4) (set kx5))

;; (def set-of-keys [(set kx1 ) (set kx2) (set kx3) (set kx4) (set kx5)])


;; (map sort set-of-keys)

;; ;; output of previous command
;; (comment

;; ((:env :form :literal? :o-tag :op :tag :top-level :type :val)
;;  (:args :children :env :fn :form :meta :o-tag :op :top-level)
;;  (:args :children :class :env :form :method :o-tag :op :raw-forms :tag :top-level :validated?)
;;  (:args :children :class :env :form :method :o-tag :op :raw-forms :tag :top-level :validated?)
;;  (:args :children :class :env :form :method :o-tag :op :raw-forms :tag :top-level :validated?)
;;  (:args :children :class :env :form :method :o-tag :op :raw-forms :tag :top-level :validated?))

;; ); end of comment


;; (apply :env [x1 x2])

;; (map :op [x1 x2 x3 x4 x5 x6])
;; (:const :invoke :static-call :static-call :static-call :static-call)


;; (map :args [x1 x2 x3])

;; ;;;;;;;;;; Actual outputs of the analysis of x-forms

;; ;(def x1 (ana.jvm/analyze '1))

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



;(def  x2 (ana.jvm/analyze '(+ 1)))

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

)



;(def  x3 (ana.jvm/analyze '(+ 1 1)))

(comment
;;;NOTE: < + > and < add > are defined differently
{:args
 [{:op :const,
   :env
   {:context :ctx/expr,
    :locals {},
    :ns clojuregem.rbrepl.rbir-dsl,
    :column 28,
    :line 113,
    :file
    "/Users/eklavya/Projects/ProjectEklavya/Mordor/Clojewel/clojuregem/src/clj/clojuregem/rbrepl/rbir_dsl.clj"},
   :type :number,
   :literal? true,
   :val 1,
   :form 1,
   :o-tag long,
   :tag long}
  {:op :const,
   :env
   {:context :ctx/expr,
    :locals {},
    :ns clojuregem.rbrepl.rbir-dsl,
    :column 28,
    :line 113,
    :file
    "/Users/eklavya/Projects/ProjectEklavya/Mordor/Clojewel/clojuregem/src/clj/clojuregem/rbrepl/rbir_dsl.clj"},
   :type :number,
   :literal? true,
   :val 1,
   :form 1,
   :o-tag long,
   :tag long}],
 :children [:args],
 :method add,
 :op :static-call,
 :env
 {:context :ctx/expr,
  :locals {},
  :ns clojuregem.rbrepl.rbir-dsl,
  :column 28,
  :line 113,
  :file
  "/Users/eklavya/Projects/ProjectEklavya/Mordor/Clojewel/clojuregem/src/clj/clojuregem/rbrepl/rbir_dsl.clj"},
 :o-tag long,
 :class clojure.lang.Numbers,
 :top-level true,
 :form (. clojure.lang.Numbers (add 1 1)),
 :tag long,
 :validated? true,
 :raw-forms ((+ 1 1))}

 )


;(def  x4 (ana.jvm/analyze '(+ 1 1 1)))
(comment

{:args
 [{:args
   [{:op :const,
     :env
     {:context :ctx/expr,
      :locals {},
      :ns clojuregem.rbrepl.rbir-dsl,
      :file
      "/private/var/folders/vt/9ywhthp94tz2b_23l97x9zcc0000gn/T/form-init1309742139128240872.clj",
      :column 28,
      :line 1},
     :type :number,
     :literal? true,
     :val 1,
     :form 1,
     :o-tag long,
     :tag long}
    {:op :const,
     :env
     {:context :ctx/expr,
      :locals {},
      :ns clojuregem.rbrepl.rbir-dsl,
      :file
      "/private/var/folders/vt/9ywhthp94tz2b_23l97x9zcc0000gn/T/form-init1309742139128240872.clj",
      :column 28,
      :line 1},
     :type :number,
     :literal? true,
     :val 1,
     :form 1,
     :o-tag long,
     :tag long}],
   :children [:args],
   :method add,
   :op :static-call,
   :env
   {:context :ctx/expr,
    :locals {},
    :ns clojuregem.rbrepl.rbir-dsl,
    :column 28,
    :line 1,
    :file
    "/private/var/folders/vt/9ywhthp94tz2b_23l97x9zcc0000gn/T/form-init1309742139128240872.clj"},
   :o-tag long,
   :class clojure.lang.Numbers,
   :form (. clojure.lang.Numbers (add 1 1)),
   :tag long,
   :validated? true}
  {:op :const,
   :env
   {:context :ctx/expr,
    :locals {},
    :ns clojuregem.rbrepl.rbir-dsl,
    :column 28,
    :line 1,
    :file
    "/private/var/folders/vt/9ywhthp94tz2b_23l97x9zcc0000gn/T/form-init1309742139128240872.clj"},
   :type :number,
   :literal? true,
   :val 1,
   :form 1,
   :o-tag long,
   :tag long}],
 :children [:args],
 :method add,
 :op :static-call,
 :env
 {:context :ctx/expr,
  :locals {},
  :ns clojuregem.rbrepl.rbir-dsl,
  :column 28,
  :line 1,
  :file
  "/private/var/folders/vt/9ywhthp94tz2b_23l97x9zcc0000gn/T/form-init1309742139128240872.clj"},
 :o-tag long,
 :class clojure.lang.Numbers,
 :top-level true,
 :form
 (. clojure.lang.Numbers (add (. clojure.lang.Numbers (add 1 1)) 1)),
 :tag long,
 :validated? true,
 :raw-forms ((+ 1 1 1))}

)


;(def  x5 (ana.jvm/analyze '(+ 1 1 1 (- 1))))

(comment

{:args
 [{:args
   [{:args
     [{:op :const,
       :env
       {:context :ctx/expr,
        :locals {},
        :ns clojuregem.rbrepl.rbir-dsl,
        :file
        "/Users/eklavya/Projects/ProjectEklavya/Mordor/Clojewel/clojuregem/src/clj/clojuregem/rbrepl/rbir_dsl.clj"},
        :column 28,
        :line 258},
       :type :number,
       :literal? true,
       :val 1,
       :form 1,
       :o-tag long,
       :tag long}
      {:op :const,
       :env
       {:context :ctx/expr,
        :locals {},
        :ns clojuregem.rbrepl.rbir-dsl,
        :file
        "/Users/eklavya/Projects/ProjectEklavya/Mordor/Clojewel/clojuregem/src/clj/clojuregem/rbrepl/rbir_dsl.clj"},
        :column 28,
        :line 258},
       :type :number,
       :literal? true,
       :val 1,
       :form 1,
       :o-tag long,
       :tag long}],
     :children [:args],
     :method add,
     :op :static-call,
     :env
     {:context :ctx/expr,
      :locals {},
      :ns clojuregem.rbrepl.rbir-dsl,
      :file
      "/Users/eklavya/Projects/ProjectEklavya/Mordor/Clojewel/clojuregem/src/clj/clojuregem/rbrepl/rbir_dsl.clj"},
   :tag long,
   :validated? true}
  {:args
   [{:op :const,
     :env
     {:context :ctx/expr,
      :locals {},
      :ns clojuregem.rbrepl.rbir-dsl,
      :file
      "/Users/eklavya/Projects/ProjectEklavya/Mordor/Clojewel/clojuregem/src/clj/clojuregem/rbrepl/rbir_dsl.clj"},
      :column 37,
      :line 258},
     :type :number,
     :literal? true,
     :val 1,
     :form 1,
     :o-tag long,
     :tag long}],
   :children [:args],
   :method minus,
   :op :static-call,
   :env
   {:context :ctx/expr,
    :locals {},
    :ns clojuregem.rbrepl.rbir-dsl,
    :column 37,
    :line 258,
    :file
    "/Users/eklavya/Projects/ProjectEklavya/Mordor/Clojewel/clojuregem/src/clj/clojuregem/rbrepl/rbir_dsl.clj"},
   :o-tag long,
   :class clojure.lang.Numbers,
   :form (. clojure.lang.Numbers (minus 1)),
   :tag long,
   :validated? true,
   :raw-forms ((- 1))}],
 :children [:args],
 :method add,
 :op :static-call,
 :env
 {:context :ctx/expr,
  :locals {},
  :ns clojuregem.rbrepl.rbir-dsl,
  :column 28,
  :line 258,
  :file
  "/Users/eklavya/Projects/ProjectEklavya/Mordor/Clojewel/clojuregem/src/clj/clojuregem/rbrepl/rbir_dsl.clj"},
 :o-tag long,
 :class clojure.lang.Numbers,
 :top-level true,
 :form
 (.
  clojure.lang.Numbers
  (add
   (. clojure.lang.Numbers (add (. clojure.lang.Numbers (add 1 1)) 1))
   (- 1))),
 :tag long,
 :validated? true,
 :raw-forms ((+ 1 1 1 (- 1)))}

)


;; (for [i ( keys x)]
;;   (i x ))


;; (for [i '( x1 x2 )]
;;   (keys i ))





;; (for [i ( keys x)]
;;   (do
;;     (println i  " : " (i x))))






;; ruby number literals

      ;; s(:begin) : %q{()}

      ;; s(:kwbegin) : %q{begin end}

      ;; s(:true) : %q{true}

      ;; s(:false) : %q{false}

      ;; s(:int, 42) : %q{42}

      ;; s(:int, -42) : %q{-42}

      ;; s(:float, 1.33) : %q{1.33}

      ;; s(:float, -1.33) : %q{-1.33}

      ;; s(:rational, Rational(42)):%q{42r}

      ;; s(:rational, Rational(421, 10)): %q{42.1r}

      ;; s(:complex, Complex(0, 42)): %q{42i}

      ;; s(:complex, Complex(0, Rational(42))): %q{42ri}

      ;; s(:complex, Complex(0, 42.1)): %q{42.1i}

      ;; s(:complex, Complex(0, Rational(421, 10))): %q{42.1ri}
