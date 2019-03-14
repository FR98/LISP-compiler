import models.Programa;
import models.StackVector;

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
                //new ArrayList<String>(Arrays.asList(limpiarPrograma()))
                limpiarPrograma()
        ));
    }

    public StackVector<Object> limpiarPrograma() {
        //Vector<String> stack = new Vector<>();
        StackVector<Object> stack = new StackVector<>();
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
            //stack.add(linea);
            stack.push(linea);
        }
        return stack;
        //return programaSplitLista;

    }

    /**
     * Lee el archivo .lisp linea por linea
     */
    public StackVector<Object> leerPrograma(StackVector<Object> programaClean) throws Exception {
        if (programaClean.empty()) {
            throw new IllegalArgumentException("Unexpected EOF while reading");
        }
        //String sec = programaClean.remove(0);
        Object sec = programaClean.getVector().remove(0);
        //System.out.println(sec);

        if (sec.equals("(")) {
            //List<Object> inst = new ArrayList<Object>(programaClean.size() - 1);
            StackVector<Object> inst = new StackVector<>(programaClean.size() - 1);

            while (!programaClean.getVector().get(0).equals(")")) {
                //inst.add(leerPrograma(programaClean));
                inst.push(leerPrograma(programaClean));
            }
            programaClean.getVector().remove(0);
            return inst;
        } else {
            StackVector<Object> v = new StackVector<Object>();
            v.push(sec);
            return v;
            //return sec;
        }
    }

    public void ejecutar(StackVector<Object> programaClean) {
        //TODO
        vectorToString(programaClean);
    }

    public void vectorToString(StackVector programaClean) {
        for (int i = 0; i < programaClean.size(); i++) {
            if (programaClean.getVector().elementAt(i).getClass() == programaClean.getClass()) {
                vectorToString((StackVector) programaClean.getVector().elementAt(i));
            } else {
                System.out.println(programaClean.getVector().elementAt(i));
            }
        }
    }

}
