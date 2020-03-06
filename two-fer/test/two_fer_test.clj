(ns two-fer-test
  (:require [clojure.test :refer [deftest is]]
            [two-fer :refer :all]))

(deftest two-fer-test
  (is (= "One for you, one for me." (two-fer))))

(deftest name-alice-test
  (is (= "One for Alice, one for me." (two-fer "Alice"))))

(deftest name-bob-test
  (is (= "One for Bob, one for me." (two-fer "Bob"))))

(deftest in-fix-test
  (is (= 2 (in-fix (1 + 1))))
  (is (= 10 (in-fix (2 * 5)))))

(deftest calc-test
  (is (= 17 (calc (2 + 3 * 5))))
  (is (= 2 (calc (1 + 1))))
  (is (= 12 (calc (5 * 3 - 3))))
  (is (= 23 (calc (2 + 3 * 5 + 1 + 3 + 4 / 2)))))