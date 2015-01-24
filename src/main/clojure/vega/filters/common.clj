(ns vega.filters.common)

(defn calc-alpha [days]
  (/ 2.0 (+ days 1.0)))


(defn calc-smooth [data]
  (loop [result (vec (take 4 data))
         datax (drop 1 data)]
    (let [[a b c d] (take 4 datax)]
      (if (nil? d)
        result
        (recur (conj result (/ (+ a (* 2.0 b) (* 2.0 c) d) 6.0)) (rest datax))))))