(ns clojuregem.rbrepl.utils
  (:gen-class))

(use '[me.raynes.conch :refer [programs with-programs let-programs] :as sh])

;(use '[me.raynes.conch.low-level :as sh-ll])

;; Import and Check the ruby version


(defn ruby-eval-cli [ruby-expression]
  (with-programs [ruby]
  (ruby "-e" ruby-expression)))

(defn ruby-eval-file [ruby-file-name]
  (with-programs [ruby]
    (ruby ruby-file-name {:out (java.io.Reader.)})))


; EXAMPLE
;(clojuregem.utils/ruby-eval-file-with-driver "./src/clojuregem/file_to_rbir.rb" "./src/clojuregem/scratch.rb" "./src/clojuregem/scratch.rbir")
(defn ruby-eval-file-with-driver
  "This function executes the cli of the form => ruby driver.rb source.rb output.rb"
  [driver-file source-file output-file]
  (with-programs [ruby]
  (ruby driver-file source-file {:out (java.io.File. output-file)})))

; EXAMPLE
;(clojuregem.utils/ruby-eval-file-with-driver "./src/clojuregem/eval_expr.rb" ruby-expression)
(defn ruby-eval-expr-with-driver
  "This function executes the cli of the form => ruby driver.rb source.rb output.rb"
  [driver-file ruby-expression]
  (with-programs [ruby]
  (ruby driver-file ruby-expression)))



(defn shell-save-to-file [file-name ruby-expression]
  (spit file-name ruby-expression))

(defn shell-show-file-content [file-name]
  (with-programs [cat]
  (cat file-name)))

(defn shell-open-file-in-sublime [file-name]
  (with-programs [subl]
  (subl file-name)))

