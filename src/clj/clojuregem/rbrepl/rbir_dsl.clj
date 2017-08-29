(ns clojuregem.rbrepl.rbir-dsl
    (:require [clojure.tools.analyzer.jvm :as ana.jvm]
              [clojure.tools.analyzer.passes.jvm.emit-form :as e]))

(ana.jvm/analyze '1)

(def expr-map
  (ana.jvm/analyze '(+ 1 2 (- 3 4))))

;;;;output
;;the value stored in expr-map




;;;;



(keys expr-map)

(:method expr-map)
(:form expr-map)



(get-in expr-map [:args ] "Nope")



;(e/emit-form (ana.jvm/analyze '1))

(e/emit-hygienic-form (ana.jvm/analyze '1))


(ana.jvm/analyze '() )


(ana.jvm/analyze 'nil)




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

