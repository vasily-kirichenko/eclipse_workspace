(ns cl.lazy)

(defn rrange [first past-end]
  (new clojure.lang.LazySeq 
       (fn []
         (if (= first past-end)
           nil
           (cons first 
                 (rrange (inc first) past-end))))))

(defn mmap [f coll]
  (new clojure.lang.LazySeq
       (fn []
         (if (empty? coll)
           nil
           (cons (f (first coll))
                 (mmap f (rest coll)))))))

(defn ffilter [pred coll]
  (new clojure.lang.LazySeq
       (fn []
         (if (empty? coll)
           nil
           (let [first (first coll)]
             (if (pred first)
               (cons first (ffilter pred (rest coll)))
               (ffilter pred (rest coll))))))))

(defn eager-filter [pred coll]
  "My silly version"
  (letfn [(work [so-far current]
                (if (empty? current)
                  so-far
                  (if (pred (first current))
                    (work (cons (first current) so-far)
                          (rest current))
                    (work so-far (rest current)))))]
         (reverse (work '() coll))))

(defn eager-filter-2 [pred coll]
  "From the book version"
  (cond (empty? coll)
        nil
        
        (pred (first coll))
        (cons (first coll) (eager-filter-2 pred (rest coll)))
        
        :else
        (eager-filter-2 pred (rest coll))))

(def eager?
     (fn [filter-function]
       (try
         (not (first (filter-function (fn [elt]
                                        (if (= elt 9999)
                                          (throw (Error.)))
                                        true)
                                      (range 10000))))
         (catch Error e
           true))))

;(mmap #(if (even? %) (+ 1000 %) %) [1 2 3 4])
;(take 8 (range))
;(ffilter #(and (odd? %) (> 20 %)) (range 100))
(eager-filter-2 #(and (odd? %) (> 20 %)) (range 100))
;(eager? eager-filter-2)










































