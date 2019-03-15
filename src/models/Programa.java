package models;

import java.util.ArrayList;

public class Programa {

    private ArrayList<String> lineas;

    public Programa(ArrayList<String> instrucciones) {
        this.lineas = instrucciones;
    }

    public Programa() {
        this.lineas = new ArrayList<String>();
    }

    public void addInstruccion(String inst) {
        this.lineas.add(inst);
    }

    public ArrayList<String> getLineas() {
        return this.lineas;
    }

    public String stringCreator() {
        String programa = "";
        for (String linea: lineas){
            programa += linea;
        }
        return programa;
    }

}
