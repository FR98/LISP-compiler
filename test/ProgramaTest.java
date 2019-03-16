import models.Programa;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class ProgramaTest {
    private ArrayList<String> lineas;

    /**
     * Junit que comprueba el add de uan isntruccion y tambien el stringCreator
     */
    @Test
    public void addInstruccionTest(){
        Programa programa = new Programa();
        String add1 = "Hola, es una prueba";
        programa.addInstruccion(add1);
        Boolean expected = false;
        Boolean result = true;

        if (programa.stringCreator().equals(add1)){
            expected = true;
        }
        assertEquals(expected, result);
    }
}
