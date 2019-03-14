public class InterpreteLISP {

    Programa programaSucio;
    //Programa programa;

    InterpreteLISP(Programa programaLeido){
        this.programaSucio = programaLeido;
        //this.programa = new Programa();
        interpretar();
    }

    public void interpretar() {
        leerPrograma(limpiarPrograma());
        ejecutar(limpiarPrograma());
    }

    public String[] limpiarPrograma() {
        Programa programa = new Programa();

        for (String linea : this.programaSucio.lineas) {
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
        return programaClean.split("\\s+");

    }

    /**
     * Lee el archivo .lisp linea por linea
     */
    public void leerPrograma(String[] programaClean) {
        /*for (String linea : this.programa.lineas) {
            for (int i = 0; i < linea.length(); i++) {
                if (linea.charAt(i) == '(') {
                    //TODO crear instruccion recursivamente
                }
            }
        }*/

    }

    public void ejecutar(String[] programaClean) {
        //TODO
        String programaSplit = "";
        for (String linea : programaClean) {
            programaSplit += linea + " ";
        }

        System.out.println(programaSplit);

    }

}
