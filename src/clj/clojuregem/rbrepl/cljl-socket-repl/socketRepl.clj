(ns socketRepl
  (:require [clojure.core.server :as socket]))

(socket/start-server :name "localhost" :port 1818 :accept "julia")
