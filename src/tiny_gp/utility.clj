(ns tiny-gp.utility)


(defn random-from
  "Returns a random element from the input"
  [inp-coll]
  (nth (into [] inp-coll) (rand-int (count inp-coll))))
