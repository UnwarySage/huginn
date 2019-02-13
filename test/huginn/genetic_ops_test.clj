(ns huginn.genetic-ops-test
  (:require [expectations :refer :all]
            [huginn.genetic-ops :refer :all]))

;;test tournament select
(expect number? (tournament-select [1 2 3 4] > 4))
(expect pos? (tournament-select [1 2 3] >))
(expect 4 (tournament-select [1 2 3 4] > 80))

;;test passthrough
(expect seq? (handle-passthrough '({:fitness 2}
                                   {:fitness 4}
                                   {:fitness 7})
                          2))
(expect #(map? (first %)) (handle-passthrough '({:fitness 2}
                                                {:fitness 4}
                                                {:fitness 7})
                                       2))
