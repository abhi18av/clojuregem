(ns clojuregem.rbrepl.utils
  (:require  [me.raynes.conch :refer [programs with-programs let-programs] :as sh]
             [me.raynes.conch.low-level :as sh-ll])
  (:gen-class))


; (pwd) =>  /Users/eklavya/Projects/ProjectEklavya/Mordor/Clojewel/clojuregem
(defn pwd []
  (with-programs [pwd]
    (pwd)))


;; Import and Check the ruby version

(defn ruby-version []
  (with-programs [ruby]
    (ruby "--version"))) ;{:verbose true})))

(ruby-version)

(defn ruby-eval-cli [ruby-expression]
  (with-programs [ruby]
    (ruby "-e" ruby-expression)))

(ruby-eval-cli "p 1 + 1 +1 ")


(defn ruby-eval-file [ruby-file-name]
  (with-programs [ruby]
    (ruby ruby-file-name))); :verbose true})))


(with-programs [ruby]
  (ruby "./src/clj/clojuregem/rbrepl/scratch.rb"));{:verbose true}))


; EXAMPLE
;(clojuregem.rbrepl.utils/ruby-eval-file-with-driver "./src/clojuregem/file_to_rbir.rb" "./src/clojuregem/scratch.rb" "./src/clojuregem/scratch.rbir")
(defn ruby-eval-file-with-driver
  "This function executes the cli of the form => ruby driver.rb source.rb output.rb"
  [driver-file source-file output-file]
  (with-programs [ruby]
    (ruby driver-file source-file {:out (java.io.File. output-file)})))




(clojuregem.rbrepl.utils/ruby-eval-file-with-driver "./src/clj/clojuregem/rbrepl/file_to_rbir.rb" "./src/clj/clojuregem/rbrepl/scratch.rb" "./src/clj/clojuregem/rbrepl/scratch.rbir")


; EXAMPLE
;(clojuregem.rbrepl.utils/ruby-eval-file-with-driver "./src/clojuregem/eval_expr.rb" ruby-expression)
(defn ruby-eval-expr-with-driver
  "This function executes the cli of the form => ruby driver.rb source.rb output.rb"
  [driver-file ruby-expression]
  (with-programs [ruby]
    (ruby driver-file ruby-expression)))



;(with-programs [ruby]
;  (ruby "./src/clj/clojuregem/rbrepl/create_s_expr.rb" " 1 + 1"  ))  ; {:verbose  true}))



(defn shell-save-to-file [file-name ruby-expression]
  (spit file-name ruby-expression))

(defn shell-show-file-content [file-name]
  (with-programs [cat]
    (cat file-name)))

(defn shell-open-file-in-sublime [file-name]
  (with-programs [subl]
    (subl file-name)))

