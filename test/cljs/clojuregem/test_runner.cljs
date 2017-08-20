(ns clojuregem.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [clojuregem.core-test]
   [clojuregem.common-test]))

(enable-console-print!)

(doo-tests 'clojuregem.core-test
           'clojuregem.common-test)
