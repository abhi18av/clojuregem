(ns clojewel.jlrepl.utils
  (:gen-class))

(use '[me.raynes.conch :refer [programs with-programs let-programs] :as sh])

;(use '[me.raynes.conch.low-level :as sh-ll])

;; Import and Check the julia version


(defn julia-eval-cli [julia-expression]
  (with-programs [julia]
  (julia "-e" julia-expression)))

(defn julia-eval-file [julia-file-name]
  (with-programs [julia]
  (julia julia-file-name)))


; EXAMPLE
;(clojewel.utils/julia-eval-file-with-driver "./src/clojewel/file_to_jlir.jl" "./src/clojewel/scratch.jl" "./src/clojewel/scratch.jlir")
(defn julia-eval-file-with-driver
  "This function executes the cli of the form => julia driver.jl source.jl output.jl"
  [driver-file source-file output-file]
  (with-programs [julia]
  (julia driver-file source-file {:out (java.io.File. output-file)})))

; EXAMPLE
;(clojewel.utils/julia-eval-file-with-driver "./src/clojewel/eval_expr.jl" julia-expression)
(defn julia-eval-expr-with-driver
  "This function executes the cli of the form => julia driver.jl source.jl output.jl"
  [driver-file julia-expression]
  (with-programs [julia]
  (julia driver-file julia-expression)))



(defn shell-save-to-file [file-name julia-expression]
  (spit file-name julia-expression))

(defn shell-show-file-content [file-name]
  (with-programs [cat]
  (cat file-name)))

(defn shell-open-file-in-sublime [file-name]
  (with-programs [subl]
  (subl file-name)))

