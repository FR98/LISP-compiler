; Los comentarios son para que se guien no hay que implementarlos en su interprete
(DEFUN factorial (num) 
    ; Calcula el factorial de un número (recursivamente)
    (*
        (COND 
            ((= num 1) 1)
            ; En la guia no dice pero pueden implementar T
            ; Se simplifica la siguiente linea:
            ; (T (factorial (- num 1)))
            ; Noten que tambien funciona si se coloca cualquier cosa ie
            ; ("Hola" (factorial (- num 1)))
            ; solo revisa que lo primero sea no nil
            ((ATOM 1) (factorial (- num 1)))
        )
        num
    )
)
(DEFUN queTanBienLeCaigoADiego(nombre)
    ; Recive un nombre y devuelve que tan bien me caen en una escala de 0 a 100
    0 ; no me se sus nombres
)
(DEFUN cuantosPuntosVoyASacarSiNoHeEmpezado (puntos)
    ; Recibe un maximo de puntos y devuelve cuantos pueden sacar si no han empezado
    (* 
        (+
            (/ puntos 2) ; divido entre dos porque no van a terminar
            7 ; sumado el tiempo en dias que les queda a partir del viernes
        )
        (queTanBienLeCaigoADiego "Juanito") ; multiplicado por que tan bien me caen
    )
)
(ATOM (LIST 1 2 3 4 5)) ; prueba si la lista es un atom, devuelve NIL
(factorial 7) ; 5040
(cuantosPuntosVoyASacarSiNoHeEmpezado 100) ; 0

; Si todo funciona sacan pueden sacar 100
; Puntos extra: WRITE CAR CDR LAST MEMBER APPEND... cualquier otro predicado
; NOTEN que van a tener la cantidad de puntos extra correspondiente a que tan dificil
; es implementar el predicado que escojan