(ns tiny-gp.genetic-ops-test
  (:require [expectations :refer :all]
            [tiny-gp.genetic-ops :refer :all]))

;;test tournament select
(expect number? (tournament-select [1 2 3 4] > 4))
(expect pos? (tournament-select [1 2 3] >))


;;test passthrough
(expect seq? (passthrough '({:fitness 2}
                            {:fitness 4}
                            {:fitness 7})
                          2))
(expect #(map? (first %)) (passthrough '({:fitness 2}
                                         {:fitness 4}
                                         {:fitness 7})
                                       2))
