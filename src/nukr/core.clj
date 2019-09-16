(ns nukr.core
  (:require [nukr.core-handlers :refer :all]
  										[nukr.core-helper :refer :all]
  										[org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
  										[ring.middleware.defaults :refer :all])
  (:gen-class))


; Our main routes
(defroutes app-routes
  (GET "/" [] index)
  (GET "/people" [] people-handler)
  (GET "/people/find" [] findpeople-handler)
  (GET "/people/suggestions" [] suggestpeople-handler)
  (GET "/people/connect" [] connectpeople-handler); UPDATE
  (GET "/people/update" [] updatepeople-handler); UPDATE
  (GET "/people/add" [] addperson-handler); POST
  (route/not-found "Error, page not found!"))

; Our main entry function
(defn -main
  "The name already say it"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    ; Run the server with Ring.defaults middleware
    (server/run-server (wrap-defaults #'app-routes site-defaults) {:port port})
    ; Run the server without ring defaults
    ;(server/run-server #'app-routes {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))


; Adding people
(addperson "Tony" "Stark")
(addperson "Peter" "Parker")
(addperson "Prof" "X")
(addperson "Ryan" "Reinolds")
(addperson "Eddie" "Vedder")
(addperson "Sarah" "Connor")
(addperson "Ozzy" "Osbourne")

; Connecting people
(markperson-connected 0 1)
(markperson-connected 0 2)
(markperson-connected 1 2)
(markperson-connected 1 3)
(markperson-connected 2 5)
(markperson-connected 2 4)