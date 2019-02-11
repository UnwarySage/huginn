(ns tiny-gp.population-test
  (:require [expectations :refer :all]
            [tiny-gp.population :refer :all]))


;;TODO: figure out how to avoid tests having any chance of failling due to random chance.
;;TODO: investigate spec tests, and see if they might work well for this.

;;test grow-method
(expect true (#(or (number? %)
                   (list? %)) (grow-method 5 [1 2 3] [4 5 6])))
;;because this can return a literal, we are going to make sure it produces a list to test againt
(def simple-indvidual (first
                        (filter list?
                          (repeatedly
                            #(grow-method 1 ["minus" "plus" "times"] [1 2 3 4 5])))))
(expect string? (first simple-indvidual))
(expect number? (nth (flatten simple-indvidual) 1))
(expect number? (nth (flatten simple-indvidual) 2))

;;now we test to see if if can generate a bunch of nodes.
;;make sure there are a vast variety of ops, so as to weigh the odds in favor of generating a complex function.
(expect true (#(< 3 (count (flatten %))) (grow-method 5 (range 50) [4 5 6])))

;;test generate-individual
(expect true (#(or (list? %)
                  (number? %))
              (generate-individual {:depth-limit 8 :op-set ["plus"]
                                    :terminal-set [1 2 3]
                                    :variable-list [4 5 6]})))

;;test generate-population
(expect true (#(or (list? (first %))
                  (number? (first %)))
              (generate-population {:depth-limit 8 :op-set ["plus"]
                                    :terminal-set [1 2 3]
                                    :variable-list [4 5 6]
                                    :pop-size 50})))
