(ns nukr.core-helper
  (:require [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.set :as set]
            [clojure.walk :as walk])
  (:gen-class))

; we store everybody here
(def people-collection (atom []))

; add new person to collection
(defn addperson [firstname lastname]
		(let [id (count @people-collection)]
  		(swap! people-collection conj {:id id
  																															:firstname firstname
                                 :lastname lastname
                                 :status "active"
                                 :friends []})))

; here we use the count of friendlist to add a new friend to a person
(defn addfriend [id idfriend]
  (swap! people-collection assoc-in 
  			[id :friends (count ((get @people-collection id) :friends))] idfriend))

; Gets person by id
(defn findperson [id] 
		(first (filter (fn [entry] (= (:id entry) id)) @people-collection)))

; Get friends from the id guy
(defn getfriends [id] (get-in (findperson id) [:friends]))

; This get all the friends from the guy friends
(defn friendsof-friends [id] (for [i (getfriends id)] (getfriends i)))

; Here we calculate the frequency of a friend appear to the guy
(defn find-suggests [id] (frequencies (into () cat (friendsof-friends id))))

; Here we got the friend suggestions based on force of friendship
(defn suggests [id] (for [a 
		(remove (into #{id} (getfriends id)) 
				(map first (find-suggests id)))] 
						(findperson a)))

; Connect persons
(defn markperson-connected [id idfriend] 
		(do (addfriend id idfriend) (addfriend idfriend id) ))

; Only retrieve the person status
(defn person-status [id] (get-in @people-collection [id :status]))

; i've try to centralize all validatons for adding friends
(defn friend-validator [id idfriend] 
		(cond
					(= id idfriend) "u're'nt the only friend of yours"
					(some #(= idfriend %) (get-in @people-collection [id :friends])) "You're both already friends"
					(= (person-status idfriend) "inactive") "This one wants to be alone"
					(markperson-connected id idfriend) "Friend added succefully!" ))

; This on turns person inactive
(defn inactiveperson [id] 
		(swap! people-collection update-in [id] merge [:status "inactive"]) "success")

; And this do the opposite from above
(defn activeperson [id] 
		(swap! people-collection update-in [id] merge [:status "active"]) "success")

; Here we auto change the status according to the actual
(defn toggleperson-status [id] 
		(let [status (person-status id)]
				(case status
						"active" (inactiveperson id) 
						"inactive" (activeperson id))) "success")