(ns nukr.core-test
  (:require [clojure.test :refer :all]
  										[nukr.core-helper :refer :all]
  										[nukr.core-handlers :refer :all]
            [nukr.core :as core :refer :all]))

(deftest inactiveperson-test
		(testing "inactiveperson test"
				(is (= "success" (inactiveperson 1)))))

(deftest activeperson-test
		(testing "activeperson test"
				(is (= "success" (activeperson 1)))))

(deftest person-status-test
		(testing "person status test"
				(is (= "active" (person-status 1)))))

(def person {:id 3, :firstname "Ryan", :lastname "Reinolds", :status "active", :friends [1]})

(deftest findperson-test
		(testing "person status test"
				(is (= person (findperson 3)))))

(def index-response {:status 200, :headers {"Content-Type" "text/html"}, :body "Welcome to nukr"})

(deftest index-handler-test
		(testing "index handler test"
				(is (= index-response (index 3)))))

(deftest people-handler-test
		(testing "people handler test"
				(is (= clojure.lang.PersistentArrayMap (type (people-handler 1))))))

(deftest addperson-handler-test
		(testing "addperson handler test"
				(is (= 200 (get (addperson-handler 1) :status)))))