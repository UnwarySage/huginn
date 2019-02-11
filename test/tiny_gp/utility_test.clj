(ns tiny-gp.utility-test
  (:require [expectations :refer :all]
            [tiny-gp.utility :refer :all]))

;;test random-from
(expect #(contains? #{1 2 3} %) (random-from #{1 2 3}))
(expect #(contains? #{1 2 3} %) (random-from [1 2 3]))
