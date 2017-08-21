(ns clojewel.jlrepl.jlrepl
  (:require [clojewel.utils :as utils])
  (:gen-class))


(def base-dir "./src/clj/clojewel/jlrepl/")



;;;; SCRATCH RELATED FUNCTIONS

(defn shell-save-to-scratch
  "This function saves the julia expression to the scratch file"
  [julia-expression]
  (let [scratch-jl (str base-dir "scratch.jl")]
    (utils/shell-save-to-file scratch-jl julia-expression)))

(defn julia-create-scratch-jlir
  "Convert the scratch file to it's JLIR version and show"
  []
  (let [driver-file  (str base-dir "file_to_jlir.jl")
        source-file  (str base-dir "scratch.jl")
        output-file  (str base-dir "scratch.jlir")]
    (utils/julia-eval-file-with-driver driver-file source-file output-file)))

(defn shell-show-scratch
  "Show the \"normal julia syntax\" contents of scratch.jl file"
  []
  (let [file (str base-dir "scratch.jl")]
    (utils/shell-show-file-content file)))

(defn shell-show-scratch-jlir
  "Show the \"JLIR\" contents of scratch.jlir file"
  []
  (let [file (str base-dir "scratch.jlir")]
    (utils/shell-show-file-content file)))

(defn julia-eval-scratch
  "This function evaluates the scratch file using << julia scratch.jl >>"
  []
  (let [scratch-jl (str "scratch.jl")]
    (utils/julia-eval-file scratch-jl)))

(defn julia-eval-scratch-jlir
  "This function evaluates the scratch file using << julia scratch.jl >>"
  []
  (let [scratch-jlir (str base-dir "scratch.jlir")]
    (utils/julia-eval-file scratch-jlir)))

;;;; IN-MEMORY RELATED EXPRESSIONS

(defn julia-create-jl-expr-from-jlir-with-driver-file
  "This function takes in the julia JLIR form and uses a driver script to print out the JL "
  [julia-expression]
  (let [driver-file "./src/clj/clojewel/jlrepl/jl_expr_from_s_expr.jl"]
    (utils/julia-eval-expr-with-driver driver-file julia-expression)))

(defn julia-eval-expr-with-driver-file
  "This function takes in the julia JLIR form and uses a driver script to print out the JL "
  [julia-expression]
  (let [driver-file "./src/clj/clojewel/jlrepl/eval_expr.jl"]
    (utils/julia-eval-expr-with-driver driver-file julia-expression)))

(defn julia-create-jlir-expr-with-driver-file
  "Working on the "
  [julia-expression]
  (let [driver-file "./src/clj/clojewel/jlrepl/create_s_expr.jl"]
    (utils/julia-eval-expr-with-driver driver-file julia-expression)))

(defn shell-open-both-scratch-in-subl []
  (do
    (utils/shell-open-file-in-sublime "./src/clj/clojewel/jlrepl/scratch.jl")
    (utils/shell-open-file-in-sublime "./src/clj/clojewel/jlrepl/scratch.jlir")))
