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
(defun hello-you (name)
    ;(format t "Hello ~a! ~%" name)
	(print "HOLA")
	(format t "Hello" name)
)

;Predicados ATOM, LIST, EQUAL, <, >


;Condiciones

;Otras
(format t "Hola Mundo")
(print "Como te llamas? ")
(defvar name (read))
(hello-you name)
(defvar num 0)
(setf num 6)
(print num)

;Este programa retorna:
;9
;1
;20
;2
;Hola Mundo
;"Como te llamas ? "
;
;"HOLA" Hello
;6