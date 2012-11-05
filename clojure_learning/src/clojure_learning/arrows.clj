(ns clojure-learning.arrows)

(def separate
  (juxt filter remove))

(separate odd? [1 2 3 4 5])