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
        ejecutar();
    }

    public void limpiarPrograma() {
        for (String inst : this.programaSucio.instrucciones) {
            if (inst.length() >= 1) {
                String nuevaInst = "";
                for (int i = 0; i < inst.length(); i++) {
                    if (inst.charAt(i) != ';') {
                        nuevaInst += inst.charAt(i);
                    } else {
                        break;
                    }
                }
                this.programa.addInstruccion(nuevaInst);
            }
        }
    }

    public void ejecutar() {
        //TODO
        for (String inst : programa.instrucciones) {
            System.out.println(inst);
        }
    }

}
