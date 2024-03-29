(ns clojure-learning.object1)

(defn class-from-instance
  [instance]
  (assert (map? instance))
  (eval (:__class_symbol__ instance)))

(defn apply-message-to
  [class instance message args]
  (let [method (or (message (:__instance_methods__ class)) message)]
  (apply method instance args)))

(defn make 
  [class & args]
  (let [seeded {:__class_symbol__ (:__own_symbol__ class)}]
    (apply-message-to class seeded :add-instance-values args)))

(defn send-to
  [instance message & args]
  (apply-message-to (class-from-instance instance) instance message args))

(defn class-instance-methods
  [class-symbol]
  (:__instance_methods__ (eval class-symbol)))
  
(defn lineage
  ([class-symbol] (lineage () class-symbol))
  ([acc class-symbol]
     (if (not class-symbol)
       acc
       (recur (conj acc class-symbol) (:__superclass_symbol__ class-symbol)))))
    

(defn method-cache
  [class]
  (let [class-symbol (:__own_symbol__ class)
        method-maps (map class-instance-methods (lineage class-symbol))]
    (apply merge method-maps)))

(def Anything
{
   :__own_symbol__ 'Anything
   :__instance_methods__
   {
      :add-instance-values identity
      :class-name :__class_symbol__
      :class class-from-instance
   }
})

(def Point
{
   :__own_symbol__ 'Point
   :__superclass_symbol__ 'Anything
   :__instance_methods__ 
   {
     :add-instance-values (fn [this x y] (assoc this :x x :y y))
     

     :shift (fn [this xinc yinc]
              (make Point 
                    (+ (send-to this :x) xinc)
                    (+ (send-to this :y) yinc)))
                 
     :add (fn [this another]
            (send-to this :shift
                     (send-to another :x)
                     (send-to another :y)))  
    }
})


(let [p1 (make Point 1 2)]
  ;((:shift (:__methods__ p1)) p1 10 20)
  ;(send-to p1 :shift 100 200)
  ;(send-to p1 :y)
  (send-to p1 :class-name)
  ;(send-to p1 :class)
  ;(send-to p1 :add (make Point 10 20))
  ;(equal-triangles? t1 t2)
  ;(make Point 2 3)
  ;(println p1)
  ;(println (shift p1 1 2))
  ;(println (add p1 p2))
  ;(println (add-with-shift p1 p2))
  )
  
  
  
  