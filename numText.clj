(ns numText)

(require '[clojure.string :as string])

(def digitos {1 "uno"   , 2 "dos"  , 3 "tres",
              4 "cuatro", 5 "cinco", 6 "seis",
              7 "siete" , 8 "ocho" , 9 "nueve"})

(def dieci {1 "once", 2 "doce", 3 "trece", 4 "catorce", 5 "quince"})

(def multiplos_diez {1 "diez"    , 2 "viente"   , 3 "treinta",
                     4 "cuarenta", 5 "cincuenta", 6 "sesenta",
                     7 "setenta" , 8 "ochenta"  , 9 "noventa"})

(def multiplos_cien {1 "ciento"       , 2 "doscientos", 3 "trescientos",
                     4 "cuatrocientos", 5 "quinientos", 6 "seiscientos",
                     7 "setecientos"  , 8 "ochocientos",9 "novecientos"})

(def llones_digitos {1 "m"    , 2 "b"    , 3 "tr",
                     4 "cuatr", 5 "quint", 6 "sext",
                     7 "sept" , 8 "oct"  , 9 "non"})

(def llones_unidades {1 "un"    , 2 "duo" , 3 "tre",
                      4 "cuator", 5 "quin", 6 "sex",
                      7 "sept"  , 8 "octo", 9 "noven"})

(def llones_diez {1 "dec"       , 2 "vigint"     , 3 "trigint",
                  4 "quadragint", 5 "quinquagint", 6 "sexagint",
                  7 "septuagint", 8 "octogint"   , 9 "nonagint"})

(defn unir [d x] (string/join d (remove string/blank? x)))

(defn hasta999 [x, m]
  (if (= x 0) ""
              (let [u (mod x 10) d (mod (quot x 10) 10) c (quot x 100)]
                (let [unidades (if (and (= u 1) (true? m)) (if (= d 2) "ún" "un")  (digitos u))
                      decenas (multiplos_diez d)
                      centenas (multiplos_cien c)]
                    (unir " "
                                 [(cond
                                    (> 100 x) ""
                                    (= 100 x) "cien"
                                    :else centenas
                                    )
                                  (cond
                                    (and (= 0 d) (<= 1 u) (<= u 9)) unidades
                                    (= 0 u) decenas
                                    (and (= 1 d) (<= 1 u) (<= u 5)) (dieci u)
                                    (and (= 1 d) (<= 6 u) (<= u 9)) (string/join "" ["dieci" unidades])
                                    (and (= 2 d) (<= 1 u) (<= u 9)) (string/join "" ["veinti" unidades])
                                    :else (string/join " " [decenas "y" unidades])
                                    )
                                  ])
                    )
                )
              )
  )

(defn zi [l]
  (cond
    (= 0 l) ""
    (< l 10) (llones_digitos l)
    :else (unir ""
                  [(llones_unidades (mod l 10))
                   (llones_diez (quot l 10))
                   ])
    )
  )

(defn hasta999999 [x,l]
  (if (= x 0) ""
              (let [m (quot x 1000) u (mod x 1000)]
                (unir " "
                             [
                              (cond
                                (< x 1000) ""
                                (= m 1) "mil"
                                :else (string/join " " [(hasta999 m true) "mil"])
                                )
                              (if (and (= l 0) (<= u 20) (< 0 u)) "y" "")
                              (hasta999 u (> l 0))
                              (string/join [(zi l) (if (< 0 l)
                                                     (if (< 1 x) "illones" "illón")
                                                     "") ] )
                              ])
                )
              )
  )


(defn parte_en_miles [s]
  (map #(Integer/parseInt % 10)
       (re-seq #"\d{1,6}" (string/join [(subs "000000" 0 (- 6 (mod (count s) 6))) s
                                        ])
               )
       )
  )

(defn nombra [s]
  (let [a (parte_en_miles s) l (range (- (count a) 1) -1 -1)]
    (unir ", " (map hasta999999 a l))
    )
  )
