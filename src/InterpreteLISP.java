import models.Programa;
import models.StackVector;

public class InterpreteLISP {

    Programa programaSucio;
    //models.Programa programa;

    InterpreteLISP(Programa programaLeido){
        this.programaSucio = programaLeido;
        //this.programa = new models.Programa();
        interpretar();
    }

    public void interpretar() {
        leerPrograma(limpiarPrograma());
        ejecutar(limpiarPrograma());
        //ejecutar(leerPrograma(limpiarPrograma()));
    }

    public StackVector<String> limpiarPrograma() {
        Programa programa = new Programa();

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
        String programaClean;
        programaClean = programa.stringCreator().replaceAll("\\("," ( ").replaceAll("\\)", " ) ");
        StackVector<String> stack = new StackVector<>();

        for (String linea : programaClean.split("\\s+")) {
            stack.push(linea);
        }

        return stack;

    }

    /**
     * Lee el archivo .lisp linea por linea
     */
    public void leerPrograma(StackVector<String> programaClean) {

    }

    public void ejecutar(StackVector<String> programaClean) {
        //TODO
        String programaSplit = "";
        for (int i = 0; i < programaClean.size(); i++) {
            programaSplit += programaClean.getVector().elementAt(i) + " ";
        }

        System.out.println(programaSplit);

    }

}
