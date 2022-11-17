package org.example;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.testng.AssertJUnit.assertEquals;

public class Fichero {

    // Lee un archivo una línea a la vez y devuelve una lista con los
    @Test
    public List<String> leer_fichero(String ruta) throws IOException {
        Path path = Paths.get(ruta);
        List<String> read = Files.readAllLines(path);
        List<Persona> personas = new ArrayList<>();
        int contador = 0;

        for (String lin : read) {
            try {
                contador++;
                personas.add(validar_formato(lin, contador));
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // Filtrado de personas
        personas.forEach(p -> System.out.println(p));
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        personas.stream()
                .filter(p -> p.getEdad().orElse(0) < 25).forEach(p -> System.out.println(p));

        personas.stream()
                .map(p ->
                {
                    return p.getNombre();
                }).forEach(p -> System.out.println(p));

        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        var persA = personas.stream().filter(p -> !p.getNombre().startsWith("A")).
                collect(Collectors.toList());

        persA.forEach(p -> System.out.println(p));
        //personas.stream().filter( p -> ! p.getNombre().startsWith("A")).forEach( p -> System.out.println(p));
        return read;
    }

    // Valida que el formato de una línea sea correcto
    public Persona validar_formato(String linea, int nLinea) throws InvalidLineFormatException {
        //  long contador = linea.chars().filter(c -> c == ':').count();
        String[] lineaDividida = linea.split(":");
        long contador = lineaDividida.length;

//        if(contador <= 2){
//            throw new InvalidLineFormatException("Faltan delimitadores de campo en en linea: "+nLinea+ " Linea: "+linea);
//        } else if(lineaDividida[0].trim().equals("")){
//            throw new InvalidLineFormatException("El campo nombre es obligatorio");
//        }

        Integer edad = null;
        if (contador > 2)
            edad = Integer.parseInt(lineaDividida[2]);
        return new Persona(lineaDividida[0],
                edad, lineaDividida[1]);
    }
}
