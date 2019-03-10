public class InterpreteLISP {

    Programa programaSucio;
    Programa programa;

    InterpreteLISP(Programa programaLeido){
        this.programaSucio = programaLeido;
        this.programa = new Programa();
        interpretar();
    }

    public void interpretar() {
        limpiarPrograma();
        leerPrograma();
        ejecutar();
    }

    public void limpiarPrograma() {
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
                this.programa.addInstruccion(nuevalinea);
            }
        }
    }

    public void leerPrograma() {
        for (String linea : this.programa.lineas) {
            for (int i = 0; i < linea.length(); i++) {
                if (linea.charAt(i) == '(') {
                    //TODO crear instruccion recursivamente
                }
            }
        }

    }

    public void ejecutar() {
        //TODO
        for (String linea : this.programa.lineas) {
            System.out.println(linea);
        }
    }

}
