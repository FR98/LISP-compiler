import java.util.ArrayList;

public class Programa {

    ArrayList<String> lineas;

    Programa(ArrayList<String> instrucciones) {
        this.lineas = instrucciones;
    }

    Programa() {
        this.lineas = new ArrayList<String>();
    }

    public void addInstruccion(String inst) {
        this.lineas.add(inst);
    }

    public String stringCreator() {
        String programa = "";
        for (String linea: lineas){
            programa += linea;
        }
        return programa;
    }

}
