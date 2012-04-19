(ns clojql.core
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

(def letters "abcdefghijklmnopqrstuvwxyz")

(defn get-rand-letter
  []
  (get letters
       (rand-int (count letters))))


(defn get-rand-string
  [len]
  (apply str
   (for [_ (range len)]
    (get-rand-letter))))

(defn rand-medium-record
  []
  (let [f1 (apply str (str (rand-int 100000)) "." (str (rand-int 100)))
        f2 (get-rand-string 10)
        f3 (rand-int 10000)
        f4 (rand-int 100)]
    {:decimalfield1 f1 
     :indexedstringfield f2
     :indexfield1 f3
     :indexfield2 f4}))

(defn medium-insert
  [n]
  (with-connection db
   (dotimes [_ n]
    (insert-records "mediumtable" (rand-medium-record)))))

(defn rand-large-record
  []
  (let [f1 (rand-int 10000000)
        f2 (rand-int 1000)
        f3 (get-rand-string 40)
        f4 (+ 1 (rand-int 50005)) ;; because the keys are 1 to 50005 
        f5 (rand-int 100000)
        f6 (rand-int 100000)
        f7 (rand-int 100000)
        f8 (rand-int 100000000)
        f9 (get-rand-string 15)]
    {:intfield1       f1 
     :indexedintfield f2
     :stringfield     f3
     :foreignkeyfield f4
     :indexfield1     f5
     :indexfield2     f6
     :indexfield3     f7
     :notnullint      f8
     :notnullstring   f9}))

;;Should work , untested 
(defn rand-large-record-alt
  []
  (let [{:intfield1 (rand-int 10000000)
        :indexedintfield (rand-int 1000)
        :stringfield (get-rand-string 40)
        :foreignkeyfield (+ 1 (rand-int 50005))  
        :indexfield1 (rand-int 100000)
        :indexfield2 (rand-int 100000)
        :indexfield3  (rand-int 100000)
        :notnullint (rand-int 100000000)
        :notnullstring (get-rand-string 15)
	:as myRow
        }]
    ))


(defn large-insert
  [n]
  (with-connection db
   (dotimes [_ n]
    (insert-records "largetable" (rand-large-record)))))


