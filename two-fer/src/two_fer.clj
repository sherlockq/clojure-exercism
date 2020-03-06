(ns two-fer)

(defn two-fer
  ([] (two-fer "you"))
  ([name] (str "One for " name ", one for me.")))

(defmacro in-fix [expr]
  (list (second expr)
        (first expr)
        (last expr)))

(defn in-fix-fn [[arg-1 opr arg-2]]
  #_(println (type opr))
  ((if (symbol? opr)
     (resolve opr)
     opr)
   arg-1
   arg-2))

(defn is-higher-cal? [opr]
  (some #(= opr %) [* /]))

(defn in-fix-all
  ([arg-1 opr arg-2] (in-fix-fn [arg-1 opr arg-2]))
  ([arg-1 opr arg-2 & rest-symbols]
   (if (is-higher-cal? opr)
     (apply in-fix-all (in-fix-all arg-1 opr arg-2) rest-symbols)
     (in-fix-all arg-1 opr (apply in-fix-all arg-2 rest-symbols)))))

;(1 + 2 * 3 + 5)
(defmacro calc [expr]
  (conj expr in-fix-all))
