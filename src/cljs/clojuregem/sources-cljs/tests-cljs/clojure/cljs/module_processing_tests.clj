(ns cljs.module-processing-tests
  (:require [clojure.java.io :as io]
            [cljs.closure :as closure]
            [clojure.test :refer :all]
            [cljs.env :as env]
            [cljs.analyzer :as ana]
            [cljs.compiler :as comp]
            [cljs.js-deps :as deps]
            [cljs.test-util :as test]))

;; Hard coded JSX transform for the test case
(defn preprocess-jsx [ijs _]
  (assoc ijs :source (clojure.string/replace
                       (:source ijs)
                       (re-pattern (str "\\(\n"
                                        "\\s*<svg width=\"200px\" height=\"200px\" className=\"center\">\n"
                                        "\\s*<circle cx=\"100px\" cy=\"100px\" r=\"100px\" fill=\\{this.props.color\\}>\n"
                                        "\\s*</circle>\n"
                                        "\\s*</svg>\n"
                                        "\\s*\\)"))
                       (str " React.createElement(\"svg\", {width:\"200px\", height:\"200px\", className:\"center\"}, "
                            "React.createElement(\"circle\", {cx:\"100px\", cy:\"100px\", r:\"100px\", fill:this.props.color})"
                            ")"))))

(defmethod closure/js-transforms :jsx [ijs opts]
  (preprocess-jsx ijs opts))

(deftest commonjs-module-processing
  (test/delete-out-files)
  (let [cenv (env/default-compiler-env)]
    ;; Reset load-library cache so that changes to processed files are noticed
    (with-redefs [cljs.js-deps/load-library (memoize cljs.js-deps/load-library*)]
      (is (= {:foreign-libs []
              :ups-foreign-libs []
              :libs ["out/src/test/cljs/reactJS.js"
                     "out/src/test/cljs/Circle.js"]
              :closure-warnings {:non-standard-jsdoc :off}}
            (env/with-compiler-env cenv
              (closure/process-js-modules
                {:foreign-libs [{:file        "src/test/cljs/reactJS.js"
                                 :provides    ["React"]
                                 :module-type :commonjs}
                                {:file        "src/test/cljs/Circle.js"
                                 :provides    ["Circle"]
                                 :module-type :commonjs
                                 :preprocess  :jsx}]
                 :closure-warnings {:non-standard-jsdoc :off}})))
        "processed modules are added to :libs"))

    (is (= {"React" "module$src$test$cljs$reactJS"
            "Circle" "module$src$test$cljs$Circle"}
           (:js-module-index @cenv))
        "Processed modules are added to :js-module-index")))

(deftest es6-module-processing
  (test/delete-out-files)
  (let [cenv (env/default-compiler-env)]

    ;; Reset load-library cache so that changes to processed files are noticed in REPL
    (with-redefs [cljs.js-deps/load-library  (memoize cljs.js-deps/load-library*)]

      (is (= {:foreign-libs []
              :ups-foreign-libs []
              :libs ["out/src/test/cljs/es6_hello.js"]
              :closure-warnings {:non-standard-jsdoc :off}}
             (env/with-compiler-env cenv
               (closure/process-js-modules
                 {:foreign-libs [{:file        "src/test/cljs/es6_hello.js"
                                  :provides    ["es6-hello"]
                                  :module-type :es6}]
                  :closure-warnings {:non-standard-jsdoc :off}})))
          "processed modules are added to :libs")

      (is (= {"es6-hello" "module$src$test$cljs$es6-hello"}
             (:js-module-index @cenv))
          "Processed modules are added to :js-module-index")

      (is (= "goog.provide(\"module$src$test$cljs$es6_hello\");var sayHello$$module$src$test$cljs$es6_hello=function(){console.log(\"Hello, world!\")};module$src$test$cljs$es6_hello.sayHello=sayHello$$module$src$test$cljs$es6_hello"
             (slurp "out/src/test/cljs/es6_hello.js"))))))

(deftest test-module-name-substitution
  (test/delete-out-files)
  (let [cenv (env/default-compiler-env)]
    (env/with-compiler-env cenv
      (let [opts (closure/process-js-modules {:foreign-libs [{:file "src/test/cljs/calculator.js"
                                                              :provides ["calculator"]
                                                              :module-type :commonjs}]})
            compile (fn [form] (with-out-str
                                 (comp/emit (ana/analyze (ana/empty-env) form))))
            output "module$src$test$cljs$calculator.add((3),(4));\n"]
        (swap! cenv
               #(assoc % :js-dependency-index (deps/js-dependency-index opts)))
        (binding [ana/*cljs-ns* 'cljs.user]
          (is (= (compile '(ns my-calculator.core (:require [calculator :as calc :refer [subtract add] :rename {subtract sub}])))
                 "goog.provide('my_calculator.core');\ngoog.require('cljs.core');\ngoog.require('module$src$test$cljs$calculator');\n"))
          (is (= (compile '(calc/add 3 4)) output))
          (is (= (compile '(calculator/add 3 4)) output))
          (is (= (compile '(add 3 4)) output))
          (is (= (compile '(sub 5 4))
                 "module$src$test$cljs$calculator.subtract((5),(4));\n")))))))

(deftest test-cljs-1822
  (test/delete-out-files)
  (let [cenv (env/default-compiler-env)]
    (with-redefs [cljs.js-deps/load-library (memoize cljs.js-deps/load-library*)
                  cljs.js-deps/load-foreign-library (memoize cljs.js-deps/load-foreign-library*)]
      (is (= {:optimizations :simple
              :foreign-libs []
              :ups-foreign-libs []
              :libs ["out/src/test/cljs/react-min.js"
                     "out/src/test/cljs/Circle-min.js"]
              :closure-warnings {:non-standard-jsdoc :off}}
            (env/with-compiler-env cenv
              (closure/process-js-modules
                {:optimizations :simple
                 :foreign-libs [{:file        "src/test/cljs/reactJS.js"
                                 :file-min    "src/test/cljs/react-min.js"
                                 :provides    ["React"]
                                 :module-type :commonjs}
                                {:file        "src/test/cljs/Circle.js"
                                 :file-min    "src/test/cljs/Circle-min.js"
                                 :provides    ["Circle"]
                                 :module-type :commonjs
                                 :preprocess  :jsx}]
                 :closure-warnings {:non-standard-jsdoc :off}})))
        "processed modules are added to :libs"))
    (is (= {"React" "module$src$test$cljs$react-min"
            "Circle" "module$src$test$cljs$Circle-min"}
           (:js-module-index @cenv))
        "Processed modules are added to :js-module-index")))

(deftest commonjs-module-processing-preprocess-symbol
  (test/delete-out-files)
  (let [cenv (env/default-compiler-env)]
    ;; Reset load-library cache so that changes to processed files are noticed
    (with-redefs [cljs.js-deps/load-library (memoize cljs.js-deps/load-library*)]
      (is (= {:foreign-libs []
              :ups-foreign-libs []
              :libs ["out/src/test/cljs/reactJS.js"
                     "out/src/test/cljs/Circle.js"]
              :closure-warnings {:non-standard-jsdoc :off}}
            (env/with-compiler-env cenv
              (closure/process-js-modules
                {:foreign-libs [{:file        "src/test/cljs/reactJS.js"
                                 :provides    ["React"]
                                 :module-type :commonjs}
                                {:file        "src/test/cljs/Circle.js"
                                 :provides    ["Circle"]
                                 :module-type :commonjs
                                 :preprocess  'cljs.module-processing-tests/preprocess-jsx}]
                 :closure-warnings {:non-standard-jsdoc :off}})))
        "processed modules are added to :libs"))

    (is (= {"React" "module$src$test$cljs$reactJS"
            "Circle" "module$src$test$cljs$Circle"}
           (:js-module-index @cenv))
        "Processed modules are added to :js-module-index")))
