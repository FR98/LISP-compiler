import models.Programa;
import models.StackVector;

public class InterpreteLISP {

    Programa programaSucio;

    InterpreteLISP(Programa programaLeido) throws Exception {
        this.programaSucio = programaLeido;
        interpretar();
    }

    public void interpretar() throws Exception {
        ejecutar(leerPrograma(
                //new ArrayList<String>(Arrays.asList(limpiarPrograma()))
                limpiarPrograma()
        ));
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
        String[] programaSplitLista = programaClean.trim().split("\\s+");

        for (String linea : programaClean.trim().split("\\s+")) {
            stack.push(linea);
        }

        //return programaSplitLista;
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
            return sec;
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
        System.out.println("");
        System.out.println("Ejecutandose...");

        if (pr instanceof StackVector) {
            StackVector programa = (StackVector) pr;
            for (int i = 0; i < programa.size(); i++) {
                /*if (programa.getVector().elementAt(i) instanceof StackVector) {
                    ejecutar(programa.getVector().elementAt(i));
                } else {
                    System.out.println(programa.getVector().elementAt(i));
                }*/
                ejecutar(programa.getVector().elementAt(i));
            }
        } else if (pr instanceof String) {
            System.out.println(pr);
        }




        return "";

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
