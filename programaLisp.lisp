;;;; Descripcion del  Programa
;;; Comentario
;; Comentario dentro del codigo
; Comentario despues de linea de codigo

#||
Comentario de varias lineas
||#

(setq *print-case* :capitalize)

(format t "Hola Mundo ~%")
(print "Como te llamas? ")
(defvar *name* (read))

(defun hello-you (name)
	(format t "Hello ~a! ~%" name)
)

(hello-you *name* )

(print (+ 5 4))
(print (- 5 4))
(print (* 5 4))
(print (/ 10 5))

(defvar *num* 0)
(setf *num* 6)