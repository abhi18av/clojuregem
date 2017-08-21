(ns boot.user)

;; Here, I'll be making my way through the metaprogramming book in Clojure


(class (read-string ":some-keyword"))

(macroexpand '(when (== 2 (+ 1 1)) (print "You got") (print " the touch!") (println)))


(macroexpand-1 '(when (== 2 (+ 1 1)) (print "You got") (print " the touch!") (println)))

(clojure.walk/macroexpand-all '(when (== 2 (+ 1 1)) (print "You got") (print " the touch!") (println)))


(defmacro info-about-caller [] (pprint {:form &form :env &env}) `(println "macro was called!"))

