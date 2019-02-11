(ns tiny-gp.genetic-ops
  (:require [tiny-gp.utility :as util]))

;;these functions take a population, and a test-map, and will iterate the population

(defn tournament-select
  "Takes an list, and the function to compare members of the list.
    Optionally a tournament size, if not provided, assumed to be 4 or the list size"
  ([inp-list comparison tournament-size]
   (first (sort comparison (take tournament-size (repeatedly #(util/random-from inp-list))))))
  ([inp-list comparison]
   (tournament-select inp-list comparison (min 4 (count inp-list)))))

(defn passthrough
  "Takes an input list, and passesthrough the most fit, tournament selected. returns a smaller list"
  [inp-list passthrough-count]
  (take passthrough-count (repeatedly
                            (fn [] (tournament-select
                                     inp-list
                                     #(<= (:fitness %1)
                                          (:fitness %2)))))))
