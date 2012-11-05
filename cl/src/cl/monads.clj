(ns cl.monads
  (:use clojure.algo.monads))

(def +?
  (with-monad maybe-m
    (m-lift 4 +)))   
    
    ;(m-lift 4 +)))

(+? 1 2 nil 3)

 
