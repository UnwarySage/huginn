(ns tiny-gp.population
  (:require [tiny-gp.utility :as util]))

;;given an initial population specification,
;;will return a list of individuals generated to meet that spec


(defn grow-method [depth ops terminals]
  (if (or (= 0 depth)
          (< (rand)
             (/ (count terminals)
                (+ (count terminals) (count ops)))))
      (util/random-from terminals)
      (list
        (util/random-from ops)
        (grow-method (dec depth) ops terminals)
        (grow-method (dec depth) ops terminals))))


(defn generate-individual [{:keys [op-set terminal-set variable-list depth-limit]}]
  (grow-method depth-limit op-set (into terminal-set variable-list)))

(defn generate-population [{:keys [op-set terminal-set variable-list depth-limit pop-size] :as pop-info}]
  (take pop-size (repeatedly #(generate-individual pop-info))))
