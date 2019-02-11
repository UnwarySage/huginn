(ns tiny-gp.fitness)

;;These functions take evalled populations, and the configuration map.
;;They return evalled populations
;;this also includes the population evaluator

(def ex-test {:args [:x :y]
               :x [1 2 3 4 5 6]
               :y [6 5 4 3 2 1]
               :expected [ 7 7 7 7 7 7]})


(defn measure-individual
  "takes a function, and the test and measures the functions performance against the test-data."
  [inp-function test-map]
  (let [args (:args test-map)
        args-seqs (map #(% test-map) args)
        results-seq (map (fn [position]
                          (Math/abs
                            (-
                              (nth (:expected test-map) position)
                              (apply inp-function (map #(nth % position) args-seqs)))))
                         (range (count (:expected test-map))))]
      (* -1 (reduce + results-seq))))


(defn fitness [{:keys [code function] :as original-ind} config]
  (merge original-ind {:fitness (measure-individual function (:test-data config))}))
