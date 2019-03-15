import models.Programa;
import models.StackVector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InterpreteLISP {

    private Programa programaSucio;
    private HashMap<String, StackVector> funciones = new HashMap<>();

    InterpreteLISP(Programa programaLeido) {
        this.programaSucio = programaLeido;
        interpretar();
    }

    private void interpretar() {
        ejecutar(leerPrograma(limpiarPrograma()));
    }

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

    private Object reconocer(Object pr) {
        if (pr instanceof StackVector) {
            StackVector programa = (StackVector) pr;
            Object sec = (programa.getVector().firstElement());
            if (sec instanceof String) {
                ejecutar(programa);
                if (sec.equals("+")) {
                    Integer num1 = (Integer) reconocer(programa.getVector().elementAt(1));
                    Integer num2 = (Integer) reconocer(programa.getVector().elementAt(2));
                    System.out.println(num1 + num2);
                    return num1 + num2;
                } else if (sec.equals("-")) {
                    Integer num1 = (Integer) reconocer(programa.getVector().elementAt(1));
                    Integer num2 = (Integer) reconocer(programa.getVector().elementAt(2));
                    System.out.println(num1 - num2);
                    return num1 - num2;
                } else if (sec.equals("*")) {
                    Integer num1 = (Integer) reconocer(programa.getVector().elementAt(1));
                    Integer num2 = (Integer) reconocer(programa.getVector().elementAt(2));
                    System.out.println(num1 * num2);
                    return num1 * num2;
                } else if (sec.equals("/")) {
                    Integer num1 = (Integer) reconocer(programa.getVector().elementAt(1));
                    Integer num2 = (Integer) reconocer(programa.getVector().elementAt(2));
                    System.out.println(num1 / num2);
                    return num1 / num2;
                } else if (((String) sec).toUpperCase().equals("DEFUN")) {
                    String nombre = (String) reconocer(programa.getVector().elementAt(1));
                    StackVector parametro = (StackVector) programa.getVector().elementAt(2);
                    this.funciones.put(nombre, programa);
                    System.out.println(nombre+" ("+parametro.getVector().firstElement()+") {}");
                } else if (((String) sec).toUpperCase().equals("ATOM")) {
                    Object algo = reconocer(programa.getVector().elementAt(1));
                    System.out.println(isAtom(algo));
                    return isAtom(algo);
                } else if (((String) sec).toUpperCase().equals("LIST")) {
                    List miLista = new ArrayList();
                    StringBuilder miListaString = new StringBuilder();
                    for (int i = 1; i < programa.size(); i++) {
                        miLista.add(reconocer(programa.getVector().elementAt(i)));
                        miListaString.append(reconocer(programa.getVector().elementAt(i))).append(" ");
                    }
                    //TODO: NO SE PORQUE SE IMPRIME DOBLE LA LISTA
                    //System.out.println("Mi Lista: " + miListaString);
                    return miLista;
                } else if (((String) sec).toUpperCase().equals("EQUAL")) {
                    List lst1 = (List) reconocer(programa.getVector().elementAt(1));
                    List lst2 = (List) reconocer(programa.getVector().elementAt(2));
                    System.out.println(esIgual(lst1, lst2));
                    return esIgual(lst1, lst2);
                } else if (sec.equals("=") || ((String) sec).toUpperCase().equals("EQ")) {
                    Object e1 = reconocer(programa.getVector().elementAt(1));
                    Object e2 = reconocer(programa.getVector().elementAt(2));
                    System.out.println(esIgual(e1, e2));
                    return esIgual(e1, e2);
                } else if (sec.equals("<")) {
                    Integer e1 = (Integer) reconocer(programa.getVector().elementAt(1));
                    Integer e2 = (Integer) reconocer(programa.getVector().elementAt(2));
                    System.out.println(comparar("<",e1, e2));
                    return comparar("<",e1, e2);
                } else if (sec.equals(">")) {
                    Integer e1 = (Integer) reconocer(programa.getVector().elementAt(1));
                    Integer e2 = (Integer) reconocer(programa.getVector().elementAt(2));
                    System.out.println(comparar(">",e1, e2));
                    return comparar(">",e1, e2);
                } else if (((String) sec).toUpperCase().equals("COND")) {
                    //TODO
                } else if (((String) sec).toUpperCase().equals("FORMAT")) {
                    //TODO
                } else if (((String) sec).toUpperCase().equals("DEFVAR")) {
                    //TODO
                } else if (((String) sec).toUpperCase().equals("SETF")) {
                    //TODO
                } else if (((String) sec).toUpperCase().equals("PRINT")) {
                    //TODO
                } else {
                    StackVector funcion = this.funciones.get(sec);
                    if (programa.size() >= 2) {
                        String parametro = (String) reconocer(programa.getVector().elementAt(1));
                        //TODO: LOGICA PARA EVALUAR LA FUNCION CON EL PARAMETRO INGRESADO

                        System.out.println(sec+" ("+parametro+") = ");
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

    private String isAtom(Object algo) {
        if (algo instanceof String) {
            return "TRUE";
        } else if (algo instanceof Integer) {
            return "TRUE";
        } else {
            return "NIL";
        }
    }

    private String esIgual(Object e1, Object e2) {
        if (e1.equals(e2)) {
            return "TRUE";
        } else {
            return "NIL";
        }
    }

    private Boolean comparar(String comp, Integer e1, Integer e2) {
        if (comp.equals("<")) {
            return e1 < e2;
        } else if (comp.equals(">")){
            return e1 > e2;
        }

        return false;
    }

    private void vectorToString(Object pr) {

        if (pr instanceof StackVector) {
            StackVector programa = (StackVector) pr;
            for (int i = 0; i < programa.size(); i++) {
                if (programa.getVector().elementAt(i) instanceof StackVector) {
                    vectorToString(programa.getVector().elementAt(i));
                } else {
                    System.out.println(programa.getVector().elementAt(i));
                }
            }
        } else {
            System.out.println("Problema");
        }

    }

}
