(ns user
	(:use numText)
	)

(defn -main []
	(println "Entrada:")
	(let [entrada (read-line)]
		(println "Salida:" (numText/nombra entrada))
		)
	)