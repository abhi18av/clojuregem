(ns clojuregem.rbrepl.core
  (:require [clojuregem.rbrepl.rbrepl :as rbrepl]
            [clojuregem.rbrepl.utils :as utils])
  (:gen-class))

;; SCRATCH related work
(rbrepl/shell-show-scratch)

;(rbrepl/shell-save-to-scratch "def add9( x) x + 9 end  ; p add9(9) ")

(rbrepl/ruby-eval-scratch)

(rbrepl/ruby-create-scratch-rbir)

(rbrepl/shell-show-scratch-rbir)

(rbrepl/shell-open-both-scratch-in-subl)

;; CLI related work
;(utils/ruby-eval-cli " require 'parser'; require 'unparser' ; Parse::Ruby23.parse(/" 1 + 1 /")")

;(rbrepl/ruby-create-rbir-expr-with-driver-file "p \"Hello, Ruby! \" ")

(rbrepl/ruby-create-rbir-expr-with-driver-file "def add9( x) x + 9 end ")

(rbrepl/ruby-create-rbir-expr-with-driver-file " '1' ")

(rbrepl/ruby-create-rbir-expr-with-driver-file " 1 + 1 - 1 ")


;(rbrepl/ruby-eval-expr-with-driver-file "Expr(:call, :+, 1, 1)")

;(rbrepl/ruby-eval-expr-with-driver-file "function add9(x) x + 9 end ; add9(9)")

(rbrepl/ruby-create-rb-expr-from-rbir-with-driver-file
"s(:send,
  s(:send,
    s(:int, 1), :+,
    s(:int, 1)), :-,
  s(:int, 1)) ")


(rbrepl/ruby-create-rb-expr-from-rbir-with-driver-file
 "s(:if,
  s(:send,
    s(:ivar, :@ready_state), :>,
    s(:const, nil, :OPEN)),
  s(:return,
    s(:false)), nil)")



(rbrepl/ruby-create-rb-expr-from-rbir-with-driver-file
 "s(:str, \"1\")")




(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Clojuregem - CLJ!"))
