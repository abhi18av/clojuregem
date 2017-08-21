(ns clojewel.rbrepl.core
  (:require [clojuregem.rbrepl.rbrepl :as rbrepl]
            [clojuregem.rbrepl.utils :as utils])
  (:gen-class))

;; SCRATCH related work
(rbrepl/shell-show-scratch)

(rbrepl/shell-save-to-scratch "function add9( x) x + 9 end  ; add9(9) |> show")

(rbrepl/ruby-eval-scratch)

(rbrepl/ruby-create-scratch-rbir)

(rbrepl/shell-show-scratch-rbir)

(rbrepl/shell-open-both-scratch-in-subl)

;; CLI related work
(utils/ruby-eval-cli "eval(Expr(:call, :print, Expr(:call, :+, 1, 1)))")

(rbrepl/ruby-create-rbir-expr-with-driver-file "println(\"Hello, Ruby! \") ")

(rbrepl/ruby-create-rbir-expr-with-driver-file "function add9( x) x + 9 end ")

(rbrepl/ruby-eval-expr-with-driver-file "Expr(:call, :+, 1, 1)")

(rbrepl/ruby-eval-expr-with-driver-file "function add9(x) x + 9 end ; add9(9)")

(rbrepl/ruby-create-jl-expr-from-rbir-with-driver-file "(:call, :+, 1, 1)")


;; EXPERIMENTATION

(rbrepl/ruby-create-jl-expr-from-rbir-with-driver-file "(:const,(:(=),(:curly, :AbstractVector, :T),(:curly, :AbstractArray, :T, 1)))")

(rbrepl/ruby-create-rbir-expr-with-driver-file "function add9( x) x + 9 end ")


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Clojewel - CLJ!"))
