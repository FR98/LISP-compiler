(DEFUN factorial (num)
    (*
        (COND
            ((= num 1) 1)
            ((ATOM 1) (factorial (- num 1)))
        )
        num
    )
)
(DEFUN queTanBienLeCaigoADiego(nombre)
    0
)
(DEFUN cuantosPuntosVoyASacarSiNoHeEmpezado (puntos)
    (*
        (+
            (/ puntos 2)
            7
        )
        (queTanBienLeCaigoADiego "Juanito")
    )
)
(print (ATOM (LIST 1 2 3 4 5)))
(print (factorial 7))
(print (cuantosPuntosVoyASacarSiNoHeEmpezado 100))