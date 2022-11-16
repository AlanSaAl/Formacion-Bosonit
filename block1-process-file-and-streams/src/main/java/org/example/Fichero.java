package org.example;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

// Clase para leer archivos
public class Fichero {
    @Test
    public List<String> leer_fichero(String ruta) throws IOException, InvalidLineFormatException {
        System.out.println("--- Dentro de la funcion leer_fichero ---");

        Path path = Paths.get(ruta);

        String pattern = "[abc]-[abc]-[0-9]{2}";
        String expected_value = "Hello, world!";

        List<String> read = Files.readAllLines(path);
        //assertEquals(expected_value, read);

        /*read.forEach(l -> {
            if(l.length() > 15)
                try {
                    throw new InvalidLineFormatException("Línea no valida");
                } catch (InvalidLineFormatException e) {
                    throw new RuntimeException(e);
                }
        });*/

        for(String reg:read){
            if(reg.matches(pattern)){
                try {
                    throw new InvalidLineFormatException("Formato de línea no valido");
                } catch (InvalidLineFormatException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return read;
    }

    /*public List<String> filtrar_personas(List<String> listaDePersonas){


        //return listaFiltrada
    }*/
}
