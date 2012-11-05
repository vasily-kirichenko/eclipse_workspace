(ns cl.matching)
(use '[clojure.core.match :only (match)])

(match [1 2 3]
       [_ _ 3] "Three"
       [_ 2 _] "Two")





