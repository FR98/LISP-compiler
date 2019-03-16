import models.Programa;
import models.StackVector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Toda la logica para interpretar un lisp
 */
public class InterpreteLISP {

    private Programa programaSucio;
    private HashMap<String, StackVector> funciones = new HashMap<>();

    /**
     * Constructor
     * @param programaLeido programa ingresado
     */
    InterpreteLISP(Programa programaLeido) {
        this.programaSucio = programaLeido;
        interpretar();
    }

    /**
     * Ejecuta el archivo leido, elimina los comentarios, luego lo lee y por ultimo utiliza la recursividad
     */
    private void interpretar() {
        ejecutar(leerPrograma(limpiarPrograma()));
    }

    /**
     * Limpia el programa leido, es decir, elimina los comentarios
     * @return
     */
    private StackVector<Object> limpiarPrograma() {
        StackVector<Object> stack = new StackVector<>();
        Programa programa = new Programa();
        String programaClean;

        programa.addInstruccion("(");
        for (String linea : this.programaSucio.getLineas()) {
            //Se borran los comentarios
            if (linea.length() >= 1 && linea.charAt(0) != ';') {
                String nuevalinea = "";
                for (int i = 0; i < linea.length(); i++) {
                    if (linea.charAt(i) != ';') {
                        nuevalinea += linea.charAt(i);
                    } else {
                        break;
                    }
                }
                //Instrucciones sin comentarios
                programa.addInstruccion(nuevalinea);
            }
        }
        programa.addInstruccion(")");

        //Linea de codigo que coloca un espacion entre parentesis para no tomarlo como una palabra
        programaClean = programa.stringCreator().replaceAll("\\("," ( ").replaceAll("\\)", " ) ");

        for (String linea : programaClean.trim().split("\\s+")) {
            stack.push(linea);
        }

        return stack;
    }

    /**
     * Lee el archivo .lisp linea por linea
     */
    private Object leerPrograma(StackVector<Object> programa) {
        if (programa.empty()) {
            throw new IllegalArgumentException("Unexpected EOF while reading");
        }
        Object sec = programa.getVector().remove(0);

        if (sec.equals("(")) {
            StackVector<Object> inst = new StackVector<>(programa.size() - 1);

            while (!programa.getVector().get(0).equals(")")) {
                inst.push(leerPrograma(programa));
            }
            programa.getVector().remove(0);
            return inst;
        } else {
            return stringA_Tipo((String) sec);
        }
    }

    /**
     * Permite la recusividad
     * @param pr objeto stackvector creado
     */
    private void ejecutar(Object pr) {
        if (pr instanceof StackVector) {
            StackVector programa = (StackVector) pr;
            for (int i = 0; i < programa.size(); i++) {
                reconocer(programa.getVector().elementAt(i));
            }

        } else if (pr instanceof String) {
            reconocer(pr);
        }
    }

    /**
     * Reconoce la operacion que se debe realizar al leerla en el programa lisp
     * @param pr objeto stackvector
     * @return el resultado de la operacion o instruccion encontrada
     */
    private Object reconocer(Object pr) {
        if (pr instanceof StackVector) {
            StackVector programa = (StackVector) pr;
            Object sec = (programa.getVector().firstElement());
            if (sec instanceof String) {
                ejecutar(programa);
                if (sec.equals("+")) {
                    Integer num1 = (Integer) reconocer(programa.getVector().elementAt(1));
                    Integer num2 = (Integer) reconocer(programa.getVector().elementAt(2));
                    return num1 + num2;

                } else if (sec.equals("-")) {
                    Integer num1 = (Integer) reconocer(programa.getVector().elementAt(1));
                    Integer num2 = (Integer) reconocer(programa.getVector().elementAt(2));
                    return num1 - num2;

                } else if (sec.equals("*")) {
                    Integer num1 = (Integer) reconocer(programa.getVector().elementAt(1));
                    Integer num2 = (Integer) reconocer(programa.getVector().elementAt(2));
                    return num1 * num2;

                } else if (sec.equals("/")) {
                    Integer num1 = (Integer) reconocer(programa.getVector().elementAt(1));
                    Integer num2 = (Integer) reconocer(programa.getVector().elementAt(2));
                    return num1 / num2;

                } else if (((String) sec).toUpperCase().equals("DEFUN")) {
                    String nombre = (String) reconocer(programa.getVector().elementAt(1));
                    this.funciones.put(nombre, programa);

                } else if (((String) sec).toUpperCase().equals("ATOM")) {
                    Object algo = reconocer(programa.getVector().elementAt(1));
                    return isAtom(algo);

                } else if (((String) sec).toUpperCase().equals("LIST")) {
                    List miLista = new ArrayList();
                    StringBuilder miListaString = new StringBuilder();
                    for (int i = 1; i < programa.size(); i++) {
                        miLista.add(reconocer(programa.getVector().elementAt(i)));
                        miListaString.append(reconocer(programa.getVector().elementAt(i))).append(" ");
                    }
                    return miLista;

                } else if (((String) sec).toUpperCase().equals("EQUAL")) {
                    List lst1 = (List) reconocer(programa.getVector().elementAt(1));
                    List lst2 = (List) reconocer(programa.getVector().elementAt(2));
                    return esIgual(lst1, lst2);

                } else if (sec.equals("=") || ((String) sec).toUpperCase().equals("EQ")) {
                    Object e1 = reconocer(programa.getVector().elementAt(1));
                    Object e2 = reconocer(programa.getVector().elementAt(2));
                    return esIgual(e1, e2);

                } else if (sec.equals("<")) {
                    Integer e1 = (Integer) reconocer(programa.getVector().elementAt(1));
                    Integer e2 = (Integer) reconocer(programa.getVector().elementAt(2));
                    return comparar("<",e1, e2);

                } else if (sec.equals(">")) {
                    Integer e1 = (Integer) reconocer(programa.getVector().elementAt(1));
                    Integer e2 = (Integer) reconocer(programa.getVector().elementAt(2));
                    return comparar(">",e1, e2);

                } else if (((String) sec).toUpperCase().equals("COND")) {
                    StackVector condiciones = new StackVector();
                    StackVector retornos = new StackVector();
                    for (int i = 1; i < programa.size(); i++) {
                        if (programa.getVector().elementAt(i) instanceof StackVector) {
                            StackVector cond = (StackVector) programa.getVector().elementAt(i);
                            Object eval = cond.getVector().elementAt(0);
                            Object res = cond.getVector().elementAt(1);
                            condiciones.push(eval);
                            retornos.push(res);
                        }
                    }

                    for (int i = 0; i < condiciones.size(); i++) {
                        if (reconocer(condiciones.getVector().elementAt(i)) instanceof Boolean) {
                            Boolean bool = (Boolean) reconocer(condiciones.getVector().elementAt(i));
                            if (bool) {
                                return reconocer(retornos.getVector().elementAt(i));
                            }
                        }
                    }

                } else if (((String) sec).toUpperCase().equals("FORMAT")) {
                    //TODO

                } else if (((String) sec).toUpperCase().equals("DEFVAR")) {
                    //TODO

                } else if (((String) sec).toUpperCase().equals("SETF")) {
                    //TODO

                } else if (((String) sec).toUpperCase().equals("PRINT")) {
                    StringBuilder printString = new StringBuilder();
                    for (int i = 1; i < programa.size(); i++) {
                        printString.append(reconocer(programa.getVector().elementAt(i))).append(" ");
                    }
                    System.out.println(printString);

                } else {
                    StackVector funcion = this.funciones.get(sec);
                    if (funcion != null) {
                        if (programa.size() >= 2) {
                            StackVector parametros = (StackVector) funcion.getVector().elementAt(2);
                            StackVector ingresados = new StackVector<Object>();
                            for (int i = 1; i < programa.size(); i++) {
                                ingresados.push(reconocer(programa.getVector().elementAt(i)));
                            }

                            StackVector funcionNueva = new StackVector();
                            for (int i = 3; i < funcion.size(); i++) {
                                funcionNueva.push(funcion.getVector().elementAt(i));
                            }

                            StackVector funcionOperable = cambiarParametros(parametros, ingresados, funcionNueva);
                            System.out.println();
                            System.out.print(sec+" (");
                            vectorToString(ingresados);
                            System.out.print(") = ");
                            //TODO: NO SE PORQUE IMPRIME LA DIRECCION
                            vectorToString(funcionOperable);

                            System.out.println(reconocer(funcionOperable));
                            //System.out.println(reconocer(funcionOperable));
                            return reconocer(funcionOperable);

                        }
                    }
                }
            }

        } else if (pr instanceof String) {
            return pr;
        } else if (pr instanceof  Integer) {
            return pr;
        }
        return pr;
    }

    /**
     * Retorna el valor segun su tipo si en dado caso el tipo que esta declarado no es el correcto
     * @param dato valor ingresado
     * @return dato cambiado
     */
    private Object stringA_Tipo(String dato) {
        try {
            return Integer.parseInt(dato);
        } catch (NumberFormatException exc) {
            try {
                return Float.parseFloat(dato);
            } catch (NumberFormatException exc2) {
                return dato;
            }
        }
    }

    /**
     * Realiza la funcion atom mostrando true o nil (false) segun la sintaxis de lisp
     * @param algo valor ingresado
     * @return el estado, si no es un string o integer es false
     */
    private Boolean isAtom(Object algo) {
        if (algo instanceof String) {
            return true;
        } else if (algo instanceof Integer) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Para las funciones EQUAL y EQ, si los objetos son iguales es true, de lo contrario nil (false)
     * @param e1 objeto 1
     * @param e2 objeto 2
     * @return estado booleano
     */
    private Boolean esIgual(Object e1, Object e2) {
        if (e1.equals(e2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Valida el tag
     * @param comp string
     * @param e1 int 1
     * @param e2 int 2
     * @return false
     */
    private Boolean comparar(String comp, Integer e1, Integer e2) {
        if (comp.equals("<")) {
            return e1 < e2;
        } else if (comp.equals(">")){
            return e1 > e2;
        }

        return false;
    }

    /**
     * Realiza toda la logica de Defun en donde se cambia el valor del parametro por lo ingresado segun el lisp
     * @param parametros reconocido en lisp (nombre de la variable)
     * @param ingresados lo que desea el usuario
     * @param funcionNueva en donde se almacena
     * @return el stack con los parametros nuevos
     */
    private StackVector cambiarParametros(StackVector parametros, StackVector ingresados, Object funcionNueva) {
        StackVector resultado = null;

        if (funcionNueva instanceof StackVector) {
            StackVector funcionOperable = (StackVector) funcionNueva;
            for (int i = 0; i < funcionOperable.size(); i++) {
                if (funcionOperable.getVector().elementAt(i) instanceof StackVector) {
                    cambiarParametros(parametros, ingresados, funcionOperable.getVector().elementAt(i));
                } else {

                    for (int j = 0; j < parametros.size(); j++) {
                        String parametro = (String) parametros.getVector().elementAt(j);
                        Object ingresado = ingresados.getVector().elementAt(j);

                        if (funcionOperable.getVector().elementAt(i) instanceof StackVector) {
                            cambiarParametros(parametros, ingresados, (StackVector) funcionOperable.getVector().elementAt(i));
                        } else {
                            if (funcionOperable.getVector().elementAt(i).equals(parametro)) {
                                funcionOperable.getVector().set(i, ingresado);
                            } else {
                                funcionOperable.getVector().set(i, funcionOperable.getVector().elementAt(i));
                            }
                        }
                    }
                }
            }

            resultado = funcionOperable;
        } else {
            System.out.print("Problema");
        }

        return resultado;
    }

    /**
     * Pasa el vector a un String para poder ser impreso
     * @param pr objeto
     */
    private void vectorToString(Object pr) {

        if (pr instanceof StackVector) {
            StackVector programa = (StackVector) pr;
            for (int i = 0; i < programa.size(); i++) {
                if (programa.getVector().elementAt(i) instanceof StackVector) {
                    vectorToString(programa.getVector().elementAt(i));
                } else {
                    System.out.print(programa.getVector().elementAt(i) + " ");
                }
            }
        } else {
            System.out.print("Problema");
        }

    }

}
