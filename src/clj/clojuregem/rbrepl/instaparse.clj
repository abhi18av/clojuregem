(ns user
  (:require [instaparse.core :as insta]
            [clojuregem.rbrepl.utils :as utils]))

(def base-dir "./src/clj/clojuregem/rbrepl/")


(defn ruby-create-instaparse-set-rbir
  "Convert the scratch file to it's RBIR version and show"
  []
  (let [driver-file  (str base-dir "file_to_rbir.rb")
        source-file  (str base-dir "instaparse_client_socket.rb")
        output-file  (str base-dir "instaparse_client_socket.rbir")]
    (utils/ruby-eval-file-with-driver driver-file source-file output-file)))

;(ruby-create-instaparse-set-rbir)

(defn shell-show-instaparse-set
  "Show the \"normal ruby syntax\" contents of scratch.rb file"
  []
  (let [file (str base-dir "instaparse_client_socket.rb")]
    (utils/shell-show-file-content file)))

;(shell-show-instaparse-set)

(defn shell-show-instaparse-rbir
  "Show the \"RBIR\" contents of scratch.rbir file"
  []
  (let [file (str base-dir "instaparse_client_socket.rbir")]
    (utils/shell-show-file-content file)))

(shell-show-instaparse-rbir)

(defn shell-open-both-instaparse-set-in-subl []
  (do
    (utils/shell-open-file-in-sublime (str base-dir "instaparse_client_socket.rb"))
    (utils/shell-open-file-in-sublime (str base-dir "instaparse_client_socket.rbir"))))

;(shell-open-both-instaparse-set-in-subl)

(def parser (insta/parser
"
rbir = lparen operation rparen
<lparen> = <'('>
<rparen> = <')'>
operation = operator + args
operator = '+'
args = snumber+
<snumber> = space number
<space> = <#'[ ]*'>
number = #'[0-9]+'
"))

(parser "(+ 1 2)")
