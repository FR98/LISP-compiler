import models.Programa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class InterpreteLISP {

    Programa programaSucio;
    //models.Programa programa;

    InterpreteLISP(Programa programaLeido) throws Exception {
        this.programaSucio = programaLeido;
        //this.programa = new models.Programa();
        interpretar();
    }

    public void interpretar() throws Exception {
        //leerPrograma(limpiarPrograma());
        //ejecutar(limpiarPrograma());
        //ejecutar(leerPrograma(limpiarPrograma()));
        ejecutar(leerPrograma(
                new ArrayList<String>(Arrays.asList(limpiarPrograma()))
        ));
    }

    public String[] limpiarPrograma() {
        Vector<String> stack = new Vector<>();
        Programa programa = new Programa();
        String programaClean;

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

        //Linea de codigo que coloca un espacion entre parentesis para no tomarlo como una palabra
        programaClean = programa.stringCreator().replaceAll("\\("," ( ").replaceAll("\\)", " ) ");

        String[] programaSplitLista = programaClean.trim().split("\\s+");

        for (String linea : programaClean.trim().split("\\s+")) {
            stack.add(linea);
        }
        //return stack;
        return programaSplitLista;

    }

    /**
     * Lee el archivo .lisp linea por linea
     */
    public Object leerPrograma(List<String> programaClean) throws Exception {
        if (programaClean.isEmpty()) {
            throw new IllegalArgumentException("Unexpected EOF while reading");
        }
        String sec = programaClean.remove(0);
        System.out.println(sec);

        if (sec.equals("(")) {
            List<Object> inst = new ArrayList<Object>(programaClean.size() - 1);
            while (!programaClean.get(0).equals(")")) {
                inst.add(leerPrograma(programaClean));
            }
            System.out.println("Hola11");
            programaClean.remove(0);
            return inst;
        } else {
            //Vector<Object> v = new Vector<Object>();
            //v.push(sec);
            return sec;
        }
    }

    public void ejecutar(Object programaClean) {
        //TODO
        /*
        String programaSplit = "";
        for (int i = 0; i < programaClean.size(); i++) {
            programaSplit += programaClean.getVector().elementAt(i);
            System.out.println(programaClean.getVector().elementAt(i));
        }
        System.out.println(programaSplit);
        */

    }

}
