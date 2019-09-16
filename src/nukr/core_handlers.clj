(ns nukr.core-handlers
  (:require [nukr.core-helper :refer :all]
            [clojure.data.json :as json])
  (:gen-class))

; Helper to get the parameter specified by pname from :params object in req
(defn getparameter [req pname] (get (:params req) pname))

(defn index [req] ;(3)
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Welcome to nukr"})

; Return List of People
(defn people-handler [req]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (str (json/write-str @people-collection))})

; Update people status by Id
(defn updatepeople-handler [req]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (-> (let [p (partial getparameter req)] 
     													(toggleperson-status (Integer/parseInt (p :id)))))})

; Connects people status using id and idfriend
(defn connectpeople-handler [req]
		{:status  200
   :headers {"Content-Type" "text/json"}
   :body    (-> (let [p (partial getparameter req)] 
         									(friend-validator 
         											(Integer/parseInt (p :id)) 
         											(Integer/parseInt(p :idfriend)))))})

; Return people by Id
(defn findpeople-handler [req]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (-> (let [p (partial getparameter req)] 
         									(findperson (Integer/parseInt (p :id)))))})

; Return people by Id
(defn suggestpeople-handler [req]
  {:status  200
  	:headers {"Content-Type" "text/json"}
   :body    (-> (let [p (partial getparameter req)] 
         									(suggests (Integer/parseInt (p :id)))))})

; Add a new person into the people-collection
(defn addperson-handler [req]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (-> (let [p (partial getparameter req)]
                  (str (json/write-str (addperson (p :firstname) (p :lastname))))))})