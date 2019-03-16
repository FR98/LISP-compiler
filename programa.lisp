;;;; Descripcion del  models.Programa
;;; Comentario
;; Comentario dentro del codigo
; Comentario despues de linea de codigo
;(format t "Hola Mundo ~%")

;Operaciones Aritmeticas
(print (+ 5 4))
(print (- 5 4))
(print (* 5 4))
(print (/ 10 5))

;Definicion de funciones
(defun hello-you (name apellido)
    ;(format t "Hello ~a! ~%" name)
    (print name)
)

;Predicados ATOM, LIST, EQUAL, <, >
(print (ATOM 1))
(print (ATOM (LIST 1 2 3 4 5)))
(print (EQUAL (LIST 1 2 3 4 5) (LIST 1 2 3 4 5)))
(print (EQUAL (LIST 1 2 5 4 5) (LIST 1 2 3 4 5)))
(print (= 1 1))
(print (= 1 2))
(print (< 1 3))
(print (< 5 3))

;Condiciones
(print
    (COND ((< 6 5) "suspenso")
          ((< 6 7) "aprobado")
    )
)

(print
    (*
        (COND
            ((= 7 1) 1)
            ((ATOM 1) 6))
        )
        7
    )
)

;Otras
(hello-you Willi RO)

(format t "Hola Mundo")
(print "Como te llamas? ")
(defvar name "Willi")
(defvar num 0)
(setf num 6)
(print num)

;Este programa retorna:
;9
;1
;20
;2
;T
;NIL
;T
;NIL
;T
;NIL
;T
;NIL Hola Mundo
;"Como te llamas? " Hello Willi!
;
;6