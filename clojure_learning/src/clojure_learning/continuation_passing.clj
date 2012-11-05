(ns clojure-learning.continuation-passing)

(-> 
  (concat '(1 2 3) '(4 5 6))
  ((fn [a]
    (-> 
      (count a)
      (odd?)))))

(-> 
  3
  ((fn [a]
     (-> 
       (+ a 2)
       ((fn [b]
          (-> (inc b))))))))