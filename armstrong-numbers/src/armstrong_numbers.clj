(ns armstrong-numbers)

;;; Math/pow will lose precision for 9 to the 17th power (9^17)
(defn- power [base exp]
  (reduce * (repeat exp base)))

(defn char-to-digit [char]
  (Character/digit ^char char 10))

(def digits-of-str (partial map char-to-digit))
(def digits-of-number (comp digits-of-str str))

(defn armstrong? [num]
  (let [digits (digits-of-number num)
        number-of-digits (count digits)]
    (= num
       (reduce + (map #(power % number-of-digits) digits)))))
