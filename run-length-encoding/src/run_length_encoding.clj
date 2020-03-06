(ns run-length-encoding)

(defn format-repetition [repetition]
  (if (= 1 repetition)
    nil
    repetition))

(defn run-length-encode
  "encodes a string with run-length-encoding"
  [plain-text]
  (loop [last-char nil
         repetition 1
         encoded nil
         remaining-text plain-text]

    (if-let [current-char (first remaining-text)]
      (if (= last-char current-char)
        (recur current-char (inc repetition) encoded (rest remaining-text))
        (recur current-char 1 (str encoded (format-repetition repetition) last-char) (rest remaining-text)))
      (str encoded (format-repetition repetition) last-char))))

(defn to-digit [char]
  (Character/digit ^char char 10))

(defn add-digit [num new-digit]
  (+ new-digit
     (* 10 (or num 0))))

(defn is-digit [char]
  (Character/isDigit ^char char))
(defn append-chars [text char-to-append times]
  (apply str
         text
         (repeat (or times 1) char-to-append)))

(defn run-length-decode
  "decodes a run-length-encoded string"
  [cipher-text]
  (loop [repetition nil
         decoded nil
         remaining-text cipher-text]
    (if-let [current-char (first remaining-text)]
      (if (is-digit current-char)
        (recur (add-digit repetition (to-digit current-char)) decoded (rest remaining-text))
        (recur nil (append-chars decoded current-char repetition) (rest remaining-text)))
      (str decoded))))
