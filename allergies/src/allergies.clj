(ns allergies)
(def allergy-scores
  {:eggs         1
   :peanuts      2
   :shellfish    4
   :strawberries 8
   :tomatoes     16
   :chocolate    32
   :pollen       64
   :cats         128})
(defn get-allergy-check-fn [score]
  (comp not zero? (partial bit-and score) val))
(defn allergies [score]
  (map key
       (filter
         (get-allergy-check-fn score)
         allergy-scores)))

(defn allergic-to? [score allergy]                          ;; <- arglist goes here
  ((get-allergy-check-fn score) (find allergy-scores allergy)))
