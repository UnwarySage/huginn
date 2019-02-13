(ns huginn.genetic-ops
  (:require
    [clojure.zip :as zip]
    [huginn.utility :as util]
    [huginn.population :as pop]))

;;these functions take a population, and a test-map, and will iterate the population

(defn tournament-select
  "Takes an list, and the function to compare members of the list.
    Optionally a tournament size, if not provided, assumed to be 4 or the list size"
  ([inp-list comparison tournament-size]
   (first (sort comparison (take tournament-size (repeatedly #(util/random-from inp-list))))))
  ([inp-list comparison]
   (tournament-select inp-list comparison (min 4 (count inp-list)))))

(defn valid-crossover-points [inp-code]
  (let [zipper (zip/seq-zip inp-code)
        all-locs (take-while (complement zip/end?) (iterate zip/next zipper))]
    ;;(filter #(not (fn? (zip/node %))) all-locs)))
    (filter #(seq? (zip/node %)) all-locs)))

(defn crossover
  [ind-a ind-b]
  (let [valid-sites-a (valid-crossover-points (:code ind-a))
        valid-sites-b (valid-crossover-points (:code ind-b))
        new-code (zip/root
                  (zip/replace
                    (util/random-from valid-sites-a)
                    (zip/node (util/random-from valid-sites-b))))]
      {:code new-code}))

(defn handle-crossover
  "takes a list of individuals, tournament selects pairs and returns their offspring"
  [inp-list passthrough-count]
  (let [ind-from-fitness
        (tournament-select
                 inp-list
                 #(<= (:fitness %1)
                      (:fitness %2)))]
    (take passthrough-count (repeatedly
                              (crossover (ind-from-fitness)
                                         (ind-from-fitness))))))
(defn handle-passthrough
  "Takes an input list, and passesthrough the most fit, tournament selected. returns a smaller list"
  [inp-list passthrough-count]
  (take passthrough-count (repeatedly
                            (fn [] (tournament-select
                                     inp-list
                                     #(<= (:fitness %1)
                                          (:fitness %2)))))))

(defn mutate
  "takes an indvidual, and a population config, and performs a headless chicken crossover"
  [individual pop-config]
  (merge individual {:code (crossover individual
                             {:code (pop/generate-individual pop-config)})}))

;;-----Pipeline functions

;(defn iterate-population [inp-population iteration-config])
