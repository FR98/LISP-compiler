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
(ATOM (LIST 1 2 3 4 5))
(factorial 7)
(cuantosPuntosVoyASacarSiNoHeEmpezado 100)