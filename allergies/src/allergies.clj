(ns allergies)
(def allergies-score
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
         allergies-score)))

(defn allergic-to? [score allergy]                          ;; <- arglist goes here
  (boolean (allergy (set (allergies score)))))
