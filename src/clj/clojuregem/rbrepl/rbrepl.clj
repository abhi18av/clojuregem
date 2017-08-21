(ns clojuregem.rbrepl.rbrepl
  (:require [clojuregem.utils :as utils])
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

(defn shell-show-scratch-rbir
  "Show the \"RBIR\" contents of scratch.rbir file"
  []
  (let [file (str base-dir "scratch.rbir")]
    (utils/shell-show-file-content file)))

(defn ruby-eval-scratch
  "This function evaluates the scratch file using << ruby scratch.rb >>"
  []
  (let [scratch-rb (str "scratch.rb")]
    (utils/ruby-eval-file scratch-rb)))

(defn ruby-eval-scratch-rbir
  "This function evaluates the scratch file using << ruby scratch.rb >>"
  []
  (let [scratch-rbir (str base-dir "scratch.rbir")]
    (utils/ruby-eval-file scratch-rbir)))

;;;; IN-MEMORY RELATED EXPRESSIONS

(defn ruby-create-rb-expr-from-rbir-with-driver-file
  "This function takes in the ruby RBIR form and uses a driver script to print out the RB "
  [ruby-expression]
  (let [driver-file "./src/clj/clojuregem/rbrepl/rb_expr_from_s_expr.rb"]
    (utils/ruby-eval-expr-with-driver driver-file ruby-expression)))

(defn ruby-eval-expr-with-driver-file
  "This function takes in the ruby RBIR form and uses a driver script to print out the RB "
  [ruby-expression]
  (let [driver-file "./src/clj/clojuregem/rbrepl/eval_expr.rb"]
    (utils/ruby-eval-expr-with-driver driver-file ruby-expression)))

(defn ruby-create-rbir-expr-with-driver-file
  "Working on the "
  [ruby-expression]
  (let [driver-file "./src/clj/clojuregem/rbrepl/create_s_expr.rb"]
    (utils/ruby-eval-expr-with-driver driver-file ruby-expression)))

(defn shell-open-both-scratch-in-subl []
  (do
    (utils/shell-open-file-in-sublime "./src/clj/clojuregem/rbrepl/scratch.rb")
    (utils/shell-open-file-in-sublime "./src/clj/clojuregem/rbrepl/scratch.rbir")))
