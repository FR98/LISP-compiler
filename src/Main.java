import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        //Para leer el archivo
        Scanner read = new Scanner(System.in);
        System.out.println("Ingrese el nombre del archivo Lisp:");
        String nombreArchivo = read.next();
        ArrayList<String> archivo = new ArrayList<>();
        try {
            Stream<String> lines = Files.lines(
                    Paths.get(nombreArchivo + ".lisp"),
                    StandardCharsets.UTF_8
            );
            lines.forEach(archivo::add);
        } catch (IOException e ){
            System.out.println("Ha ocurrido un error");
        }
        /*
        for (String linea : archivo) {
            System.out.println(linea);
        }*/

        Programa programa = new Programa(archivo);
        InterpreteLISP interprete = new InterpreteLISP(programa);
        interprete.ejecutar();
    }
}
