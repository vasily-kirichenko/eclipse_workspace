(ns cl.error-test
  (:use cl.error
        clojure.test))

(deftest result-success-test
  (are [n r] (= (result n) r)
       0 2
       1 2))

(deftest result-error-test
  (is (let [res (result -1)]
        (and (map? res)
             (= (:type res) :error)
             (= (:number res) -1)))))

(run-tests)
