(ns clojure-learning.oo-fp-1)

(defn add-squares
  [& args]
  (apply + (map #(* % %) args)))

;(add-squares 1 2 5)

(defn factorial
  [n]
  (reduce * (drop 1 (range (+ n 1)))))

;(factorial 11)

(defn prefix-of
  [f s]
  (= (take (count f) s) f))

;(prefix-of [1 2 5] '(1 2 5 nil))

(defn tails
  ([coll] (tails [] coll))
  ([acc coll]
    (if (empty? coll)
      (seq (conj acc coll))
      (recur (conj acc coll)
             (rest coll)))))

(defn tails-2
  "wow!"
  [coll]
  (map #(drop % coll) (range (inc (count coll)))))

;(tails '(1 2 3 4 5))
;(tails-2 '(1 2 3 4 5))


