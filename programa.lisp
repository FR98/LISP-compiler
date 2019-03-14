;;;; Descripcion del  models.Programa
;;; Comentario
;; Comentario dentro del codigo
; Comentario despues de linea de codigo
;(format t "Hola Mundo ~%")

(defun hello-you (name)
    ;(format t "Hello ~a! ~%" name)
	(format t "Hello" name)
)
(format t "Hola Mundo")
(print "Como te llamas? ")
(defvar name (read))

(defun hello-you (name)
    ;(format t "Hello ~a! ~%" name)
	(format t "Hello" name)
)

(hello-you name)

(print (+ 5 4))
(print (- 5 4)) ;Hola
(print (* 5 4))
(print (/ 10 5))

(defvar num 0)
(setf num 6)
(print num)