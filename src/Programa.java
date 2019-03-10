import java.util.ArrayList;

public class Programa {

    ArrayList<String> instrucciones;

    Programa(ArrayList<String> instrucciones) {
        this.instrucciones = instrucciones;
    }

    Programa() {
        this.instrucciones = new ArrayList<String>();
    }

    public void addInstruccion(String inst) {
        this.instrucciones.add(inst);
    }

}
