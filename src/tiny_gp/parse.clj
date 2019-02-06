(ns tiny-gp.parse)

;;given a context and a indvidual,
;;these functions will return a clojure function.


(defn parse-list [inp-list {:keys [variable-list] :as context}]
  (let [arguments (into [] variable-list)]
       (eval (list 'fn arguments inp-list))))
