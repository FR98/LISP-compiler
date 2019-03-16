package models;

import java.util.ArrayList;

/**
 * Clase con la logica al leer el programa ingresado en txt
 */
public class Programa {

    private ArrayList<String> lineas;

    /**
     * Constructor
     * @param instrucciones instrucciones encontradas
     */
    public Programa(ArrayList<String> instrucciones) {
        this.lineas = instrucciones;
    }

    /**
     * constructor vacio
     */
    public Programa() {
        this.lineas = new ArrayList<String>();
    }

    /**
     * Agrega una instruccion al arraylist, un nuevo elemento al arraylist
     * @param inst instruccion
     */
    public void addInstruccion(String inst) {
        this.lineas.add(inst);
    }

    /**
     * Captura las lineas del arraylist
     * @return lineas
     */
    public ArrayList<String> getLineas() {
        return this.lineas;
    }

    /**
     * crea un string para almacenar todas las lineas, todas las lineas son ahora un string
     * @return string del programa
     */
    public String stringCreator() {
        String programa = "";
        for (String linea: lineas){
            programa += linea;
        }
        return programa;
    }

}
