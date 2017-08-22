;; TODO:  start with macros that transform the clojure expression to a julia expression

(ns user
(:require [clojure.tools.analyzer :as ana]
[clojure.tools.analyzer.env :as env]
[clojure.tools.analyzer.ast :as ast]))

;(defn analyze [form env]
;  (binding [ana/macroexpand-1 macroexpand-1
;            ana/create-var    create-var
;            ana/parse         parse
;            ana/var?          var?]
;    (env/ensure (global-env)
;                (run-passes (-analyze form env)))))

;(ast/children (ana/analyze '(do 1 2 :foo)))

(require '[clojure.tools.analyzer.jvm :as jvm-ana])

;(keys (deref
;       (:namespaces (jvm-ana/empty-env))))

(clojure.pprint/pprint (jvm-ana/analyze '(if true 42 21) (jvm-ana/empty-env)))


(-> (jvm-ana/analyze '(fn [x] x) (jvm-ana/empty-env))
                     :methods
                     first
                     :body
                     :env)

(defmulti to-clj :op)


(to-clj (jvm-ana/analyze '(fn [x & r] (let [v x] (if v r 0))) (jvm-ana/empty-env))






(defmethod to-clj :fn )


; TODO : make it work
(defn jvm-analyze-and-print [quoted-form]
  (println quoted-form)
  (clojure.pprint/pprint (jvm-ana/analyze quoted-form)
                         (jvm-ana/empty-env)))

(jvm-analyze-and-print '(fn [x] (+ x 9)))

