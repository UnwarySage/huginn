(ns huginn.utility-test
  (:require [expectations :refer :all]
            [huginn.utility :refer :all]))

;;test random-from
(expect #(contains? #{1 2 3} %) (random-from #{1 2 3}))
(expect #(contains? #{1 2 3} %) (random-from [1 2 3]))
