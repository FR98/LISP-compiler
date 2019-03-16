(DEFUN SEGUNDAPRUEBA (probamos complejidad ahora) (cond
    ((ATOM (LIST 1 2 3)) 1)
    ((= 20 50) 2)
    ((> 1 2) 3)
    ((< 7 6) 4)
    ((ATOM (List 8 2)) 5)
    ((ATOM 1) 6)
))
(DEFUN terceraprueba (seguimos con complejidad) 
    (EQUAL
        (EQUAL
            (cond 
                ((< seguimos 2) (SEGUNDAPRUEBA 1 2 3))
            )
            (cond 
                ((> CON SEGUIMOS) (SEGUNDAPRUEBA 1 2 3))
            )
        )
        (EQUAL 
            (+ 20 30 50)
            (* 100 (/ 20 20))
        )
    )
)
(SEGUNDAPRUEBA 1 2 3) ; 6
(terceraprueba 1 2 3) ; T
