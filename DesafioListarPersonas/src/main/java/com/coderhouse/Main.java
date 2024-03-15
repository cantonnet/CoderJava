package com.coderhouse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Alejandro", "González"));
        personas.add(new Persona("Carme", "Ruiz"));
        personas.add(new Persona("Bernardo", "Alvarado"));
        personas.add(new Persona("Diana", "Jiménez"));
        personas.add(new Persona("Elena", "Torres"));

        // Por nombre
        personas.sort(Comparator.comparing(Persona::getNombre));
        System.out.println("Ordenado por nombre:");
        personas.forEach(System.out::println);

        // Apellidos
        personas.sort(Comparator.comparing(Persona::getApellido));
        System.out.println("\nOrdenado por apellido:");
        personas.forEach(System.out::println);

        // Apellidos alrevez
        personas.sort(Comparator.comparing(Persona::getApellido).reversed());
        System.out.println("\nOrdenado apellidos a la inversa:");
        personas.forEach(System.out::println);
    }
}