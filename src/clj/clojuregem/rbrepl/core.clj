(ns clojewel.jlrepl.core
  (:require [clojewel.jlrepl.jlrepl :as jlrepl]
            [clojewel.jlrepl.utils :as utils])
  (:gen-class))

;; SCRATCH related work
(jlrepl/shell-show-scratch)

(jlrepl/shell-save-to-scratch "function add9( x) x + 9 end  ; add9(9) |> show")

(jlrepl/julia-eval-scratch)

(jlrepl/julia-create-scratch-jlir)

(jlrepl/shell-show-scratch-jlir)

(jlrepl/shell-open-both-scratch-in-subl)

;; CLI related work
(utils/julia-eval-cli "eval(Expr(:call, :print, Expr(:call, :+, 1, 1)))")

(jlrepl/julia-create-jlir-expr-with-driver-file "println(\"Hello, Julia! \") ")

(jlrepl/julia-create-jlir-expr-with-driver-file "function add9( x) x + 9 end ")

(jlrepl/julia-eval-expr-with-driver-file "Expr(:call, :+, 1, 1)")

(jlrepl/julia-eval-expr-with-driver-file "function add9(x) x + 9 end ; add9(9)")

(jlrepl/julia-create-jl-expr-from-jlir-with-driver-file "(:call, :+, 1, 1)")


;; EXPERIMENTATION

(jlrepl/julia-create-jl-expr-from-jlir-with-driver-file "(:const,(:(=),(:curly, :AbstractVector, :T),(:curly, :AbstractArray, :T, 1)))")

(jlrepl/julia-create-jlir-expr-with-driver-file "function add9( x) x + 9 end ")


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Clojewel - CLJ!"))
