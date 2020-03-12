(ns isbn-verifier)
(def not-dash? (comp not #(= \- %)))
(defn to-digit-or-10 [char]
  (if (= \X char)
    10
    (Character/digit char 10)))

(def seq-10-to-1 (range 10 0 -1))

(defn checksum [isbn]
  (->> isbn
       (filter not-dash?)
       (map to-digit-or-10)
       (map * seq-10-to-1)
       (reduce +)))

(defn is-digit? [char]
  (Character/isDigit ^char char))

(defn last-digit-valid? [isbn]
  (or (is-digit? (last isbn)) (= \X (last isbn))))

(defn valid? [isbn]
  (let [no-dash-isbn (filter not-dash? isbn)]
    (and
      (= 10 (count no-dash-isbn))
      (every? is-digit? (take 9 no-dash-isbn))
      (last-digit-valid? no-dash-isbn))))

(defn isbn? [isbn]
  (and (valid? isbn)
       (-> isbn
           checksum
           (mod 11)
           zero?)))