(ns tiny-gp.population)

;;given an initial population specification,
;;will return a list of individuals generated to meet that spec

(defn random-from [inp-coll]
  (nth (into [] inp-coll) (rand-int (count inp-coll))))

(defn grow-method [depth ops terminals]
  (if (or (= 0 depth)
          (< (rand)
             (/ (count terminals)
                (+ (count terminals) (count ops)))))
      (random-from terminals)
      (list
        (random-from ops)
        (grow-method (dec depth) ops terminals)
        (grow-method (dec depth) ops terminals))))


(defn generate-individual [{:keys [op-set terminal-set variable-list depth-limit]}]
  (grow-method depth-limit op-set (into terminal-set variable-list)))

(defn generate-population [{:keys [op-set terminal-set variable-list depth-limit pop-size] :as pop-info}]
  (take pop-size (repeatedly #(generate-individual pop-info))))
