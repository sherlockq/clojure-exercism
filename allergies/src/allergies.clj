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

(defn top-score-allergy-pair [score]
  (last
    (take-while
      #(<= (val %) score)
      allergies-score)))

(defn check-allergy [[score existed-allergies] allergy]
  (if (<= (val allergy) score)
    [(- score (val allergy)) (conj existed-allergies allergy)]
    [score existed-allergies]))

(defn allergies [score]                                     ;; <- arglist goes here
  (->> (reduce check-allergy [score []] (reverse allergies-score))
       second
       (sort-by val)
       (map key)
    )
  )

(defn allergic-to? [score allergy]                          ;; <- arglist goes here
  (boolean (some #(= allergy %) (allergies score)))
  )
