(ns huginn.ops)

;;this namespace implements the operations and operation sets that we will evolve over.

(defn op-plus [x y]
  (+ x y))

(defn op-minus [x y]
  (- x y))

(defn op-multiply [x y]
  (* x y))

(defn op-divide [x y]
  (if-not (= 0 y)
    (/ x y)
    0))

(defn op-power [x y]
  (Math/pow x y))

(def arithmetic-ops #{op-plus op-minus op-multiply op-divide})
