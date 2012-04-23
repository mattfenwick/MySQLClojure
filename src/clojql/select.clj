(ns clojql.select
  (:use clojure.contrib.sql))


(def db {
  :classname "com.mysql.jdbc.Driver" 
  :subprotocol "mysql" 
  :subname "//localhost:3306/mysqlclojure" 
  :user "root"})

(defn query-1
  []
  (with-connection db 
   (with-query-results rs 
    ["select * from assign"] 
    (count rs))))

(defn query-2
  []
  (with-connection db 
   (with-query-results rs 
     ["select * from assign"]
     (vec rs))))

(defn select-pk-large
  [id]
  (with-connection db
   (with-query-results rs
     ["select * from largetable where id = ?" id]
     (doall rs))))


