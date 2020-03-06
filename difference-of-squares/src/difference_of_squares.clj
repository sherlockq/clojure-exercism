(ns difference-of-squares)

(defn square [num]
  (* num num))

(defn natural-range [num]
  (range 1 (inc num)))

(def sum (partial reduce +))

(defn add-with-squares [sum number]
  (+ sum (square number)))

(defn sum-of-squares [num]
  (reduce add-with-squares (natural-range num)))

(defn square-of-sum [num]
  (square (sum (natural-range num))))

(defn difference [num]
  (- (square-of-sum num) (sum-of-squares num)))

