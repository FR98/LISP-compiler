import models.Programa;
import models.StackVector;

import java.util.HashMap;

public class InterpreteLISP {

    Programa programaSucio;
    HashMap<String, StackVector> funciones = new HashMap<>();

    InterpreteLISP(Programa programaLeido) throws Exception {
        this.programaSucio = programaLeido;
        interpretar();
    }

    public void interpretar() throws Exception {
        ejecutar(leerPrograma(limpiarPrograma()));
    }

    public StackVector<Object> limpiarPrograma() {
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
    public Object leerPrograma(StackVector<Object> programa) throws Exception {
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

    public Object ejecutar(Object pr) {
        /*
        if (pr instanceof StackVector) {
            StackVector programa = (StackVector) pr;
            for (int i = 0; i < programa.size(); i++) {
                System.out.println(programa.getVector().elementAt(i));
            }
        }*/
        //vectorToString(pr);
        //System.out.println("");

        if (pr instanceof StackVector) {
            StackVector programa = (StackVector) pr;
            for (int i = 0; i < programa.size(); i++) {
                //ejecutar(programa.getVector().elementAt(i));
                reconocer(programa.getVector().elementAt(i));
            }

        } else if (pr instanceof String) {
            reconocer(pr);
        }

        return "";
    }

    public Object reconocer(Object pr) {
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
                } else if (((String) sec).toUpperCase().equals("LIST")) {
                    //TODO
                } else if (((String) sec).toUpperCase().equals("EQUAL")) {
                    //TODO
                } else if (sec.equals("<")) {
                    //TODO
                } else if (sec.equals(">")) {
                    //TODO
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
            //TODO
            //System.out.println(pr);
            return pr;
        } else if (pr instanceof  Integer) {
            //TODO
            //System.out.println(pr);
            return pr;
        }
        return pr;
    }

    public Object stringA_Tipo(String dato) {
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

    public String isAtom(Object algo) {
        if (algo instanceof String) {
            return "TRUE";
        } else if (algo instanceof Integer) {
            return "TRUE";
        } else {
            return "NIL";
        }
    }

    public void vectorToString(Object pr) {

        if (pr instanceof StackVector) {
            StackVector programa = (StackVector) pr;
            for (int i = 0; i < programa.size(); i++) {
                if (programa.getVector().elementAt(i) instanceof StackVector) {
                    vectorToString((StackVector) programa.getVector().elementAt(i));
                } else {
                    System.out.println(programa.getVector().elementAt(i));
                }
            }
        } else {
            System.out.println("Problema");
        }

    }

}
