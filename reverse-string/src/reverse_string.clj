(ns reverse-string)

;; best version on exercism
(defn reverse-string [s]
  (apply str (into () s)))

(defn reverse-string [s]
  (clojure.string/join (reduce conj '() (clojure.string/split s #"")))
)
