(ns clojure-learning.recursion)

(defn recursion-function
  [f coll so-far]
  (if (empty? coll)
    so-far
    (recur f (rest coll) (f so-far (first coll)))))

;(recursion-function * [1 2 3 4] 1)
(recursion-function #(assoc % %2 (count %)) [:a :b :c] {})