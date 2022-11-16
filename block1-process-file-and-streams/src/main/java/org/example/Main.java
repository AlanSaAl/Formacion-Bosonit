package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Fichero ficheroDePrueba = new Fichero();

        try{
            List<String> textoDelFichero = ficheroDePrueba.leer_fichero("block1-process-file-and-streams/src/test/people.csv");
            System.out.println(textoDelFichero);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}