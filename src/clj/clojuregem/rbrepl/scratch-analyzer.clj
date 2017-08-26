(require '[clojure.tools.analyzer.jvm :as ana.jvm])

(require '[clojure.tools.analyzer.passes.jvm.emit-form :as e])


(e/emit-hygienic-form (ana.jvm/analyze '(let [a 1 a a] a)))



