(ns armstrong-numbers)


;;; Math/pow will lose precision for 9 to the 17th power (9^17)
(defn- pow [x n]
  (reduce * (repeat n x)))

;; Version 1
(defn- getdigit [num digit]
  (Character/getNumericValue (.charAt (str num) digit)))

(defn armstrong2? [num]
  (let [len (.length (str num))]
    (= num
       (reduce +
               (map
                 #(pow (getdigit num %) len)
                 (range len))))))
;; Version 2
(defn- sumNumberAndString [number numberInString len]
  (+
    number
    (pow (Integer/valueOf ^String numberInString) len)))

(defn armstrong? [num]
  (let [len (.length (str num))]
    (= num
       (reduce
         #(sumNumberAndString %1 %2 len)
         0
         (.split (str num) "")))))