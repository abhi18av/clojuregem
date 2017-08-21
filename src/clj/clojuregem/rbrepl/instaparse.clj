(ns user
  (:require [instaparse.core :as insta]
            [clojewel.jlrepl.utils :as utils]))

(def base-dir "./src/clj/clojewel/jlrepl/")


(defn julia-create-instaparse-set-jlir
  "Convert the scratch file to it's JLIR version and show"
  []
  (let [driver-file  (str base-dir "file_to_jlir.jl")
        source-file  (str base-dir "instaparse_set.jl")
        output-file  (str base-dir "instaparse_set.jlir")]
    (utils/julia-eval-file-with-driver driver-file source-file output-file)))

(julia-create-instaparse-set-jlir)

(defn shell-show-instaparse-set
  "Show the \"normal julia syntax\" contents of scratch.jl file"
  []
  (let [file (str base-dir "instaparse_set.jl")]
    (utils/shell-show-file-content file)))

(shell-show-instaparse-set)

(defn shell-show-instaparse-jlir
  "Show the \"JLIR\" contents of scratch.jlir file"
  []
  (let [file (str base-dir "instaparse_set.jlir")]
    (utils/shell-show-file-content file)))

(shell-show-instaparse-jlir)

(defn shell-open-both-instaparse-set-in-subl []
  (do
    (utils/shell-open-file-in-sublime (str base-dir "instaparse_set.jl"))
    (utils/shell-open-file-in-sublime (str base-dir "instaparse_set.jlir"))))

(shell-open-both-instaparse-set-in-subl)

(def parser (insta/parser
             "jlir = space | lparen space number space rparen | space
              <lparen> = <'('>
              <rparen> = <')'>
              number = #'[0-9]+'
              <space> = <#'[ ]*'>"))

(parser "(1)")

(parser "( 1 )")

