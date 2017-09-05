(ns clojuregem.rbrepl.rbrepl
  (:require [clojuregem.rbrepl.utils :as utils])
  (:gen-class))

(def base-dir "./src/clj/clojuregem/rbrepl/")

;;;; SCRATCH RELATED FUNCTIONS

(defn shell-save-to-scratch
  "This function saves the ruby expression to the scratch file"
  [ruby-expression]
  (let [scratch-rb (str base-dir "scratch.rb")]
    (utils/shell-save-to-file scratch-rb ruby-expression)))

(defn ruby-create-scratch-rbir
  "Convert the scratch file to it's RBIR version and show"
  []
  (let [driver-file  (str base-dir "file_to_rbir.rb")
        source-file  (str base-dir "scratch.rb")
        output-file  (str base-dir "scratch.rbir")]
    (utils/ruby-eval-file-with-driver driver-file source-file output-file)))

(defn shell-show-scratch
  "Show the \"normal ruby syntax\" contents of scratch.rb file"
  []
  (let [file (str base-dir "scratch.rb")]
    (utils/shell-show-file-content file)))

;(shell-show-scratch)

(defn shell-show-scratch-rbir
  "Show the \"RBIR\" contents of scratch.rbir file"
  []
  (let [file (str base-dir "scratch.rbir")]
    (utils/shell-show-file-content file)))

;(shell-show-scratch-rbir)

(defn ruby-eval-scratch
  "This function evaluates the scratch file using << ruby scratch.rb >>"
  []
  (let [scratch-rb (str base-dir "scratch.rb")]
    (utils/ruby-eval-file scratch-rb)))

;(ruby-eval-scratch)

(defn ruby-eval-scratch-rbir
  "This function evaluates the scratch file using << ruby scratch.rb >>"
  []
  (let [scratch-rbir (str base-dir "scratch.rbir")]
    (utils/ruby-eval-file scratch-rbir)))

;(ruby-eval-scratch-rbir)


;;;; IN-MEMORY RELATED EXPRESSIONS

(defn ruby-create-rb-expr-from-rbir-with-driver-file
  "This function takes in the ruby RBIR form and uses a driver script to print out the RB "
  [ruby-expression]
  (let [driver-file (str base-dir "rb_expr_from_s_expr.rb")]
    (utils/ruby-eval-expr-with-driver driver-file ruby-expression)))

(ruby-create-rb-expr-from-rbir-with-driver-file
 "s(:send,
   s(:int, 1), :+,
   s(:int, 1))")


(ruby-create-rb-expr-from-rbir-with-driver-file
"s(:lvasgn, :text1,
           s(:str, /"def add9(x) x + 9 end/" ))")
 

(defn ruby-eval-expr-with-driver-file
  "This function takes in the ruby RBIR form and uses a driver script to print out the RB expression "
  [ruby-expression]
  (let [driver-file (str base-dir "eval_expr.rb")]
    (utils/ruby-eval-expr-with-driver driver-file ruby-expression)))

;(ruby-eval-expr-with-driver-file "puts 1 + 1")


(defn ruby-create-rbir-expr-with-driver-file
  "Working on the creation of the RBIR using a driver file"
  [ruby-expression]
  (let [driver-file (str base-dir "create_s_expr.rb")]
    (utils/ruby-eval-expr-with-driver driver-file ruby-expression)))

(defn shell-open-both-scratch-in-subl []
  (do
    (utils/shell-open-file-in-sublime (str base-dir "scratch.rb"))
    (utils/shell-open-file-in-sublime (str base-dir "scratch.rbir"))))
