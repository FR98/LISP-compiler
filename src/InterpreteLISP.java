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
        for (String inst : this.programaSucio.instrucciones) {
            //Se borran los comentarios
            if (inst.length() >= 1 && inst.charAt(0) != ';') {
                String nuevaInst = "";
                for (int i = 0; i < inst.length(); i++) {
                    if (inst.charAt(i) != ';') {
                        nuevaInst += inst.charAt(i);
                    } else {
                        break;
                    }
                }
                //Instrucciones sin comentarios
                this.programa.addInstruccion(nuevaInst);
            }
        }
    }

    public void leerPrograma() {

    }

    public void ejecutar() {
        //TODO
        for (String inst : programa.instrucciones) {
            System.out.println(inst);
        }
    }

}
